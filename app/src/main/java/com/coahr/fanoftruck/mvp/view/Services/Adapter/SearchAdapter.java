package com.coahr.fanoftruck.mvp.view.Services.Adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.model.Bean.SearchBean;
import com.socks.library.KLog;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 17:43
 */
public class SearchAdapter extends BaseQuickAdapter<SearchBean.JdataEntity.SearchEntity,BaseViewHolder> {

    private OnSearchItemClickListener onSearchItemClickListener;
    public SearchAdapter() {
        super(R.layout.item_fragment_search, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final SearchBean.JdataEntity.SearchEntity item) {
        KLog.d("搜索",item.getName());
        helper.setText(R.id.tv_search_item,item.getName());
        helper.getView(R.id.tv_search_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchItemClickListener.onClick(item,1);
            }
        });
    }

    public void setOnItemClickListener(OnSearchItemClickListener onSearchItemClickListener){
        this.onSearchItemClickListener=onSearchItemClickListener;
    }
}
