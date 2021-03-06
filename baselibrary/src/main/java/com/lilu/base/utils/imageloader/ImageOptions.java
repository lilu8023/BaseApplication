package com.lilu.base.utils.imageloader;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import java.io.File;

import androidx.annotation.DrawableRes;

/**
 * Description:
 *
 * @author lilu on 2020/12/4
 * No one knows this better than me
 */
public class ImageOptions {

    public int placeholderResId;
    public int errorResId;
    public boolean isCenterCrop;
    public boolean isCenterInside;
    public boolean skipLocalCache; //是否缓存到本地
    public boolean skipNetCache;
    public Bitmap.Config config = Bitmap.Config.RGB_565;
    public int targetWidth;
    public int targetHeight;
    public float bitmapAngle; //圆角角度
    public float degrees; //旋转角度.注意:picasso针对三星等本地图片，默认旋转回0度，即正常位置。此时不需要自己rotate
    public Drawable placeholder;
    public View targetView;//targetView展示图片
    public BitmapCallBack callBack;
    public String url;
    public File file;
    public int drawableResId;
    public Uri uri;
    public ILoader loader;//实时切换图片加载库

    public ImageOptions(String url) {
        this.url = url;
    }

    public ImageOptions(File file) {
        this.file = file;
    }

    public ImageOptions(int drawableResId) {
        this.drawableResId = drawableResId;
    }

    public ImageOptions(Uri uri) {
        this.uri = uri;
    }

    public void into(View targetView) {
        this.targetView = targetView;
        ImageLoader.getInstance().loadOptions(this);
    }

    public void bitmap(BitmapCallBack callBack) {
        this.callBack = callBack;
        ImageLoader.getInstance().loadOptions(this);
    }

    public ImageOptions loader(ILoader imageLoader) {
        this.loader = imageLoader;
        return this;
    }

    public ImageOptions placeholder(@DrawableRes int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    public ImageOptions placeholder(Drawable placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    public ImageOptions error(@DrawableRes int errorResId) {
        this.errorResId = errorResId;
        return this;
    }

    public ImageOptions centerCrop() {
        isCenterCrop = true;
        return this;
    }

    public ImageOptions centerInside() {
        isCenterInside = true;
        return this;
    }

    public ImageOptions config(Bitmap.Config config) {
        this.config = config;
        return this;
    }

    public ImageOptions resize(int targetWidth, int targetHeight) {
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        return this;
    }

    /**
     * 圆角
     * @param bitmapAngle   度数
     * @return
     */
    public ImageOptions angle(float bitmapAngle) {
        this.bitmapAngle = bitmapAngle;
        return this;
    }

    public ImageOptions skipLocalCache(boolean skipLocalCache) {
        this.skipLocalCache = skipLocalCache;
        return this;
    }

    public ImageOptions skipNetCache(boolean skipNetCache) {
        this.skipNetCache = skipNetCache;
        return this;
    }

    public ImageOptions rotate(float degrees) {
        this.degrees = degrees;
        return this;
    }
    
}
