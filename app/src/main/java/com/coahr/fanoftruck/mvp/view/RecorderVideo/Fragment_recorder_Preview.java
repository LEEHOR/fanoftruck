package com.coahr.fanoftruck.mvp.view.RecorderVideo;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.Nullable;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.bumptech.glide.Glide;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.imageLoader.BitmapUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_recorder_preview_C;
import com.coahr.fanoftruck.mvp.model.Bean.Video_upload;
import com.coahr.fanoftruck.mvp.presenter.Fragment_recorder_preview_P;
import com.coahr.fanoftruck.widgets.SelectTextView;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Created by Leehor
 * on 2018/11/25
 * on 22:21
 */
public class Fragment_recorder_Preview extends BaseFragment<Fragment_recorder_preview_C.Presenter> implements Fragment_recorder_preview_C.View, View.OnClickListener {


    @Inject
    Fragment_recorder_preview_P p;
    @BindView(R.id.mytitlebar)
    MyTittleBar mytitlebar;
    @BindView(R.id.preview_video)
    JzvdStd playerStandard;
    @BindView(R.id.video_describe)
    EditText video_describe;
    @BindView(R.id.video_submit)
    TextView video_submit;
    @BindView(R.id.text_count)
    TextView text_count;
    @BindView(R.id.service_tag1)
    SelectTextView service_tag1;

    @BindView(R.id.service_tag2)
    SelectTextView service_tag2;

    @BindView(R.id.service_tag3)
    SelectTextView service_tag3;

    private String videoPath;
    List<String> pathList = new ArrayList<>();
    private String video_type;
    private String saveImagePath;
    private boolean isUpload; //是否上传成功

    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private String zipPath;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Bitmap netVideoBitmap = Imageloader.getNetVideoBitmap(videoPath);
                                saveImagePath = FileUtils.saveImage(netVideoBitmap);

                                KLog.e("lizhiguo", "saveImagePath == " + saveImagePath + "--");
                                if (saveImagePath != null) {
                                    Bitmap bitmap = BitmapFactory.decodeFile(saveImagePath);
                                    Bitmap zipBitmap = BitmapUtils.ImageCompress(bitmap, 1024);
                                    zipPath = BitmapUtils.saveBitmapFile(zipBitmap);
                                    if (zipPath != null) {
                                        FileUtils.deleteFile(saveImagePath);
                                        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
                                    } else {
                                        mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
                                    }
                                } else {
                                    mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
                                }
                            }
                        });
                        thread.start();
                    } else {
                        if (zipPath != null) {
                            mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
                        } else {
                            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
                        }
                    }

                    break;
                case MSG_LOAD_SUCCESS:
                    if (zipPath != null) {
                        Map map = new HashMap();
                        map.put("video_describe", video_describe.getText().toString());
                        map.put("video_type", video_type);
                        map.put("token", Constants.token);
                        pathList.clear();
                        pathList.add(0, videoPath);
                        pathList.add(1, zipPath);
                        KLog.d("videoPath", videoPath, "zipPath", zipPath);
                        // p.uploadVideo(map, pathList);
                        setWait_dialog_text("正在上传...");
                        p.uploadVideo(map, pathList);
                    }
                    break;
                case MSG_LOAD_FAILED:
                    dismissWaitDialog();
                    ToastUtils.showLong("加载失败");
                    break;
            }
        }
    };

    public static Fragment_recorder_Preview newInstance(String videoPath, String videoThumbnail) {
        Fragment_recorder_Preview fragment_recorder_preview = new Fragment_recorder_Preview();

        Bundle bundle = new Bundle();

        bundle.putString("videoPath", videoPath);
        bundle.putString("videoThumbnail", videoThumbnail);

        fragment_recorder_preview.setArguments(bundle);

        return fragment_recorder_preview;
    }

    @Override
    public Fragment_recorder_preview_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_preview;
    }

    @Override
    public void initView() {
        mytitlebar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

        videoPath = getArguments().getString("videoPath");
        playerStandard.setUp(videoPath, null, Jzvd.SCREEN_WINDOW_NORMAL);
        Glide.with(_mActivity).load(videoPath).into(playerStandard.thumbImageView);
        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
        service_tag1.setOnClickListener(this);
        service_tag2.setOnClickListener(this);
        service_tag3.setOnClickListener(this);

    }

    @Override
    public void initData() {
        video_describe.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                video_describe.setFocusable(true);
                video_describe.setFocusableInTouchMode(true);
                return false;
            }
        });

        video_describe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                KLog.d(charSequence.length());
                text_count.setText(charSequence.length() + "个");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        video_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUpload) {  //防止重复上传
                    if (TextUtils.isEmpty(video_describe.getText()) || video_describe.getText().toString().equals("")
                            || (video_describe.getText().toString().length() > 50)) {
                        ToastUtils.showLong("请填写1～50字的描述");
                        return;
                    }
                    if (video_type == null && video_type.equals("")) {
                        ToastUtils.showLong("请选择分类");
                        return;
                    }
                    isUpload = true;
                    pathList.clear();
                    WaitingDialog();
                    mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                } else {
                    ToastUtils.showLong("已上传，无需重复上传");
                }

            }
        });

    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void uploadVideoSuccess(Video_upload video_upload) {
        KLog.d("上传成功", video_upload.getMsg());
        dismissWaitDialog();
        FileUtils.deleteFile(zipPath);
        ToastUtils.showLong("上传成功" + video_upload.getMsg());
    }

    @Override
    public void uploadVideoFailure(String failure) {
        KLog.d("上传失败", failure);
        dismissWaitDialog();
        isUpload = false;
        ToastUtils.showLong("上传失败" + failure);
        KLog.e("lizhiguo", "failure == " + failure);
    }


    @Override
    public void onPause() {
        super.onPause();

        Jzvd.releaseAllVideos();
        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }

    @Override
    public boolean onBackPressedSupport() {
        if (JzvdStd.backPress()) {
            return true;
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_tag1:
                video_type = "1";
                chanceMode(service_tag1, service_tag2, service_tag3);
                break;
            case R.id.service_tag2:
                video_type = "2";
                chanceMode(service_tag2, service_tag1, service_tag3);
                break;
            case R.id.service_tag3:
                video_type = "3";
                chanceMode(service_tag3, service_tag1, service_tag2);
                break;
        }
    }

    private void chanceMode(SelectTextView view1, SelectTextView view2, SelectTextView view3) {
        if (view1.getTag() == null || String.valueOf(view1.getTag()).equals("1")) {
            view1.toggle(false);
            view1.setTag("2");
        } else {
            video_type = "";
            view1.toggle(true);
            view1.setTag("1");
        }
        if (view2.getTag() == null || String.valueOf(view2.getTag()).equals("1")) {
            view2.toggle(true);
            view2.setTag("1");
        } else {
            video_type = "";
            view2.toggle(true);
            view2.setTag("1");
        }

        if (view3.getTag() == null || String.valueOf(view3.getTag()).equals("1")) {
            view3.toggle(true);
            view3.setTag("1");
        } else {
            video_type = "";
            view3.toggle(true);
            view3.setTag("1");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void showError(@Nullable Throwable e) {
        super.showError(e);
        ToastUtils.showLong(e.toString());
        KLog.e("lizhiguo", "Throwable == " + e.toString());
        isUpload = false;
        dismissWaitDialog();
        dismissLoading();
        video_submit.setEnabled(true);
    }
}
