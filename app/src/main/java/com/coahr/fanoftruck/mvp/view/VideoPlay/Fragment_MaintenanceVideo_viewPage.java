package com.coahr.fanoftruck.mvp.view.VideoPlay;

import android.Manifest;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.KeyBoardUtils;
import com.coahr.fanoftruck.Utils.Permission.OnRequestPermissionListener;
import com.coahr.fanoftruck.Utils.Permission.RequestPermissionUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_viewp_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.presenter.Fragment_maintenance_viewp_P;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_login;
import com.coahr.fanoftruck.mvp.view.RecorderVideo.FragmentRecorder;
import com.coahr.fanoftruck.mvp.view.RecorderVideo.Fragment_recorder_Preview;
import com.coahr.fanoftruck.mvp.view.VideoPlay.adapter.Fragment_maintenance_child_adapter;
import com.coahr.fanoftruck.mvp.view.VideoPlay.adapter.Fragment_maintenance_viewpager_adapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.bean.MediaBean;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageMultipleResultEvent;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 0:28
 * 维修视频
 */
public class Fragment_MaintenanceVideo_viewPage extends BaseFragment<Fragment_maintenance_viewp_C.Presenter> implements Fragment_maintenance_viewp_C.View,OnEditorActionListener{
    @Inject
    Fragment_maintenance_viewp_P p;

