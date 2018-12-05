package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_certification_C;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 14:36
 */
public class Fragment_certification_M extends BaseModel<Fragment_certification_C.Presenter> implements Fragment_certification_C.Model {
   @Inject
    public Fragment_certification_M() {
        super();
    }
}
