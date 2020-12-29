package com.lilu.application.function.notify;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.lilu.application.R;
import com.lilu.base.activity.BaseActivity;

import androidx.annotation.Nullable;

/**
 * Description:
 *
 * @author lilu on 2020/12/29
 * No one knows this better than me
 */
public class ImportantActivity extends BaseActivity {
    @Override
    protected int getContentView() {
        return R.layout.activity_important;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }
}
