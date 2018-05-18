package com.zichen.frame.base;

import android.os.Bundle;
import android.text.TextUtils;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.zichen.frame.entity.event.NormalEvent;
import com.zichen.frame.util.ActivityStackManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public abstract class BaseFragmentActivity extends QMUIFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        ActivityStackManager.getInstance().addActivity(new WeakReference<>(this));
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeActivity(new WeakReference<>(this));
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(NormalEvent event) {
        // 没有此方法EventBus注册时会报错
    }

    protected void showToastySuccess(CharSequence sequence) {
        Toasty.success(this, sequence).show();
    }

    protected void showToastyError(CharSequence sequence) {
        Toasty.error(this, sequence).show();
    }

    protected void showToastyNormal(CharSequence sequence) {
        Toasty.normal(this, sequence).show();
    }

    protected boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

}
