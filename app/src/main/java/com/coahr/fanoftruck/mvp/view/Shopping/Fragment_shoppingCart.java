package com.coahr.fanoftruck.mvp.view.Shopping;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.ValidateUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_ShoppingCart_C;
import com.coahr.fanoftruck.mvp.model.Bean.DelFormShoppingCart;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingCart;
import com.coahr.fanoftruck.mvp.presenter.Fragment_ShoppingCart_P;
import com.coahr.fanoftruck.mvp.view.ConfirmCommodityOrder.Fragment_confirmCommodityOrder;
import com.coahr.fanoftruck.mvp.view.Shopping.adapter.ShoppingCartAdapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.socks.library.KLog;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 12:54
 * 购物车
 */
public class Fragment_shoppingCart extends BaseFragment<Fragment_ShoppingCart_C.Presenter> implements Fragment_ShoppingCart_C.View {
    @Inject
    Fragment_ShoppingCart_P p;
    @BindView(R.id.my_shopping_car_tittle)
    MyTittleBar my_shopping_car_tittle;
    @BindView(R.id.my_shopping_car_swipe)
    SwipeRefreshLayout my_shopping_car_swipe;
    @BindView(R.id.my_shopping_car_recycler)
    RecyclerView my_shopping_car_recycler;
    @BindView(R.id.tv_check)
    CheckBox tv_check;     //选择按钮
    @BindView(R.id.tv_settlement)
    TextView tv_settlement;  //提交结算
    @BindView(R.id.tv_totalprice)
    TextView tv_totalprice;  //总数
    @BindView(R.id.text0_he)
    TextView text0;    //合计（文字）
    @BindView(R.id.text1_push_money)
    TextView text1; //不含运费（文字）
    private boolean isLoading;
    private boolean isDelete;
    private boolean isError=false;
    private int Right_type = 1; //控制删除状态
    private LinearLayoutManager linearLayout;
    private ShoppingCartAdapter shoppingCartAdapter;
    private float total_textPrice; //底部显示
    private String orderConfirm = "";
    //是否全选
    private boolean isCheckedAll = false;

    public static Fragment_shoppingCart newInstance() {
        return new Fragment_shoppingCart();
    }

