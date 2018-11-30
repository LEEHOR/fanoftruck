package com.coahr.fanoftruck.mvp.view.VideoPlay.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.model.ApiContact;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.widgets.MyVideo.MyVideoPlay_Normal;
import com.coahr.fanoftruck.widgets.MyVideo.MyVideoPlayer_douyin;

import cn.jzvd.JzvdStd;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 23:22
 */
public class Video_play_adapter extends BaseQuickAdapter<MaintenanceVideoList.JdataBean,BaseViewHolder> {
    private boolean isscrolling;
    private video_play video_playListener;
    public Video_play_adapter() {
        super(R.layout.item_video_play, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MaintenanceVideoList.JdataBean item) {
        if (item != null) {
            ((MyVideoPlay_Normal) helper.getView(R.id.myVideo)).setUp(ApiContact.baseUrl+item.getVideo_link(),"",JzvdStd.SCREEN_WINDOW_NORMAL);
             if (helper.getAdapterPosition() == 0 || isscrolling) {
            ((MyVideoPlay_Normal) helper.getView(R.id.myVideo)).startVideo();
            isscrolling=false;
        }
       Glide.with(BaseApplication.mContext).load(ApiContact.baseUrl+item.getVideo_link()).into(((MyVideoPlay_Normal)helper.getView(R.id.myVideo)).thumbImageView);
           helper.setText(R.id.video_view_num,item.getVideo_view_num())
                   .setText(R.id.video_discuss_num,item.getDiscuss_num())
                   .setText(R.id.video_dz_num,item.getVideo_dz_num())
                   .setText(R.id.video_type,item.getVideo_type())
                   .setText(R.id.video_name,item.getVideo_name());
           //评论
           helper.getView(R.id.rl_2).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (video_playListener != null) {
                       video_playListener.video_discuss(item.getId());
                   }
               }
           });

           //点赞
           helper.getView(R.id.rl_3).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (video_playListener != null) {
                       video_playListener.video_dz(item.getId());
                   }
               }
           });

           //分享
           helper.getView(R.id.rl_4).setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (video_playListener != null) {
                       video_playListener.video_share(item.getId(),item.getVideo_name(),item.getVideo_link());
                   }
               }
           });


    }
    }
    public void isScrroll(boolean isscrolling,int position){
        this.isscrolling =isscrolling;
        notifyItemChanged(position);
    }

    public void setVideo_playListener(video_play video_playListener) {
        this.video_playListener = video_playListener;
    }

    public  interface  video_play{
        void video_discuss(String video_id);

        void video_dz(String video_id);

        void video_share(String video_id,String video_name,String video_url);
    }
}
