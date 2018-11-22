package com.coahr.fanoftruck.mvp.view.Services.Adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.StarBar;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.model.Bean.StoreBean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 9:42
 */
public class Fragment_store_adapter extends BaseQuickAdapter<StoreBean.JdataEntity.StationEntity,BaseViewHolder> {

    private StoreItemClickListener itemClickListener;
    public Fragment_store_adapter() {
        super(R.layout.item_recyclerview_store, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final StoreBean.JdataEntity.StationEntity item) {
        helper.setText(R.id.tv_store_name, item.getS_name())
                .setText(R.id.tv_store_locatioon, item.getS_address())
                .setText(R.id.tv_store_distance, item.getDistance() + "km");
        Imageloader.loadImage(item.getPic1(), (ImageView) helper.getView(R.id.iv_store_img));
        ((StarBar) helper.getView(R.id.sb_evaluate)).setStarMark(Float.parseFloat(item.getLevel_score()));
        helper.getView(R.id.rl_store_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(item);
            }
        });
    }

    public void setItemClickListener(StoreItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
