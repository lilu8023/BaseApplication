package com.lilu.application;

import com.lilu.base.BaseApplication;
import com.lilu.base.utils.logger.AndroidLogAdapter;
import com.lilu.base.utils.logger.Logger;

/**
 * Description:
 *
 * @author lilu on 2020/12/22
 * No one knows this better than me
 */
public class MainApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
