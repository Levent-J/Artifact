package com.levent_j.artifacthelper.util;

import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class RxCallback<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        String errorMsg;
//        if (e instanceof NetworkException) {
//            /** 网络正常，http 请求成功，服务器返回逻辑错误 */
//            errorMsg = e.getMessage();
//        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
//            errorMsg = "网络异常，请检查网络";
//        } else if (e instanceof TimeoutException || e instanceof SocketTimeoutException) {
//            errorMsg = "网络不畅，请稍后再试！";
//        } else if (e instanceof JsonSyntaxException) {
//            errorMsg = "数据解析异常";
//        } else if (e instanceof IOException){
//            errorMsg = e.getMessage();
//            e.printStackTrace();
//        } else if (e instanceof ClientException){
//            errorMsg = "客户端异常";
//        } else if (e instanceof ServiceException){
//            errorMsg = "服务器异常";
//        }else {
//            errorMsg = "未知异常:"+e.getMessage();
//        }
//        onFailure(errorMsg);
        onFailure(e.getMessage());
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(String msg);

}
