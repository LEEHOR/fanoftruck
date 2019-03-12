package com.coahr.fanoftruck.mvp.view.Shopping;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.KeyBoardUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.model.Bean.SearchBean;
import com.coahr.fanoftruck.mvp.constract.Fragment_shopping_C;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_shopping_P;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.Services.Adapter.OnSearchItemClickListener;
import com.coahr.fanoftruck.mvp.view.Services.Adapter.SearchAdapter;
import com.coahr.fanoftruck.mvp.view.Shopping.adapter.ShoppingMallListAdapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.DropDown.ConditionSelectView;
import com.coahr.fanoftruck.widgets.PopupWindows.PopupWindows_city;

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
 * on 11:11
 */
public class Fragment_Shopping extends BaseFragment<Fragment_shopping_C.Presenter> implements Fragment_shopping_C.View, TextView.OnEditorActionListener {
    @Inject
    Fragment_shopping_P p;
    @BindView(R.id.rl_store_condition)
    ConditionSelectView rl_store_condition;
    /* @BindView(R.id.mytitle)
     MyTittleBar myTittleBar;*/
    @BindView(R.id.shopping_recycler)
    RecyclerView shopping_recycler;
    @BindView(R.id.search_re)
    RecyclerView search_re;
    @BindView(R.id.shopping_swipe)
    SwipeRefreshLayout shopping_swipe;
    @BindView(R.id.tv_mall_brand)
    TextView tv_mall_brand;
    @BindView(R.id.tv_mall_class)
    TextView tv_mall_class;
    @BindView(R.id.tv_mall_sort)
    TextView tv_mall_sort;

    @BindView(R.id.tv_chanels)
    TextView tv_chanels;
    @BindView(R.id.ed_search)
    EditText ed_search;
    PopupWindows_city popupWindows_city = new PopupWindows_city();
    private boolean isSearch;
    private boolean isLoading;
    private ShoppingMallListAdapter adapter;
    private SearchAdapter searchAdapter;
    private LinearLayoutManager search_lin;
    private LinearLayoutManager linearLayoutManager;
    private int selection_brand_position, selection_class_position, selection_sort_position;
    private int conditionItemShow = 100;//条件选择器，哪一个正在显示
    private int pageLength = 10; //每页数量
    private int currentPage; //访问的节点
    private String select_brand, select_class, select_sort, select_price;
    private List<ShoppingMallBean.JdataEntity.CommodityEntity> commodity = new ArrayList<>();
    private int lastVisibleItemPosition;
    private List<String> brandList = new ArrayList<>();//品牌
    private List<String> classificationlist = new ArrayList<>();//分类
    private static String[] sortStringArray = new String[4];//排序

    private List<String> sortlist = new ArrayList<>();
    public static Fragment_Shopping newInstance() {
        return new Fragment_Shopping();
    }