    @BindView(R.id.mytitle)
    MyTittleBar myTittleBar;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tv_play_video)
    TextView tv_play_video;
    @BindView(R.id.viewpager_linear)
    LinearLayout viewpager_linear;
    @BindView(R.id.search_recycler)
    RecyclerView search_recycler;
    private Fragment_maintenance_child_adapter fragment_maintenance_child_adapter;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private boolean isLoading;
    private List<MaintenanceVideoList.JdataBean> maintenanceVideoListJdata = new ArrayList<>();
    private int pageLength = 9;
    private int start = 0;
    private int[] lastVisibleItemPositions;
    private int max;
    private String video_name;
    private GridLayoutManager gridLayoutManager;
    private Dialog dialog;
    private View inflate;
    private MaterialDialog.Builder builder;
    private int firstVisibleItemPosition;

    public static Fragment_MaintenanceVideo_viewPage newInstance() {
        return  new Fragment_MaintenanceVideo_viewPage();
    }


    @Override
    public Fragment_maintenance_viewp_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_maintenance_video;
    }

    @Override
    public void initView() {
        viewpager_linear.setVisibility(View.VISIBLE);
        search_recycler.setVisibility(View.GONE);
        myTittleBar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
            myTittleBar.getRightIcon().setVisibility(View.VISIBLE); //右边搜索框
            myTittleBar.getRightIcon().setImageDrawable(getResources().getDrawable(R.mipmap.search));

            myTittleBar.getRightIcon().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myTittleBar.getEditSearch().setVisibility(View.VISIBLE);
                    myTittleBar.getTvTittle().setVisibility(View.GONE);
                    myTittleBar.getRightIcon().setVisibility(View.GONE);
                    myTittleBar.getRightText().setVisibility(View.VISIBLE);
                    start = 0;
                    pageLength=9;
                    viewpager_linear.setVisibility(View.GONE);
                    search_recycler.setVisibility(View.VISIBLE);

                    myTittleBar.getRightText().setText("取消");
                    myTittleBar.getRightText().setTextColor(getResources().getColor(R.color.red_1));
                }
            });

            myTittleBar.getRightText().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myTittleBar.getEditSearch().setFocusable(false);
                    myTittleBar.getEditSearch().setText(null);
                    myTittleBar.getEditSearch().setHint("请输入要查询的内容");
                    myTittleBar.getTvTittle().setVisibility(View.VISIBLE);
                    myTittleBar.getEditSearch().setVisibility(View.GONE);
                    myTittleBar.getRightText().setVisibility(View.GONE);
                    myTittleBar.getRightIcon().setVisibility(View.VISIBLE);

                    viewpager_linear.setVisibility(View.VISIBLE);
                    search_recycler.setVisibility(View.GONE);
                }
            });

            myTittleBar.getEditSearch().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myTittleBar.getEditSearch().setFocusable(true);
                    myTittleBar.getEditSearch().setFocusableInTouchMode(true);
                }
            });


            myTittleBar.getEditSearch().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   // myTittleBar.getRightText().setVisibility(View.VISIBLE);
                   // myTittleBar.getRightIcon().setVisibility(View.GONE);

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            myTittleBar.getEditSearch().setOnEditorActionListener(this);


        tv_play_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasLogin()){
                   // start(Fragment_login.newInstance(Constants.Fragment_MaintenanceVideo_viewPage));
                    openVideoSelectRadioMethod();
                }else {
                    new MaterialDialog.Builder(_mActivity)
                            .title("提示")
                            .content("需要登陆")
                            .positiveText("登录")
                            .negativeText("取消")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                    start(Fragment_login.newInstance(Constants.Fragment_MaintenanceVideo_viewPage));
                                }
                            })
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                }
                            }).build().show();

                }
            }
        });
    }

    @Override
    public void initData() {

        Fragment_maintenance_viewpager_adapter adapter=new Fragment_maintenance_viewpager_adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        fragment_maintenance_child_adapter = new Fragment_maintenance_child_adapter();
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager = new GridLayoutManager(BaseApplication.mContext, 2);
        search_recycler.setLayoutManager(gridLayoutManager);
        search_recycler.setAdapter(fragment_maintenance_child_adapter);
        search_recycler.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext, 8), DensityUtils.dp2px(BaseApplication.mContext, 5), getResources().getColor(R.color.material_grey_200)));
        for (int i = 0; i < search_recycler.getItemDecorationCount(); i++) {
            if (i != 0) {
                search_recycler.removeItemDecorationAt(i);
            }
        }
        search_recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (fragment_maintenance_child_adapter.getFooterLayoutCount() == 0 && fragment_maintenance_child_adapter.getData().size() >= 10) {
                    fragment_maintenance_child_adapter.addFooterView(addFooterView);
                }
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                //屏幕中最后一个可见子项的position
                // int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                //当前屏幕所看到的子项个数
                int visibleItemCount = layoutManager.getChildCount();
                //当前RecyclerView的所有子项个数
                int totalItemCount = layoutManager.getItemCount();
                //RecyclerView的滑动状态
                int state = recyclerView.getScrollState();
                if (visibleItemCount > 0 && firstVisibleItemPosition == totalItemCount - 1 && state == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!isLoading) {
                        search_recycler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isLoading = true;
                                start+=10;
                                pageLength+=10;
                                getSearchMoreList();
                            }
                        }, 500);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
              //  int[] lastVisibleItemPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(new int[staggeredGridLayoutManager.getSpanCount()]);
              //  max = findMax(lastVisibleItemPositions);
                firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
                KLog.d("max", max);
            }
        });
    }

    /**
     * 找到数组中最大的数
     *
     * @param lastPositions
     * @return
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }


    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            KeyBoardUtils.hideKeybord(textView, getActivity());
            video_name = myTittleBar.getEditSearch().getText().toString();
            getSearchList();
            } else {
            return true;
        }
        return false;
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getVideoSearchSuccess(MaintenanceVideoList maintenanceVideoList) {
        isLoading = false;
        maintenanceVideoListJdata.clear();
        maintenanceVideoListJdata.addAll(maintenanceVideoList.getJdata());
        fragment_maintenance_child_adapter.setNewData(maintenanceVideoListJdata);
    }

    @Override
    public void getVideoSearchFailure(String failure) {
        isLoading = false;
        ToastUtils.showLong(failure);
    }

    @Override
    public void getVideoSearchMoreSuccess(MaintenanceVideoList maintenanceVideoList) {
        if (maintenanceVideoList.getJdata() != null && maintenanceVideoList.getJdata().size() == 0) {
            start-=10;
            pageLength-=10;
            Toast.makeText(_mActivity, "没有更多视频了", Toast.LENGTH_LONG).show();
        } else {
            maintenanceVideoListJdata.addAll(maintenanceVideoList.getJdata());
            fragment_maintenance_child_adapter.setNewData(maintenanceVideoListJdata);
        }
        isLoading = false;
        if (fragment_maintenance_child_adapter.getFooterLayoutCount() > 0) {
            fragment_maintenance_child_adapter.removeAllFooterView();
        }
    }

    @Override
    public void getVideoSearchMoreFailure(String failure) {
        isLoading = false;
        start-=10;
        pageLength-=10;
        if (fragment_maintenance_child_adapter.getFooterLayoutCount() > 0) {
            fragment_maintenance_child_adapter.removeAllFooterView();
        }
        ToastUtils.showLong(failure);
    }

    private void getSearchList() {
        Map map = new HashMap();
        map.put("video_name", video_name);
        map.put("video_type", "");
        map.put("start", String.valueOf(start));
        map.put("length", String.valueOf(pageLength));
        p.getVideoSearch(map);
    }

    private void getSearchMoreList() {
        Map map = new HashMap();
        map.put("video_name", video_name);
        map.put("video_type", "");
        map.put("start", String.valueOf(start));
        map.put("length", String.valueOf(pageLength));
        p.getVideoSearchMore(map);
    }

    /**
     *
    **
     * 视频单选回调
     */
    private void openVideoSelectRadioMethod() {
        RxGalleryFinalApi
                .getInstance(_mActivity)
                .setCrop()
                .setType(RxGalleryFinalApi.SelectRXType.TYPE_VIDEO, RxGalleryFinalApi.SelectRXType.TYPE_SELECT_RADIO)
                .setVDRadioResultEvent(new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        KLog.d("视频回调" ,imageRadioResultEvent.getResult().getOriginalPath(), imageRadioResultEvent.getResult().getThumbnailSmallPath());
                        Toast.makeText(_mActivity, imageRadioResultEvent.getResult().getOriginalPath(), Toast.LENGTH_SHORT).show();
                        start(Fragment_recorder_Preview.newInstance(imageRadioResultEvent.getResult().getOriginalPath()
                                ,imageRadioResultEvent.getResult().getThumbnailBigPath()));
                    }
                })
                .open();
    }

    /**
     * 获取动态权限
     */
    private void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RequestPermissionUtils.requestPermission(_mActivity, new OnRequestPermissionListener() {
                        @Override
                        public void PermissionSuccess(List<String> permissions) {
                            start(FragmentRecorder.newInstance());
                        }

                        @Override
                        public void PermissionFail(List<String> permissions) {
                            ToastUtils.showLong("没有权限无法打开");
                        }

                        @Override
                        public void PermissionHave() {
                            start(FragmentRecorder.newInstance());
                        }
                    }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE
                    , Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO);
        } else {
            start(FragmentRecorder.newInstance());

        }
    }


}
