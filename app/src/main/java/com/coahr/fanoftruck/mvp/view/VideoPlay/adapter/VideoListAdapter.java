package com.coahr.fanoftruck.mvp.view.VideoPlay.adapter;


import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;


import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 19:25
 */
public class VideoListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public VideoListAdapter() {
        super(R.layout.item_recyclerview_videolist, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (item != null) {
           JzvdStd jzvdStd = helper.getView(R.id.video_list);
           //((JzvdStd) helper.getView(R.id.video_list))
            jzvdStd .setUp(item,"hahah",Jzvd.SCREEN_WINDOW_NORMAL);
            Glide.with(jzvdStd.getContext()).load(item).into(jzvdStd.thumbImageView);
          /*  Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
            Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;*/
        }

    }
}
