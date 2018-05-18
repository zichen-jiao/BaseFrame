package com.zichen.frame.activity;

import android.app.Fragment;
import android.os.Bundle;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.zichen.frame.R;
import com.zichen.frame.base.BaseFragment;
import com.zichen.frame.base.BaseFragmentActivity;
import com.zichen.frame.fragment.home.HomeController;
import com.zichen.frame.fragment.home.HomeFragment;

public class MainActivity extends BaseFragmentActivity {

    @Override
    protected int getContextViewId() {
        return R.id.qmuidemo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            BaseFragment fragment = new HomeFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(getContextViewId(), fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    private long mLastClickTime;

    @Override
    public void onBackPressed() {
        QMUIFragment fragment = getCurrentFragment();
        if (null != fragment && fragment instanceof HomeFragment) {
            long currTime = System.currentTimeMillis();
            if (currTime - mLastClickTime > 2000) {
                showToastyNormal(getString(R.string.click_again_to_exist));
                mLastClickTime = currTime;
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }
}
