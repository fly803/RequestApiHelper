package com.cg.requestapi.request.retrofit.interfaces;

/**
 * Created by sam on 16/3/10.
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
    void onSeverError(int code, String msg);
}
