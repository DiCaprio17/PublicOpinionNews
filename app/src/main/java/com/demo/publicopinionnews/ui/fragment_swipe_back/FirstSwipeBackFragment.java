package com.demo.publicopinionnews.ui.fragment_swipe_back;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstSwipeBackFragment extends BaseSwipeBackFragment {
    private Toolbar mToolbar;

    public static FirstSwipeBackFragment newInstance() {

        Bundle args = new Bundle();

        FirstSwipeBackFragment fragment = new FirstSwipeBackFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(com.demo.publicopinionnews.R.layout.fragment_swipe_back_first, container, false);

        mToolbar = (Toolbar) view.findViewById(com.demo.publicopinionnews.R.id.toolbar);
        mToolbar.setTitle("关于此app");
        _initToolbar(mToolbar);

        view.findViewById(com.demo.publicopinionnews.R.id.tv_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(RecyclerSwipeBackFragment.newInstance());
            }
        });

        return attachToSwipeBack(view);
    }
}
