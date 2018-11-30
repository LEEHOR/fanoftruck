package com.coahr.fanoftruck.mvp.view.VideoPlay.adapter;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.TimeUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.model.ApiContact;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 16:33
 */
public class Fragment_maintenance_child_adapter extends BaseQuickAdapter<MaintenanceVideoList.JdataBean, BaseViewHolder> {
    private VideoOnClick videoOnClick;
    public Fragment_maintenance_child_adapter() {
        super(R.layout.item_recyclerview_maintenance_video, null);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MaintenanceVideoList.JdataBean item) {
        if (item != null) {
            helper.setText(R.id.video_size, item.getVideo_state())
                    .setText(R.id.video_type, item.getVideo_type())
                    .setText(R.id.video_name, item.getVideo_name())
                    .setText(R.id.tv_user_name, item.getUser_info())
                    .setText(R.id.discuss_time,TimeUtils.getStringDate_Normal(item.getAdd_time()))
                    .setText(R.id.video_browse, item.getVideo_view_num())
                    .setText(R.id.video_comment, item.getVideo_view_num())
                    .setText(R.id.video_like, item.getVideo_dz_num());
            Imageloader.loadImage(ApiContact.baseUrl + item.getVideo_cover(), helper.getView(R.id.video_thumbImageView));
            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (videoOnClick != null) {
                        videoOnClick.OnClick(helper.getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setVideoOnClick(VideoOnClick videoOnClick) {
        this.videoOnClick = videoOnClick;
    }

    public interface VideoOnClick {
        void OnClick(int position);
    }

/*   开启子线程加载图片
 private void asyncloadImage(final ImageView imageView, final String uri) {
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    Bitmap bitmap = (Bitmap) msg.obj;
                    if (imageView != null && uri != null) {
                        imageView.setImageBitmap(bitmap);
                        Imageloader.loadBitMap(bitmap,imageView);
                    }

                }
            }
        };
        // 子线程，开启子线程去下载或者去缓存目录找图片，并且返回图片在缓存目录的地址
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //这个URI是图片下载到本地后的缓存目录中的URI
                    if (uri != null) {
                        Bitmap netVideoBitmap = Imageloader.createVideoThumbnail(uri,100,100);
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = netVideoBitmap;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }*/


}
