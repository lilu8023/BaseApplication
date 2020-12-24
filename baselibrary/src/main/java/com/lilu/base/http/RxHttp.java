package com.lilu.base.http;

import android.app.Application;
import android.content.Context;

import com.lilu.base.http.cookie.CookieManger;
import com.lilu.base.http.utils.HttpsUtils;

import org.apache.http.params.HttpParams;

import java.io.File;
import java.io.InputStream;
import java.net.Proxy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.reactivex.disposables.Disposable;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.http.HttpHeaders;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Description:
 *
 * @author lilu on 2020/12/18
 * No one knows this better than me
 */
public class RxHttp {

    private static Application sContext;
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
    private OkHttpClient.Builder okHttpClientBuilder;                 //okhttp请求的客户端
    private Retrofit.Builder retrofitBuilder;                         //Retrofit请求Builder
    private CookieManger cookieJar;                                   //Cookie管理
    private volatile static RxHttp singleton = null;

    private RxHttp() {
        okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.hostnameVerifier(new DefaultHostnameVerifier());
        okHttpClientBuilder.connectTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        okHttpClientBuilder.readTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        okHttpClientBuilder.writeTimeout(DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);
        retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());//增加RxJava2CallAdapterFactory
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

    public static OkHttpClient getOkHttpClient() {
        return getInstance().okHttpClientBuilder.build();
    }

    public static Retrofit getRetrofit() {
        return getInstance().retrofitBuilder.build();
    }


    /**
     * 对外暴露 OkHttpClient,方便自定义
     */
    public static OkHttpClient.Builder getOkHttpClientBuilder() {
        return getInstance().okHttpClientBuilder;
    }

    /**
     * 对外暴露 Retrofit,方便自定义
     */
    public static Retrofit.Builder getRetrofitBuilder() {
        return getInstance().retrofitBuilder;
    }




