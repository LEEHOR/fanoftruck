package com.coahr.fanoftruck.mvp.view.VideoPlay;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseDialogFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_discuss_dialog_C;
import com.coahr.fanoftruck.mvp.model.Bean.AddDiscuss;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoDiscussList;
import com.coahr.fanoftruck.mvp.presenter.Fragment_discuss_dialog_P;
import com.coahr.fanoftruck.mvp.view.VideoPlay.adapter.Fragment_discuss_adapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.AltDialog.Input_keyboardDialog;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/28
 * on 0:05
 */
public class Fragment_discuss extends BaseDialogFragment<Fragment_discuss_dialog_C.Presenter> implements Fragment_discuss_dialog_C.View {

    @Inject
    Fragment_discuss_dialog_P p;
    @BindView(R.id.discuss_dialog)
    RelativeLayout discuss_dialog;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.discuss_count)
    TextView discuss_count;
    @BindView(R.id.discuss_recycler)
    RecyclerView discuss_recycler;
    @BindView(R.id.discuss_swipe)
    SwipeRefreshLayout discuss_swipe;
    @BindView(R.id.send_discuss)
    LinearLayout send_discuss;
    @BindView(R.id.input_discuss)
    TextView input_ediText;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    private String video_id;
    private LinearLayoutManager manager;
    private Fragment_discuss_adapter adapter;
    private List<MaintenanceVideoDiscussList.JdataBean> jdataBeanList=new ArrayList<>();
    private int SHOW_DEALER_COUNT = 0;  //显示
    private int ALL_DEALER_COUNT = 0; //总数
    private boolean isLoading;
    private Dialog dialogs;
    private AddDiscussListener addDiscussListener;

    public static Fragment_discuss newInstance(String video_id) {
        Fragment_discuss discuss = new Fragment_discuss();
        Bundle bundle = new Bundle();
        bundle.putString("video_id", video_id);
        discuss.setArguments(bundle);
        return discuss;
    }

    @Override
    public Fragment_discuss_dialog_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.layout_discuss_dialog;
    }

    @Override
    public void initView() {
        video_id = getArguments().getString("video_id");
        getDiscussList();
        manager = new LinearLayoutManager(BaseApplication.mContext);
        adapter = new Fragment_discuss_adapter();
        discuss_recycler.setLayoutManager(manager);
        discuss_recycler.setAdapter(adapter);
        discuss_recycler.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,10)));
        for (int i = 0; i <discuss_recycler.getItemDecorationCount() ; i++) {
            if (i!=0){
                discuss_recycler.removeItemDecorationAt(i);
            }
        }

    }

    @Override
    public void initData() {

        discuss_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            dismiss();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        send_discuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Input_keyboardDialog input_keyboardDialog=new Input_keyboardDialog();
                input_keyboardDialog.setVideoSendDiscussListener(new Input_keyboardDialog.videoSendDiscuss() {
                    @Override
                    public void sendVideoDiscuss(String s, Dialog dialog) {
                        dialogs=dialog;
                        getAddDiscuss(s);
                    }
                });
                input_keyboardDialog.show(getChildFragmentManager(),TAG);

            }
        });

        discuss_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (manager.findLastVisibleItemPosition() == adapter.getItemCount() - 1 && Math.abs(dy) > 5) {
                    if (ALL_DEALER_COUNT - SHOW_DEALER_COUNT >= 10) {
                        adapter.setNewData(jdataBeanList.subList(0, SHOW_DEALER_COUNT + 10));
                        SHOW_DEALER_COUNT = SHOW_DEALER_COUNT + 10;
                    } else if (ALL_DEALER_COUNT == SHOW_DEALER_COUNT) {
                        adapter.removeAllFooterView();
                        adapter.addFooterView(addFootView);
                    } else {
                        adapter.setNewData(jdataBeanList.subList(0, ALL_DEALER_COUNT));
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

        discuss_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDiscussList();
            }
        });
    }

    @Override
    public void initAnimate() {

    }

    @Override
    public void iniWidow(Window window) {
        if (window != null) {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.dimAmount = 0.5f;   //黑暗度
            lp.alpha = 0.9f;  //透明度
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setLayout(lp.MATCH_PARENT, lp.MATCH_PARENT);
            window.setAttributes(lp);
            window.setWindowAnimations(R.style.bottom_in_out_animation);
        }
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getVideoDiscussSuccess(MaintenanceVideoDiscussList maintenanceVideoDiscussList) {
        if (maintenanceVideoDiscussList.getJdata() != null && maintenanceVideoDiscussList.getJdata().size()>0) {
            jdataBeanList.clear();
            jdataBeanList.addAll(maintenanceVideoDiscussList.getJdata());
                discuss_count.setText("("+jdataBeanList.size()+"条评论)");
                ALL_DEALER_COUNT=jdataBeanList.size();
            if (ALL_DEALER_COUNT>=10){
                adapter.setNewData(jdataBeanList.subList(0,10));
                SHOW_DEALER_COUNT=10;
            } else {
                adapter.setNewData(jdataBeanList);
                SHOW_DEALER_COUNT=jdataBeanList.size();
            }
        }
        discuss_swipe.setRefreshing(false);
    }

    @Override
    public void getVideoDiscussFailure(String failure) {
        ToastUtils.showLong(failure);
        discuss_swipe.setRefreshing(false);
    }

    @Override
    public void getVideoDiscussMoreSuccess(MaintenanceVideoDiscussList maintenanceVideoDiscussList) {

    }

    @Override
    public void getVideoDiscussMoreFailure(String failure) {

    }

    @Override
    public void getAddDiscussSuccess(AddDiscuss addDiscuss) {
        KLog.d("评论成功回调");
        ToastUtils.showLong(addDiscuss.getMsg());
        dialogs.dismiss();
        if (addDiscussListener != null) {
            addDiscussListener.addDiscussSuccess();
        }
    }

    @Override
    public void getAddDiscussFailure(String failure) {
        ToastUtils.showLong(failure);
        dialogs.dismiss();
    }


    /**
     * 获取评论列表列表
     */
    void getDiscussList() {
        Map map = new HashMap();
        map.put("video_id", video_id);
        map.put("token", Constants.token);
        p.getVideoDiscussList(map);
    }

    /**
     * 发表评论
     * @param describe
     */
    void  getAddDiscuss(String describe){
        Map map = new HashMap();
        map.put("video_id", video_id);
        map.put("token", Constants.token);
        map.put("discuss_content",describe);
        p.getAddDiscuss(map);
    }
    public interface AddDiscussListener{
        void addDiscussSuccess();
    }

    public void setAddDiscussListener(AddDiscussListener addDiscussListener) {
        this.addDiscussListener = addDiscussListener;
    }
}
