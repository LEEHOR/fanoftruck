package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_addCar_C;
import com.coahr.fanoftruck.mvp.model.Bean.SaveUserCar;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 18:23
 */
public class Fragment_addCar_M extends BaseModel<Fragment_addCar_C.Presenter> implements Fragment_addCar_C.Model {
    @Inject
    public Fragment_addCar_M() {
        super();
    }

    @Override
    public void startLocation() {

    }

    @Override
    public void OnSaveUserCar(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<SaveUserCar>(getApiService().Save_use_Car(map.get("token"),map.get("car_frameno"),map.get("car_no"))))
                .subscribeWith(new SimpleDisposableSubscriber<SaveUserCar>() {
                    @Override
                    public void _onNext(SaveUserCar saveUserCar) {
                        if (getPresenter() != null) {
                            if (saveUserCar.getCode()==0) {
                                getPresenter().OnSaveUserCarSuccess(saveUserCar);
                            }else {
                                getPresenter().OnSaveUserCarFailure(saveUserCar.getMsg());
                            }
                        }
                    }
                }));
    }

}
