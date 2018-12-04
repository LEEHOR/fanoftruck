package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.model.Bean.CarDefaultBean;
import com.coahr.fanoftruck.mvp.constract.Fragment_myCar_C;
import com.coahr.fanoftruck.mvp.model.Bean.CarListBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 18:08
 */
public class Fragment_myCar_M extends BaseModel<Fragment_myCar_C.Presenter> implements Fragment_myCar_C.Model {
    @Inject
    public Fragment_myCar_M() {
        super();
    }

    @Override
    public void startLocation() {

    }

    @Override
    public void getCarList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CarListBean>(getApiService().car_list(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<CarListBean>() {
                    @Override
                    public void _onNext(CarListBean carListBean) {
                        if (getPresenter() != null) {
                            if (carListBean.getCode()==0) {
                                getPresenter().getCarListSuccess(carListBean);
                            }else {
                                getPresenter().getCarListFailure(carListBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void setCarDefault(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CarDefaultBean>(getApiService().set_car_default(map.get("token"),map.get("car_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<CarDefaultBean>() {
                    @Override
                    public void _onNext(CarDefaultBean carDefaultBean) {
                        if (getPresenter() != null) {
                            if (carDefaultBean.getCode()==0) {
                                getPresenter().setCarDefaultSuccess(carDefaultBean);
                            }else {
                                getPresenter().setCarDefaultFailure(carDefaultBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void deleteCar(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CarDefaultBean>(getApiService().del_car(map.get("token"),map.get("car_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<CarDefaultBean>() {
                    @Override
                    public void _onNext(CarDefaultBean carDefaultBean) {
                        if (getPresenter() != null) {
                            if (carDefaultBean.getCode()==0) {
                                getPresenter().deleteCarSuccess(carDefaultBean);
                            }else {
                                getPresenter().deleteCarFailure(carDefaultBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