    @Override
    public Fragment_shopping_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void initView() {
        sortStringArray = _mActivity.getResources().getStringArray(R.array.shoppingmall_ssortStringArray);
        sortlist = Arrays.asList(sortStringArray);
        popupWindows_city.showPopupWindows(rl_store_condition, 0, 0);
        adapter = new ShoppingMallListAdapter();
        searchAdapter = new SearchAdapter();
        search_lin = new LinearLayoutManager(BaseApplication.mContext);
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        searchListener();
        shopping_recycler.setLayoutManager(linearLayoutManager);
        shopping_recycler.setAdapter(adapter);
        shopping_recycler.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 1), getResources().getColor(R.color.material_grey_200)));
        for (int i = 0; i < shopping_recycler.getItemDecorationCount(); i++) {
            if (i != 0) {
                shopping_recycler.removeItemDecorationAt(i);
            }
        }
        search_re.setLayoutManager(search_lin);
        search_re.setAdapter(searchAdapter);
        search_re.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 1), getResources().getColor(R.color.material_grey_200)));
        for (int i = 0; i < search_re.getItemDecorationCount(); i++) {
            if (i != 0) {
                search_re.removeItemDecorationAt(i);
            }
        }
        shopping_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isSearch && !isLoading && !popupWindows_city.isMenuOpen()) {
                    getShoppingList();
                    isLoading = true;
                } else {
                    shopping_swipe.setRefreshing(false);
                }
            }
        });

        //条件选择器
        rl_store_condition.setItemShowHidenListener(new ConditionSelectView.onItemShowHidenListener() {
            @Override
            public void onItemShow(int position) {

                if (!isLoading && !isSearch) {
                    switch (position) {
                        case 0:
                            popupWindows_city.setSortList(brandList, selection_brand_position, 0).showAsDropDown();
                            conditionItemShow = 0;
                            break;
                        case 1:
                            popupWindows_city.setSortList(classificationlist, selection_class_position, 1).showAsDropDown();
                            conditionItemShow = 1;
                            break;
                        case 2:
                            popupWindows_city.setSortList(sortlist, selection_sort_position, 2).showAsDropDown();
                            conditionItemShow = 2;
                            break;
                    }
                }
            }

            @Override
            public void onAllHiden() {
                popupWindows_city.dismiss();
            }
        });

        popupWindows_city.setListener(new PopupWindows_city.getCity() {
            @Override
            public void getCity(String city) {

            }

            @Override
            public void getSort(String sort, int type, int position) {
                if (type == 0) {
                    if (sort.equals("全部品牌")) {
                        select_brand = "";
                    } else {
                        select_brand = sort;
                    }
                    tv_mall_brand.setText(sort);
                    selection_brand_position=position;
                }

                if (type == 1) {
                    if (sort.equals("全部分类")) {
                        select_class = "";
                    } else {
                        select_class = sort;
                    }
                    tv_mall_class.setText(sort);
                    selection_class_position=position;
                }

                if (type == 2) {
                    if (sort.equals("价格最低")) {
                        select_price = sort;
                        select_sort = "";
                    } else {
                        select_sort = sort;
                        select_price = "";
                    }
                    tv_mall_sort.setText(sort);
                    selection_sort_position=position;
                }
            }

            @Override
            public void dismiss() {
                rl_store_condition.hidenAll();
                getShoppingList();
            }


        });

        addRecyclerScroll();
        adapter.setItemClickListener(new ShoppingMallListAdapter.ShoppingListener() {
            @Override
            public void onClick(ShoppingMallBean.JdataEntity.CommodityEntity entity) {
                Intent intent = new Intent(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_shoppingDetail);
                intent.putExtra("c_id", entity.getC_id());
                startActivity(intent);
            }
        });
        searchAdapter.setOnItemClickListener(new OnSearchItemClickListener() {
            @Override
            public void onClick(SearchBean.JdataEntity.SearchEntity item, int type) {
                Intent intent = new Intent(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_shoppingDetail);
                intent.putExtra("c_id", item.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

        getShoppingList();
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getShoppingListSuccess(ShoppingMallBean shoppingMallBean) {
        isLoading = false;
        shopping_swipe.setRefreshing(false);
        shopping_recycler.setVisibility(View.VISIBLE);
        search_re.setVisibility(View.GONE);
        commodity.clear();
        commodity.addAll(shoppingMallBean.getJdata().getCommodity());
        adapter.setNewData(commodity);
        brandList.clear();
        brandList.add(0, "全部品牌");
        List<ShoppingMallBean.JdataEntity.BrandEntity> brand = shoppingMallBean.getJdata().getBrand();
        for (ShoppingMallBean.JdataEntity.BrandEntity entity : brand) {
            brandList.add(entity.getName());
        }
        classificationlist.clear();
        classificationlist.add(0, "全部分类");
        List<ShoppingMallBean.JdataEntity.SortEntity> sort = shoppingMallBean.getJdata().getSort();
        for (ShoppingMallBean.JdataEntity.SortEntity sortEntity :
                sort) {
            classificationlist.add(sortEntity.getName());
        }


    }

    @Override
    public void getShoppingListFailure(String failure) {
        isLoading = false;
        shopping_swipe.setRefreshing(false);
    }

    @Override
    public void getShoppingMoreSuccess(ShoppingMallBean shoppingMallBean) {
        isLoading = false;
        shopping_swipe.setRefreshing(false);
        if (shoppingMallBean.getJdata().getCommodity() != null && shoppingMallBean.getJdata().getCommodity().size() == 0) {
            currentPage--;
            Toast.makeText(_mActivity, "没有更多商品了", Toast.LENGTH_LONG).show();
        }
        commodity.addAll(shoppingMallBean.getJdata().getCommodity());
        adapter.setNewData(commodity);
        isLoading = false;
        if (adapter.getFooterLayoutCount() > 0) {
            adapter.removeAllFooterView();
        }
    }

    @Override
    public void getShoppingMoreFailure(String failure) {
        currentPage--;
        isLoading = false;
        shopping_swipe.setRefreshing(false);
        if (adapter.getFooterLayoutCount() > 0) {
            adapter.removeAllFooterView();
        }
    }

    @Override
    public void getSearchSuccess(SearchBean searchBean) {
        shopping_recycler.setVisibility(View.GONE);
        search_re.setVisibility(View.VISIBLE);
        searchAdapter.setNewData(searchBean.getJdata().getResult());
    }

    @Override
    public void getSearchFailure(String failure) {
        ToastUtils.showLong(failure);
    }

/*    private void addMyTitleListener() {
        tvTittle = myTittleBar.getTvTittle();
        editSearch = myTittleBar.getEditSearch();
        editSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editSearch.setOnEditorActionListener(this);
        //搜索栏
        imageView = rightIcon = myTittleBar.getRightIcon();
        //右侧文字栏
        rightText = myTittleBar.getRightText();
        rightIcon.setVisibility(View.VISIBLE);
        rightIcon.setImageDrawable(getResources().getDrawable(R.mipmap.search));
        rightIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindows_city.dismiss();
                rightIcon.setVisibility(View.GONE);
                tvTittle.setVisibility(View.GONE);
                editSearch.setVisibility(View.VISIBLE);
                rightText.setVisibility(View.VISIBLE);

                rightText.setText("取消");
                rightText.setTextColor(getResources().getColor(R.color.red_1));
                shopping_recycler.setVisibility(View.GONE);
                search_re.setVisibility(View.VISIBLE);
            }
        });
        rightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isSearch = false;
                rightText.setVisibility(View.GONE);
                rightIcon.setVisibility(View.VISIBLE);
                tvTittle.setVisibility(View.VISIBLE);

                editSearch.setVisibility(View.GONE);
                editSearch.setFocusable(false);
                editSearch.setText(null);
                editSearch.setHint("搜索");
                shopping_recycler.setVisibility(View.VISIBLE);
                search_re.setVisibility(View.GONE);
            }
        });

        editSearch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                editSearch.setFocusable(true);
                editSearch.setFocusableInTouchMode(true);
                return false;
            }
        });

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                shopping_recycler.setVisibility(View.GONE);
                search_re.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }*/
private void searchListener(){
    ed_search.setOnEditorActionListener(this);
    tv_chanels.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            isSearch = false;
            ed_search.setFocusable(false);
            ed_search.setFocusableInTouchMode(false);
            ed_search.setText(null);
            ed_search.setHint("请输入要查询的内容");
            shopping_recycler.setVisibility(View.VISIBLE);
            search_re.setVisibility(View.GONE);
            tv_chanels.setVisibility(View.INVISIBLE);
        }
    });

    ed_search.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            tv_chanels.setVisibility(View.VISIBLE);
            ed_search.setFocusable(true);
            ed_search.setFocusableInTouchMode(true);
            return false;
        }
    });
    ed_search.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            shopping_recycler.setVisibility(View.GONE);
            search_re.setVisibility(View.VISIBLE);
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

}
    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            KeyBoardUtils.hideKeybord(textView, getActivity());
            if (!isSearch) {
                if (ed_search.getText() != null && !ed_search.getText().equals("")) {
                    Map map = new HashMap();
                    map.put("search_key", ed_search.getText().toString());
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

    private void getShoppingList() {
        Map map = new HashMap();
        map.put("brand", select_brand);
        map.put("sort", select_class);
        map.put("order", select_sort);
        map.put("price", select_price);
        map.put("page", String.valueOf(currentPage));
        map.put("length", String.valueOf(pageLength));
        p.getShoppingList(map);
    }

    private void getShoppingMore(int currentPages) {
        Map map = new HashMap();
        map.put("brand", select_brand);
        map.put("sort", select_class);
        map.put("order", select_sort);
        map.put("price", select_price);
        map.put("page", String.valueOf(currentPages));
        map.put("length", String.valueOf(pageLength));
        p.getShoppingMore(map);
    }


    private void addRecyclerScroll() {
        shopping_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (adapter.getFooterLayoutCount() == 0 && adapter.getData().size() >= pageLength) {
                    adapter.addFooterView(addFooterView);
                }
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //屏幕中最后一个可见子项的position
                // int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                //当前屏幕所看到的子项个数
                int visibleItemCount = layoutManager.getChildCount();
                //当前RecyclerView的所有子项个数
                int totalItemCount = layoutManager.getItemCount();
                //RecyclerView的滑动状态
                int state = recyclerView.getScrollState();
                if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isLoading || !isSearch) {
                        shopping_recycler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isLoading = true;
                                currentPage++;
                                getShoppingMore(currentPage);
                            }
                        }, 500);
                    } else {
                        ToastUtils.showLong("正在加载中...");
                    }


                } else {

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }
}
