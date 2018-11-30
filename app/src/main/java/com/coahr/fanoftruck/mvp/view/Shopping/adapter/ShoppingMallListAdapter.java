package com.coahr.fanoftruck.mvp.view.Shopping.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallBean;
import com.coahr.fanoftruck.widgets.OriginalPriceTextView;

/**
 * Author： hengzwd on 2018/7/24.
 * Email：hengzwdhengzwd@qq.com
 */
public class ShoppingMallListAdapter extends BaseQuickAdapter<ShoppingMallBean.JdataEntity.CommodityEntity, BaseViewHolder> {

    private ShoppingListener itemClickListener;

    public ShoppingMallListAdapter() {
        super(R.layout.item_recyclerview_shoppingmal, null);
    }


    @Override
    protected void convert(BaseViewHolder helper, final ShoppingMallBean.JdataEntity.CommodityEntity item) {
        helper.setText(R.id.tv_commodity_price, "¥"+item.getC_price())
                .setText(R.id.tv_payment_count, item.getC_sold_real()+"人已购")
                .setText(R.id.tv_commodity_info, item.getC_name());
//        Glide.with(BaseApplication.mContext).load(item.getC_thumbnail()).crossFade().diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.mipmap.a).into((ImageView) helper.getView(R.id.iv_commodity_img));
        OriginalPriceTextView old_price = helper.getView(R.id.tv_commodity_originalprice);
        if (item.getC_price_old() != null && !item.getC_price_old().equals("0.00")) {
            old_price.setText("¥"+item.getC_price_old());
        } else {
            old_price.setVisibility(View.GONE);
        }
        Imageloader.loadImage(item.getC_thumbnail(), (ImageView) helper.getView(R.id.iv_commodity_img));
        helper.getView(R.id.rl_commodity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onClick(item);

                }
            }
        });
    }

    public void setItemClickListener(ShoppingListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
  public   interface ShoppingListener{
    void  onClick(ShoppingMallBean.JdataEntity.CommodityEntity entity);

    }
}
