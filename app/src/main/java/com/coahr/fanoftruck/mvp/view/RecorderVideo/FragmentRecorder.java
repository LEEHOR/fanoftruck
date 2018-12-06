package com.coahr.fanoftruck.mvp.view.RecorderVideo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;


import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.socks.library.KLog;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Leehor
 * on 2018/11/24
 * on 15:21
 * 已弃用此拍照
 */
public class FragmentRecorder extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.sv_record)
    TextureView mTextureView;
    @BindView(R.id.record_control)
    ImageView record_control;
    @BindView(R.id.iv_switch)
    ImageView iv_switch;
    @BindView(R.id.record_pause)
    ImageView record_pause;
    @BindView(R.id.record_time)
    Chronometer record_time;

    private long mPauseTime = 0;           //录制暂停时间间隔
    private int mRecorderState = 0; //录制状态
    public static final int STATE_INIT = 0;  //初始录制
    public static final int STATE_RECORDING = 1; //正在录制
    public static final int STATE_PAUSE = 2;  //暂停录制
    public boolean isMerge = false;

    private static final int SENSOR_ORIENTATION_DEFAULT_DEGREES = 90;
    private static final int SENSOR_ORIENTATION_INVERSE_DEGREES = 270;
    private static final SparseIntArray DEFAULT_ORIENTATIONS = new SparseIntArray();
    private static final SparseIntArray INVERSE_ORIENTATIONS = new SparseIntArray();

    static {
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_0, 90);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_90, 0);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_180, 270);
        DEFAULT_ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    static {
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_0, 270);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_90, 180);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_180, 90);
        INVERSE_ORIENTATIONS.append(Surface.ROTATION_270, 0);
    }

    private CameraManager cameraManager;
    private int camera_type = 0;
    /**
     * 相机设备
     */
    private CameraDevice mCameraDevice;

    /**
     * 会话：用于相机和硬件之间通讯
     */
    private CameraCaptureSession mPreviewSession;

    private HandlerThread mBackgroundThread;


    private Handler mBackgroundHandler;

    private CameraCharacteristics cameraCharacteristics;
    //视频宽高
    private Size videoSize;
    //视频宽高比例
    private Size chooseOptimalSize;
    //录音
    private MediaRecorder mediaRecorder;
    //摄像机拍摄方向
    private Integer mSensorOrientation;
    //视频保存地址
    private String mNextVideoAbsolutePath;
    //录制视频请求
    private CaptureRequest.Builder mPreviewBuilder;
    //宽高
    private int heights, widths;
    private boolean isRecordering=false;

    /**
     * A {@link Semaphore} to prevent the app from exiting before closing the camera.
     */
    private Semaphore mCameraOpenCloseLock = new Semaphore(1);

    //获取预览宽高
    /**
     * Max preview width that is guaranteed by Camera2 API
     */
    private static final int MAX_PREVIEW_WIDTH = 1920;

    /**
     * Max preview height that is guaranteed by Camera2 API
     */
    private static final int MAX_PREVIEW_HEIGHT = 1080;
    private String saveVideoPath = "";
    private CopyOnWriteArrayList<String> oldVideoPath;


    /**
     * 在这个样本中，我们选择一个具有3x4宽高比的视频大小。
     * 而且，我们不使用大于1080p的尺寸，因为MediaRecorder不能处理这样的高分辨率视频
     *
     * @param choices
     * @return
     */
    private static Size chooseVideoSize(Size[] choices) {
        for (Size size : choices) {
            if (size.getWidth() <= 1920) {
                Log.d("返回大小", size.getWidth() + "/" + size.getHeight());
                return size;
            }
        }
        return choices[choices.length - 1];
    }
    //设置宽高比例

    /**
     * 给定相机支持的{@code Size}s的{@code choices}，选择最小的
     * 宽度和高度至少与相应的请求值一样大，以及它们的方面
     * 比率与指定值匹配
     *
     * @param choices
     * @param width
     * @param height
     * @param aspectRatio
     * @return
     */
    private static Size chooseOptimalSize(Size[] choices, int width, int height, int maxPreviewWidth, int maxPreviewHeight, Size aspectRatio) {
        Size size_result = null;//储存最合适的尺寸
        // List<Size> bigEnough = new ArrayList<>();
        // Collect the supported resolutions that are smaller than the preview Surface
        // List<Size> notBigEnough = new ArrayList<>();
        //  int w = aspectRatio.getWidth();
        //   int h = aspectRatio.getHeight();
  /*      for (Size option : choices) {
            if (option.getWidth() <= maxPreviewWidth && option.getHeight() <= maxPreviewHeight &&
                    option.getHeight() == option.getWidth() * h / w) {
                if (option.getWidth() >= width &&
                        option.getHeight() >= height) {
                    bigEnough.add(option);
                } else {
                    notBigEnough.add(option);
                }
            }

        }*/

        // Pick the smallest of those big enough. If there is no one big enough, pick the
        // largest of those not big enough.
   /*     if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizesByArea());
        } else if (notBigEnough.size() > 0) {
            return Collections.max(notBigEnough, new CompareSizesByArea());
        } else {
            Log.e("大小", "Couldn't find any suitable preview size");
            return choices[0];
        }*/



    /*
        List<Size> bigEnough = new ArrayList<>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for (Size option : choices) {
            if (option.getHeight() == option.getWidth() * h / w &&
                    option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }

        // 如果为空获取最小的宽高
        if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizesByArea());
        } else {
            return choices[0];
        }*/

        // 得到与传入的宽高比最接近的size
        Log.d("尺寸1", maxPreviewWidth + "/" + maxPreviewHeight);
        Log.d("尺寸2", width + "/" + height);
        float reqRatio = ((float) maxPreviewWidth) / maxPreviewHeight;
        Log.d("尺寸比例1", reqRatio + "");
        float curRatio, deltaRatio;
        float deltaRatioMin = Float.MAX_VALUE;
        Log.d("尺寸比例2", deltaRatioMin + "");

        for (Size option : choices) { //先查找preview中是否存在与surfaceview相同宽高的尺寸
            if (option.getWidth() == maxPreviewWidth && option.getHeight() == maxPreviewHeight) {
                Log.d("相等", option.getWidth() + "/" + option.getHeight());
                size_result = option;
                return size_result;
            } else if (option.getWidth() == maxPreviewHeight && option.getHeight() == maxPreviewWidth) {
                Log.d("相反", option.getWidth() + "/" + option.getHeight());
                Size size_l = new Size(maxPreviewHeight, maxPreviewWidth);
                size_result = size_l;
                return size_result;
            }


        }
        if (size_result == null) {
            for (Size option : choices) {
                if (option.getWidth() == 1024) continue;//1024表示可接受的最小尺寸，否则图像会很模糊，可以随意修改
                curRatio = ((float) option.getWidth()) / option.getHeight();
                Log.d("尺寸比例3", curRatio + "");
                deltaRatio = Math.abs(reqRatio - curRatio);
                Log.d("尺寸循环", option.getHeight() + "/" + option.getWidth());
                if (deltaRatio < deltaRatioMin) {
                    size_result = option;
                    deltaRatioMin = deltaRatio;
                }
            }
        }
        //  Log.d("尺寸结果",size_result.getWidth()+"/"+size_result.getHeight());
        return size_result;
    }

    /**
     * 根据它们的区域比较两个{size}。
     */
    static class CompareSizesByArea implements Comparator<Size> {
        @Override
        public int compare(Size lhs, Size rhs) {
            // We cast here to ensure the multiplications won't overflow
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() -
                    (long) rhs.getWidth() * rhs.getHeight());
        }

    }

    public static FragmentRecorder newInstance() {
        FragmentRecorder recorder=new FragmentRecorder();

        return recorder;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_recorder;
    }

    @Override
    public void initView() {
        record_control.setOnClickListener(this);
        iv_switch.setOnClickListener(this);
        record_pause.setOnClickListener(this);
        oldVideoPath = new CopyOnWriteArrayList<>();
    }

    @Override
    public void initData() {

    }

    private MediaRecorder.OnErrorListener OnErrorListener = new MediaRecorder.OnErrorListener() {
        @Override
        public void onError(MediaRecorder mediaRecorder, int what, int extra) {
            try {
                if (mediaRecorder != null) {
                    mediaRecorder.reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 相机预览
     */
    private TextureView.SurfaceTextureListener mSurfaceTextureListener
            = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture,
                                              int width, int height) {   //相机预览准备完毕
         heights=height;
         widths=width;
            openCamera(width, height);
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture,
                                                int width, int height) {
            configureTransform(width, height);
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

    };


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.record_control:     //开始按钮
                if (mRecorderState == STATE_INIT) {//初始录制
                    startRecordingVideo();
                    isRecordering=true;
                    _mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshControlUI();
                        }
                    });
                    Log.d("录像", "onClick:初始录制 ");
                    mRecorderState = STATE_RECORDING;

                } else if (mRecorderState == STATE_RECORDING) {  //正在录制状态---停止
                    stopRecordingVideo();
                    isRecordering=false;
                    Log.d("录像", "onClick:正在录制状态---停止 ");
                    //  refreshControlUI();
                    _mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshControlUI();
                        }
                    });
                    record_control.setEnabled(false);
                    mergeMultipleFileCallBack(mNextVideoAbsolutePath);
                    mRecorderState = STATE_INIT;
                } else if (mRecorderState == STATE_PAUSE) {  //暂停状态 --停止--文件已经合并了
                    _mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshControlUI();
                        }
                    });
                    closeCamera();
                    if (isMerge) {  //是否合并过
                        Log.d("暂停状态 --停止--1", "onClick: " + oldVideoPath.get(0));
                        //start(Fragment_recorder_Preview.newInstance());
                        startWithPop(Fragment_recorder_Preview.newInstance(oldVideoPath.get(0),null));
                    } else {
                        startWithPop(Fragment_recorder_Preview.newInstance(mNextVideoAbsolutePath,null));
                        Log.d("暂停状态 --停止--2", "onClick:暂停状态 --停止 " + mNextVideoAbsolutePath);
                     //   start(Fragment_recorder_Preview.newInstance(mNextVideoAbsolutePath));
                    }
                    isRecordering=false;
                }

                break;
            case R.id.record_pause:
                if (mRecorderState == STATE_RECORDING) {   //暂停录制
                    isRecordering=true;
                    stopRecordingVideo();
                    _mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshPauseUI();
                        }
                    });
                    Log.d("录像", "onClick:暂停状态 --停止 " + mNextVideoAbsolutePath);
                    oldVideoPath.add(mNextVideoAbsolutePath);
                    if (oldVideoPath.size() > 1) {  //如果有超过两段视频就合并
                        record_control.setEnabled(false); //合并中中间按钮禁止
                        mergeMultipleFile();
                    }
                    mRecorderState = STATE_PAUSE;
                } else if (mRecorderState == STATE_PAUSE) {

                    isRecordering=true;
                    //  FileUtils.getSDPath(this,FileUtils.)
                    startRecordingVideo();
                    _mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            refreshPauseUI();
                        }
                    });
                    mRecorderState = STATE_RECORDING;
                }


                break;
            case R.id.iv_switch:
                if (!isRecordering) {
                    if (camera_type == 0) { //默认打开后置摄像头
                                closeCamera();
                                camera_type=1;
                                openCamera(heights,widths);
                                //startRecordingVideo();
                    } else if (camera_type ==1){
                        closeCamera();
                        camera_type=0;
                        openCamera(heights,widths);
                    }
                }
        }
    }

    /**
     * 点击中间按钮，执行的UI更新操作
     */
    private void refreshControlUI() {
        if (mRecorderState == STATE_INIT) {
            //录像时间计时
            record_time.setBase(SystemClock.elapsedRealtime());
            record_time.start();
            iv_switch.setVisibility(View.GONE);
            record_control.setImageResource(R.drawable.recordvideo_stop);
            //1s后才能按停止录制按钮
            record_control.setEnabled(false);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    record_control.setEnabled(true);
                }
            }, 1000);
            record_pause.setVisibility(View.VISIBLE);
            record_pause.setEnabled(true);


        } else if (mRecorderState == STATE_RECORDING) {
            mPauseTime = 0;
            record_time.stop();

            record_control.setImageResource(R.drawable.recordvideo_start);
            record_pause.setVisibility(View.GONE);
            record_pause.setEnabled(false);
            iv_switch.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 点击暂停继续按钮，执行的UI更新操作
     */
    private void refreshPauseUI() {
        if (mRecorderState == STATE_RECORDING) {
            record_pause.setImageResource(R.drawable.control_play);

            mPauseTime = SystemClock.elapsedRealtime();
            record_time.stop();
        } else if (mRecorderState == STATE_PAUSE) {
            record_pause.setImageResource(R.drawable.control_pause);

            if (mPauseTime == 0) {
                record_time.setBase(SystemClock.elapsedRealtime());
            } else {
                record_time.setBase(SystemClock.elapsedRealtime() - (mPauseTime - record_time.getBase()));
            }
            record_time.start();
        }
    }

    /**
     * 相机设备的回调
     */
    private CameraDevice.StateCallback mStateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {  //相机初始化完毕
            mCameraDevice = cameraDevice;
            /**
             * 开始预览  解锁相机
             */
            if (mTextureView != null) {
                startPreview();
                mCameraOpenCloseLock.release();

            }
            if (null != mTextureView) {
                configureTransform(mTextureView.getWidth(), mTextureView.getHeight());
            }

        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            mCameraOpenCloseLock.release();
            cameraDevice.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i) {
            mCameraOpenCloseLock.release();
            cameraDevice.close();
            mCameraDevice = null;
            _mActivity.onBackPressed();
        }
    };

    /**
     * 初始化相机
     */
    private void openCamera(int height, int width) {
        cameraManager = (CameraManager) _mActivity.getSystemService(Context.CAMERA_SERVICE);
        String cameraId = "";
        try {
            String[] cameraIdList = cameraManager.getCameraIdList();
            if (cameraIdList != null && cameraIdList.length > 0) {  //支持前后
                if (camera_type == 0) {                       //默认打开后摄
                    //相机参数的对象
                    cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraIdList[0]); //获取后置参数
                    cameraId = cameraIdList[0];
                } else {
                    cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraIdList[1]); //获取前置参数
                    cameraId = cameraIdList[1];
                }
            } else {                                                //不支持前摄
                camera_type = 0;
                cameraId = cameraIdList[0];
                cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraIdList[0]); //打开后置
            }

            StreamConfigurationMap map = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            int displayRotation = _mActivity.getWindowManager().getDefaultDisplay().getRotation();
            mSensorOrientation = cameraCharacteristics.get(CameraCharacteristics.SENSOR_ORIENTATION);
            if (map == null) {
                throw new RuntimeException("Cannot get available preview/video sizes");
            }
            //获取视频宽高
            videoSize = chooseVideoSize(map.getOutputSizes(MediaRecorder.class));
            boolean swappedDimensions = false;
            switch (displayRotation) {
                case Surface.ROTATION_0:
                    if (mSensorOrientation == 90 || mSensorOrientation == 270) {
                        swappedDimensions = true;
                    }
                case Surface.ROTATION_180:

                    break;
                case Surface.ROTATION_90:
                    if (mSensorOrientation == 0 || mSensorOrientation == 180) {
                        swappedDimensions = true;
                    }
                case Surface.ROTATION_270:

                    break;
                default:
                    Log.e("zxzxz", "Display rotation is invalid: " + displayRotation);
            }
            Point displaySize = new Point();
            _mActivity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            int rotatedPreviewWidth = width;
            int rotatedPreviewHeight = height;
            int maxPreviewWidth = displaySize.x;
            int maxPreviewHeight = displaySize.y;

            if (swappedDimensions) {
                rotatedPreviewWidth = height;
                rotatedPreviewHeight = width;
                maxPreviewWidth = displaySize.y;
                maxPreviewHeight = displaySize.x;
            }

            if (maxPreviewWidth > MAX_PREVIEW_WIDTH) {
                maxPreviewWidth = MAX_PREVIEW_WIDTH;
            }

            if (maxPreviewHeight > MAX_PREVIEW_HEIGHT) {
                maxPreviewHeight = MAX_PREVIEW_HEIGHT;
            }
            //适配视频宽高比例
            chooseOptimalSize = chooseOptimalSize(map.getOutputSizes(SurfaceTexture.class), rotatedPreviewWidth, rotatedPreviewHeight, maxPreviewWidth,
                    maxPreviewHeight, videoSize);
            int orientation = _mActivity.getResources().getConfiguration().orientation;

            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {   //如果为横屏反之宽高调换
                configureTransform(width, height);
                Log.d("横屏", "" + chooseOptimalSize.getWidth() + "" + chooseOptimalSize.getHeight());
            } else {
                configureTransform(height, width);
               // mTextureView.set(chooseOptimalSize.getHeight(), chooseOptimalSize.getWidth());
                Log.d("竖屏", "" + chooseOptimalSize.getWidth() + "" + chooseOptimalSize.getHeight());
            }


            if (ActivityCompat.checkSelfPermission(_mActivity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            cameraManager.openCamera(cameraId, mStateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
            _mActivity.onBackPressed();
        }

    }

    /**
     * 关闭相机
     */
    private void closeCamera() {
        try {
            mCameraOpenCloseLock.acquire();
            closePreviewSession();
            if (null != mCameraDevice) {
                mCameraDevice.close();
                mCameraDevice = null;
            }
            if (null != mediaRecorder) {
                mediaRecorder.release();
                mediaRecorder = null;
            }

        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted while trying to lock camera closing.");
        } finally {
            mCameraOpenCloseLock.release();
        }
    }

    /**
     * 开始预览
     */
    private void startPreview() {
        if (null == mCameraDevice || ! mTextureView.isAvailable() || null == chooseOptimalSize) {
            return;
        }
        try {
            closePreviewSession();
            SurfaceTexture texture = mTextureView.getSurfaceTexture();
            assert texture != null;

            /**
             *
             */
            texture.setDefaultBufferSize(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
            Log.d("显示", chooseOptimalSize.getWidth() + "/" + chooseOptimalSize.getHeight());
            //surfaceTexture.setDefaultBufferSize(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

            Surface previewSurface = new Surface(texture);
            mPreviewBuilder.addTarget(previewSurface);

            mCameraDevice.createCaptureSession(Collections.singletonList(previewSurface),
                    new CameraCaptureSession.StateCallback() {

                        @Override
                        public void onConfigured(@NonNull CameraCaptureSession session) {
                            mPreviewSession = session;
                            updatePreview();
                        }

                        @Override
                        public void onConfigureFailed(@NonNull CameraCaptureSession session) {


                            Toast.makeText(_mActivity, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    }, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the camera preview. {@link #startPreview()} 需要提前调用。
     */
    private void updatePreview() {
        if (null == mCameraDevice) {
            return;
        }
        try {
            setUpCaptureRequestBuilder(mPreviewBuilder);
          //  HandlerThread thread = new HandlerThread("CameraPreview");
          //  thread.start();
            mPreviewSession.setRepeatingRequest(mPreviewBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void setUpCaptureRequestBuilder(CaptureRequest.Builder builder) {
        //自动模式
        builder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
        // 设置自动对焦模式
         builder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
        // 设置自动曝光模式
        builder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
    }


    /**
     * 将必要的{@link android.graphics.Matrix}转换配置为“mTextureView”。
     * <p>
     * 在确定摄像机预览大小之前，不应调用此方法
     * OpenCAMA，或者直到“MtTrutVIEW”的大小固定
     *
     * @param viewWidth
     * @param viewHeight
     */
    private void configureTransform(int viewWidth, int viewHeight) {

        if (null == mTextureView || null == chooseOptimalSize) {
            return;
        }
        int rotation = _mActivity.getWindowManager().getDefaultDisplay().getRotation();
        Matrix matrix = new Matrix();
        RectF viewRect = new RectF(0, 0, viewWidth, viewHeight);
        RectF bufferRect = new RectF(0, 0, chooseOptimalSize.getHeight(), chooseOptimalSize.getWidth());
        float centerX = viewRect.centerX();
        float centerY = viewRect.centerY();
        if (Surface.ROTATION_90 == rotation || Surface.ROTATION_270 == rotation) {
            bufferRect.offset(centerX - bufferRect.centerX(), centerY - bufferRect.centerY());
            matrix.setRectToRect(viewRect, bufferRect, Matrix.ScaleToFit.FILL);
            float scale = Math.max(
                    (float) viewHeight / chooseOptimalSize.getHeight(),
                    (float) viewWidth / chooseOptimalSize.getWidth());
            matrix.postScale(scale, scale, centerX, centerY);
            matrix.postRotate(90 * (rotation - 2), centerX, centerY);
        } else if (Surface.ROTATION_180 == rotation) {
            matrix.postRotate(180, centerX, centerY);
        }
        mTextureView.setTransform(matrix);
    }

    /**
     * 视频录制工具准备
     *
     * @throws IOException
     */
    private void setUpMediaRecorder() throws IOException {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setOnErrorListener(OnErrorListener);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mNextVideoAbsolutePath = FileUtils.getSDVideoPath(FileUtils.getVideoName());
        Log.d("路径", mNextVideoAbsolutePath);
        mediaRecorder.setOutputFile(mNextVideoAbsolutePath);
        //音频一秒钟包含多少数据位
       /* CamcorderProfile mProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH_SPEED_720P);
        mediaRecorder.setAudioEncodingBitRate(44100);
        if (mProfile.videoBitRate > 2 * 1024 * 1024) {
            mediaRecorder.setVideoEncodingBitRate(2 * 1024 * 1024);
        }else {
            mediaRecorder.setVideoEncodingBitRate(1024 * 1024);
        }*/
        // mediaRecorder.setVideoFrameRate(mProfile.videoFrameRate);
        mediaRecorder.setVideoEncodingBitRate(1000000);
        //设置选择角度，顺时针方向，因为默认是逆向90度的，这样图像就是正常显示了,这里设置的是观看保存后的视频的角度
        mediaRecorder.setOrientationHint(90);
        mediaRecorder.setVideoFrameRate(30);
        mediaRecorder.setVideoSize(videoSize.getWidth(), videoSize.getHeight());
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        int rotation = _mActivity.getWindowManager().getDefaultDisplay().getRotation();
        switch (mSensorOrientation) {
            case SENSOR_ORIENTATION_DEFAULT_DEGREES:
                mediaRecorder.setOrientationHint(DEFAULT_ORIENTATIONS.get(rotation));
                break;
            case SENSOR_ORIENTATION_INVERSE_DEGREES:
                mediaRecorder.setOrientationHint(INVERSE_ORIENTATIONS.get(rotation));
                break;
        }
        mediaRecorder.prepare();
    }

    private void startRecordingVideo() {
        if (null == mCameraDevice || !mTextureView.isAvailable() || null == chooseOptimalSize) {
            return;
        }
        try {
            closePreviewSession();
            setUpMediaRecorder();
            SurfaceTexture texture = mTextureView.getSurfaceTexture();
            assert texture != null;
            texture.setDefaultBufferSize(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
            mPreviewBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD);
            List<Surface> surfaces = new ArrayList<>();

            // Set up Surface for the camera preview
            Surface previewSurface = new Surface(texture);
            surfaces.add(previewSurface);
            mPreviewBuilder.addTarget(previewSurface);

            // Set up Surface for the MediaRecorder
            Surface recorderSurface = mediaRecorder.getSurface();
            surfaces.add(recorderSurface);
            mPreviewBuilder.addTarget(recorderSurface);

            // Start a capture session
            // Once the session starts, we can update the UI and start recording
            mCameraDevice.createCaptureSession(surfaces, new CameraCaptureSession.StateCallback() {

                @Override
                public void onConfigured(@NonNull CameraCaptureSession cameraCaptureSession) {
                    mPreviewSession = cameraCaptureSession;
                    updatePreview();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            mediaRecorder.start();
                        }
                    }).start();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            // UI
//
//                            // Start recording
//                            mediaRecorder.start();
//                        }
//                    });
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {


                    Toast.makeText(_mActivity, "Failed", Toast.LENGTH_SHORT).show();

                }
            }, mBackgroundHandler);
        } catch (CameraAccessException | IOException e) {
            e.printStackTrace();
        }

    }

    private void closePreviewSession() {
        if (mPreviewSession != null) {
            mPreviewSession.close();
            mPreviewSession = null;
        }
    }

    private void stopRecordingVideo() {
        // UI
        // Stop recording
        if (mediaRecorder != null) {
            mediaRecorder.setOnErrorListener(null);
            mediaRecorder.stop();
            mediaRecorder.reset();
        }
        Toast.makeText(_mActivity, "Video saved: " + mNextVideoAbsolutePath,
                Toast.LENGTH_SHORT).show();
        Log.d("停止录制", "Video saved: " + mNextVideoAbsolutePath);
        startPreview();
    }


    private void pauseRecordingVideo() {
        mRecorderState = STATE_PAUSE;
        mediaRecorder.setOnErrorListener(null);
        mediaRecorder.stop();
        mediaRecorder.reset();
        Log.d("暂停录制", "Video saved: " + mNextVideoAbsolutePath);
        mNextVideoAbsolutePath = null;
        startPreview();
    }


    /**
     * 视频保存地址
     *
     * @param context
     * @return
     */
    private String getVideoFilePath(Context context) {
        final File dir = context.getExternalFilesDir(null);
        return (dir == null ? "" : (dir.getAbsolutePath() + "/"))
                + System.currentTimeMillis() + ".mp4";
    }

    /**
     * Starts a background thread and its {@link Handler}.
     */
    private void startBackgroundThread() {
        mBackgroundThread = new HandlerThread("CameraBackground");
        mBackgroundThread.start();
        mBackgroundHandler = new Handler(mBackgroundThread.getLooper());
    }

    /**
     * Stops the background thread and its {@link Handler}.
     */
    private void stopBackgroundThread() {
        try {
            mBackgroundThread.quitSafely();
            mBackgroundThread.join();
            mBackgroundThread = null;
            mBackgroundHandler = null;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        startBackgroundThread();

        if (mTextureView.isAvailable()) {
            openCamera(mTextureView.getWidth(), mTextureView.getHeight());
        } else {
            mTextureView.setSurfaceTextureListener(mSurfaceTextureListener);
        }
    }

    @Override
    public void onPause() {
        closeCamera();
        stopBackgroundThread();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    /**
     * 创建视频文件保存路径
     */
    public static String getSDPath(Context context) {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Toast.makeText(context, "请查看您的SD卡是否存在！", Toast.LENGTH_SHORT).show();
            return null;
        }

        File sdDir = Environment.getExternalStorageDirectory();
        File eis = new File(sdDir.toString() + "/RecordVideo/");
        if (!eis.exists()) {
            eis.mkdir();
        }
        return sdDir.toString() + "/RecordVideo/";
    }

    /**
     * 暂停后又从新恢复录制，合并多个视频文件
     */
    private void mergeMultipleFile() {
     /*   new Thread(new Runnable() {
            @Override
            public void run() {
                FileUtils.mergeMultipleVideoFile(FragmentRecorder.this, oldVideoPath.get(0), oldVideoPath.get(1));
            }
        }).start();*/

        ObservableBuilder.createMergeMuiltFile(_mActivity, oldVideoPath.get(0), oldVideoPath.get(1))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        record_control.setEnabled(true);
                        isMerge = true;
                        oldVideoPath.clear();
                        oldVideoPath.add(s);
                        Log.d("合并", "onNext: " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 完成录制，输出最终的视频录制文件
     */
    private void mergeMultipleFileCallBack(final String lastFile) {

           /* new Thread(new Runnable() {
                @Override
                public void run() {
                    if (oldVideoPath.size() > 0) {
                        FileUtils.mergeMultipleVideoFile(FragmentRecorder.this, oldVideoPath.get(0), lastFile);
                    } else {

                    }
                }
            }).start();*/
        if (oldVideoPath.size() > 0) {
            ObservableBuilder.createMergeMuiltFile(_mActivity, oldVideoPath.get(0), lastFile)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<String>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(String s) {
                            Log.d("结果", s);
                            closeCamera();
                            startWithPop(Fragment_recorder_Preview.newInstance(s,null));


                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            record_control.setEnabled(true);
            Log.d("结果", lastFile);
            closeCamera();
            startWithPop(Fragment_recorder_Preview.newInstance(lastFile,null));
        }
    }

}
