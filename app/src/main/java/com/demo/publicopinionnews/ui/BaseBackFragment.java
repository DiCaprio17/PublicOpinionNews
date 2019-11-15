package com.demo.publicopinionnews.ui;

import android.support.v7.widget.Toolbar;
import android.view.View;

public class BaseBackFragment extends BaseFragment {

    protected void initToolbarNav(Toolbar toolbar) {
        toolbar.setNavigationIcon(com.demo.publicopinionnews.R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });

        initToolbarMenu(toolbar);
    }
}
