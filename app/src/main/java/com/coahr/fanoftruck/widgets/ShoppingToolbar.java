package com.coahr.fanoftruck.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.coahr.fanoftruck.R;

/**
 * Created by Leehor
 * on 2018/11/19
 * on 19:24
 */
public class ShoppingToolbar extends LinearLayout {

    private View view;

    public ShoppingToolbar(Context context) {
        super(context,null);
    }

    public ShoppingToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        view = LayoutInflater.from(context).inflate(R.layout.layout_shopping_toolbar, this);
    }
    /**
     * 获取搜索栏
     */

    public TextView getTvSearch()
    {
        return (TextView) view.findViewById(R.id.tv_search);
    }

    public TextView getTvSearch(int resId)
    {
        return (TextView) view.findViewById(resId);
    }

    /**
     * 获取搜索图片
     */

    public TextView getTvSearch_IV()
    {
        return (TextView) view.findViewById(R.id.tv_search_t);
    }

    public TextView getTvSearch_IV(int resId)
    {
        return (TextView) view.findViewById(resId);
    }

    /**
     * 获取购物车
     */

    public TextView getTvShoppingCar()
    {
        return (TextView) view.findViewById(R.id.tv_shoppingCar);
    }

    public TextView getTvShoppingCar(int resId)
    {
        return (TextView) view.findViewById(resId);
    }
}
