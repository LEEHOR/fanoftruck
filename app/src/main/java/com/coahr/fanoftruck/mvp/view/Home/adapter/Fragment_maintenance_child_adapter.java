package com.coahr.fanoftruck.mvp.view.Home.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 16:33
 */
public class Fragment_maintenance_child_adapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public Fragment_maintenance_child_adapter() {
        super(R.layout.item_recyclerview_maintenance_video, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (item != null) {
        }
    }
}