    /**
     * 此类是用于主机名验证的基接口。 在握手期间，如果 URL 的主机名和服务器的标识主机名不匹配，
     * 则验证机制可以回调此接口的实现程序来确定是否应该允许此连接。策略可以是基于证书的或依赖于其他验证方案。
     * 当验证 URL 主机名使用的默认规则失败时使用这些回调。如果主机名是可接受的，则返回 true
     */
    public class DefaultHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * https的全局访问规则
     */
    public RxHttp setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        okHttpClientBuilder.hostnameVerifier(hostnameVerifier);
        return this;
    }

    /**
     * https的全局自签名证书
     */
    public RxHttp setCertificates(InputStream... certificates) {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, certificates);
        okHttpClientBuilder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * https双向认证证书
     */
    public RxHttp setCertificates(InputStream bksFile, String password, InputStream... certificates) {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(bksFile, password, certificates);
        okHttpClientBuilder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        return this;
    }

    /**
     * 全局cookie存取规则
     */
    public RxHttp setCookieStore(CookieManger cookieManager) {
        cookieJar = cookieManager;
        okHttpClientBuilder.cookieJar(cookieJar);
        return this;
    }

    /**
     * 获取全局的cookie实例
     */
    public static CookieManger getCookieJar() {
        return getInstance().cookieJar;
    }

    /**
     * 全局读取超时时间
     */
    public RxHttp setReadTimeOut(long readTimeOut) {
        okHttpClientBuilder.readTimeout(readTimeOut, TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * 全局写入超时时间
     */
    public RxHttp setWriteTimeOut(long writeTimeout) {
        okHttpClientBuilder.writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * 全局连接超时时间
     */
    public RxHttp setConnectTimeout(long connectTimeout) {
        okHttpClientBuilder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        return this;
    }

    /**
     * 超时重试次数
     */
    public RxHttp setRetryCount(int retryCount) {
        if (retryCount < 0) throw new IllegalArgumentException("retryCount must > 0");
        mRetryCount = retryCount;
        return this;
    }

    /**
     * 超时重试次数
     */
    public static int getRetryCount() {
        return getInstance().mRetryCount;
    }

    /**
     * 超时重试延迟时间
     */
    public RxHttp setRetryDelay(int retryDelay) {
        if (retryDelay < 0) throw new IllegalArgumentException("retryDelay must > 0");
        mRetryDelay = retryDelay;
        return this;
    }

    /**
     * 超时重试延迟时间
     */
    public static int getRetryDelay() {
        return getInstance().mRetryDelay;
    }

    /**
     * 超时重试延迟叠加时间
     */
    public RxHttp setRetryIncreaseDelay(int retryIncreaseDelay) {
        if (retryIncreaseDelay < 0)
            throw new IllegalArgumentException("retryIncreaseDelay must > 0");
        mRetryIncreaseDelay = retryIncreaseDelay;
        return this;
    }

    /**
     * 超时重试延迟叠加时间
     */
    public static int getRetryIncreaseDelay() {
        return getInstance().mRetryIncreaseDelay;
    }


    /**
     * 全局的缓存过期时间
     */
    public RxHttp setCacheTime(long cacheTime) {
        if (cacheTime <= -1) cacheTime = DEFAULT_CACHE_NEVER_EXPIRE;
        mCacheTime = cacheTime;
        return this;
    }

    /**
     * 获取全局的缓存过期时间
     */
    public static long getCacheTime() {
        return getInstance().mCacheTime;
    }

    /**
     * 全局的缓存大小,默认50M
     */
    public RxHttp setCacheMaxSize(long maxSize) {
        mCacheMaxSize = maxSize;
        return this;
    }

    /**
     * 获取全局的缓存大小
     */
    public static long getCacheMaxSize() {
        return getInstance().mCacheMaxSize;
    }



    /**
     * 获取缓存的路劲
     */
    public static File getCacheDirectory() {
        return getInstance().mCacheDirectory;
    }


    /**
     * 全局设置OkHttp的缓存,默认是3天
     */
    public RxHttp setHttpCache(Cache cache) {
        this.mCache = cache;
        return this;
    }

    /**
     * 获取OkHttp的缓存<br>
     */
    public static Cache getHttpCache() {
        return getInstance().mCache;
    }

    /**
     * 添加全局公共请求参数
     */
    public RxHttp addCommonParams(HttpParams commonParams) {
//        if (mCommonParams == null) mCommonParams = new HttpParams();
//        mCommonParams.put(commonParams);
        return this;
    }

    /**
     * 获取全局公共请求参数
     */
    public HttpParams getCommonParams() {
        return mCommonParams;
    }

    /**
     * 获取全局公共请求头
     */
    public HttpHeaders getCommonHeaders() {
        return mCommonHeaders;
    }

    /**
     * 添加全局公共请求参数
     */
    public RxHttp addCommonHeaders(HttpHeaders commonHeaders) {
//        if (mCommonHeaders == null) mCommonHeaders = new HttpHeaders();
//        mCommonHeaders.put(commonHeaders);
        return this;
    }

    /**
     * 添加全局拦截器
     */
    public RxHttp addInterceptor(Interceptor interceptor) {
        okHttpClientBuilder.addInterceptor(interceptor);
        return this;
    }

    /**
     * 添加全局网络拦截器
     */
    public RxHttp addNetworkInterceptor(Interceptor interceptor) {
        okHttpClientBuilder.addNetworkInterceptor(interceptor);
        return this;
    }

    /**
     * 全局设置代理
     */
    public RxHttp setProxy(Proxy proxy) {
        okHttpClientBuilder.proxy(proxy);
        return this;
    }

    /**
     * 全局设置请求的连接池
     */
    public RxHttp setConnectionPool(ConnectionPool connectionPool) {
        okHttpClientBuilder.connectionPool(connectionPool);
        return this;
    }

    /**
     * 全局为Retrofit设置自定义的OkHttpClient
     */
    public RxHttp setClient(OkHttpClient client) {
        retrofitBuilder.client(client);
        return this;
    }

    /**
     * 全局设置Converter.Factory,默认GsonConverterFactory.create()
     */
    public RxHttp addConverterFactory(Converter.Factory factory) {
        retrofitBuilder.addConverterFactory(factory);
        return this;
    }

    /**
     * 全局设置CallAdapter.Factory,默认RxJavaCallAdapterFactory.create()
     */
    public RxHttp addCallAdapterFactory(CallAdapter.Factory factory) {
        retrofitBuilder.addCallAdapterFactory(factory);
        return this;
    }

    /**
     * 全局设置Retrofit callbackExecutor
     */
    public RxHttp setCallbackExecutor(Executor executor) {
        retrofitBuilder.callbackExecutor(executor);
        return this;
    }

    /**
     * 全局设置Retrofit对象Factory
     */
    public RxHttp setCallFactory(okhttp3.Call.Factory factory) {
        retrofitBuilder.callFactory(factory);
        return this;
    }

    /**
     * 全局设置baseurl
     */
    public RxHttp setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
        return this;
    }

    /**
     * 获取全局baseurl
     */
    public static String getBaseUrl() {
        return getInstance().mBaseUrl;
    }



    /**
     * 取消订阅
     */
    public static void cancelSubscription(Disposable disposable) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

}
