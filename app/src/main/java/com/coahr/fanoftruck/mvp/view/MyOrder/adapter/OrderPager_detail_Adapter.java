package com.coahr.fanoftruck.mvp.view.MyOrder.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.model.Bean.CommodityOrderBean;

import java.util.List;


/**
 * Author： hengzwd on 2018/8/9.
 * Email：hengzwdhengzwd@qq.com
 */
public class OrderPager_detail_Adapter extends BaseQuickAdapter<CommodityOrderBean.JdataEntity.OrderListEntity.CommodityEntity,BaseViewHolder> {

    public OrderPager_detail_Adapter(List<CommodityOrderBean.JdataEntity.OrderListEntity.CommodityEntity> data) {
        super(R.layout.item_recyclerview_confirm_commodityorder, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, CommodityOrderBean.JdataEntity.OrderListEntity.CommodityEntity item) {
                helper.setText(R.id.tv_commodity_info,item.getC_name())
                        .setText(R.id.tv_commodity_price,"¥"+item.getC_price())
                        .setText(R.id.tv_payment_count,"x"+item.getC_num());
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
    }
}
