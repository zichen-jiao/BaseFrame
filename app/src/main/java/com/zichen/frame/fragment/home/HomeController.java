package com.zichen.frame.fragment.home;

import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.FrameLayout;

import com.zichen.frame.base.BaseFragment;

import es.dmoral.toasty.Toasty;
/**
 * @作者 : zichen
 * @日期 : 2018/5/18
 * @注释 : 主界面Fragment的基类
 * @version: 1.0
 */
public abstract class HomeController extends BaseFragment {
    /**
     * 不允许滑动返回
     * @return
     */
    @Override
    protected boolean canDragBack() {
        return false;
    }
}
