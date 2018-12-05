package com.coahr.fanoftruck.mvp.view.Myself;

import android.Manifest;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.Permission.OnRequestPermissionListener;
import com.coahr.fanoftruck.Utils.Permission.RequestPermissionUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_certification_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_certification_P;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.BaseResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 14:32
 * 实名认证页面
 */
public class Fragment_certification extends BaseFragment<Fragment_certification_C.Presenter> implements Fragment_certification_C.View {
    @Inject
    Fragment_certification_P p;
    @BindView(R.id.ed_ID_name)
    EditText ed_ID_name;
    @BindView(R.id.iv_fragment)
    FrameLayout iv_fragment;
    @BindView(R.id.tv_fragment)
    FrameLayout tv_fragment;
    @BindView(R.id.iv_ID_z)
    ImageView iv_ID_z;

    public static Fragment_certification newInstance() {
        return new Fragment_certification();
    }

    @Override
    public Fragment_certification_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_certification;
    }

    @Override
    public void initView() {
        iv_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage(1);
            }
        });
        tv_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage(1);
            }
        });
    }

    @Override
    public void initData() {

    }

    /**
     * 身份证拍照
     *
     * @param type 正反面区分
     */
    private void openImage(int type) {
        RxGalleryFinal
                .with(BaseApplication.mContext)
                .image()
                .radio()
                .imageLoader(ImageLoaderType.GLIDE)
                .subscribe(new RxBusResultDisposable<ImageMultipleResultEvent>() {
                    @Override
                    protected void onEvent(ImageMultipleResultEvent imageMultipleResultEvent) throws Exception {
                        if (type == 1) {  //身份证正面
                           // iv_ID_z.setImageBitmap();
                            String originalPath = imageMultipleResultEvent.getResult().get(0).getOriginalPath();
                            Imageloader.loadImage(originalPath,iv_ID_z);
                            tv_fragment.setVisibility(View.INVISIBLE);
                        }
                    }
                })
                .openGallery();
    }
}
