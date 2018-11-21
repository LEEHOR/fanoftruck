package com.coahr.fanoftruck.widgets.PopupWindows;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ScreenUtils;
import com.coahr.fanoftruck.Utils.SideBar;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.model.Bean.CityBean;
import com.coahr.fanoftruck.mvp.view.decoration.RecyclerSimplePadding_color;
import com.coahr.fanoftruck.mvp.view.decoration.RecyclerViewItemDecoration_StickyHeader;
import com.socks.library.KLog;

import java.util.List;

import static android.widget.LinearLayout.*;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 18:42
 */
public class PopupWindows_city {
    private getCity listener;
    private RecyclerView recycler_ct;
    private SideBar sidebar;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerViewCity_adapter recyclerView_City_adapter;
    private List<CityBean> sortEntity;
    private TextView text_dialog;
    private int measuredHeight2;
    private int measuredHeight1;
    private boolean isMenuOpen;
    private View inflate;
    private PopupWindow window;
    private int i;
    private View view;
    private int xoff;
    private int yoff;
    private Recycler_sort_adapter recycler_sort_adapter;
    private LinearLayoutManager linearLayoutManager1;

    public void showPopupWindows( View view, int xoff, int yoff) {
        this.view = view;
        this.xoff = xoff;
        this.yoff = yoff;
        inflate = LayoutInflater.from(BaseApplication.mContext).inflate(R.layout.layout_popupwindows, null);
        recycler_ct = inflate.findViewById(R.id.cp_city_recyclerview);
        sidebar = inflate.findViewById(R.id.cp_side_index_bar);
        text_dialog = inflate.findViewById(R.id.textdialog);
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        linearLayoutManager1 = new LinearLayoutManager(BaseApplication.mContext);

        recyclerView_City_adapter = new RecyclerViewCity_adapter(BaseApplication.mContext);
        recycler_sort_adapter = new Recycler_sort_adapter();

        sidebar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onHit(String letter) {
                text_dialog.setVisibility(View.VISIBLE);
                text_dialog.setText(letter);
                if (sortEntity == null || sortEntity.isEmpty()) return;
                if (TextUtils.isEmpty(letter)) return;
                int size = sortEntity.size();
                for (int i = 0; i < size; i++) {
                    if (TextUtils.equals(letter.substring(0, 1), sortEntity.get(i).getCity_code().substring(0, 1).toUpperCase())) {
                        if (linearLayoutManager != null) {
                            linearLayoutManager.scrollToPositionWithOffset(i, 0);
                            return;
                        }
                    }
                }
            }

            @Override
            public void onCancel() {
                text_dialog.setVisibility(GONE);
            }
        });
        recycler_ct.addItemDecoration(new RecyclerSimplePadding_color(BaseApplication.mContext));

        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点

        getPopupWindow();
    }
    public PopupWindows_city setCityList(List<CityBean> list, int selectedPosition) {
        sidebar.setVisibility(View.VISIBLE);
        recycler_ct.setLayoutManager(linearLayoutManager);
        recycler_ct.setAdapter(recyclerView_City_adapter);
        sortEntity = CityBean.getSortEntity(list);
        if (recycler_ct.getItemDecorationCount() == 1){
        recycler_ct.addItemDecoration(new RecyclerViewItemDecoration_StickyHeader(BaseApplication.mContext, new RecyclerViewItemDecoration_StickyHeader.DecorationCallback() {
            @Override
            public long getGroupId(int position) {
                return Character.toUpperCase(sortEntity.get(position).getCity_code().charAt(0));
            }

            @Override
            public String getGroupFirstLine(int position) {
                return sortEntity.get(position).getCity_code().substring(0, 1).toUpperCase();
            }
        }));
        recyclerView_City_adapter.setData(sortEntity);
        recyclerView_City_adapter.setListener(new RecyclerViewCity_adapter.getCity_adapter() {
            @Override
            public void getCity_adapter(String string) {
                if (listener != null) {
                    if (window.isShowing()) {
                        window.dismiss();
                    }
                    listener.getCity(string);
                }
            }
        });
        }
        return this;
    }

    public PopupWindows_city setSortList(final List<String> list, int selectedPosition, final int type) {
        sidebar.setVisibility(View.GONE);
        recycler_ct.setLayoutManager(linearLayoutManager1);
        recycler_ct.setAdapter(recycler_sort_adapter);
        if (recycler_ct.getItemDecorationCount()==2){
            recycler_ct.removeItemDecorationAt(1);
        }
        recycler_sort_adapter.setData(list,selectedPosition);
            recycler_sort_adapter.setListener(new Recycler_sort_adapter.getSort_adapter() {
                @Override
                public void getSort_adapter(String string,int position) {
                    if (listener != null) {
                        listener.getSort(string,type,position);
                    }
                }
            });
        return this;
    }
    public PopupWindow getPopupWindow() {
        // 设置PopupWindow的背景
        window = new PopupWindow(inflate);
        window.setWidth(ScreenUtils.getScreenWidth(BaseApplication.mContext));
        window.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        KLog.d("高度", ScreenUtils.getScreenHeight(BaseApplication.mContext), i);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(false);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setFocusable(false);
        window.setAnimationStyle(R.style.Commodity_Condition_Popupwindow_Animation);
        // 显示PopupWindow，其中：
        // 第一个参数是PopupWindow的锚点，第二和第三个参数分别是PopupWindow相对锚点的x、y偏移

        // 或者也可以调用此方法显示PopupWindow，其中：
        // 第一个参数是PopupWindow的父View，第二个参数是PopupWindow相对父View的位置，
        // 第三和第四个参数分别是PopupWindow相对父View的x、y偏移
        // window.showAtLocation(view, Gravity.BOTTOM, xoff, yoff);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                for (int j = 0; j < recycler_ct.getItemDecorationCount() ; j++) {
                    if (j==1){
                        recycler_ct.removeItemDecorationAt(1);
                    }
                }
                if (listener != null) {
                    listener.dismiss();
                }
                isMenuOpen = false;
            }
        });
        return window;
    }

    public void setListener(getCity listener) {
        this.listener = listener;
    }

    public interface getCity {
        void getCity(String city);
        void  getSort(String sort,int type,int position);
        void  dismiss();
    }

    public boolean isMenuOpen() {
        return isMenuOpen;
    }

    public void dismiss() {
        if (isMenuOpen) {
            window.dismiss();
        }
    }

    public void showAsDropDown() {//以类似dropdown形式展示
        if (window != null && !window.isShowing()) {
            window.showAsDropDown(view, xoff, yoff);
            isMenuOpen = true;
        }
    }

}
