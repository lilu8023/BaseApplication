package com.lilu.application.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.lilu.base.utils.logger.Logger;

/**
 * Description:
 *
 * @author lilu on 2020/12/23
 * No one knows this better than me
 */
@Interceptor(priority = 1,name = "main拦截器")
public class RouterInterceptor implements IInterceptor {

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {


        Logger.i(postcard.getPath());
        callback.onContinue(postcard);
    }

    @Override
    public void init(Context context) {

        Logger.i("main拦截器");

    }
}
