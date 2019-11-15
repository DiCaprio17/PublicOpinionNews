package com.demo.publicopinionnews.ui.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.demo.publicopinionnews.ui.BaseBackFragment;
import com.demo.publicopinionnews.ui.fragment.CycleFragment;

public class ModifyDetailFragment extends BaseBackFragment {
    private static final String ARG_TITLE = "arg_title";

    private Toolbar mToolbar;
    private EditText mEtModiyTitle;
    private Button mBtnModify, mBtnNext;

    private String mTitle;

    public static ModifyDetailFragment newInstance(String title) {
        Bundle args = new Bundle();
        ModifyDetailFragment fragment = new ModifyDetailFragment();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mTitle = args.getString(ARG_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.demo.publicopinionnews.R.layout.fragment_modify_detail, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mToolbar = (Toolbar) view.findViewById(com.demo.publicopinionnews.R.id.toolbar);
        mEtModiyTitle = (EditText) view.findViewById(com.demo.publicopinionnews.R.id.et_modify_title);
        mBtnModify = (Button) view.findViewById(com.demo.publicopinionnews.R.id.btn_modify);
        mBtnNext = (Button) view.findViewById(com.demo.publicopinionnews.R.id.btn_next);

        mToolbar.setTitle("测试startForResult");
        initToolbarNav(mToolbar);

        mEtModiyTitle.setText(mTitle);

        // 显示 软键盘
        showSoftInput(mEtModiyTitle);

        mBtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("title", mEtModiyTitle.getText().toString());
                setFramgentResult(RESULT_OK, bundle);

                Toast.makeText(_mActivity, "修改成功!", Toast.LENGTH_SHORT).show();
            }
        });
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(CycleFragment.newInstance(1));
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            hideSoftInput();
        }
    }
}
