package com.coahr.fanoftruck.mvp.view.BusinessOpportunity.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 15:44
 */
public class Business_child_Adapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public Business_child_Adapter(List<String> data) {
        super(R.layout.item_dropdowm,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
