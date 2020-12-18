package com.lilu.base.http.subsciber;

import android.content.Context;

import com.lilu.base.http.callback.CallBack;
import com.lilu.base.http.exception.ApiException;

/**
 * Description:
 *
 * @author lilu on 2020/12/18
 * No one knows this better than me
 */
public class CallBackSubscriber<T> extends BaseSubscriber<T> {

    private CallBack<T> mCallBack;

    public CallBackSubscriber(Context context, CallBack<T> mCallBack) {
        super(context);
        this.mCallBack = mCallBack;
    }

    @Override
    public void onError(ApiException e) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onComplete() {

    }
}
