package com.demo.publicopinionnews.ui;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import me.yokeyword.fragmentation.SupportFragment;

public class BaseFragment extends SupportFragment {
    private static final String TAG = "Fragmentation";

    protected void initToolbarMenu(Toolbar toolbar) {
        toolbar.inflateMenu(com.demo.publicopinionnews.R.menu.hierarchy);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case com.demo.publicopinionnews.R.id.action_hierarchy:
                        _mActivity.showFragmentStackHierarchyView();
                        _mActivity.logFragmentStackHierarchy(TAG);
                        break;
                }
                return true;
            }
        });
    }
}
