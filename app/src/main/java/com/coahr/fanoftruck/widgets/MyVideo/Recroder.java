package com.coahr.fanoftruck.widgets.MyVideo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
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
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;


import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.widgets.AutoFitTextureView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Leehor
 * on 2018/11/24
 * on 15:21
 */
public class Recroder extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.sv_record)
    AutoFitTextureView mTextureView;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.btn_switch)
    Button btnSwitch;
    @BindView(R.id.btn_end)
    Button btnEnd;
    @BindView(R.id.pb_record)
    SeekBar pbRecord;
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

    private static final int RC_STORAGE = 1001;
    private CameraManager cameraManager;
    private int camera_type =0;
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
    private  int  height,width;

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
                Log.d("返回大小", size.getWidth()+"/"+size.getHeight());
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
        Size size_result=null;//储存最合适的尺寸
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
        Log.d("尺寸1", maxPreviewWidth+"/"+maxPreviewHeight);
        Log.d("尺寸2", width+"/"+height);
        float reqRatio = ((float) maxPreviewWidth) / maxPreviewHeight;
        Log.d("尺寸比例1",reqRatio+"");
        float curRatio, deltaRatio;
        float deltaRatioMin = Float.MAX_VALUE;
        Log.d("尺寸比例2",deltaRatioMin+"");

        for (Size option : choices) { //先查找preview中是否存在与surfaceview相同宽高的尺寸
            if (option.getWidth() == maxPreviewWidth  &&  option.getHeight() == maxPreviewHeight){
                Log.d("相等",option.getWidth()+"/"+option.getHeight());
                size_result = option;
                return size_result;
            } else if (option.getWidth() == maxPreviewHeight && option.getHeight() ==maxPreviewWidth){
                Log.d("相反",option.getWidth()+"/"+option.getHeight());
                Size size_l =new Size(maxPreviewHeight,maxPreviewWidth);
                size_result = size_l;
                return size_result;
            }


        }
        if (size_result == null) {
            for (Size option : choices) {
                //if (option.getWidth()== 1024) continue;//1024表示可接受的最小尺寸，否则图像会很模糊，可以随意修改
                curRatio = ((float) option.getWidth()) / option.getHeight();
                Log.d("尺寸比例3",curRatio+"");
                deltaRatio = Math.abs(reqRatio - curRatio);
                Log.d("尺寸循环",option.getHeight()+"/"+option.getWidth());
                if (deltaRatio < deltaRatioMin) {
                    size_result =option;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_record);
        ButterKnife.bind(this);
        btnStart.setOnClickListener(this);
        btnEnd.setOnClickListener(this);
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
            switch (view.getId()){
                case R.id.btn_end:
                    stopRecordingVideo();
                    break;
                case R.id.btn_start:
                    startRecordingVideo();
                    break;
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
            startPreview();
            mCameraOpenCloseLock.release();
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
            finish();
        }
    };

    /**
     * 初始化相机
     */
    private void openCamera(int height, int width) {
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
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
            int displayRotation = this.getWindowManager().getDefaultDisplay().getRotation();
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
            this.getWindowManager().getDefaultDisplay().getSize(displaySize);
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
            int orientation = getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {   //如果为横屏反之宽高调换
                mTextureView.setAspectRatio(chooseOptimalSize.getWidth(), chooseOptimalSize.getHeight());
                Log.d("横屏",""+chooseOptimalSize.getWidth()+""+chooseOptimalSize.getHeight());
            } else {
                mTextureView.setAspectRatio(chooseOptimalSize.getHeight(), chooseOptimalSize.getWidth());
                Log.d("竖屏",""+chooseOptimalSize.getWidth()+""+chooseOptimalSize.getHeight());
            }
           configureTransform(width, height);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
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
            finish();
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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startPreview() {
        if (null == mCameraDevice || !mTextureView.isAvailable() || null == chooseOptimalSize) {
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
            Log.d("显示",chooseOptimalSize.getWidth()+"/"+chooseOptimalSize.getHeight());
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


                                Toast.makeText(Recroder.this, "Failed", Toast.LENGTH_SHORT).show();
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
            HandlerThread thread = new HandlerThread("CameraPreview");
            thread.start();
            mPreviewSession.setRepeatingRequest(mPreviewBuilder.build(), null, mBackgroundHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void setUpCaptureRequestBuilder(CaptureRequest.Builder builder) {
        //自动模式
        builder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
        // 设置自动对焦模式
       // builder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
        // 设置自动曝光模式
       //builder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
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
        int rotation = this.getWindowManager().getDefaultDisplay().getRotation();
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
     * @throws IOException
     */
    private void setUpMediaRecorder() throws IOException {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setOnErrorListener(OnErrorListener);
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        if (mNextVideoAbsolutePath == null || mNextVideoAbsolutePath.isEmpty()) {
            mNextVideoAbsolutePath = getVideoFilePath(this);
        }
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
        mediaRecorder.setVideoEncodingBitRate(10000000);
        //设置选择角度，顺时针方向，因为默认是逆向90度的，这样图像就是正常显示了,这里设置的是观看保存后的视频的角度
        mediaRecorder.setOrientationHint(90);
        mediaRecorder.setVideoFrameRate(30);
        mediaRecorder.setVideoSize(videoSize.getWidth(), videoSize.getHeight());
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        int rotation = this.getWindowManager().getDefaultDisplay().getRotation();
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // UI

                            // Start recording
                            mediaRecorder.start();
                        }
                    });
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession cameraCaptureSession) {


                        Toast.makeText(Recroder.this, "Failed", Toast.LENGTH_SHORT).show();

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
        mediaRecorder.setOnErrorListener(null);
        mediaRecorder.stop();
        mediaRecorder.reset();

            Toast.makeText(Recroder.this, "Video saved: " + mNextVideoAbsolutePath,
                    Toast.LENGTH_SHORT).show();
            Log.d("停止录制", "Video saved: " + mNextVideoAbsolutePath);

        mNextVideoAbsolutePath = null;
        startPreview();
    }

    /**
     * 视频保存地址
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
        mBackgroundThread.quitSafely();
        try {
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
}
