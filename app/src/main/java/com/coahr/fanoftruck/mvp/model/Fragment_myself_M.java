package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_myself_C;

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
    public void startLocation() {
    }
}
