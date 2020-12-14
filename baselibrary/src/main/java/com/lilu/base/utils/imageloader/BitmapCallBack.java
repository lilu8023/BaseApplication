package com.lilu.base.utils.imageloader;

import android.graphics.Bitmap;

/**
 * Description:
 *
 * @author lilu on 2020/12/4
 * No one knows this better than me
 */
public interface BitmapCallBack {

    void onBitmapLoaded(Bitmap bitmap);

    void onBitmapFailed(Exception e);

    public static class EmptyCallback implements BitmapCallBack {


        @Override
        public void onBitmapLoaded(Bitmap bitmap) {

        }

        @Override
        public void onBitmapFailed(Exception e) {

        }
    }
}
