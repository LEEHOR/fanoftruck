package com.coahr.fanoftruck.mvp.view.Home.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.model.Bean.VideoList;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 16:33
 */
public class Fragment_maintenance_child_adapter extends BaseQuickAdapter<VideoList,BaseViewHolder> {
    private VideoOnClick videoOnClick;
    public Fragment_maintenance_child_adapter() {
        super(R.layout.item_recyclerview_maintenance_video, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoList item) {
        if (item != null) {
            helper.setText(R.id.video_type,item.getTag1())
                    .setText(R.id.video_tag,item.getTag2())
                    .setText(R.id.video_name,item.getTag3())
                    .setText(R.id.tv_user_name,item.getTag4())
                    .setText(R.id.video_browse,"44444");
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (videoOnClick != null) {
                        videoOnClick.OnClick();
                    }
                }
            });
        }
    }

    public void setVideoOnClick(VideoOnClick videoOnClick) {
        this.videoOnClick = videoOnClick;
    }

   public    interface VideoOnClick{
        void OnClick();
    }

}
