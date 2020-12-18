package com.lilu.base.http.subsciber;

import android.content.Context;

import com.lilu.base.http.exception.ApiException;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Description:
 *
 * @author lilu on 2020/12/18
 * No one knows this better than me
 */
public abstract class BaseSubscriber<T> extends DisposableObserver<T> {

    public WeakReference<Context> contextWeakReference;

    public BaseSubscriber() {
    }

    @Override
    protected void onStart() {
//        if (contextWeakReference != null && contextWeakReference.get() != null && !isNetworkAvailable(contextWeakReference.get())) {
//            //Toast.makeText(context, "无网络，读取缓存数据", Toast.LENGTH_SHORT).show();
//            onComplete();
//        }
    }


    public BaseSubscriber(Context context) {
        if (context != null) {
            contextWeakReference = new WeakReference<>(context);
        }
    }

    @Override
    public final void onError(java.lang.Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(ApiException.handleException(e));
        }
    }


    public abstract void onError(ApiException e);
}
