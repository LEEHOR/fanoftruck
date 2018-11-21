package com.coahr.fanoftruck.mvp.view.Services.Adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.model.Bean.StoreDetailBean;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 12:41
 */
public class Store_score_adapter extends BaseQuickAdapter<StoreDetailBean.JdataEntity.ServiceEntity, BaseViewHolder> {

private Store_scoreOnClick store_scoreOnClick;
    public Store_score_adapter() {
        super(R.layout.item_fragment_storedetail_score, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final StoreDetailBean.JdataEntity.ServiceEntity item) {
        if (item != null) {
            helper.setText(R.id.score_name,item.getB_name());
            if (item.getB_price_max().equals("价") && item.getB_price_min().equals("格")){
                helper.setText(R.id.tv_lable,item.getB_price_max()+item.getB_price_min());
            } else {
                helper.setText(R.id.tv_lable,"￥"+item.getB_price_min()+"～"+"￥"+item.getB_price_max());
            }

            if (item.getB_content() !=null){
                helper.getView(R.id.score_detail).setVisibility(View.VISIBLE);
                helper.setText(R.id.score_detail,"说明");
            } else {
                helper.getView(R.id.score_detail).setVisibility(View.GONE);
            }

            helper.getView(R.id.score_detail).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (store_scoreOnClick != null) {
                        store_scoreOnClick.scoreOnClick(helper.getView(R.id.re_score),helper.getAdapterPosition(),item.getB_content());
                    }
                }
            });
        }
    }

    public void setStore_scoreOnClick(Store_scoreOnClick store_scoreOnClick) {
        this.store_scoreOnClick = store_scoreOnClick;
    }

    public  interface Store_scoreOnClick{
        void scoreOnClick(View view,int position,String s);
    }
}
