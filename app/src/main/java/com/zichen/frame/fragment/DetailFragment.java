package com.zichen.frame.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.zichen.frame.R;
import com.zichen.frame.base.BaseFragment;
import com.zichen.frame.util.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends BaseFragment {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    String title = "";

    private void initTopBar() {
        topbar.addLeftBackImageButton().setOnClickListener(v -> {
            popBackStack();
        });
        topbar.setTitle(TextUtils.isEmpty(title) ? "详情页" : title);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    protected View initViews() {
        Bundle args = getArguments();
        title = args.getString("title");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_detail, null);
        ButterKnife.bind(this, view);
        initTopBar();

        return view;
    }

    @Override
    protected void initData() {
        LogUtils.d("详情：initData");
    }

    public static DetailFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
