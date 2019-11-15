package com.demo.publicopinionnews;

import android.os.Bundle;

import com.demo.publicopinionnews.ui.fragment_swipe_back.FirstSwipeBackFragment;

import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import me.yokeyword.fragmentation_swipeback.SwipeBackActivity;

public class SwipeBackSampleActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.demo.publicopinionnews.R.layout.activity_swipe_back);

        if (savedInstanceState == null) {
            start(FirstSwipeBackFragment.newInstance());
        }

        getSwipeBackLayout().setEdgeOrientation(SwipeBackLayout.EDGE_ALL);
    }

    @Override
    protected int setContainerId() {
        return com.demo.publicopinionnews.R.id.fl_container;
    }

    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }
}
