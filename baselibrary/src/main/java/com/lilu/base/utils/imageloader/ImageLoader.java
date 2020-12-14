package com.lilu.base.utils.imageloader;

import android.net.Uri;


import java.io.File;


/**
 * Description:
 *
 * @author lilu
 * @date 2020/3/12
 * 勿以恶小而为之，勿以善小而不为
 */
public class ImageLoader {

    private static ILoader sLoader;
    private static volatile ImageLoader sInstance;

    //单例模式
    public static ImageLoader getInstance() {
        if (sInstance == null) {
            synchronized (ImageLoader.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoader();
                }
            }
        }
        return sInstance;
    }

    /**
     * 提供全局替换图片加载框架的接口，若切换其它框架，可以实现一键全局替换
     */
    public void setGlobalImageLoader(ILoader loader) {
        sLoader = loader;
    }

    public ImageOptions load(String url) {
        return new ImageOptions(url);
    }

    public ImageOptions load(int drawable) {
        return new ImageOptions(drawable);
    }

    public ImageOptions load(File file) {
        return new ImageOptions(file);
    }

    public ImageOptions load(Uri uri) {
        return new ImageOptions(uri);
    }

    /**
     * 优先使用实时设置的图片loader，其次使用全局设置的图片loader
     * @param options
     */
    public void loadOptions(ImageOptions options) {
        if (options.loader != null) {
            options.loader.loadImage(options);
        } else {
            checkNotNull();
            sLoader.loadImage(options);
        }
    }

    public void clearMemoryCache() {
        checkNotNull();
        sLoader.clearMemory();
    }

    public void clearDiskCache() {
        checkNotNull();
        sLoader.clearDiskCache();
    }

    private void checkNotNull() {
        if (sLoader == null) {
            throw new NullPointerException("you must be set your imageLoader at first!");
        }
    }
}
