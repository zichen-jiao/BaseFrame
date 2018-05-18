package com.zichen.frame.fragment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zichen.frame.R;
import com.zichen.frame.adapter.ProductAdapter;
import com.zichen.frame.decorator.DividerItemDecoration;
import com.zichen.frame.decorator.GridDividerItemDecoration;
import com.zichen.frame.entity.request.GetNewsListRequest;
import com.zichen.frame.entity.response.GetNewsListResponse;
import com.zichen.frame.fragment.DetailFragment;
import com.zichen.frame.http.HttpMethods;
import com.zichen.frame.http.ObserverOnNextListener;
import com.zichen.frame.http.ProgressObserver;
import com.zichen.frame.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
/**
 * @作者 : zichen
 * @日期 : 2018/5/18
 * @注释 : 主界面中产品标签页的Fragment实现
 * @version: 1.0
 */
public class ProductFragmentController extends HomeController {
    @BindView(R.id.topbar)
    QMUITopBar topbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private List<GetNewsListResponse.News> mNewsList;
    private ProductAdapter productAdapter;

    private void initTopBar() {
        topbar.setTitle("产品");
        topbar.addRightTextButton("产品", R.id.topbar_right_message_button).setOnClickListener(v -> {
            showToastySuccess("产品");
            startFragment(DetailFragment.newInstance("产品详情"));
        });
    }

    public static ProductFragmentController newInstance() {
        Bundle args = new Bundle();
        ProductFragmentController fragment = new ProductFragmentController();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View initViews() {
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_product_tab_layout, null);
        ButterKnife.bind(this, root);
        initTopBar();
        initAdapter();
        initRefreshLayout();
        return root;
    }

    @Override
    protected void initData() {
        LogUtils.d("产品：initData");
        refreshLayout.autoRefresh();
    }

    private void initAdapter() {
        mNewsList = new ArrayList<>();
        productAdapter = new ProductAdapter(R.layout.listitem_news, mNewsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false));
        recyclerView.addItemDecoration(new GridDividerItemDecoration(getContext(), 50));
        recyclerView.setAdapter(productAdapter);
    }

    private void initRefreshLayout() {
        refreshLayout.setPrimaryColorsId(R.color.qmui_config_color_white, R.color.qmui_config_color_white);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            mPageNo = 1;
            getNews();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            mPageNo++;
            getNews();
        });
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadMore(true);
    }

    private int mPageNo = 1;
    /**
     * 新闻类型：公司新闻/民生视点。
     */
    private String mNewsType = "2";

    private void getNews() {
        ObserverOnNextListener<GetNewsListResponse> observerOnNextListener = new ObserverOnNextListener<GetNewsListResponse>() {
            @Override
            public void onNext(GetNewsListResponse getNewsListResponse) {
                LogUtils.d(getNewsListResponse.toString());
                if (isEmpty(getNewsListResponse.getErrorCode())) {
                    List<GetNewsListResponse.News> newsList = getNewsListResponse.getNewsList();
                    mNewsList.addAll(newsList);
                    productAdapter.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onComplete() {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }
        };


        GetNewsListRequest request = new GetNewsListRequest();
        request.setPageNo(mPageNo + "");
        request.setType(mNewsType);
        HttpMethods.getInstance().getNewsList(new ProgressObserver<>(getContext(), false, observerOnNextListener), request);
    }


}
