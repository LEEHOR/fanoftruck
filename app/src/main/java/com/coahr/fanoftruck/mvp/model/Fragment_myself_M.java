package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_myself_C;
import com.coahr.fanoftruck.mvp.model.Bean.BindWXData;
import com.coahr.fanoftruck.mvp.model.Bean.MyselfData;
import com.coahr.fanoftruck.mvp.model.Bean.UnsetWXData;
import com.socks.library.KLog;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:53
 */
public class Fragment_myself_M extends BaseModel<Fragment_myself_C.Presenter> implements Fragment_myself_C.Model {
    @Inject
    public Fragment_myself_M() {
        super();
    }

    @Override
    public void bindWX(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<BindWXData>(getApiService()
                .bindWX(map.get("token"), map.get("unionid"), map.get("openid"), map.get("nickname"), map.get("headimgurl"))))
                .subscribeWith(new SimpleDisposableSubscriber<BindWXData>() {
                    @Override
                    public void _onNext(BindWXData bindWXData) {
                        if (getPresenter() != null) {
                            if (bindWXData.getCode() == 0) {
                                getPresenter().bindWXSuccess(bindWXData);
                            } else {
                                getPresenter().bindWXFailure(bindWXData.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void unsetWX(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<UnsetWXData>(getApiService()
                .unsetWX(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<UnsetWXData>() {
                    @Override
                    public void _onNext(UnsetWXData unsetWXData) {
                        if (getPresenter() != null) {
                            if (unsetWXData.getCode() == 0) {
                                getPresenter().unsetWXSuccess(unsetWXData);
                            } else {
                                getPresenter().unsetWXFailure(unsetWXData.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void startLocation() {

    }

    @Override
    public void getMySelfData(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MyselfData>(getApiService().myself_data(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<MyselfData>() {
                    @Override
                    public void _onNext(MyselfData myselfData) {
                        if (getPresenter() != null) {
                            if (myselfData.getCode() == 0) {
                                getPresenter().getMySelfDataSuccess(myselfData);
                            } else {
                                getPresenter().getMySelfDataFailure(myselfData.getMsg());
                            }
                        }
                    }
                }));
    }
}
