package com.demo.publicopinionnews.ui.fragment.shop;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.demo.publicopinionnews.ui.BaseFragment;
import com.demo.publicopinionnews.ui.fragment.CycleFragment;

import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class ContentFragment extends BaseFragment {
    private static final String ARG_MENU = "arg_menu";

    private TextView mTvContent;
    private Button mBtnNext;

    private String mMenu;

    public static ContentFragment newInstance(String menu) {

        Bundle args = new Bundle();
        args.putString(ARG_MENU, menu);

        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            mMenu = args.getString(ARG_MENU);
        }
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimation() {
        return new DefaultNoAnimator();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.demo.publicopinionnews.R.layout.fragment_content, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTvContent = (TextView) view.findViewById(com.demo.publicopinionnews.R.id.tv_content);
        mBtnNext = (Button) view.findViewById(com.demo.publicopinionnews.R.id.btn_next);

        mTvContent.setText("Fragment内容:\n" + mMenu);

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 和MsgFragment同级别的跳转 交给MsgFragment处理
                if (getParentFragment() instanceof ShopFragment) {
                    ((ShopFragment) getParentFragment()).start(CycleFragment.newInstance(1));
                }
            }
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        // ContentFragment是ShopFragment的栈顶子Fragment,可以在此处理返回按键事件
        return super.onBackPressedSupport();
    }
}
