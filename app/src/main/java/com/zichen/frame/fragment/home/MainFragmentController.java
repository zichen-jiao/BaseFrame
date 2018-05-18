package com.zichen.frame.fragment.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.zichen.frame.R;
import com.zichen.frame.fragment.DetailFragment;
import com.zichen.frame.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @作者 : zichen
 * @日期 : 2018/5/18
 * @注释 : 主界面中我的标签页的Fragment实现
 * @version: 1.0
 */
public class MainFragmentController extends HomeController {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.txt_main_tab_layout)
    TextView txtMainTabLayout;


    private void initTopBar() {
        topbar.setTitle("我的");
        topbar.addRightTextButton("我的", R.id.topbar_right_message_button).setOnClickListener(v -> {
            showToastyNormal("我的");
            startFragment(DetailFragment.newInstance("我的详情"));
        });
    }

    public static MainFragmentController newInstance() {
        Bundle args = new Bundle();
        MainFragmentController fragment = new MainFragmentController();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initViews() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main_tab_layout, null);
        ButterKnife.bind(this, root);
        initTopBar();
        return root;
    }

    @Override
    protected void initData() {
        LogUtils.d("我的：initData");
    }

}
