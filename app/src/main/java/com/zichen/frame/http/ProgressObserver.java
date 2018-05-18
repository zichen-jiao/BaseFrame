package com.zichen.frame.http;

import android.accounts.NetworkErrorException;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.zichen.frame.R;
import com.zichen.frame.constants.ErrorCode;
import com.zichen.frame.entity.response.MobileResponse;
import com.zichen.frame.util.ActivityStackManager;
import com.zichen.frame.util.LogUtils;

import java.net.SocketTimeoutException;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressObserver<T extends MobileResponse> implements Observer<T>, ProgressCancelListener {

    private ObserverOnNextListener<MobileResponse> mSubscriberOnNextListener;
    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;
    private boolean isShowProgressDialog;
    private Disposable disposable;

    public ProgressObserver(Context context, boolean isShowProgressDialog, ObserverOnNextListener subscriberOnNextListener) {
        this(context, isShowProgressDialog, subscriberOnNextListener, true);
    }

    public ProgressObserver(Context context, ObserverOnNextListener subscriberOnNextListener, boolean isDialogCancelable) {
        this(context, true, subscriberOnNextListener, isDialogCancelable);
    }

    public ProgressObserver(Context context, ObserverOnNextListener subscriberOnNextListener) {
        this(context, true, subscriberOnNextListener, true);
    }

    public ProgressObserver(Context context, boolean isShowProgressDialog, ObserverOnNextListener mSubscriberOnNextListener, boolean isDialogCancelable) {
        this.context = context;
        this.isShowProgressDialog = isShowProgressDialog;
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, isDialogCancelable);
    }


    private void showProgressDialog() {
        if (mProgressDialogHandler != null && isShowProgressDialog) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null && isShowProgressDialog) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onSubscribe(@NonNull Disposable d) {
        showProgressDialog();
        disposable = d;
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(MobileResponse t) {
        if (t != null && !TextUtils.isEmpty(t.getErrorCode())) {
            if (ErrorCode.ERROR_CONCURRENT.equals(t.getErrorCode())) { // 已在别处登录
                LogUtils.json(t);
                disposable.dispose();
                dismissProgressDialog();
                showMessageNegativeDialog();
                return;
            }
        }

        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        if (e instanceof SocketTimeoutException) {
            Toasty.error(context, "连接超时").show();
            // 有的请求可能并不需要弹toast提示
            //ToastUtils.showMessage("网络连接失败，请检查网络");
        } else if (e instanceof NetworkErrorException) {
            LogUtils.d("NetworkError");
        } else {
            LogUtils.d("其他网络错误 " + e.getMessage());
        }
    }

    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onComplete() {
        dismissProgressDialog();
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onComplete();
        }
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public Disposable getDisposable() {
        return disposable;
    }

    /**
     * 取消网络请求。
     */
    public void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    private void showMessageNegativeDialog() {
        new QMUIDialog.MessageDialogBuilder(context)
                .setTitle(R.string.common_hint)
                .setMessage(R.string.dialog_msg_force_offline)
                .addAction(0, R.string.confirm2, QMUIDialogAction.ACTION_PROP_NEGATIVE, (dialog, index) -> {
                    dialog.dismiss();
                    ActivityStackManager.getInstance().killAllActivity();
                })
                .create(R.style.QMUI_Dialog).show();
    }
}