package com.coahr.fanoftruck.mvp.view.Services.Adapter;

import com.coahr.fanoftruck.mvp.model.Bean.SearchBean;

/**
 * Author： hengzwd on 2018/7/23.
 * Email：hengzwdhengzwd@qq.com
 */
public interface OnSearchItemClickListener {

    void onClick(SearchBean.JdataEntity.SearchEntity item, int type);
}
