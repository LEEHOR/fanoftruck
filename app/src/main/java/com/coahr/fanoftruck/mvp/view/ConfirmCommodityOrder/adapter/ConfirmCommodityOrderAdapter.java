package com.coahr.fanoftruck.mvp.view.ConfirmCommodityOrder.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.model.Bean.Confirm_order;

/**
 * Author： hengzwd on 2018/8/17.
 * Email：hengzwdhengzwd@qq.com
 */
public class ConfirmCommodityOrderAdapter extends BaseQuickAdapter<Confirm_order.JdataBean.CommodityBean,BaseViewHolder> {

    public ConfirmCommodityOrderAdapter() {
        super(R.layout.item_recyclerview_confirm_commodityorder, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, Confirm_order.JdataBean.CommodityBean item) {

        helper.setText(R.id.tv_commodity_info,item.getC_name())
                .setText(R.id.tv_commodity_price,"￥"+item.getC_price());
        ((TextView)helper.getView(R.id.tv_commodity_count)).setText("X"+item.getC_num());
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
    }
}
