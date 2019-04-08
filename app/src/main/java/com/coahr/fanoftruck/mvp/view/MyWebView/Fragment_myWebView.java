package com.coahr.fanoftruck.mvp.view.MyWebView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.PhotoUtil;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.model.ApiContact;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.coahr.fanoftruck.widgets.x5web.X5WebViewByMyShelf;
import com.socks.library.KLog;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/19
 * H5页面
 */
public class Fragment_myWebView extends BaseFragment {
    @BindView(R.id.swipe)
    SwipeRefreshLayout mywebview_swipe;
    @BindView(R.id.my_webview_title)
    MyTittleBar my_webview_title;
    @BindView(R.id.my_webView)
    X5WebViewByMyShelf webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private String url;
    private String title;
    private boolean isLoading;

    //发票管理
    private static final int LOAD_INVOICE = 1;

    /**
     * H5与本地交互（相册）
     */
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private String mCurrentPhotoPath;
    private String mLastPhothPath;
    private Thread mThread;
    public static final int REQUEST_CODE_ALBUM = 0x01;
    public static final int REQUEST_CODE_CAMERA = 0x02;
    private static final int REQUEST_CODE_PERMISSION_CAMERA = 0x03;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mCurrentPhotoPath = PhotoUtil.takePhoto(Fragment_myWebView.this);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ALBUM || requestCode == REQUEST_CODE_CAMERA) {
            if (uploadMessage == null && uploadMessageAboveL == null) {
                return;
            }

            //取消拍照或者图片选择时
            if (resultCode != RESULT_OK) {
                //一定要返回null,否则<input file> 就是没有反应
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(null);
                    uploadMessageAboveL = null;
                }
            }

            //拍照成功和选取照片时
            if (resultCode == RESULT_OK) {
                Uri imageUri = null;

                switch (requestCode) {
                    case REQUEST_CODE_ALBUM:
                        if (data != null) {
                            imageUri = data.getData();
                        }
                        break;
                    case REQUEST_CODE_CAMERA:
                        if (!TextUtils.isEmpty(mCurrentPhotoPath)) {
                            File file = new File(mCurrentPhotoPath);
                            Uri localUri = Uri.fromFile(file);
                            Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
                            _mActivity.sendBroadcast(localIntent);
                            imageUri = Uri.fromFile(file);
                            mLastPhothPath = mCurrentPhotoPath;
                        }
                        break;
                }

                //上传文件
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(imageUri);
                    uploadMessage = null;
                }
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(new Uri[]{imageUri});
                    uploadMessageAboveL = null;
                }
            }
        }
    }

    public static Fragment_myWebView newInstance(String url, String title, int type) {
        Fragment_myWebView fragment_myWebView = new Fragment_myWebView();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("title", title);
        bundle.putInt("type", type);
        fragment_myWebView.setArguments(bundle);
        return fragment_myWebView;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_webview;
    }

    @Override
    public void initView() {
        initHardwareAccelerate();
        my_webview_title.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });

        //发票，即显示添加
        if (LOAD_INVOICE == getArguments().getInt("type", 0)) {
            TextView rightText = my_webview_title.getRightText();
            rightText.setVisibility(View.VISIBLE);
            rightText.setText("添加");
            rightText.setTextColor(Color.parseColor("#1C54BC"));
            rightText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    webView.loadUrl(ApiContact.ADD_INVOICE +"?token=" + Constants.token);
                }
            });
        }
        mywebview_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    mywebview_swipe.post(new Runnable() {
                        @Override
                        public void run() {
                            isLoading = true;
                            webView.reload();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                } else {
                    ToastUtils.showLong("加载中");
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressBar.setProgress(i, true);
                } else {
                    progressBar.setProgress(i);
                }
            }

            //For Android  >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                uploadPicture();
                return true;
            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                mywebview_swipe.setRefreshing(false);
                ToastUtils.showLong("加载完成");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                ToastUtils.showLong("正在加载页面");
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                ToastUtils.showLong("加载失败请重试");
                progressBar.setVisibility(View.GONE);
                isLoading = false;
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            url = getArguments().getString("url");
            title = getArguments().getString("title");
            my_webview_title.getTvTittle().setText(title);
        }
        initX5WebView(url);
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                _mActivity.getWindow().setFlags(
                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initX5WebView(String url) {
        webView.getSettings().setDisplayZoomControls(false);
        webView.clearCache(true);
        IX5WebViewExtension ix5 = webView.getX5WebViewExtension();
        if (null != ix5) {
            ix5.setScrollBarFadingEnabled(false);
        }
        webView.loadUrl(url);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放资源
        if (webView != null) {
            webView.destroy();
            webView.clearCache(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (webView != null)
            webView.onResume();
    }

    @Override
    public boolean onBackPressedSupport() {
        if (webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onBackPressedSupport();
    }

    /**
     * 选择相机或者相册
     */
    public void uploadPicture() {
        AlertDialog.Builder builder = new AlertDialog.Builder(_mActivity);
        builder.setTitle("请选择图片上传方式");

        //取消对话框
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                //一定要返回null,否则<input type='file'>
                if (uploadMessage != null) {
                    uploadMessage.onReceiveValue(null);
                    uploadMessage = null;
                }
                if (uploadMessageAboveL != null) {
                    uploadMessageAboveL.onReceiveValue(null);
                    uploadMessageAboveL = null;
                }
            }
        });

        builder.setPositiveButton("相机", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!TextUtils.isEmpty(mLastPhothPath)){
                    //上一张拍照的图片删除
                    mThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            File file = new File(mLastPhothPath);
                            if(file!= null){
                                file.delete();
                            }
                            mHandler.sendEmptyMessage(1);
                        }
                    });

                    mThread.start();
                }else{
                    //请求拍照权限
                    if (ActivityCompat.checkSelfPermission(_mActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        mCurrentPhotoPath = PhotoUtil.takePhoto(Fragment_myWebView.this);
                    } else {
                        ActivityCompat.requestPermissions(_mActivity, new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSION_CAMERA);
                    }
                }
            }
        });
        builder.setNegativeButton("相册", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PhotoUtil.chooseAlbumPic(Fragment_myWebView.this);
            }
        });
        builder.create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults == null && grantResults.length == 0) {
            return;
        }

        if (requestCode == REQUEST_CODE_PERMISSION_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                PhotoUtil.takePhoto(this);
            } else {
                // Permission Denied
                new AlertDialog.Builder(_mActivity)
                        .setTitle("无法拍照")
                        .setMessage("您未授予拍照权限")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent localIntent = new Intent();
                                localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                localIntent.setData(Uri.fromParts("package", _mActivity.getPackageName(), null));
                                startActivity(localIntent);
                            }
                        }).create().show();
            }
        }
    }
}