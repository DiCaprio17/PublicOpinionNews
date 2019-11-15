package com.demo.publicopinionnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.publicopinionnews.ui.BaseMainFragment;
import com.demo.publicopinionnews.ui.fragment.account.LoginFragment;
import com.demo.publicopinionnews.ui.fragment.discover.DiscoverFragment;
import com.demo.publicopinionnews.ui.fragment.home.HomeFragment;
import com.demo.publicopinionnews.ui.fragment.shop.ShopFragment;
import com.demo.publicopinionnews.ui.fragment_swipe_back.SwipeBackSampleFragment;

import cn.bmob.v3.Bmob;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends SupportActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseMainFragment.OnFragmentOpenDrawerListener
        , LoginFragment.OnLoginSuccessListener, SwipeBackSampleFragment.OnLockDrawLayoutListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private TextView mTvName;   // NavigationView上的名字
    private ImageView mImgNav;  // NavigationView上的头像

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "dd4c63ac45326ba15f7b6ea5cfa5c648");
        setContentView(com.demo.publicopinionnews.R.layout.activity_main);

        if (savedInstanceState == null) {
            start(HomeFragment.newInstance());
        }

        initView();
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        // 设置默认Fragment动画  默认竖向(和安卓5.0以上的动画相同)
        return super.onCreateFragmentAnimator();
        // 设置横向(和安卓4.x动画相同)
//        return new DefaultHorizontalAnimator();
        // 设置自定义动画
//        return new FragmentAnimator(enter,exit,popEnter,popExit);
    }

    private void initView() {
        mDrawer = (DrawerLayout) findViewById(com.demo.publicopinionnews.R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, com.demo.publicopinionnews.R.string.navigation_drawer_open, com.demo.publicopinionnews.R.string.navigation_drawer_close);
//        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(com.demo.publicopinionnews.R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(com.demo.publicopinionnews.R.id.nav_home);

        LinearLayout llNavHeader = (LinearLayout) mNavigationView.getHeaderView(0);
        mTvName = (TextView) llNavHeader.findViewById(com.demo.publicopinionnews.R.id.tv_name);
        mImgNav = (ImageView) llNavHeader.findViewById(com.demo.publicopinionnews.R.id.img_nav);
        llNavHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawer(GravityCompat.START);

                mDrawer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        goLogin();
                    }
                }, 250);
            }
        });
    }

    @Override
    public int setContainerId() {
        return com.demo.publicopinionnews.R.id.fl_container;
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {

            Fragment topFragment = getTopFragment();

            // 主页的Fragment
            if (topFragment instanceof DiscoverFragment || topFragment instanceof ShopFragment) {
                mNavigationView.setCheckedItem(com.demo.publicopinionnews.R.id.nav_home);
            }
            super.onBackPressed();
        }
    }

    /**
     * 打开抽屉
     */
    @Override
    public void onOpenDrawer() {
        if (!mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {
        mDrawer.closeDrawer(GravityCompat.START);

        mDrawer.postDelayed(new Runnable() {
            @Override
            public void run() {
                int id = item.getItemId();

                final SupportFragment topFragment = getTopFragment();

                if (id == com.demo.publicopinionnews.R.id.nav_home) {

                    HomeFragment fragment = findFragment(HomeFragment.class);
                    Bundle newBundle = new Bundle();
                    newBundle.putString("from", "主页-->来自:" + topFragment.getClass().getSimpleName());
                    fragment.putNewBundle(newBundle);

                    start(fragment, SupportFragment.SINGLETASK);
                } else if (id == com.demo.publicopinionnews.R.id.nav_discover) {
                    DiscoverFragment fragment = findFragment(DiscoverFragment.class);
                    if (fragment == null) {
                        popTo(HomeFragment.class, false, new Runnable() {
                            @Override
                            public void run() {
                                start(DiscoverFragment.newInstance());
                            }
                        });
                    } else {
                        // 如果已经在栈内,则以SingleTask模式start
                        start(fragment, SupportFragment.SINGLETASK);
                    }
                } else if (id == com.demo.publicopinionnews.R.id.nav_msg) {
                    ShopFragment fragment = findFragment(ShopFragment.class);
                    if (fragment == null) {
                        popTo(HomeFragment.class, false, new Runnable() {
                            @Override
                            public void run() {
                                start(ShopFragment.newInstance());
                            }
                        });
                    } else {
                        // 如果已经在栈内,则以SingleTask模式start
//                        start(fragment, SupportFragment.SINGLETASK);
                        popTo(ShopFragment.class, false);
                    }
                } else if (id == com.demo.publicopinionnews.R.id.nav_login) {
                    goLogin();
                } else if (id == com.demo.publicopinionnews.R.id.nav_swipe_back) {
                    startActivity(new Intent(MainActivity.this, SwipeBackSampleActivity.class));
                } else if (id == com.demo.publicopinionnews.R.id.nav_swipe_back_f) {
                    start(SwipeBackSampleFragment.newInstance());
                }
            }
        }, 250);

        return true;
    }

    private void goLogin() {
        start(LoginFragment.newInstance());
    }

    @Override
    public void onLoginSuccess(String account) {
        mTvName.setText(account);
        mImgNav.setImageResource(com.demo.publicopinionnews.R.drawable.ic_account_circle_white_48dp);
        Toast.makeText(this, "登录成功,NavigationView的用户名已经更改!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLockDrawLayout(boolean lock) {
        if (lock) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        } else {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }
}
