package com.zichen.frame.http;


import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * author : lyw
 * time : 2017/9/18
 * desc : OKHttp拦截器 用于解决session过期的问题
 * version: 1.0
 */
public class CacheInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originResponse = chain.proceed(chain.request());

        //设置缓存时间为60秒，并移除了pragma消息头，移除它的原因是因为pragma也是控制缓存的一个消息头属性
        return originResponse.newBuilder().removeHeader("pragma")
                .removeHeader("Cache-Control")//清楚响应体对Cache有影响的信息
                .header("Cache-Control", "public, max-age=10")
                .build();
    }
}
