package com.coahr.fanoftruck.mvp.view.Home;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.SetCustomBannerUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_home_C;
import com.coahr.fanoftruck.mvp.model.Bean.HomeData;
import com.coahr.fanoftruck.mvp.presenter.Fragment_home_P;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.TribuneActivity;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.donkingliang.banner.CustomBanner;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.cardview.widget.CardView;
import butterknife.BindView;

/**
 * 首页主页
 * Created by Leehor
 * on 2018/11/20
 * on 11:06
 */
public class Fragment_Home extends BaseFragment<Fragment_home_C.Presenter> implements Fragment_home_C.View {
    @Inject
    Fragment_home_P p;
    @BindView(R.id.mytittle)
    MyTittleBar mytittle;
    @BindView(R.id.lin_1)
    LinearLayout lin_1;
    @BindView(R.id.lin_2)
    LinearLayout lin_2;
    @BindView(R.id.lin_3)
    LinearLayout lin_3;
    @BindView(R.id.lin_4)
    LinearLayout lin_4;
    @BindView(R.id.lin_5)
    LinearLayout lin_5;

    @BindView(R.id.to_video_play)
    CardView to_video_play;

    @BindView(R.id.tv_more_videos)
    TextView tv_more_videos;
    @BindView(R.id.custom_banner)
    CustomBanner customBanner;
    @BindView(R.id.iv_truck_video)
    ImageView ivTruckVideo;

    //头部轮播图
    private List<String> headUrls = new ArrayList<>();
    private HomeData.JdataBean.VideosBean videosBean;

    public static Fragment_Home newInstance() {
        return new Fragment_Home();
    }

    @Override
    public Fragment_home_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        mytittle.getLeftIcon().setVisibility(View.INVISIBLE);
        lin_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_maintenance);
                startActivity(intent);
            }
        });
        lin_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_help);
                startActivity(intent);
            }
        });
        lin_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_maintenance);
                startActivity(intent);
            }
        });
        lin_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TribuneActivity.class);
                startActivity(intent);
            }
        });
        lin_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_maintenance);
                startActivity(intent);
            }
        });
        to_video_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_videoPlay);
                intent.putExtra("videoLink", videosBean.getVideo_link());
                intent.putExtra("videoCover", videosBean.getVideo_cover());
                startActivity(intent);
            }
        });

        tv_more_videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_videoList);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        p.getHomeData();
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {
    }

    @Override
    public void getHomeDataSuccess(HomeData homeData) {
        if (homeData != null) {
            HomeData.JdataBean jdata = homeData.getJdata();
            if (jdata != null) {
                List<HomeData.JdataBean.HeadImagesBean> headImages = jdata.getHeadImages();
                if (headImages != null && !headImages.isEmpty()) {
                    if (headUrls.isEmpty()) {
                        for (HomeData.JdataBean.HeadImagesBean headImagesBean : headImages) {
                            headUrls.add(headImagesBean.getAdv_img());
                        }
                    }
                    SetCustomBannerUtils.setCustomBanner(customBanner, headUrls, ImageView.ScaleType.CENTER_INSIDE);
                }

                List<HomeData.JdataBean.VideosBean> videos = jdata.getVideos();
                if (videos != null && !videos.isEmpty()) {
                    videosBean = videos.get(0);
                    if (videosBean != null) {
                        Imageloader.loadImage_larger(videosBean.getVideo_cover(), ivTruckVideo);
                    }
                }
            }
        }
    }

    @Override
    public void getHomeDataFailure(String failure) {
        ToastUtils.showShort(_mActivity, failure);
    }
}