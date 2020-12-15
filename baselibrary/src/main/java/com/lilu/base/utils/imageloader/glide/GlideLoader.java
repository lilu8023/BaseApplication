package com.lilu.base.utils.imageloader.glide;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.lilu.base.utils.imageloader.ILoader;
import com.lilu.base.utils.imageloader.ImageOptions;

/**
 * Description:
 * Glide加载图片
 * @author lilu on 2020/12/14
 * No one knows this better than me
 */
public class GlideLoader implements ILoader {

    @Override
    public void init(Context context) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadImage(ImageOptions options) {

        RequestManager glideManager = Glide.with(options.targetView.getContext());

        RequestBuilder<Drawable> glideBuilder = null;
        if(options.url != null){
            glideBuilder = glideManager.load(options.url);
        }else if(options.file != null){
            glideBuilder = glideManager.load(options.file);
        }
        else if(options.drawableResId != 0){
            glideBuilder = glideManager.load(options.drawableResId);
        }
        else if(options.uri != null){
            glideBuilder = glideManager.load(options.uri);
        }

        if (glideBuilder == null) {
            throw new NullPointerException("requestCreator must not be null");
        }

        //加载中占位图
        if(options.placeholderResId != 0){
            glideBuilder.placeholder(options.placeholderResId);
        }
        //加载错误占位图
        if(options.errorResId != 0){
            glideBuilder.error(options.errorResId);
        }
        //后备回调
        glideBuilder.fallback(options.errorResId);


        if(options.targetView instanceof ImageView){
            glideBuilder.into((ImageView)options.targetView);
        }

    }



    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void clearDiskCache() {

    }

    @Override
    public void clearMemory() {

    }

}
