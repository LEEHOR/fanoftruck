package com.coahr.fanoftruck.mvp.view.Myself;

import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.LoadCity.JsonBean;
import com.coahr.fanoftruck.Utils.LoadCity.JsonFileReader;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.google.gson.Gson;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class AddInvoiceInfoFragment2 extends BaseFragment {
    @BindView(R.id.mytitle)
    MyTittleBar mytitle;
    @BindView(R.id.rb_personal)
    RadioButton rbPersonal;
    @BindView(R.id.rb_company)
    RadioButton rbCompany;
    @BindView(R.id.rg_radioGroup)
    RadioGroup rgRadioGroup;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_user_idcard)
    EditText etUserIdcard;
    @BindView(R.id.tv_district)
    TextView tvDistrict;
    @BindView(R.id.et_user_address)
    EditText etUserAddress;
    @BindView(R.id.et_user_phone)
    EditText etUserPhone;
    @BindView(R.id.tv_invoice_type)
    TextView tvInvoiceType;

    //发票类型
    private String type = "1";

    private boolean isLoaded = false;

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_add_invoice_info_2;
    }

    @Override
    public void initView() {
        initTitle();

        rgRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton button = rgRadioGroup.findViewById(i);

                if (!button.isPressed()) {
                    return;
                }
                if (rbPersonal.isChecked()) {
                    type = "1";
                }
                if (rbCompany.isChecked()) {
                    type = "2";
                }
            }
        });
    }

    private void initTitle() {
        mytitle.getLeftIcon().setOnClickListener(titleClickListener);

        TextView rightText = mytitle.getRightText();
        rightText.setVisibility(View.VISIBLE);
        rightText.setText("添加");
        rightText.setTextColor(Color.parseColor("#1C54BC"));
        rightText.setOnClickListener(titleClickListener);
    }

    private View.OnClickListener titleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_left:
                    _mActivity.onBackPressed();
                    break;
                case R.id.tv_right:
                    break;
            }
        }
    };

    @Override
    public void initData() {

    }

    @OnClick({R.id.rl_district, R.id.rl_invoice_type, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_district:
                if (!isLoaded) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            getJson();
                        }
                    });
                } else {
                    showCityPickerView();
                }
                break;
            case R.id.rl_invoice_type:
                break;
            case R.id.tv_submit:
                break;
        }
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

        isLoaded = true;
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
            isLoaded = false;
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
                tvDistrict.setText(tx);
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
}
