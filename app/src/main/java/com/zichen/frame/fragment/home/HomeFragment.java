package com.zichen.frame.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUIPagerAdapter;
import com.qmuiteam.qmui.widget.QMUITabSegment;
import com.qmuiteam.qmui.widget.QMUIViewPager;
import com.zichen.frame.R;
import com.zichen.frame.base.BaseFragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends BaseFragment {
    //<editor-fold desc="全局变量 ">
    private final static String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.pager) QMUIViewPager mPager;
    @BindView(R.id.tabs) QMUITabSegment mTabs;
    Unbinder unbinder;
//    @BindView(R.id.pager) QMUIViewPager pager;
//    @BindView(R.id.tabs) QMUITabSegment tabs;

    private HashMap<Pager, HomeController> mPages;

    //</editor-fold>

    @Override
    protected View initViews() {
        FrameLayout layout = (FrameLayout) LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, layout);
        initTabs();
        initPagers();
        return layout;
    }

    @Override
    protected void initData() {

    }

    //<editor-fold desc="Tabs相关">
    private void initTabs() {
        int normalColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.home_tab_normalColor);
        int selectColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.home_tab_selectColor);
        mTabs.setDefaultNormalColor(normalColor);
        mTabs.setDefaultSelectedColor(selectColor);

        QMUITabSegment.Tab first = new QMUITabSegment.Tab(ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component), ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component_selected), "首页", true);
        QMUITabSegment.Tab second = new QMUITabSegment.Tab(ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_lab), ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_lab_selected), "产品", true);
        QMUITabSegment.Tab third = new QMUITabSegment.Tab(ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_util), ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_util_selected), "发现", true);
        QMUITabSegment.Tab fourth = new QMUITabSegment.Tab(ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_lab), ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_lab_selected), "我的", true);
        mTabs.addTab(first).addTab(second).addTab(third).addTab(fourth);

        mTabs.notifyDataChanged();
        //        mTabSegment.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_BOTTOM);
        //        // 如果你的 icon 显示大小和实际大小不吻合:
        //        // 1. 设置icon 的 bounds
        //        // 2. Tab 使用拥有5个参数的构造器
        //        // 3. 最后一个参数（setIntrinsicSize）设置为false
        //        int iconShowSize = QMUIDisplayHelper.dp2px(getContext(), 20);
        //        Drawable normalDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component);
        //        normalDrawable.setBounds(0, 0, iconShowSize, iconShowSize);
        //        Drawable selectDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component_selected);
        //
        //        selectDrawable.setBounds(0, 0, iconShowSize, iconShowSize);
        //
        //        QMUITabSegment.Tab component = new QMUITabSegment.Tab(
        //                normalDrawable,
        //                normalDrawable,
        //                "Components", false, false
        //        );
    }
    //</editor-fold>

    //<editor-fold desc="QMUIViewPager初始化相关">
    private void initPagers() {
        mPages = new HashMap<>();

        HomeController homeController = HomeFragmentController.newInstance();
        HomeController productFragmentController = ProductFragmentController.newInstance();
        HomeController findFragmentController = FindFragmentController.newInstance();
        HomeController mainFragmentController = MainFragmentController.newInstance();//我的

        mPages.put(Pager.HOME, homeController);
        mPages.put(Pager.PRODUCT, productFragmentController);
        mPages.put(Pager.FIND, findFragmentController);
        mPages.put(Pager.MAIN, mainFragmentController);


        mPager.setAdapter(qmuiPagerAdapter);
        mPager.setSwipeable(false);
        mTabs.setupWithViewPager(mPager, false, true);
    }

    QMUIPagerAdapter qmuiPagerAdapter = new QMUIPagerAdapter() {
        private FragmentTransaction mCurrentTransaction;
        private Fragment mCurrentPrimaryItem = null;

        @Override
        protected Object hydrate(ViewGroup container, int position) {
            return mPages.get(Pager.getPagerFromPositon(position));
        }

        @Override
        protected void populate(ViewGroup container, Object item, int position) {
            String name = makeFragmentName(container.getId(), position);
            if (mCurrentTransaction == null) {
                mCurrentTransaction = getChildFragmentManager()
                        .beginTransaction();
            }
            Fragment fragment = getChildFragmentManager().findFragmentByTag(name);
            if (fragment != null) {
                mCurrentTransaction.attach(fragment);
            } else {
                fragment = (Fragment) item;
                mCurrentTransaction.add(container.getId(), fragment, name);
            }
            if (fragment != mCurrentPrimaryItem) {
                fragment.setMenuVisibility(false);
                fragment.setUserVisibleHint(false);
            }
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            Fragment fragment = (Fragment) object;
            if (fragment != mCurrentPrimaryItem) {
                if (mCurrentPrimaryItem != null) {
                    mCurrentPrimaryItem.setMenuVisibility(false);
                    mCurrentPrimaryItem.setUserVisibleHint(false);
                }
                if (fragment != null) {
                    fragment.setMenuVisibility(true);
                    fragment.setUserVisibleHint(true);
                }
                mCurrentPrimaryItem = fragment;
            }
        }

        @Override
        public void startUpdate(ViewGroup container) {
            if (container.getId() == View.NO_ID) {
                throw new IllegalStateException("ViewPager with adapter " + this
                        + " requires a view id");
            }
        }

        @Override
        public void finishUpdate(ViewGroup container) {
            if (mCurrentTransaction != null) {
                mCurrentTransaction.commitNowAllowingStateLoss();
                mCurrentTransaction = null;
            }
        }

        @Override
        protected void destroy(ViewGroup container, int position, Object object) {
            if (mCurrentTransaction == null) {
                mCurrentTransaction = getChildFragmentManager()
                        .beginTransaction();
            }
            mCurrentTransaction.detach((Fragment) object);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == ((Fragment) object).getView();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return Pager.getPagerFromPositon(position).getPageName();
        }

        private String makeFragmentName(int viewId, long id) {
            return "HomeFragmentQMUIPagerAdapter:" + viewId + ":" + id;
        }
    };
    //</editor-fold>

    //<editor-fold desc="禁用滑动返回 ">
    @Override
    protected boolean canDragBack() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    //</editor-fold>

    enum Pager {
        HOME("首页"), PRODUCT("产品"), FIND("发现"), MAIN("我的");
        private String pageName;

        Pager(String pageName) {
            this.pageName = pageName;
        }

        public String getPageName() {
            return pageName;
        }

        public static Pager getPagerFromPositon(int position) {
            switch (position) {
                case 0:
                    return HOME;
                case 1:
                    return PRODUCT;
                case 2:
                    return FIND;
                case 3:
                    return MAIN;
                default:
                    return HOME;
            }
        }
    }

}
