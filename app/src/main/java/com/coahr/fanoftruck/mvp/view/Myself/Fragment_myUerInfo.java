package com.coahr.fanoftruck.mvp.view.Myself;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.LoadCity.JsonBean;
import com.coahr.fanoftruck.Utils.LoadCity.JsonFileReader;
import com.coahr.fanoftruck.Utils.PreferenceUtils;
import com.coahr.fanoftruck.Utils.StoreSpaceUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_userInfo_C;
import com.coahr.fanoftruck.mvp.model.Bean.Center_Initial_Data;
import com.coahr.fanoftruck.mvp.model.Bean.LoginOutBean;
import com.coahr.fanoftruck.mvp.model.Bean.Save_Identity_Info;
import com.coahr.fanoftruck.mvp.presenter.Fragment_UserInfo_P;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.google.gson.Gson;
import com.socks.library.KLog;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 18:06
 */
public class Fragment_myUerInfo extends BaseFragment<Fragment_userInfo_C.Presenter> implements Fragment_userInfo_C.View, View.OnClickListener {
   @Inject
    Fragment_UserInfo_P p;
   @BindView(R.id.tv_certification)
    TextView tv_certification;
    @BindView(R.id.iv_user_head)
    ImageView iv_user_head;
    @BindView(R.id.ed_user_nickname)
    EditText ed_user_nickname;
    @BindView(R.id.rg_radioGroup)
    RadioGroup rg_radioGroup;
    @BindView(R.id.rb_sex_man)
    RadioButton rb_sex_man;
    @BindView(R.id.rb_sex_woman)
    RadioButton rb_sex_woman;
    @BindView(R.id.ed_user_phone)
    EditText ed_user_phone;
    @BindView(R.id.tv_select_address)
    TextView tv_select_address;
    @BindView(R.id.tv_postal_address)
    TextView tv_postal_address;
    @BindView(R.id.ed_detail_address)
    EditText ed_detail_address;
    @BindView(R.id.userInfo_title)
    MyTittleBar userInfo_title;
    @BindView(R.id.tv_logout)
    TextView tv_logout;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded;
    private String sex = "1";
    private String pic1, pic2;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (!isLoaded) {
                        if (thread == null) {//如果已创建就不再重新创建子线程了
                            thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    getJson();
                                }
                            });
                            thread.start();
                        }
                    } else {
                        showCityPickerView();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    showCityPickerView();
                    break;
                case MSG_LOAD_FAILED:
                    isLoaded = false;
                    break;
            }
        }
    };
    private String originalPath;

    public static Fragment_myUerInfo newInstance() {
  Fragment_myUerInfo uerInfo=new Fragment_myUerInfo();
  return uerInfo;
 }

 @Override
    public Fragment_userInfo_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_userinfo;
    }

    @Override
    public void initView() {
        tv_certification.setOnClickListener(this);
        tv_select_address.setOnClickListener(this);
        iv_user_head.setOnClickListener(this);
        tv_logout.setOnClickListener(this);

        userInfo_title.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        userInfo_title.getRightText().setText("保存");
        userInfo_title.getRightText().setVisibility(View.VISIBLE);
        userInfo_title.getRightText().setTextColor(getResources().getColor(R.color.origin_1));
        userInfo_title.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasLogin()) {
                    saveInfoMassage();
                } else {
                    new MaterialDialog.Builder(_mActivity)
                            .title("提示")
                            .content("您当前未登录")
                            .negativeText("取消")
                            .positiveText("去登陆")
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                }
                            }).onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            start(Fragment_login.newInstance(Constants.Fragment_myUserInfo));
                        }
                    }).build().show();
                }
            }
        });

        rg_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton button = rg_radioGroup.findViewById(i);

                if (!button.isPressed()) {
                    return;
                }
                if (rb_sex_man.isChecked()) {
                    sex = "1";
                }
                if (rb_sex_woman.isChecked()) {
                    sex = "2";
                }
            }
        });
    }

    @Override
    public void initData() {
        getInfoMassage();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_certification:
                start(Fragment_certification.newInstance(pic1,pic2));
                break;
            case R.id.tv_select_address:
                mHandler.sendEmptyMessage(MSG_LOAD_DATA);
                break;
            case R.id.iv_user_head:
                openImage();
                break;
            case R.id.tv_logout:
                if (hasLogin()) {
                    logout();
                } else {
                    ToastUtils.showLong("已经退出登录");
                }
                break;
        }
    }

    @Override
    public void getCenter_Initial_DataSuccess(Center_Initial_Data center_initial_data) {
        KLog.d("lizhiguo", "data = " + center_initial_data.toString());
        if (center_initial_data != null) {
            Center_Initial_Data.JdataBean.UserBean user = center_initial_data.getJdata().getUser();
            if (user != null) {
                String userheadimg = user.getUserheadimg();
                if (userheadimg != null) {
                    Imageloader.loadCircularImage(userheadimg, iv_user_head);
                }

                String username = user.getUsername();
                if (user != null) {
                    ed_user_nickname.setText(username);
                }
                String sex = user.getSex();
                if (sex != null) {
                    if (sex.equals("1")) {
                        rb_sex_man.toggle();
                    } else if (sex.equals("2")) {
                        rb_sex_woman.toggle();
                    } else {
                        rb_sex_man.toggle();
                    }
                }
                String phone = user.getPhone();
                if (phone != null) {
                    ed_user_phone.setText(phone);
                }

                String postal_address = user.getPostal_address();
                if (postal_address != null) {
                    tv_postal_address.setText(postal_address);
                }
                String detail_address = user.getDetail_address();
                if (detail_address != null) {
                    ed_detail_address.setText(detail_address);
                }

                pic1 = user.getPic1();

                pic2 = user.getPic2();

                if (pic1 !=null && pic2 !=null){
                    tv_certification.setVisibility(View.GONE);
                }
            }


        }
    }

    @Override
    public void getCenter_Initial_DataFailure(String failure) {

    }

    @Override
    public void Save_Identity_InfoSuccess(Save_Identity_Info save_identity_info) {
        ToastUtils.showLong(save_identity_info.getJdata().getJmsg());
    }

    @Override
    public void Save_Identity_InfoFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    @Override
    public void LoginOutSuccess(LoginOutBean loginOutBean) {
                ToastUtils.showLong(loginOutBean.getJdata().getJmsg());
        PreferenceUtils.setPrefString(BaseApplication.mContext,Constants.token_key,null);
        PreferenceUtils.setPrefString(BaseApplication.mContext,Constants.uid_key,null);
        Constants.token=null;
        Constants.uid=null;
    }

    @Override
    public void LoginOutFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    /**
     * 解析json
     */
    public void getJson() {
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = JsonFileReader.getJson(BaseApplication.mContext, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 解析Gson
     *
     * @param result
     * @return
     */
    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    /**
     * 城市
     */
    private void showCityPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(getActivity(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                tv_postal_address.setText(tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    /**
     * 保存数据
     */
    private void saveInfoMassage() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        originalPath = TextUtils.isEmpty(originalPath) ? "" : StoreSpaceUtils.getImageStr(originalPath);
        map.put("userheadimg", originalPath);
        map.put("nickname", ed_user_nickname.getText().toString());
        map.put("sex", sex);
        map.put("postal_address", tv_postal_address.getText().toString());
        map.put("detail_address", ed_detail_address.getText().toString());
        p.Save_Identity_Info(map);
    }

    /**
     * 登出
     */
    private void logout() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        p.LoginOut(map);
    }

    /**
     * 初始化数据
     */
    private void getInfoMassage() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        p.getCenter_Initial_Data(map);
    }

    /**
     * 身份证拍照
     */
    private void openImage() {
        //快速打开单选图片,flag使用true不裁剪
        RxGalleryFinalApi
                .openRadioSelectImage(_mActivity, new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent o) throws Exception {
                        originalPath = o.getResult().getOriginalPath();
                        KLog.d("头像",originalPath);
                        Imageloader.loadCircularImage(originalPath, iv_user_head);
                    }
                }, true);
    }
}
