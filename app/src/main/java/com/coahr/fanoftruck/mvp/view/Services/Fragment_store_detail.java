package com.coahr.fanoftruck.mvp.view.Services;

import android.content.Intent;
import android.location.SettingInjectorService;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.MapUtils;
import com.coahr.fanoftruck.Utils.SetCustomBannerUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_store_detail_C;
import com.coahr.fanoftruck.mvp.model.Bean.StoreDetailBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_store_detail_P;
import com.coahr.fanoftruck.mvp.view.Services.Adapter.Store_lable_adapter;
import com.coahr.fanoftruck.mvp.view.Services.Adapter.Store_score_adapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.PopupWindows.ConfirmPopWindow;
import com.donkingliang.banner.CustomBanner;
import com.socks.library.KLog;
import com.tencent.smtt.sdk.QbSdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 10:56
 */
public class Fragment_store_detail extends BaseFragment<Fragment_store_detail_C.Presenter> implements Fragment_store_detail_C.View {
    @Inject
    Fragment_store_detail_P p;
    @BindView(R.id.store_detail_swipe)
    SwipeRefreshLayout store_detail_swipe;
    @BindView(R.id.storeDetail_Banner)
    CustomBanner<String> storeDetail_Banner;
    @BindView(R.id.tv_store_name)
    TextView tv_store_name;
    @BindView(R.id.tv_store_business_hours)
    TextView  tv_store_business_hours;
    @BindView(R.id.store_tag)
    RecyclerView store_tag;
    @BindView(R.id.tv_store_address)
    TextView tv_store_address;
    @BindView(R.id.iv_to_store)
    ImageView iv_to_store;
    @BindView(R.id.tv_tel)
    TextView tv_tel;
    @BindView(R.id.iv_play_tel)
    ImageView iv_play_tel;
    @BindView(R.id.store_detail_businessScope)
    RecyclerView store_detail_businessScope;

    private String s_id;
    private boolean isLoading;
    private List<StoreDetailBean.JdataEntity.StationEntity.TurnPicEntity> turn_pic;
    private List<String> bannerList =new ArrayList<>();
    private List<StoreDetailBean.JdataEntity.ServiceEntity> service;
    private Store_lable_adapter store_lable_adapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private LinearLayoutManager linearLayoutManager;
    private Store_score_adapter store_score_adapter;
    private List<StoreDetailBean.JdataEntity.StationEntity.SServiceTagEntity> s_service_tag;
    private String s_phone;

    private NavigatorDialogFragment navigatorDialogFragment = NavigatorDialogFragment.newInstance();
    private String s_latitude;
    private String s_longitude;

    public static Fragment_store_detail newInstance(String id) {
        Fragment_store_detail fragment_store_detail = new Fragment_store_detail();
        Bundle args = new Bundle();
        args.putString("s_id", id);
        fragment_store_detail.setArguments(args);
        return fragment_store_detail;
    }


