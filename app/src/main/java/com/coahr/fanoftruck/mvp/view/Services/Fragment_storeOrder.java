package com.coahr.fanoftruck.mvp.view.Services;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_storeOrder_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_storeOrder_P;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/26
 * on 14:35
 */
public class Fragment_storeOrder extends BaseFragment<Fragment_storeOrder_C.Presenter> implements Fragment_storeOrder_C.View {
    @Inject
    Fragment_storeOrder_P p;
    @BindView(R.id.iv_store_img)
    ImageView iv_store_img;
    @BindView(R.id.tv_store_name)
    TextView tv_store_name;
    @BindView(R.id.tv_store_distance)
    TextView tv_store_distance;
    @BindView(R.id.tv_store_locatioon)
    TextView tv_store_locatioon;




    private int storeOrderType;
    private String store_pic;
    private String store_name;
    private String store_address;
    private String store_stars;
    private String distance;

    public static Fragment_storeOrder newInstance(int storeOrderType, String store_pic, String store_name,
                                                  String store_address, String store_stars,String distance) {
        Fragment_storeOrder fragment_storeOrder = new Fragment_storeOrder();
        Bundle bundle = new Bundle();
        bundle.putInt("storeOrderType", storeOrderType);
        bundle.putString("store_pic", store_pic);
        bundle.putString("store_name", store_name);
        bundle.putString("store_address", store_address);
        bundle.putString("store_stars", store_stars);
        bundle.putString("distance",distance);
        fragment_storeOrder.setArguments(bundle);
        return fragment_storeOrder;
    }

    @Override
    public Fragment_storeOrder_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_storeorder;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            storeOrderType = getArguments().getInt("storeOrderType");
            store_pic = getArguments().getString("store_pic");
            store_name = getArguments().getString("store_name");
            store_address = getArguments().getString("store_address");
            store_stars = getArguments().getString("store_stars");
            distance = getArguments().getString("distance");
            Imageloader.loadImage(store_pic,iv_store_img);
            tv_store_name.setText(store_name);
            tv_store_distance.setText(distance);
            tv_store_locatioon.setText(store_address);
        }
    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
