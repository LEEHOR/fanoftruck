package com.coahr.fanoftruck.mvp.view.Services;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.KeyBoardUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.Base.SearchBean;
import com.coahr.fanoftruck.mvp.constract.Fragment_store_C;
import com.coahr.fanoftruck.mvp.model.Bean.CityBean;
import com.coahr.fanoftruck.mvp.model.Bean.CityInfoBean;
import com.coahr.fanoftruck.mvp.model.Bean.StoreBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_store_P;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.Services.Adapter.Fragment_store_adapter;
import com.coahr.fanoftruck.mvp.view.Services.Adapter.OnSearchItemClickListener;
import com.coahr.fanoftruck.mvp.view.Services.Adapter.SearchAdapter;
import com.coahr.fanoftruck.mvp.view.Services.Adapter.StoreItemClickListener;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.DropDown.ConditionSelectView;
import com.coahr.fanoftruck.widgets.PopupWindows.PopupWindows_city;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 17:03
 * 门店首页
 */
public class Fragment_Store extends BaseFragment<Fragment_store_C.Presenter> implements Fragment_store_C.View , TextView.OnEditorActionListener{
    @Inject
    Fragment_store_P p;
    @BindView(R.id.rl_store_condition)
    ConditionSelectView spinner;
    @BindView(R.id.store_recycler)
    RecyclerView store_recycler;
    @BindView(R.id.Ed_search)
    EditText Ed_search;
    @BindView(R.id.tv_cancel_search)
    TextView tv_cancel_search;
    @BindView(R.id.store_swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.search_recycler)
    RecyclerView search_recycler;
    @BindView(R.id.store_city_select)
    TextView tv_store_city;
    @BindView(R.id.store_sort)
    TextView tv_store_sort;
    private List<CityInfoBean.JdataEntity.CityListEntity> cityInfoBeanList = new ArrayList<>();
    private static String[] sortStringArray = new String[3];//排序
    private List<String> sortList = new ArrayList<>();
    private PopupWindows_city city;
    private int selectSortPosition;
    private LinearLayoutManager linearLayoutManager_store;
    private int lastVisibleItemPosition;

    private int pageLength=10; //每页数量
    private int currentPage; //访问的节点
    private String selectedCity;//选择的城市
    private String selectSort; //排序方式
    private boolean isLoading; //是否正在加载
    private boolean isSearch=false;//是否正在搜索

    private List<StoreBean.JdataEntity.StationEntity> stationEntityList=new ArrayList<>();
    private Fragment_store_adapter fragment_store_adapter;
    private SearchAdapter searchAdapter;
    private LinearLayoutManager linearLayoutManager_search;

    public static Fragment_Store newInstance(int type) {
        Fragment_Store store = new Fragment_Store();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        store.setArguments(bundle);
        return store;
    }

