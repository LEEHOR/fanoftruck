package com.coahr.fanoftruck.mvp.view.Shopping.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ScreenUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
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
        ImageView view = helper.getView(R.id.iv_commodity_detail_img);
        Imageloader.setViewSize(view,ScreenUtils.getScreenWidth(BaseApplication.mContext),ViewGroup.LayoutParams.WRAP_CONTENT,ImageView.ScaleType.CENTER_INSIDE);
        Imageloader.loadImage_larger(item.getPic(),view);
    }
}
