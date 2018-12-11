package com.coahr.fanoftruck.mvp.view.Myself;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.TouchDelegate;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.Permission.OnRequestPermissionListener;
import com.coahr.fanoftruck.Utils.Permission.RequestPermissionUtils;
import com.coahr.fanoftruck.Utils.StoreSpaceUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.dagger.components.BaseActivityComponents;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_certification_C;
import com.coahr.fanoftruck.mvp.model.Bean.save_identity_pic;
import com.coahr.fanoftruck.mvp.presenter.Fragment_certification_P;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.BaseResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 14:32
 * 实名认证页面
 */
public class Fragment_certification extends BaseFragment<Fragment_certification_C.Presenter> implements Fragment_certification_C.View,View.OnClickListener {
    @Inject
    Fragment_certification_P p;
    @BindView(R.id.ed_ID_name)
    EditText ed_ID_name;
    @BindView(R.id.iv_fragment_z)
    FrameLayout iv_fragment_z;
    @BindView(R.id.tv_fragment_z)
    FrameLayout tv_fragment_z;
    @BindView(R.id.iv_fragment_f)
    FrameLayout iv_fragment_f;
    @BindView(R.id.tv_fragment_f)
    FrameLayout tv_fragment_f;
    @BindView(R.id.iv_ID_z)
    ImageView iv_IDImage_z;
    @BindView(R.id.iv_image_f)
    ImageView iv_IDImage_f;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.mytittle)
    MyTittleBar mytittle;
    private String imgIdZ,imgIdF;

    private List<String> picList=new ArrayList<>();
    private String pic1;
    private String pic2;

    public static Fragment_certification newInstance(String pic1,String pic2) {
        Fragment_certification certification=new Fragment_certification();
        Bundle bundle=new Bundle();
        bundle.putString("pic1",pic1);
        bundle.putString("pic2",pic2);
        certification.setArguments(bundle);
        return certification;
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
        iv_fragment_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage(1);
            }
        });
        tv_fragment_z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage(1);
            }
        });
        iv_fragment_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage(2);
            }
        });
        tv_fragment_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImage(2);
            }
        });
        tv_submit.setOnClickListener(this);
        mytittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            pic1 = getArguments().getString("pic1");
            pic2 = getArguments().getString("pic2");
        }
        if (pic1 !=null && pic2 !=null){
            tv_fragment_z.setVisibility(View.INVISIBLE);
            tv_fragment_f.setVisibility(View.INVISIBLE);
            Imageloader.loadImage(pic1,iv_IDImage_z);
            Imageloader.loadImage(pic2,iv_IDImage_f);
        }
    }

    /**
     * 身份证拍照
     *
     * @param type 正反面区分
     */
    private void openImage(int type) {
        //快速打开单选图片,flag使用true不裁剪
        RxGalleryFinalApi
                .openRadioSelectImage(_mActivity, new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent o) throws Exception {
                        if (type == 1) {  //身份证正面
                            // iv_ID_z.setImageBitmap();
                            imgIdZ = o.getResult().getOriginalPath();
                            Imageloader.loadImage(imgIdZ,iv_IDImage_z);
                            tv_fragment_z.setVisibility(View.INVISIBLE);
                        }

                        if (type == 2) {  //身份证正面
                            // iv_ID_z.setImageBitmap();
                            imgIdF = o.getResult().getOriginalPath();
                            Imageloader.loadImage(imgIdF,iv_IDImage_f);
                            tv_fragment_f.setVisibility(View.INVISIBLE);
                        }

                    }
                }, true);
    }

    @Override
    public void Save_identity_picSuccess(save_identity_pic save_identity_pic) {
    ToastUtils.showLong(save_identity_pic.getJdata().getJmsg());
    }

    @Override
    public void Save_identity_picFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.tv_submit:
               if (TextUtils.isEmpty(ed_ID_name.getText()) || ed_ID_name.getText().toString().equals("")){
                   ToastUtils.showLong("真实姓名不能为空");
                   return;
               }
               if (imgIdZ ==null || imgIdF==null){
                   ToastUtils.showLong("图片不能为空");
                   return;
               }

               savePic();
               break;
       }
    }

    private void savePic(){
        Map map=new HashMap();
        map.put("token",Constants.token);
        map.put("realname",ed_ID_name.getText().toString());
        map.put("pic1",StoreSpaceUtils.getImageStr(imgIdZ));
        map.put("pic2",StoreSpaceUtils.getImageStr(imgIdF));
        p.Save_identity_pic(map,picList);
    }
}
