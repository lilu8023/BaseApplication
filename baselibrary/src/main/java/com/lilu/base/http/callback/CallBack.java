package com.lilu.base.http.callback;

import com.lilu.base.http.exception.ApiException;

/**
 * Description:
 *
 * @author lilu on 2020/12/18
 * No one knows this better than me
 */
public abstract class CallBack<T>{

    public abstract void onStart();

    public abstract void onCompleted();

    public abstract void onError(ApiException e);

    public abstract void onSuccess(T t);

}