    @Override
    public Fragment_ShoppingCart_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_shoppingcart;
    }

    @Override
    public void initView() {
        my_shopping_car_tittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        my_shopping_car_tittle.getRightText().setVisibility(View.VISIBLE);
        my_shopping_car_tittle.getRightText().setText("编辑");
        my_shopping_car_tittle.getRightText().setTextColor(getResources().getColor(R.color.material_blue_550));
        my_shopping_car_tittle.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (my_shopping_car_tittle.getRightText().getTag() == null || my_shopping_car_tittle.getRightText().getTag().equals("1")) {
                    my_shopping_car_tittle.getRightText().setText("删除");
                    my_shopping_car_tittle.getRightText().setTextColor(getResources().getColor(R.color.red_1));
                    my_shopping_car_tittle.getRightText().setTag("2");
                    text0.setVisibility(View.INVISIBLE);
                    text1.setVisibility(View.INVISIBLE);
                    tv_totalprice.setVisibility(View.INVISIBLE);
                    tv_settlement.setText("删除(0)");
                    isDelete = true;
                    Right_type = 2;
                } else if (my_shopping_car_tittle.getRightText().getTag().equals("2")) {
                    my_shopping_car_tittle.getRightText().setText("编辑");
                    my_shopping_car_tittle.getRightText().setTextColor(getResources().getColor(R.color.material_blue_550));
                    my_shopping_car_tittle.getRightText().setTag("1");
                    text0.setVisibility(View.VISIBLE);
                    text1.setVisibility(View.VISIBLE);
                    tv_totalprice.setVisibility(View.VISIBLE);
                    tv_settlement.setText("结算(0)");
                    Right_type = 1;
                    isDelete = false;
                }
                shoppingCartAdapter.unCheckAll();

                //点击后，更新状态
                isCheckedAll = false;
            }
        });

        my_shopping_car_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading && !isDelete) {
                    isLoading=true;
                    getShoppingCarList();
                } else {
                    my_shopping_car_swipe.setRefreshing(false);
                }
            }
        });
        linearLayout = new LinearLayoutManager(BaseApplication.mContext);
        shoppingCartAdapter = new ShoppingCartAdapter();
        my_shopping_car_recycler.setLayoutManager(linearLayout);
        my_shopping_car_recycler.setAdapter(shoppingCartAdapter);
        my_shopping_car_recycler.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 4), getResources().getColor(R.color.material_grey_250)));
        for (int i = 0; i < my_shopping_car_recycler.getItemDecorationCount(); i++) {
            if (i != 0) {
                my_shopping_car_recycler.removeItemDecorationAt(i);
            }
        }
        shoppingCartAdapter.setOnSelectChangeListener(new ShoppingCartAdapter.onSelectChangeListener() {
            @Override
            public void onChange(boolean Error) {
                isError=Error;
                total_textPrice = 0.0f;
                for (ShoppingCart.JdataBean.CommodityBean entity : shoppingCartAdapter.getmSelectedPositions()) {
                    total_textPrice += Float.parseFloat(entity.getC_price()) * Float.parseFloat(entity.getC_num());
                }
                String aDouble = ValidateUtils.getDouble(total_textPrice);
                tv_totalprice.setText(aDouble);

                if (shoppingCartAdapter.isAllSelected()) {
                    tv_check.setChecked(true);
                } else {
                    tv_check.setChecked(false);
                }
                if (Right_type == 1) {
                    tv_settlement.setText("结算(" + shoppingCartAdapter.getmSelectedPositions().size() + ")");
                }
                if (Right_type == 2) {
                    tv_settlement.setText("删除(" + shoppingCartAdapter.getmSelectedPositions().size() + ")");
                }
            }

        });

        tv_settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isError){
                    ToastUtils.showLong("商品数量不能为空");
                    return;
                }
                if (shoppingCartAdapter.getmSelectedPositions().size() == 0) {
                    Toast.makeText(BaseApplication.mContext, "请选择要操作商品", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < shoppingCartAdapter.getmSelectedPositions().size(); i++) {
                    ShoppingCart.JdataBean.CommodityBean commodityBean = shoppingCartAdapter.getmSelectedPositions().get(i);
                    if (i == 0) {
//                        c_id=154&num=2,c_id=159&num=3,
                        orderConfirm = ("c_id="+String.format("%s", commodityBean.getC_id()) + "&num="+ String.format("%s", commodityBean.getC_num()));
                    } else {
                        orderConfirm += (",c_id="+String.format("%s", commodityBean.getC_id()) + "&num="+ String.format("%s", commodityBean.getC_num()));
                    }
                }
                orderConfirm+=",";
                if (orderConfirm.equals(",")) {
                    ToastUtils.showLong("请选择要操作的商品");
                    return;
                }
                if (Right_type == 1) {  //结算
//                    tv_settlement.setText("结算(0)");
//                    shoppingCartAdapter.unCheckAll();
                    KLog.e("lizhiguo", "orderConfirm == " + orderConfirm.toString());
                    start(Fragment_confirmCommodityOrder.newInstance(orderConfirm, ""));
                }

                if (Right_type == 2) { //删除
                    deleteShoppingFormCart();
                }
            }
        });

        tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isCheckedAll = !isCheckedAll;
                tv_check.setChecked(isCheckedAll);
                if (isCheckedAll){
                    shoppingCartAdapter.checkAll();
                } else {
                    shoppingCartAdapter.unCheckAll();
                }
            }
        });
    }

    @Override
    public void initData() {
        getShoppingCarList();
    }

    @Override
    public void getShoppingListSuccess(ShoppingCart shoppingCart) {
        ToastUtils.showLong("获取列表成功");
        shoppingCartAdapter.setNewData(shoppingCart.getJdata().getCommodity());
        my_shopping_car_swipe.setRefreshing(false);
        isLoading=false;
    }

    @Override
    public void getShoppingListFailure(String failure) {
        ToastUtils.showLong(failure);
        my_shopping_car_swipe.setRefreshing(false);
        isLoading=false;
    }

    @Override
    public void delShoppingSuccess(DelFormShoppingCart delFormShoppingCart) {
        ToastUtils.showLong("删除成功");
        tv_settlement.setText("删除(0)");
        shoppingCartAdapter.unCheckAll();
        getShoppingCarList();
    }

    @Override
    public void delShoppingFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    /**
     * 获取列表
     */
    private void getShoppingCarList() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        p.getShoppingCarList(map);
    }

    /**
     * 删除购物车中的商品
     */
    private void deleteShoppingFormCart() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("c_id", orderConfirm);
        p.delShopping(map);
    }
}
