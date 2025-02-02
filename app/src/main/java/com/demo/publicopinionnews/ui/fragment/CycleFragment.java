package com.demo.publicopinionnews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.demo.publicopinionnews.ui.BaseBackFragment;

public class CycleFragment extends BaseBackFragment {
    private static final String ARG_NUMBER = "arg_number";

    private Toolbar mToolbar;
    private TextView mTvName;
    private Button mBtnNext, mBtnNextWithFinish;

    private int mNumber;

    public static CycleFragment newInstance(int number) {
        CycleFragment fragment = new CycleFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NUMBER, number);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mNumber = args.getInt(ARG_NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.demo.publicopinionnews.R.layout.fragment_cycle, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(com.demo.publicopinionnews.R.id.toolbar);
        mTvName = (TextView) view.findViewById(com.demo.publicopinionnews.R.id.tv_name);
        mBtnNext = (Button) view.findViewById(com.demo.publicopinionnews.R.id.btn_next);
        mBtnNextWithFinish = (Button) view.findViewById(com.demo.publicopinionnews.R.id.btn_next_with_finish);

        String title = "首页:循环Fragment" + mNumber;

        mToolbar.setTitle(title);
        initToolbarNav(mToolbar);

        mTvName.setText(title);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(CycleFragment.newInstance(mNumber + 1));
            }
        });
        mBtnNextWithFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWithPop(CycleFragment.newInstance(mNumber + 1));
            }
        });
    }
}