    @Override
    public Fragment_store_detail_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_store_detail;
    }

    @Override
    public void initView() {
        store_detail_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading=true;
                    Map map = new HashMap();
                    map.put("s_id", s_id);
                    map.put("longitude", String.valueOf(Constants.Longitude));
                    map.put("latitude", String.valueOf(Constants.Latitude));
                    p.getStoreDetail(map);
                }
            }
        });
        iv_play_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call(s_phone);
            }
        });
        iv_to_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigatorDialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);
            }
        });
        navigatorDialogFragment.setOnNavigatiorItemSelectListener(new NavigatorDialogFragment.NavigatorItemSelectListener() {
            @Override
            public void onItemSelct(String mapName) {
                if (mapName.equals("baidu")) {
                    // 调用百度地图客户端
                    if (MapUtils.isInstallByRead("com.baidu.BaiduMap")) {
                        MapUtils.getBaiduMapUri(_mActivity, s_latitude + "", s_longitude + "");
                    } else {
                        Toast.makeText(_mActivity, "您尚未安装百度地图", Toast.LENGTH_LONG).show();
                        Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                } else if (mapName.equals("gaode")) {
                    //调用高德地图客户端
                    double[] gd_lat_lon_des = MapUtils.bdToGaoDe(Double.parseDouble(s_latitude), Double.parseDouble(s_longitude));
                    if (MapUtils.isInstallByRead("com.autonavi.minimap")) {
                        MapUtils.getGaoDeMapUri(_mActivity, gd_lat_lon_des[1] + "", gd_lat_lon_des[0] + "");
                    } else {
                        Toast.makeText(_mActivity, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
                        Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    @Override
    public void initData() {
        final ConfirmPopWindow confirmPopWindow=new ConfirmPopWindow(BaseApplication.mContext);
        s_id = getArguments().getString("s_id");
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL);
        store_lable_adapter = new Store_lable_adapter();
        store_tag.setLayoutManager(staggeredGridLayoutManager);
        store_tag.setAdapter(store_lable_adapter);
        store_tag.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,4),DensityUtils.dp2px(BaseApplication.mContext,4)),getResources().getColor(R.color.colorWhite));

        for (int i = 0; i <store_tag.getItemDecorationCount() ; i++) {
            if (i!=0){
                store_tag.removeItemDecorationAt(i);
            }
        }

        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        store_score_adapter = new Store_score_adapter();
        store_detail_businessScope.setLayoutManager(linearLayoutManager);
        store_detail_businessScope.setAdapter(store_score_adapter);
        store_detail_businessScope.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.material_grey_250)));
        for (int i = 0; i <store_detail_businessScope.getItemDecorationCount() ; i++) {
            if (i!=0){
                store_detail_businessScope.removeItemDecorationAt(i);
            }
        }
        store_score_adapter.setStore_scoreOnClick(new Store_score_adapter.Store_scoreOnClick() {
            @Override
            public void scoreOnClick(View view,int position,String s) {
                confirmPopWindow.showAtBottom(view,s);
            }
        });
        p.startLocation();
    }

    @Override
    public void onLocationSuccess(BDLocation location) {
        isLoading = true;
        Constants.Longitude = location.getLongitude();
        Constants.Latitude = location.getLatitude();
        Map map = new HashMap();
        map.put("s_id", s_id);
        map.put("longitude", String.valueOf(Constants.Longitude));
        map.put("latitude", String.valueOf(Constants.Latitude));
        p.getStoreDetail(map);
    }

    @Override
    public void onLocationFailure(int failure) {
        ToastUtils.showLong("定位失败");
    }

    @Override
    public void getStoreDetailSuccess(StoreDetailBean storeDetailBean) {
        isLoading=false;
        store_detail_swipe.setRefreshing(false);
        if (storeDetailBean != null) {
            turn_pic = storeDetailBean.getJdata().getStation().getTurn_pic();
            //轮播图
            if (turn_pic !=null && turn_pic.size()>0){
                for (int i = 0; i <turn_pic.size() ; i++) {
                    bannerList.add(turn_pic.get(i).getPic());
                }
                SetCustomBannerUtils.setCustomBanner(storeDetail_Banner,bannerList,ImageView.ScaleType.FIT_XY);
            }

            //门店名
            tv_store_name.setText(storeDetailBean.getJdata().getStation().getS_name());
            //门店营业时间
            tv_store_business_hours.setText(storeDetailBean.getJdata().getStation().getS_starttime()+"--"+storeDetailBean.getJdata().getStation().getS_endtime());
            //门店标签
            s_service_tag = storeDetailBean.getJdata().getStation().getS_service_tag();
            KLog.d("标签",s_service_tag.size(),s_service_tag.get(0).getTag());
            store_lable_adapter.setNewData(s_service_tag);
            //门店地址
            tv_store_address.setText(storeDetailBean.getJdata().getStation().getS_address());
            //门店经纬度
            s_latitude = storeDetailBean.getJdata().getStation().getS_latitude();
            s_longitude = storeDetailBean.getJdata().getStation().getS_longitude();
            //门店电话
            s_phone = storeDetailBean.getJdata().getS_phone();
            tv_tel.setText(s_phone);
            //门店经营范围
            service = storeDetailBean.getJdata().getService();
            service.add(0,new StoreDetailBean.JdataEntity.ServiceEntity(null,null,"说明","价","格"));
            store_score_adapter.setNewData(service);
        }
    }

    @Override
    public void getStoreDeailFailure(String failure) {
        isLoading=false;
        ToastUtils.showLong(failure);
        store_detail_swipe.setRefreshing(false);
    }

    @Override
    public void showError(@Nullable Throwable e) {
        super.showError(e);
        store_detail_swipe.setRefreshing(false);
    }
}
