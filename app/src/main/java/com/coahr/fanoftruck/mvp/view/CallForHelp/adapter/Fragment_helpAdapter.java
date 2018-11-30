package com.coahr.fanoftruck.mvp.view.CallForHelp.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/26
 * on 17:23
 */
public class Fragment_helpAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public Fragment_helpAdapter() {
        super(R.layout.item_recyclerview_callforhelp, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
