package com.coahr.fanoftruck.mvp.view.Shopping;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.SetCustomBannerUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_shoppingDetail_C;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallDetailBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_shoppingDetail_P;
import com.coahr.fanoftruck.mvp.view.Shopping.adapter.ShoppingDetailAdapter;
import com.donkingliang.banner.CustomBanner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/26
 * on 10:14
 * 商品详情
 */
public class Fragment_ShoppingDetail extends BaseFragment<Fragment_shoppingDetail_C.Presenter> implements Fragment_shoppingDetail_C.View {

    @Inject
    Fragment_shoppingDetail_P p;
    @BindView(R.id.custom_banner)
    CustomBanner<String> custom_Banner;
    @BindView(R.id.mall_name)
    TextView mall_name;
    @BindView(R.id.mall_price)
    TextView mall_price;
    @BindView(R.id.send_address)
    TextView send_address;
    @BindView(R.id.mall_recycler)
    RecyclerView mall_recycler;
    @BindView(R.id.shopping_now)
    LinearLayout shopping_now;
    @BindView(R.id.append_shoppingCar)
    LinearLayout append_shoppingCar;
    @BindView(R.id.send_address_rel)
    RelativeLayout send_address_rel;
    @BindView(R.id.shopping_swipe)
    SwipeRefreshLayout shopping_swipe;
    private String c_id;
    private ShoppingDetailAdapter shoppingDetailAdapter;
    private LinearLayoutManager linearLayoutManager;


    public static Fragment_ShoppingDetail newInstance(String c_id) {
        Fragment_ShoppingDetail shoppingDetail = new Fragment_ShoppingDetail();
        Bundle bundle = new Bundle();
        bundle.putString("c_id", c_id);
        shoppingDetail.setArguments(bundle);
        return shoppingDetail;
    }

    @Override
    public Fragment_shoppingDetail_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_shoppingmalldetail;
    }

    @Override
    public void initView() {
        shopping_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        append_shoppingCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        send_address_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        shopping_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getShoppingMallDetail();
            }
        });
    }

    @Override
    public void initData() {

        if (getArguments() != null) {
            c_id = getArguments().getString("c_id");
        }
        getShoppingMallDetail();
        shoppingDetailAdapter = new ShoppingDetailAdapter();
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        mall_recycler.setLayoutManager(linearLayoutManager);
        mall_recycler.setAdapter(shoppingDetailAdapter);
    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getShoppingDetailSuccess(ShoppingMallDetailBean shoppingMallDetailBean) {
        ShoppingMallDetailBean.JdataEntity.CommodityEntity commodity = shoppingMallDetailBean.getJdata().getCommodity();
        if (commodity != null) {
            List<String> cp_path = commodity.getCp_path();
            if (cp_path != null && cp_path.size()>0) {
                SetCustomBannerUtils.setCustomBanner(custom_Banner,cp_path,ImageView.ScaleType.FIT_CENTER);

            }
            mall_name.setText(commodity.getC_name());
            mall_price.setText(commodity.getC_price());

        }
        ShoppingMallDetailBean.JdataEntity.AddressEntity address = shoppingMallDetailBean.getJdata().getAddress();
        if (address != null) {
            send_address.setText(address.getAddress());
        }
        ShoppingMallDetailBean.JdataEntity.CommodityDescriptionEntity commodity_description = shoppingMallDetailBean.getJdata().getCommodity_description();
        if (commodity_description != null) {
            List<ShoppingMallDetailBean.JdataEntity.CommodityDescriptionEntity.DesPicEntity> des_pic = commodity_description.getDes_pic();
            if (des_pic != null && des_pic.size() > 0) {

                shoppingDetailAdapter.setNewData(des_pic);
            }
        }
        shopping_swipe.setRefreshing(false);
}

    @Override
    public void getShoppingDetailFailure(String failure) {
        shopping_swipe.setRefreshing(false);
    }

    private void getShoppingMallDetail() {
        Map map = new HashMap();
        map.put("c_id", c_id);
        map.put("token", "9a99788a604f85782dc5f625966205cb");
        p.getShoppingDetail(map);
    }
}
