package com.lilu.base.http;

import android.app.Application;
import android.content.Context;

import com.lilu.base.http.cookie.CookieManger;

import org.apache.http.params.HttpParams;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.internal.http.HttpHeaders;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:
 * 网络请求入口嘞
 * @author lilu on 2020/12/18
 * No one knows this better than me
 */
public class RxHttp {

    private static Application sContext;

    private OkHttpClient.Builder clientBuilder;                       //OkHttp请求的客户端
    private Retrofit.Builder retrofitBuilder;                         //Retrofit请求Builder
    
    public static final int DEFAULT_MILLISECONDS = 60000;             //默认的超时时间
    private static final int DEFAULT_RETRY_COUNT = 3;                 //默认重试次数
    private static final int DEFAULT_RETRY_INCREASE_DELAY = 0;         //默认重试叠加时间
    private static final int DEFAULT_RETRY_DELAY = 500;               //默认重试延时
    public static final int DEFAULT_CACHE_NEVER_EXPIRE = -1;          //缓存过期时间，默认永久缓存
    private Cache mCache = null;                                      //Okhttp缓存对象
    private long mCacheTime = -1;                                     //缓存时间
    private File mCacheDirectory;                                     //缓存目录
    private long mCacheMaxSize;                                       //缓存大小
    private String mBaseUrl;                                          //全局BaseUrl
    private int mRetryCount = DEFAULT_RETRY_COUNT;                    //重试次数默认3次
    private int mRetryDelay = DEFAULT_RETRY_DELAY;                    //延迟xxms重试
    private int mRetryIncreaseDelay = DEFAULT_RETRY_INCREASE_DELAY;    //叠加延迟
    private HttpHeaders mCommonHeaders;                               //全局公共请求头
    private HttpParams mCommonParams;                                 //全局公共请求参数
    
    private CookieManger cookieJar;                                   //Cookie管理
    private volatile static RxHttp singleton = null;

    private RxHttp() {
        //初始化OkHttpClient
        clientBuilder = new OkHttpClient.Builder();
        //初始化retrofit
        retrofitBuilder = new Retrofit.Builder();
        //添加GSON解析
        retrofitBuilder.addConverterFactory(GsonConverterFactory.create());
        //增加RxJava2CallAdapterFactory  支持RxJava
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    public static RxHttp getInstance() {
        testInitialize();
        if (singleton == null) {
            synchronized (RxHttp.class) {
                if (singleton == null) {
                    singleton = new RxHttp();
                }
            }
        }
        return singleton;
    }

    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     */
    public static void init(Application app) {
        sContext = app;
    }

    /**
     * 获取全局上下文
     */
    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    private static void testInitialize() {
        if (sContext == null)
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttp.init() 初始化！");
    }


    public RxHttp baseUrl(String url) {
        retrofitBuilder.baseUrl(url);
        return this;
    }

    /**
     * 全局为Retrofit设置自定义的OkHttpClient
     */
    public RxHttp setClient(OkHttpClient client) {
        retrofitBuilder.client(client);
        return this;
    }

    public <A> A create(Class<A> apiService){

        return retrofitBuilder.build().create(apiService);
    }

}
