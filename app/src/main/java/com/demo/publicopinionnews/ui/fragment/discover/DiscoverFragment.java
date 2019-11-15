package com.demo.publicopinionnews.ui.fragment.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.publicopinionnews.adapter.DiscoverFragmentAdapter;
import com.demo.publicopinionnews.ui.BaseMainFragment;

public class DiscoverFragment extends BaseMainFragment {

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.demo.publicopinionnews.R.layout.fragment_discover, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        Toolbar mToolbar = (Toolbar) view.findViewById(com.demo.publicopinionnews.R.id.toolbar);
        TabLayout mTabLayout = (TabLayout) view.findViewById(com.demo.publicopinionnews.R.id.tab_layout);
        ViewPager mViewPager = (ViewPager) view.findViewById(com.demo.publicopinionnews.R.id.viewPager);

        mToolbar.setTitle("发现");
        initToolbarNav(mToolbar);

        mTabLayout.addTab(mTabLayout.newTab().setText(com.demo.publicopinionnews.R.string.recommend));
        mTabLayout.addTab(mTabLayout.newTab().setText(com.demo.publicopinionnews.R.string.hot));
        mTabLayout.addTab(mTabLayout.newTab().setText(com.demo.publicopinionnews.R.string.favorite));
        mViewPager.setAdapter(new DiscoverFragmentAdapter(getChildFragmentManager()));
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
