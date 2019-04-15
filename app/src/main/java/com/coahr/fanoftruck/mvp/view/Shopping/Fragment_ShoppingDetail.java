package com.coahr.fanoftruck.mvp.view.Shopping;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.SetCustomBannerUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_shoppingDetail_C;
import com.coahr.fanoftruck.mvp.model.Bean.AddShoppingCart;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallDetailBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_shoppingDetail_P;
import com.coahr.fanoftruck.mvp.view.ConfirmCommodityOrder.Fragment_confirmCommodityOrder;
import com.coahr.fanoftruck.mvp.view.MyAddress.Fragment_address_list;
import com.coahr.fanoftruck.mvp.view.Shopping.adapter.ShoppingDetailAdapter;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.donkingliang.banner.CustomBanner;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

import static com.coahr.fanoftruck.mvp.view.Shopping.Fragment_dialog_shopping.TO_ADD_CART;
import static com.coahr.fanoftruck.mvp.view.Shopping.Fragment_dialog_shopping.TO_BUY;

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
    LinearLayout append_shoppingCart;
    @BindView(R.id.send_address_rel)
    RelativeLayout send_address_rel;
    @BindView(R.id.shopping_swipe)
    SwipeRefreshLayout shopping_swipe;
    @BindView(R.id.mytittlebar)
    MyTittleBar mytittlebar;
    private String c_id;
    private ShoppingDetailAdapter shoppingDetailAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String c_thumbnail;
    private String c_price;
    private String address_id;


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
        mytittlebar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        shopping_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_dialog_shopping fragment_dialog_shopping = Fragment_dialog_shopping.newInstance(Float.parseFloat(c_price), c_thumbnail, TO_BUY);
                fragment_dialog_shopping.setListener(new Fragment_dialog_shopping.getShoppingDialog() {
                    @Override
                    public void getShoppingCount(String count) {
                        KLog.d("数目", count);
                        start(Fragment_confirmCommodityOrder.newInstance(String.format("cid=%s&num=%s", c_id, count), address_id == null ? "" : address_id));
                    }
                });
                fragment_dialog_shopping.show(getChildFragmentManager(), TAG);
            }
        });

        append_shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment_dialog_shopping fragment_dialog_shopping = Fragment_dialog_shopping.newInstance(Float.parseFloat(c_price), c_thumbnail, TO_ADD_CART);
                fragment_dialog_shopping.setListener(new Fragment_dialog_shopping.getShoppingDialog() {
                    @Override
                    public void getShoppingCount(String count) {
                        add_to_Shopping(count);
                    }
                });
                fragment_dialog_shopping.show(getChildFragmentManager(), TAG);
            }
        });

        send_address_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForResult(Fragment_address_list.newInstance(Constants.Fragment_shoppingDetail), 10);
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
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getShoppingDetailSuccess(ShoppingMallDetailBean shoppingMallDetailBean) {
        ShoppingMallDetailBean.JdataEntity.CommodityEntity commodity = shoppingMallDetailBean.getJdata().getCommodity();
        if (commodity != null) {
            List<String> cp_path = commodity.getCp_path();
            if (cp_path != null && cp_path.size() > 0) {
                SetCustomBannerUtils.setCustomBanner(custom_Banner, cp_path, ImageView.ScaleType.FIT_CENTER);

            }
            c_price = commodity.getC_price();
            mall_name.setText(commodity.getC_name());
            mall_price.setText(commodity.getC_price());
            c_thumbnail = commodity.getC_thumbnail();

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

    @Override
    public void AddShoppingCarSuccess(AddShoppingCart addShoppingCart) {
        ToastUtils.showLong("加入购物车成功");
    }

    @Override
    public void AddShoppingCarFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    private void getShoppingMallDetail() {
        Map map = new HashMap();
        map.put("c_id", c_id);
        map.put("token", Constants.token);
        p.getShoppingDetail(map);
    }

    private void add_to_Shopping(String num) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("c_id", c_id);
        map.put("c_num", num);
        p.AddShoppingCar(map);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 10) {
            if (data != null) {
                send_address.setText((String) data.get("address"));
                address_id = (String) data.get("address_id");
            }

        }
    }
}
