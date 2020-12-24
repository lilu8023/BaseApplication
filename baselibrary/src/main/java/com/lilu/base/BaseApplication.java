package com.lilu.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.lilu.base.update.VersionUpdate;
import com.lilu.base.widget.statuslayout.callback.EmptyCallback;
import com.lilu.base.widget.statuslayout.callback.ErrorCallback;
import com.lilu.base.widget.statuslayout.callback.LoadingCallback;
import com.lilu.base.widget.statuslayout.callback.SuccessCallback;
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

        if (true) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化

        LoadSir.beginBuilder()
                .addCallback(new EmptyCallback())
                .addCallback(new ErrorCallback())
                .addCallback(new LoadingCallback())
                .setDefaultCallback(SuccessCallback.class)
                .commit();

        VersionUpdate.get().setApkCacheDir("ddd").init(this);
    }
}
