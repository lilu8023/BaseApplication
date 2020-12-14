package com.lilu.base.utils.imageloader;

import android.content.Context;


/**
 * Description:
 *
 * @author lilu
 * @date 2020/3/12
 * 勿以恶小而为之，勿以善小而不为
 */
public interface ILoader {

    void init(Context context);

    void loadImage(ImageOptions options);

    void pause();

    void resume();

    void clearDiskCache();


    void clearMemory();

}
