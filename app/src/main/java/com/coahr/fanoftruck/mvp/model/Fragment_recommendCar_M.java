package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_recommendCar_C;
import com.coahr.fanoftruck.mvp.model.Bean.Business_car;
import com.coahr.fanoftruck.mvp.model.Bean.SaveBusinessCarBean;
import com.coahr.fanoftruck.mvp.model.Bean.getBuyCarCode;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 17:44
 */
public class Fragment_recommendCar_M extends BaseModel<Fragment_recommendCar_C.Presenter> implements Fragment_recommendCar_C.Model {
    @Inject
    public Fragment_recommendCar_M() {
        super();
    }

    @Override
    public void startLocation() {

    }

    @Override
    public void getBusiness_Car(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<Business_car>(getApiService().buyCar_initial_data(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<Business_car>() {
                    @Override
                    public void _onNext(Business_car business_car) {
                        if (getPresenter() != null) {
                            if (business_car.getCode()==0) {
                                getPresenter().getBusiness_CarSuccess(business_car);
                            } else {
                                getPresenter().getBusiness_CarFailure(business_car.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getCarCode(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<getBuyCarCode>(getApiService().get_buyCar_code(map.get("phone"))))
                .subscribeWith(new SimpleDisposableSubscriber<getBuyCarCode>() {
                    @Override
                    public void _onNext(getBuyCarCode carCode) {
                        if (getPresenter() != null) {
                            if (carCode.getCode()==0) {
                                getPresenter().getCarCodeSuccess(carCode);
                            } else {
                                getPresenter().getCarCodeFailure(carCode.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void SaveBusinessCar(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<SaveBusinessCarBean>(getApiService().save_business_order(map.get("phone")
        ,map.get("token"),map.get("verify_code"),map.get("username"),map.get("proid"),map.get("num"),map.get("address"))))
                .subscribeWith(new SimpleDisposableSubscriber<SaveBusinessCarBean>() {
                    @Override
                    public void _onNext(SaveBusinessCarBean saveBusinessCarBean) {
                        if (getPresenter() != null) {
                            if (saveBusinessCarBean.getCode()==0) {
                                getPresenter().SaveBusinessCarSuccess(saveBusinessCarBean);
                            } else {
                                getPresenter().SaveBusinessCarFailure(saveBusinessCarBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
