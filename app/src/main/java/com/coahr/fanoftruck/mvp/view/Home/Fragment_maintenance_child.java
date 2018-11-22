package com.coahr.fanoftruck.mvp.view.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseChildFragment;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_child_C;
import com.coahr.fanoftruck.mvp.model.Bean.VideoList;
import com.coahr.fanoftruck.mvp.presenter.Fragment_maintenance_child_P;
import com.coahr.fanoftruck.mvp.view.Home.adapter.Fragment_maintenance_child_adapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 15:21
 * 维修视频列表
 */
public class Fragment_maintenance_child extends BaseChildFragment<Fragment_maintenance_child_C.Presenter> implements Fragment_maintenance_child_C.View{
    @Inject
    Fragment_maintenance_child_P p;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    private Fragment_maintenance_child_adapter adapter;

    public static Fragment_maintenance_child newInstance(int status){
        Fragment_maintenance_child child=new Fragment_maintenance_child();
        Bundle bundle=new Bundle();
        bundle.putInt("status",status);
        child.setArguments(bundle);
        return child;
    }
List<VideoList> lists=new ArrayList<>();

    @Override
    public BaseContract.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_maintance_child;
    }

    @Override
    public void initView() {
        adapter = new Fragment_maintenance_child_adapter();
        StaggeredGridLayoutManager linearLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext,10),DensityUtils.dp2px(BaseApplication.mContext,5),getResources().getColor(R.color.colorAccent)));
        for (int i = 0; i <recyclerView.getItemDecorationCount() ; i++) {
            if (i !=0){
                recyclerView.removeItemDecorationAt(i);
            }
        }
        recyclerView.setAdapter(adapter);
        adapter.setVideoOnClick(new Fragment_maintenance_child_adapter.VideoOnClick() {
            @Override
            public void OnClick() {
                KLog.d("跳转到播放页面");
                ((SupportFragment)getParentFragment()).start(Fragment_maintenance_videoPlay.newInstance(0));
            }
        });
    }

    @Override
    public void initData() {
        for (int i = 0; i <50 ; i++) {
            if (i%2 ==0){
                lists.add(new VideoList("1","2","2","3","sddsadasada"));

            } else {
                lists.add(new VideoList("1","2","2","3","sddsadasdsaddsdsdsdsdsdsdsdssdsasddsada"));

            }
        }
        adapter.setNewData(lists);
    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
