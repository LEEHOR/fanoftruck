package com.coahr.fanoftruck.mvp.view.Myself;

import android.os.Bundle;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.widgets.NoScrollViewPager;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

public class AddInvoiceInfoFragment1 extends BaseFragment {
    @BindView(R.id.mytitle)
    MyTittleBar mytitle;
    @BindView(R.id.tabInvoiceType)
    TabLayout tabInvoiceType;
    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;

    private List<String> titles = Arrays.asList("个人", "企业");
    private List<SupportFragment> fragments = new ArrayList<>();

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_add_invoice_info_1;
    }

    public AddInvoiceInfoFragment1 newInstance(int type){
        return new AddInvoiceInfoFragment1();
    }

    @Override
    public void initView() {
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(InvoiceInfoFragment.newInstance(i));
        }
        InvoicePagerAdapter pageAdapter = new InvoicePagerAdapter(_mActivity.getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        viewPager.setOffscreenPageLimit(titles.size());
        tabInvoiceType.setupWithViewPager(viewPager);
    }

    @Override
    public void initData() {

    }

    private class InvoicePagerAdapter extends FragmentPagerAdapter{

        public InvoicePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return titles == null? 0: titles.size();
        }
    }
}