package com.coahr.fanoftruck.mvp.view.Home.adapter;

import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.widgets.MyVideo.MyVideoPlayer;

import java.util.List;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 23:22
 */
public class Video_play_adapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public Video_play_adapter() {
        super(R.layout.item_video_play, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ViewGroup.LayoutParams layoutParams = helper.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

        ((MyVideoPlayer)helper.getView(R.id.myVideo)).setUp(item, JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
        if (helper.getAdapterPosition() == 0) {
            ((MyVideoPlayer)helper.getView(R.id.myVideo)).startVideo();
        }
       // Glide.with(BaseApplication.mContext).load(item).into(holder.mp_video.thumbImageView);
        Imageloader.loadImage_larger(item,((MyVideoPlayer)helper.getView(R.id.myVideo)).thumbImageView);
    }
}
