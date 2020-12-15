package com.lilu.base;

import android.app.Application;

import com.lilu.base.widget.statuslayout.callback.EmptyCallback;
import com.lilu.base.widget.statuslayout.callback.ErrorCallback;
import com.lilu.base.widget.statuslayout.callback.LoadingCallback;
import com.lilu.base.widget.statuslayout.core.LoadSir;

/**
 * Description:
 *
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        LoadSir.beginBuilder()
                .addCallback(new EmptyCallback())
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
