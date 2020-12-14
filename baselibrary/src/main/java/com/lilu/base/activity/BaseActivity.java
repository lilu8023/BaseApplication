package com.lilu.base.activity;

import android.os.Bundle;

import com.lilu.base.utils.ActivityManagerUtil;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Description:
 * 基础的activity
 * 1、封装了基础的toolbar
 * @author lilu on 2020/12/14
 *  No one knows this better than me
 */
public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 子类布局
     * 子类需要实现
     * @return 子类布局ID 必须是layout文件
     */
    protected abstract @LayoutRes int getContentView();

    /**
     * 子类入口
     * 子类需要实现
     * @param savedInstanceState 缓存状态
     */
    protected abstract void init(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除栈管理
        ActivityManagerUtil.getManager().removeActivity(this);
    }
}
