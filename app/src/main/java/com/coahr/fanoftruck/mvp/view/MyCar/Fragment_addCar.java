package com.coahr.fanoftruck.mvp.view.MyCar;


import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_addCar_C;
import com.coahr.fanoftruck.mvp.model.Bean.SaveUserCar;
import com.coahr.fanoftruck.mvp.presenter.Fragment_addCar_P;
import com.mob.bbssdk.gui.GUIManager;
import com.mob.bbssdk.gui.utils.OperationCallback;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 18:15
 */
public class Fragment_addCar extends BaseFragment<Fragment_addCar_C.Presenter> implements Fragment_addCar_C.View {
    @Inject
    Fragment_addCar_P p;
    @BindView(R.id.et_saveUserCarInfo)
    EditText et_saveUserCarInfo;
    @BindView(R.id.ev_car_num)
    EditText ev_car_num;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    public static Fragment_addCar newInstance() {
        return new Fragment_addCar();
    }

    @Override
    public Fragment_addCar_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_addcar;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_saveUserCarInfo.getText()) || et_saveUserCarInfo.getText().toString().equals("")){
                    ToastUtils.showLong("请填写正确的车架号");
                    return;
                }
                SaveUserCar();
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
    public void OnSaveUserCarSuccess(SaveUserCar saveUserCar) {
        ToastUtils.showLong(saveUserCar.getMsg());
    }

    @Override
    public void OnSaveUserCarFailure(String failure) {
        ToastUtils.showLong(failure);
    }




    private void SaveUserCar(){
        Map map=new HashMap();
        map.put("token",Constants.token);
        map.put("car_frameno",et_saveUserCarInfo.getText().toString());
        map.put("car_no",ev_car_num.getText().toString());
        p.OnSaveUserCar(map);
    }
}
