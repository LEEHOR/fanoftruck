package com.coahr.fanoftruck.mvp.view.VideoPlay.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.TimeUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoDiscussList;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/28
 * on 9:33
 */
public class Fragment_discuss_adapter extends BaseQuickAdapter<MaintenanceVideoDiscussList.JdataBean,BaseViewHolder> {
    public Fragment_discuss_adapter() {
        super(R.layout.item_recyclerview_discuss, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MaintenanceVideoDiscussList.JdataBean item) {
            helper.setText(R.id.tv_discuss_name,item.getUser_info())
                    .setText(R.id.tv_discuss_time,TimeUtils.getStringDate_Normal(item.getAdd_time()))
                    .setText(R.id.tv_discuss_msg,item.getDiscuss_content());
            if (item.getUserHeadImg() !=null) {
                Imageloader.loadCircularImage(item.getUserHeadImg(), helper.getView(R.id.iv_discuss_user_head));
            }
    }
}
