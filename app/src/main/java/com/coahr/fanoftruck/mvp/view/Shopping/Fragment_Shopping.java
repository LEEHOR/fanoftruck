package com.coahr.fanoftruck.mvp.view.Shopping;

import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseChildFragment;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_shopping_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_shopping_P;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:11
 */
public class Fragment_Shopping extends BaseFragment<Fragment_shopping_C.Presenter> implements Fragment_shopping_C.View {
    @Inject
    Fragment_shopping_P p;
    @BindView(R.id.spinner_left)
    Spinner spinner_left;
    @BindView(R.id.spinner_center)
    Spinner spinner_center;
    @BindView(R.id.spinner_right)
    Spinner spinner_right;

    private RecyclerView recyclerView;

    private String citys[] = {"城市","不限", "武汉", "北京", "上海", "成都", "广州", "深圳", "重庆", "天津", "西安", "南京", "杭州"};// 第一个选择里面的内容
    private String ages[] = {"年龄","不限", "18岁以下", "18-22岁", "23-26岁", "27-35岁", "35岁以上"};
    private String sexs[] = {"性别","不限", "男", "女"};
    private String constellations[] = {"星座","不限", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};

    public static Fragment_Shopping newInstance() {
        return new Fragment_Shopping();
    }

    @Override
    public Fragment_shopping_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void initView() {

        View inflate = LayoutInflater.from(BaseApplication.mContext).inflate(R.layout.layout_recyclerview, null);
        recyclerView = inflate.findViewById(R.id.root_recyclerView);

    }

    @Override
    public void initData() {
        ArrayAdapter<String> arrayAdapter_city = new ArrayAdapter<String>(BaseApplication.mContext,R.layout.layout_spinner_stytle, citys);
        ArrayAdapter<String> arrayAdapter_age = new ArrayAdapter<String>(BaseApplication.mContext, R.layout.layout_spinner_stytle, ages);
        ArrayAdapter<String> arrayAdapter_sex = new ArrayAdapter<String>(BaseApplication.mContext, R.layout.layout_spinner_stytle, sexs);
        ArrayAdapter<String> arrayAdapter_start = new ArrayAdapter<String>(BaseApplication.mContext, R.layout.layout_spinner_stytle, constellations);
        arrayAdapter_city.setDropDownViewResource(R.layout.layout_spinner_dropdown_stytle);
        arrayAdapter_age.setDropDownViewResource(R.layout.layout_spinner_dropdown_stytle);
        arrayAdapter_sex.setDropDownViewResource(R.layout.layout_spinner_dropdown_stytle);
        arrayAdapter_start.setDropDownViewResource(R.layout.layout_spinner_dropdown_stytle);
        spinner_left.setAdapter(arrayAdapter_city);
        spinner_center.setAdapter(arrayAdapter_age);
        spinner_right.setAdapter(arrayAdapter_sex);
    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
