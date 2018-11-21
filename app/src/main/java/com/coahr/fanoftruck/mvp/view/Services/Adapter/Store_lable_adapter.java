package com.coahr.fanoftruck.mvp.view.Services.Adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.model.Bean.StoreDetailBean;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 13:57
 */
public class Store_lable_adapter extends BaseQuickAdapter<StoreDetailBean.JdataEntity.StationEntity.SServiceTagEntity, BaseViewHolder> {


    public Store_lable_adapter() {
        super(R.layout.item_recycler_storedetail_tag, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, StoreDetailBean.JdataEntity.StationEntity.SServiceTagEntity item) {
        if (item != null) {
            helper.setText(R.id.store_tag,item.getTag());
        }
    }
}
