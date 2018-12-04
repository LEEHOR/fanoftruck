package com.coahr.fanoftruck.mvp.view.MyCar;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.model.Bean.CarDefaultBean;
import com.coahr.fanoftruck.mvp.model.Bean.EventBusUserCar;
import com.coahr.fanoftruck.mvp.constract.Fragment_myCar_C;
import com.coahr.fanoftruck.mvp.model.Bean.CarListBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_myCar_P;
import com.coahr.fanoftruck.mvp.view.MyCar.adapter.MyCarAdapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 17:58
 */
public class Fragment_myCar extends BaseFragment<Fragment_myCar_C.Presenter> implements Fragment_myCar_C.View {

   @Inject
    Fragment_myCar_P p;
   @BindView(R.id.mytittle)
    MyTittleBar mytittle;
    @BindView(R.id.car_swipe)
    SwipeRefreshLayout car_swipe;
    @BindView(R.id.car_recycler)
    RecyclerView car_recycler;
    private MyCarAdapter myCarAdapter;
    private boolean isLoading;
    private LinearLayoutManager linearLayoutManager;
    private int toFragment;
    private String car_frameno;
    private String car_no;

    public static Fragment_myCar newInstance(int toFragment) {
        Fragment_myCar car=new Fragment_myCar();
        Bundle bundle=new Bundle();
        bundle.putInt("tofragment",toFragment);
        car.setArguments(bundle);
        return car;
    }

    @Override
    public Fragment_myCar_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_mycar;
    }

    @Override
    public void initView() {
        mytittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });

        mytittle.getRightText().setVisibility(View.VISIBLE);
        mytittle.getRightText().setText("添加车辆");
        mytittle.getRightText().setTextColor(getResources().getColor(R.color.material_blue_550));
        mytittle.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    start(Fragment_addCar.newInstance());

            }
        });
        car_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (! isLoading) {
                    isLoading=true;
                    getCarList();
                } else {
                    car_swipe.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void initData() {
        toFragment = getArguments().getInt("tofragment"); //是否为你保养预约页面
        getCarList();
        myCarAdapter = new MyCarAdapter();
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        car_recycler.setLayoutManager(linearLayoutManager);
        car_recycler.setAdapter(myCarAdapter);
        car_recycler.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 4), getResources().getColor(R.color.material_grey_200)));
        for (int i = 0; i < car_recycler.getItemDecorationCount(); i++) {
            if (i != 0) {
                car_recycler.removeItemDecorationAt(i);
            }
        }

        myCarAdapter.setOnLovelyCarHandleListener(new MyCarAdapter.OnLovelyCarHandleListener() {



            @Override
            public void onDeleteCar(CarListBean.JdataBean.MycarBean item) {
                del_car(item.getCar_id());
            }

            @Override
            public void onSetPrimary(CarListBean.JdataBean.MycarBean item) {
                setCarDefault(item.getCar_id());
            }

            @Override
            public void onSelectMyOrderCar(CarListBean.JdataBean.MycarBean item) {
                if (toFragment ==Constants.Fragment_appointment){  //保养页面
                    car_frameno = item.getCar_frameno();
                    car_no = item.getCar_no();
                    setCarDefault(item.getCar_id());
                }
            }
        });

    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getCarListSuccess(CarListBean carListBean) {
        myCarAdapter.setNewData(carListBean.getJdata().getMycar());
        isLoading=false;
        car_swipe.setRefreshing(false);
    }

    @Override
    public void getCarListFailure(String failure) {
        ToastUtils.showLong(failure);
        isLoading=false;
        car_swipe.setRefreshing(false);
    }

    @Override
    public void setCarDefaultSuccess(CarDefaultBean carDefaultSuccess) {

        if (toFragment ==Constants.Fragment_appointment){  //保养页面
            EventBus.getDefault().postSticky(new EventBusUserCar(car_frameno,car_no));
            _mActivity.onBackPressed();
        } else {
            getCarList();
        }
    }

    @Override
    public void setCarDefaultFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    @Override
    public void deleteCarSuccess(CarDefaultBean carDefaultBean) {
        getCarList();
    }

    @Override
    public void deleteCarFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    /**
     * 车辆列表
     */
    private void getCarList() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        p.getCarList(map);
    }

    /**
     * 设置默认或使用车辆
     */
    private void setCarDefault(String car_id) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("car_id", car_id);
        p.setCarDefault(map);
    }

    /**
     * 删除车辆
     */
    private void del_car(String car_id) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("car_id", car_id);
        p.deleteCar(map);
    }

    @Override
    public void onResume() {
        super.onResume();
        getCarList();
    }
}
