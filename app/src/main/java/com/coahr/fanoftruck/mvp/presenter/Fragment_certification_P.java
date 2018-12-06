package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_certification_C;
import com.coahr.fanoftruck.mvp.constract.Fragment_certification_C.Presenter;
import com.coahr.fanoftruck.mvp.model.Bean.save_identity_pic;
import com.coahr.fanoftruck.mvp.model.Fragment_certification_M;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_certification;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 14:37
 */
public class Fragment_certification_P extends BasePresenter<Fragment_certification_C.View,Fragment_certification_C.Model> implements Presenter {

    @Inject
    public Fragment_certification_P(Fragment_certification mview, Fragment_certification_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void Save_identity_pic(Map<String, String> map, List<String> list) {
        if (mModle != null) {
            mModle.Save_identity_pic(map,list);
        }
    }

    @Override
    public void Save_identity_picSuccess(save_identity_pic save_identity_pic) {
        if (getView() != null) {
            getView().Save_identity_picSuccess(save_identity_pic);
        }
    }

    @Override
    public void Save_identity_picFailure(String failure) {
        if (getView() != null) {
            getView().Save_identity_picFailure(failure);
        }
    }
}
