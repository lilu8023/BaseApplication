package com.lilu.application;

import com.lilu.base.BaseApplication;
import com.lilu.base.http.RxHttp;
import com.lilu.base.utils.logger.AndroidLogAdapter;
import com.lilu.base.utils.logger.Logger;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

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

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)
                .build();

        RxHttp.init(this);
        RxHttp.getInstance()
                .baseUrl("https://wanandroid.com/")
                .setClient(client);

    }
}
