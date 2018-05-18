package com.zichen.frame.http;

import android.text.TextUtils;

import com.zichen.frame.BuildConfig;
import com.zichen.frame.constants.Config;
import com.zichen.frame.constants.ErrorCode;
import com.zichen.frame.constants.TokenExpiredException;
import com.zichen.frame.entity.request.GetBannerListRequest;
import com.zichen.frame.entity.request.GetNewsListRequest;
import com.zichen.frame.entity.request.LoginRequest;
import com.zichen.frame.entity.request.MobileRequest;
import com.zichen.frame.entity.response.GetBannerListResponse;
import com.zichen.frame.entity.response.GetNewsListResponse;
import com.zichen.frame.entity.response.GetProductsForIndexResponse;
import com.zichen.frame.entity.response.LoginResponse;
import com.zichen.frame.entity.response.MobileResponse;
import com.zichen.frame.manager.BFPreferenceManager;
import com.zichen.frame.util.DeviceUtils;
import com.zichen.frame.util.LogUtils;
import com.zichen.frame.util.Utils;
import com.zichen.frame.util.user.UserInfo;
import com.zichen.frame.util.user.UserInfoKeeper;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * author : zichen
 * time : 2018/5/15
 * desc : http请求方法。使用retrofit+rxjava2.0，对service中方法进一步封装。
 * version: 1.0
 */
public class HttpMethods {

    /**
     * 得到图片完整路径
     *
     * @param picturePath
     * @return
     */
    public static String getPicturePath(String picturePath) {
        return BuildConfig.BASE_RESOURCE_URL + picturePath;
    }

    private BaseFrameService apiService;

    private HttpMethods() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Config.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Config.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Config.READ_TIMEOUT, TimeUnit.SECONDS)
//                .addInterceptor(new CacheInterceptor())
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(BaseFrameService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 添加线程管理并订阅
     *
     * @param observable 被观察对象
     * @param observer   观察者
     */
    private void toSubscribe(Observable<? extends MobileResponse> observable, Observer observer) {
        // 每次访问接口都记录访问时间
        // 判断访问时间，如果超过指定时间则提示需要重新登录？
        BFPreferenceManager.getInstance().saveTheLastTimeVisitied();
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<MobileResponse, Observable<? extends MobileResponse>>() {

                    @Override
                    public Observable<? extends MobileResponse> apply(@NonNull MobileResponse response) throws Exception {
                        if (ErrorCode.ERROR_NOAUTH.equals(response.getErrorCode())) {
                            return Observable.error(new TokenExpiredException());
                        }
//                        LogUtils.d("root： " + response.toString());
                        return Observable.just(response);
                    }
                })
                .retryWhen(throwableObservable -> throwableObservable.flatMap((Function<Throwable, ObservableSource<?>>) throwable -> {
                    if (throwable instanceof TokenExpiredException) {
                        return apiService.login(HttpMethods.this.buildLoginRequest())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .unsubscribeOn(Schedulers.io())
                                .doOnNext(loginResponse -> {
                                    UserInfoKeeper.getInstance().setSession(loginResponse.getSessionId());
                                });
                    } else if (throwable instanceof SocketTimeoutException) {
                        return Observable.error(throwable);
                    }
                    return Observable.error(throwable);
                }))
                .subscribe(observer);
    }

    public void login(Observer<LoginResponse> observer, LoginRequest loginRequest) {
        LogUtils.d("LOGIN");
        toSubscribe(apiService.login(loginRequest), observer);
    }

    public Observable<LoginResponse> login() {
        return apiService.login(buildLoginRequest());
    }


    private LoginRequest buildLoginRequest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setMobile(UserInfoKeeper.getInstance().getPhone());
        loginRequest.setPassword(UserInfoKeeper.getInstance().getPassword());
        loginRequest.setEquipmentBrand(DeviceUtils.getEquipmentBrand());
        loginRequest.setEquipmentId(DeviceUtils.getEquipmentId());
        loginRequest.setEquipmentType(DeviceUtils.getEquipmentType());
        loginRequest.setEquipmentVersion(DeviceUtils.getEquipmentVersion());
        loginRequest.setClientVersion(DeviceUtils.getClientVersion());
        return loginRequest;
    }

    public void getProductForHome(Observer<GetProductsForIndexResponse> observer, MobileRequest request) {
//        toSubscribe(apiService.getProductForHome(request), observer);

        Observable<GetProductsForIndexResponse> observable = Observable.just(1)
                .flatMap(new Function<Object, Observable<GetProductsForIndexResponse>>() {
                    @Override
                    public Observable<GetProductsForIndexResponse> apply(@NonNull Object object) throws Exception {
//                        request.setSessionId(UserInfoKeeper.getInstance().getSession());
                        return apiService.getProductForHome(request);
                    }
                });
        toSubscribe(observable, observer);
    }

    public void getBanner(Observer<GetBannerListResponse> observer, GetBannerListRequest getBannerListRequest) {
        toSubscribe(apiService.getBanner(getBannerListRequest), observer);
    }

    public void getNewsList(Observer<GetNewsListResponse> observer, GetNewsListRequest getNewsListRequest) {
        toSubscribe(apiService.getNewsList(getNewsListRequest), observer);
    }
}

