package com.coahr.fanoftruck.mvp.view.Shopping.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallDetailBean;

/**
 * Author： hengzwd on 2018/7/25.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingDetailAdapter extends BaseQuickAdapter<ShoppingMallDetailBean.JdataEntity.CommodityDescriptionEntity.DesPicEntity, BaseViewHolder> {

    public ShoppingDetailAdapter() {
        super(R.layout.item_recycler_shoppingdetail_img, null);
    }


    @Override
    protected void convert(BaseViewHolder helper,ShoppingMallDetailBean.JdataEntity.CommodityDescriptionEntity.DesPicEntity item) {
        Imageloader.loadImage_larger(item.getPic(), (ImageView) helper.getView(R.id.iv_commodity_detail_img));
    }
}
