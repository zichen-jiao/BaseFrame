package com.zichen.frame.http;

public interface ObserverOnNextListener<T> {
    void onNext(T t);

    void onComplete();
}
