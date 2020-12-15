package com.lilu.application.function.imageloader;

import android.os.Bundle;
import android.widget.ImageView;

import com.lilu.application.R;
import com.lilu.base.activity.BaseActivity;
import com.lilu.base.utils.imageloader.ImageLoader;

/**
 * Description:
 * 图片加载工具测试界面
 * @author lilu on 2020/12/15
 * No one knows this better than me
 */
public class ImageLoaderActivity extends BaseActivity {

    ImageView iv;

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        ImageLoader.getInstance()
                .load("")
                .placeholder(R.drawable.ic_launcher_background)
                .into(iv);
    }
}