    @Override
    public Fragment_store_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_store;
    }

    @Override
    public void initView() {
        sortStringArray = _mActivity.getResources().getStringArray(R.array.store_ssortStringArray);
        sortList = Arrays.asList(sortStringArray);
        Ed_search.addTextChangedListener(new edit_textChance());
        Ed_search.setOnEditorActionListener(this);

        Ed_search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Ed_search.setFocusable(true);
                Ed_search.setFocusableInTouchMode(true);
                return false;
            }
        });
        city = new PopupWindows_city();
        city.showPopupWindows(spinner,0,0);

        spinner.setItemShowHidenListener(new ConditionSelectView.onItemShowHidenListener() {
            @Override
            public void onItemShow(int position) {
            if (position ==0){ //城市
                city.setCityList(cityInfoBeanList, 0);
            }
            if (position==1){
                city.setSortList(sortList,selectSortPosition,1);
            }
            city.showAsDropDown();
            }

            @Override
            public void onAllHiden() {
                city.dismiss();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                KLog.d("ewew",isLoading,isSearch);
                if (!isLoading && !isSearch) {
                    isLoading=true;
                    currentPage=0;
                    KLog.d("刷新",isLoading,isSearch);
                    Map map = new HashMap();
                    map.put("page", String.valueOf(currentPage));
                    map.put("length", String.valueOf(pageLength));
                    map.put("order", selectSort);
                    map.put("city", selectedCity);
                    map.put("longitude", String.valueOf(Constants.Longitude));
                    map.put("latitude", String .valueOf(Constants.Latitude));
                    p.getStoreList(map);
                } else {
                    KLog.d("不刷新",isLoading,isSearch);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        tv_cancel_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Ed_search.setFocusable(false);
                Ed_search.setText(null);
                Ed_search.setHint("搜索");
                isSearch=false;
                store_recycler.setVisibility(View.VISIBLE);
                search_recycler.setVisibility(View.GONE);
                tv_cancel_search.setVisibility(View.GONE);

            }
        });
        fragment_store_adapter = new Fragment_store_adapter();

        fragment_store_adapter.setItemClickListener(new StoreItemClickListener() {
            @Override
            public void onItemClick(StoreBean.JdataEntity.StationEntity entity) {
              /*  Intent intent=new Intent(_mActivity,ContainerActivity.class);
                intent.putExtra("tofragment",Constants.Fragment_Store_Detail);
                intent.putExtra("s_id",entity.getS_id());
                startActivity(intent);*/
                start(Fragment_store_detail.newInstance(entity.getS_id()));
            }
        });
        searchAdapter = new SearchAdapter();
        searchAdapter.setOnItemClickListener(new OnSearchItemClickListener() {
            @Override
            public void onClick(SearchBean.JdataEntity.SearchEntity item, int type) {
              /*  Intent intent=new Intent(_mActivity,ContainerActivity.class);
                intent.putExtra("tofragment",Constants.Fragment_Store_Detail);
                intent.putExtra("s_id",item.getId());
                startActivity(intent);*/
                start(Fragment_store_detail.newInstance(item.getId()));
            }
        });
    }

    @Override
    public void initData() {
        p.startLocation();
        Map map=new HashMap();
        map.put("token","9a99788a604f85782dc5f625966205cb");
        p.getCityList(map);
        linearLayoutManager_search = new LinearLayoutManager(BaseApplication.mContext);
        search_recycler.setLayoutManager(linearLayoutManager_search);
        search_recycler.setAdapter(searchAdapter);
        search_recycler.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.decoration_f5f5f8)));
        for (int i = 0; i <search_recycler.getItemDecorationCount() ; i++) {
            if (i!=0){
                search_recycler.removeItemDecorationAt(i);
            }
        }
        linearLayoutManager_store = new LinearLayoutManager(BaseApplication.mContext);
        store_recycler.setLayoutManager(linearLayoutManager_store);
        store_recycler.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,2),getResources().getColor(R.color.material_grey_250)));
        int itemDecorationCount = store_recycler.getItemDecorationCount();
        for (int i = 0; i <itemDecorationCount ; i++) {
            if (i !=0){
                store_recycler.removeItemDecorationAt(i);
            }
        }
        store_recycler.setAdapter(fragment_store_adapter);


        city.setListener(new PopupWindows_city.getCity() {
            @Override
            public void getCity(String city) {
                if (city !=null){
                    selectedCity=city;
                    if (!city.equals("")){
                        tv_store_city.setText(city);
                    }
                }
            }

            @Override
            public void getSort(String sort,int type,int position) {
                selectSortPosition = position;
                selectSort=sort;
                if (sort !=null && !sort.equals("")){
                    tv_store_sort.setText(sort);
                }

            }

            @Override
            public void dismiss() {
                spinner.hidenAll();
                getDataList();

            }
        });



        store_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);

                if (fragment_store_adapter.getFooterLayoutCount() ==0 && fragment_store_adapter.getData().size()>=pageLength){
                    fragment_store_adapter.addFooterView(addFooterView);
                }
               /* if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == fragment_store_adapter.getItemCount()){

                }*/
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //屏幕中最后一个可见子项的position
               // int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                //当前屏幕所看到的子项个数
                int visibleItemCount = layoutManager.getChildCount();
                //当前RecyclerView的所有子项个数
                int totalItemCount = layoutManager.getItemCount();
                //RecyclerView的滑动状态
                int state = recyclerView.getScrollState();
                if(visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == RecyclerView.SCROLL_STATE_IDLE ){
                    if (!isLoading) {
                        store_recycler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isLoading = true;
                                currentPage++;
                                Map map = new HashMap();
                                map.put("page", String.valueOf(currentPage));
                                map.put("length", String.valueOf(pageLength));
                                map.put("order", selectSort);
                                map.put("city", selectedCity);
                                map.put("longitude", String.valueOf(Constants.Longitude));
                                map.put("latitude", String.valueOf(Constants.Latitude));
                                p.getStoreMore(map);
                            }
                        }, 500);
                    } else {
                        ToastUtils.showLong("正在加载中...");
                    }


                }else {

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
               // 屏幕中最后一个可见子项的position
                lastVisibleItemPosition = linearLayoutManager_store.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void onLocationSuccess(BDLocation location) {
        isLoading=true;
        currentPage=0;
        Constants.Latitude=location.getLatitude();
        Constants.Longitude=location.getLongitude();
        Map map = new HashMap();
        map.put("page", String.valueOf(currentPage));
        map.put("length", String.valueOf(pageLength));
        map.put("order", selectSort);
        map.put("city", selectedCity);
        map.put("longitude", String.valueOf(Constants.Longitude));
        map.put("latitude", String .valueOf(Constants.Latitude));
        p.getStoreList(map);
    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getStoreListSuccess(StoreBean storeBean) {
        store_recycler.setVisibility(View.VISIBLE);
        search_recycler.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        isLoading=false;
        stationEntityList.clear();
        stationEntityList.addAll(storeBean.getJdata().getStation());
        fragment_store_adapter.setNewData(stationEntityList);
    }

    @Override
    public void getStoreListFailure(String failure) {
        swipeRefreshLayout.setRefreshing(false);
        isLoading=false;
        ToastUtils.showLong(failure);
    }

    @Override
    public void getStoreMoreSuccess(StoreBean storeBean) {


        if (storeBean.getJdata().getStation() != null && storeBean.getJdata().getStation().size() == 0) {
            currentPage--;
            Toast.makeText(_mActivity, "没有更多门店了", Toast.LENGTH_LONG).show();
        }
        stationEntityList.addAll(storeBean.getJdata().getStation());
        fragment_store_adapter.setNewData(stationEntityList);
        isLoading=false;
        swipeRefreshLayout.setRefreshing(false);
        if (fragment_store_adapter.getFooterLayoutCount() > 0) {
            fragment_store_adapter.removeAllFooterView();
        }
    }

    @Override
    public void getStoreMoreFailure(String failure) {
        isLoading=false;
        currentPage--;
        swipeRefreshLayout.setRefreshing(false);
        if (fragment_store_adapter.getFooterLayoutCount() > 0) {
            fragment_store_adapter.removeAllFooterView();
        }
    }

    @Override
    public void getSearchSuccess(SearchBean searchBean) {
        store_recycler.setVisibility(View.GONE);
        search_recycler.setVisibility(View.VISIBLE);
        KLog.d("搜索成功");
        searchAdapter.setNewData(searchBean.getJdata().getResult());
    }

    @Override
    public void getSearchFailure(String failure) {
            ToastUtils.showLong(failure);
    }

    @Override
    public void getCityListSuccess(CityInfoBean cityInfoBean) {
        List<CityInfoBean.JdataEntity.CityListEntity> city_list = cityInfoBean.getJdata().getCity_list();
        if (city_list != null && city_list.size() > 0) {
            cityInfoBeanList.addAll(city_list);
        }
    }

    @Override
    public void getCityListFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    @Override
    public boolean onBackPressedSupport() {
        if (city.isMenuOpen()) {
            city.dismiss();
            return true;
        } else {
            return super.onBackPressedSupport();
        }
    }
    class edit_textChance implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            tv_cancel_search.setVisibility(View.VISIBLE);

                         isSearch=true;
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        isSearch=true;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            store_recycler.setVisibility(View.GONE);
            search_recycler.setVisibility(View.VISIBLE);
                        isSearch=false;
        }
    }

 @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            KeyBoardUtils.hideKeybord(v, getActivity());
            if (!isSearch) {
                if (Ed_search.getText() != null &&  !Ed_search.getText().equals("")) {
                    Map map = new HashMap();
                    map.put("search_key", Ed_search.getText().toString());
                    p.getSearchMap(map);
                    isSearch = true;
                } else {
                    Toast.makeText(_mActivity, "请输入有效字段", Toast.LENGTH_LONG).show();
                }
            }
            return true;
        }
        return false;
    }
private void getDataList(){
    Map map = new HashMap();
    map.put("page", String.valueOf(currentPage));
    map.put("length", String.valueOf(pageLength));
    map.put("order", selectSort);
    map.put("city", selectedCity);
    map.put("longitude", String.valueOf(Constants.Longitude));
    map.put("latitude", String .valueOf(Constants.Latitude));
    p.getStoreList(map);
}
}
