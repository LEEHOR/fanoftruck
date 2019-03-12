package com.coahr.fanoftruck.mvp.view.Services;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.model.Bean.AppointmentBean;
import com.coahr.fanoftruck.mvp.model.Bean.AppointmentDefaultBean;
import com.coahr.fanoftruck.mvp.model.Bean.EventBusUserCar;
import com.coahr.fanoftruck.mvp.constract.Fragment_appointment_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_appointment_P;
import com.coahr.fanoftruck.mvp.view.MyCar.Fragment_myCar;
import com.coahr.fanoftruck.widgets.SelectTextView;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 16:13
 * 预约保养
 */
public class Fragment_appointment extends BaseFragment<Fragment_appointment_C.Presenter> implements Fragment_appointment_C.View, OnDateSetListener, View.OnClickListener {

@Inject
    Fragment_appointment_P p;
    @BindView(R.id.mytitle)
    MyTittleBar myTittleBar;
    @BindView(R.id.tv_type)
    TextView tv_type;
    @BindView(R.id.service_car_frameno)
    EditText service_car_frameno;
    @BindView(R.id.select_car_no)
    TextView select_car_no;
    @BindView(R.id.service_car_carno)
    EditText service_car_carno;
    @BindView(R.id.user_name)
    EditText user_name;
    @BindView(R.id.user_phone)
    EditText user_phone;
    @BindView(R.id.select_time)
    TextView select_time;
    @BindView(R.id.appoint_time)
    TextView appoint_time;
    @BindView(R.id.input_describe)
    EditText input_describe;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.service_tag1)
    SelectTextView service_tag1;
    @BindView(R.id.service_tag2)
    SelectTextView service_tag2;
    @BindView(R.id.service_tag3)
    SelectTextView service_tag3;
    @BindView(R.id.service_tag4)
    SelectTextView service_tag4;
    @BindView(R.id.service_tag5)
    SelectTextView service_tag5;
    @BindView(R.id.service_tag6)
    SelectTextView service_tag6;
    private TimePickerDialog datePickerDialog;
    SimpleDateFormat sfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private String service_item;
    private String type;
    private String s_id;

    public static Fragment_appointment newInstance(String type, String s_id) {
        Fragment_appointment appointment = new Fragment_appointment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putString("s_id", s_id);
        appointment.setArguments(bundle);
        return appointment;
    }

    @Override
    public Fragment_appointment_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_appointment;
    }

    @Override
    public void initView() {
        initTimePickerDialog();
        myTittleBar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        select_car_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(Fragment_myCar.newInstance(Constants.Fragment_appointment));
            }
        });

        select_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show(getChildFragmentManager(), "dataPickDialog");
            }
        });
        input_describe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString()) && !editable.toString().equals("")
                        && editable.toString().length() > 0 && editable.toString().length() <= 50) {

                } else {
                    ToastUtils.showLong("请保持在1～50字内");
                }
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (service_car_frameno.getText().toString().equals("") && TextUtils.isEmpty(service_car_frameno.getText())) {
                    ToastUtils.showLong("请填写正确的车架号");
                    return;
                }
                if (TextUtils.isEmpty(user_name.getText()) && user_name.getText().toString().equals("")) {
                    ToastUtils.showLong("请填写预约人姓名");
                    return;
                }
                if (TextUtils.isEmpty(user_phone.getText()) && user_phone.getText().toString().equals("")) {
                    ToastUtils.showLong("请填写预约人手机号");
                    return;
                }

                if (TextUtils.isEmpty(appoint_time.getText()) && appoint_time.getText().toString().equals("")) {
                    ToastUtils.showLong("请填写预约时间");
                    return;
                }
                saveAppointmentOrder();
            }
        });
        service_tag1.setOnClickListener(this);
        service_tag2.setOnClickListener(this);
        service_tag3.setOnClickListener(this);
        service_tag4.setOnClickListener(this);
        service_tag5.setOnClickListener(this);
        service_tag6.setOnClickListener(this);
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            type = getArguments().getString("type");
            s_id = getArguments().getString("s_id");
        }
        if (type.equals("1")){
            myTittleBar.getTvTittle().setText("保养预约");
            tv_type.setText("保养服务");
        }
        if (type.equals("2")){
            myTittleBar.getTvTittle().setText("维修预约");
            tv_type.setText("维修服务");
        }
        getAppointmentDefault();
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void saveOrderSuccess(AppointmentBean appointmentBean) {
        ToastUtils.showLong(appointmentBean.getMsg());
    }

    @Override
    public void saveOrderFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    @Override
    public void getAppointmentDefaultSuccess(AppointmentDefaultBean appointmentDefaultBean) {
        service_car_frameno.setText(appointmentDefaultBean.getJdata().getMycar().getCar_frameno());
        service_car_carno.setText(appointmentDefaultBean.getJdata().getMycar().getCar_no());
    }

    @Override
    public void getAppointmentDefaultFailure(String failure) {

    }

    @Override
    public void onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }


    /**
     * EcenBus回调
     *
     * @param userCar
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEvenBus(EventBusUserCar userCar) {
        service_car_frameno.setText(userCar.getCar_frameno());
        service_car_carno.setText(userCar.getCar_no());
    }


    private void initTimePickerDialog() {
        KLog.e(TAG, "---initTimePickerDialog---pre---");
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        long fourHour = 60 * 60 * 4L * 1000;
        datePickerDialog = new TimePickerDialog.Builder()
                .setCallBack(this)
                .setCancelStringId("取消")
                .setSureStringId("确定")
                .setTitleStringId("预约日期")
                .setYearText("年")
                .setMonthText("月")
                .setDayText("日")
                .setHourText("时")
                .setMinuteText("分")
                .setCyclic(false)
                .setMinMillseconds(System.currentTimeMillis() + fourHour)
                .setMaxMillseconds(System.currentTimeMillis() + tenYears)
                .setCurrentMillseconds(System.currentTimeMillis() + fourHour)
                .setThemeColor(getResources().getColor(R.color.material_blue_600))
                .setType(Type.ALL)
                .setWheelItemTextNormalColor(getResources().getColor(R.color.timetimepicker_default_text_color))
                .setWheelItemTextSelectorColor(getResources().getColor(R.color.timepicker_toolbar_bg))
                .setWheelItemTextSize(12)
                .build();

    }

    @Override
    public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
        appoint_time.setText(getDateToString(sfDate, millseconds));
    }

    public String getDateToString(SimpleDateFormat sf, long time) {
        Date d = new Date(time);
        return sf.format(d);
    }


    //提交订单
    private void saveAppointmentOrder() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("car_frameno", service_car_frameno.getText().toString());
        map.put("car_no", service_car_carno.getText().toString());
        map.put("username", user_name.getText().toString());
        map.put("telephone", user_phone.getText().toString());
        map.put("appoint_time", appoint_time.getText().toString());
        map.put("service_item", service_item);
        map.put("description", input_describe.getText().toString());
        map.put("s_type", type);
        map.put("s_id", s_id);
        p.saveOrder(map);
    }


        //默认数据
    private void getAppointmentDefault() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        p.getAppointmentDefault(map);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.service_tag1:
                chanceMode(service_tag1);
                service_item = service_tag1.getText().toString();
                break;
            case R.id.service_tag2:
                chanceMode(service_tag2);
                service_item = service_tag2.getText().toString();
                break;
            case R.id.service_tag3:
                chanceMode(service_tag3);
                service_item = service_tag3.getText().toString();
                break;
            case R.id.service_tag4:
                chanceMode(service_tag4);
                service_item = service_tag4.getText().toString();
                break;
            case R.id.service_tag5:
                chanceMode(service_tag5);
                service_item = service_tag5.getText().toString();
                break;
            case R.id.service_tag6:
                chanceMode(service_tag6);
                service_item = service_tag6.getText().toString();
                break;
        }
    }

    private void chanceMode(SelectTextView view) {
        if (view.getTag() == null || String.valueOf(view.getTag()).equals("1")) {
            view.toggle(false);
            view.setTag("2");
        } else {
            view.toggle(true);
            view.setTag("1");
        }
    }
}
