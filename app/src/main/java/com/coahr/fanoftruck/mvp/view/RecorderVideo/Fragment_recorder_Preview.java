package com.coahr.fanoftruck.mvp.view.RecorderVideo;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.location.AMapLocation;
import com.bumptech.glide.Glide;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_recorder_preview_C;
import com.coahr.fanoftruck.mvp.model.Bean.Video_upload;
import com.coahr.fanoftruck.mvp.presenter.Fragment_recorder_preview_P;
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
public class Fragment_recorder_Preview extends BaseFragment<Fragment_recorder_preview_C.Presenter> implements Fragment_recorder_preview_C.View {


    @Inject
    Fragment_recorder_preview_P p;
    @BindView(R.id.preview_video)
    JzvdStd playerStandard;
    @BindView(R.id.preview)
    LinearLayout preview;
    @BindView(R.id.video_describe)
    EditText video_describe;
    @BindView(R.id.video_submit)
    TextView video_submit;
    @BindView(R.id.text_count)
    TextView text_count;
    private String videoPath;
    private Bitmap netVideoBitmap;
    List<String> pathList = new ArrayList<>();
    private int video_type;
    private Map map;
    private String videoThumbnail;
    private MaterialDialog.Builder builder;
    private String saveImagePath;
    private boolean isUpload; //是否上传成功
    private String zipVideoPath;

    public static Fragment_recorder_Preview newInstance(String videoPath, int video_type, String videoThumbnail) {
        Fragment_recorder_Preview fragment_recorder_preview = new Fragment_recorder_Preview();

        Bundle bundle = new Bundle();

        bundle.putString("videoPath", videoPath);
        bundle.putInt("video_type", video_type);
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
        videoPath = getArguments().getString("videoPath");
        video_type = getArguments().getInt("video_type");
        videoThumbnail = getArguments().getString("videoThumbnail");
        playerStandard.setUp(videoPath, null, Jzvd.SCREEN_WINDOW_NORMAL);
        Glide.with(this).load(videoPath).into(playerStandard.thumbImageView);
        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    }

    @Override
    public void initData() {
        video_submit.setBackgroundColor(getResources().getColor(R.color.material_grey_550));
        video_submit.setEnabled(false);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video_describe.setFocusable(false);
                video_describe.setFocusableInTouchMode(false);
            }
        });
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
                KLog.d(editable.length());
                if (!TextUtils.isEmpty(editable) && !editable.toString().equals("")
                        && editable.toString().length() > 0 && editable.toString().length() <= 50) {
                    video_submit.setEnabled(true);
                    video_submit.setBackgroundColor(getResources().getColor(R.color.material_blue_700));
                } else {
                    video_submit.setEnabled(false);
                    video_submit.setBackgroundColor(getResources().getColor(R.color.material_grey_550));
                }
            }
        });


        video_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isUpload) {  //防止重复上传

                    isUpload = true;
                    video_submit.setEnabled(false);
                    pathList.clear();
                    map = new HashMap();
                    map.put("video_describe", video_describe.getText().toString());
                    if (video_type == 0) {
                        map.put("video_type", "1");
                    } else {
                        map.put("video_type", String.valueOf(video_type));
                    }
                    map.put("token", "9a99788a604f85782dc5f625966205cb");
                    pathList.add(0, videoPath);
                    getCoverImage(videoPath);
                   // zipVideo(videoPath);
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
        dismissWaitDialog();
        FileUtils.deleteFile(saveImagePath);
        video_submit.setEnabled(false);
        ToastUtils.showLong(video_upload.getMsg());
    }

    @Override
    public void uploadVideoFailure(String failure) {
        dismissWaitDialog();
        isUpload = false;
        video_submit.setEnabled(true);
        ToastUtils.showLong(failure);
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

    // 子线程，开启子线程去获取视频封面
    private void getCoverImage(String videoPath_s) {

        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1) {
                    saveImagePath = (String) msg.obj;
                    if (saveImagePath != null) {
                        pathList.add(1, saveImagePath);
                        // p.uploadVideo(map, pathList);
                        setWait_dialog_text("正在上传...");
                    }
                }
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //这个URI是图片下载到本地后的缓存目录中的URI
                    if (videoPath_s != null) {
                        Bitmap netVideoBitmap = Imageloader.getNetVideoBitmap(videoPath_s);
                        String saveImage = FileUtils.saveImage(netVideoBitmap);
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = saveImage;
                        mHandler.sendMessage(msg);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }

    /**
     * 压缩视频
     *
     * @param intVideoPath    源文件
     */
    private void zipVideo(String intVideoPath) {

        zipVideoPath = FileUtils.getSDVideoPath(FileUtils.getVideoName());
           /* VideoCompress.compressVideoLow(intVideoPath, zipVideoPath, new VideoCompress.CompressListener() {
                @Override
                public void onStart() {
                    WaitingDialog();
                }

                @Override
                public void onSuccess() {
                    setWait_dialog_text("压缩成功...");
                }

                @Override
                public void onFail() {
                    setWait_dialog_text("压缩失败...");
                }

                @Override
                public void onProgress(float percent) {
                        KLog.d("压缩进度",percent);
                }
            });*/
        }

}
