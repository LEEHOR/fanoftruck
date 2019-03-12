package com.coahr.fanoftruck.mvp.view.MyAddress;

import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_address_list_C;
import com.coahr.fanoftruck.mvp.model.Bean.AddressListBean;
import com.coahr.fanoftruck.mvp.model.Bean.del_addressBean;
import com.coahr.fanoftruck.mvp.model.Bean.set_address_defaultBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_address_list_P;
import com.coahr.fanoftruck.mvp.view.MyAddress.adapter.AddressAdapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 18:17
 * 我的地址
 */
public class Fragment_address_list extends BaseFragment<Fragment_address_list_C.Presenter> implements Fragment_address_list_C.View {

    @Inject
    Fragment_address_list_P p;
    @BindView(R.id.address_mytitle)
    MyTittleBar address_mytitle;
    @BindView(R.id.address_swipe)
    SwipeRefreshLayout address_swipe;
    @BindView(R.id.address_recycler)
    RecyclerView address_recycler;
    private boolean isLoading;
    private LinearLayoutManager linearLayoutManager;
    private AddressAdapter addressAdapter;
    private int formFragment; //来至那个页面

    public static Fragment_address_list newInstance(int formFragment) {
        Fragment_address_list address_list = new Fragment_address_list();
        Bundle bundle = new Bundle();
        bundle.putInt("formFragment", formFragment);
        address_list.setArguments(bundle);
        return address_list;
    }

    @Override
    public Fragment_address_list_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {

        return R.layout.fragment_my_address_list;
    }

    @Override
    public void initView() {
        address_mytitle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        address_mytitle.getRightText().setVisibility(View.VISIBLE);
        address_mytitle.getRightText().setText("新增地址");
        address_mytitle.getRightText().setTextColor(getResources().getColor(R.color.material_blue_550));
        address_mytitle.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(Fragment_add_address.newInstance());
            }
        });
        address_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                    getAddressList();
                } else {
                    address_swipe.setRefreshing(false);
                }
            }
        });
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        addressAdapter = new AddressAdapter();
        address_recycler.setLayoutManager(linearLayoutManager);
        address_recycler.setAdapter(addressAdapter);
        address_recycler.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 4), getResources().getColor(R.color.material_grey_250)));
        for (int i = 0; i < address_recycler.getItemDecorationCount(); i++) {
            if (i != 0) {
                address_recycler.removeItemDecorationAt(i);
            }
        }
        addressAdapter.setOnAddressHandleListener(new AddressAdapter.onAddressHandleListener() {
            //选择
            @Override
            public void onItemClick(AddressListBean.JdataBean.AddressEntity item) {

                if (formFragment == Constants.Fragment_shoppingDetail) {
                    Bundle bundle = new Bundle();
                    bundle.putString("address", item.getAddress());
                    bundle.putString("address_id", item.getId());
                    setFragmentResult(10, bundle);
                    _mActivity.onBackPressed();
                }

                if (formFragment==Constants.Fragment_confirmCommodityOrder){
                    Bundle bundle = new Bundle();
                    bundle.putString("address", item.getAddress());
                    bundle.putString("address_id", item.getId());
                    bundle.putString("address_user",item.getUsername());
                    bundle.putString("address_phone",item.getTelephone());
                    setFragmentResult(11, bundle);
                    _mActivity.onBackPressed();
                }

            }

            //编辑
            @Override
            public void edit(AddressListBean.JdataBean.AddressEntity item) {

            }

            //删除
            @Override
            public void delete(AddressListBean.JdataBean.AddressEntity item) {
                del_address(item.getId());
            }

            //设置为默认
            @Override
            public void onSetPrimary(AddressListBean.JdataBean.AddressEntity item) {
                del_address(item.getId());
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            formFragment = getArguments().getInt("formFragment");
        }
        getAddressList();
    }

    @Override
    public void getAddressSuccess(AddressListBean addressListBean) {
        addressAdapter.setNewData(addressListBean.getJdata().getAddress());
        isLoading = false;
        address_swipe.setRefreshing(false);
    }

    @Override
    public void getAddressFailure(String failure) {
        ToastUtils.showLong(failure);
        isLoading = false;
        address_swipe.setRefreshing(false);
    }

    @Override
    public void setAddressDefaultSuccess(set_address_defaultBean addressDefaultSuccess) {
        ToastUtils.showLong(addressDefaultSuccess.getJdata().getJmsg());
        getAddressList();
    }

    @Override
    public void setAddressDefaultFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    @Override
    public void delAddressSuccess(del_addressBean addressBean) {
        ToastUtils.showLong(addressBean.getJdata().getJmsg());
    }

    @Override
    public void delAddressFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    /**
     * 地址列表
     */
    private void getAddressList() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        p.getAddressList(map);
    }

    /**
     * 删除地址
     *
     * @param address_id
     */
    private void del_address(String address_id) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("addressId", address_id);
        p.delAddress(map);
    }

    /**
     * 设为默认地址
     *
     * @param address_id
     */
    private void setAddressDefault(String address_id) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("address_id", address_id);
        p.getAddressList(map);
    }
}
