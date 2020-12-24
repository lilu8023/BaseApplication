package com.lilu.base.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.lilu.base.R;
import com.lilu.base.utils.ActivityUtils;
import com.lilu.base.utils.viewutils.TextViewUtils;
import com.lilu.base.widget.statuslayout.callback.Callback;
import com.lilu.base.widget.statuslayout.callback.EmptyCallback;
import com.lilu.base.widget.statuslayout.callback.ErrorCallback;
import com.lilu.base.widget.statuslayout.callback.LoadingCallback;
import com.lilu.base.widget.statuslayout.callback.SuccessCallback;
import com.lilu.base.widget.statuslayout.core.LoadService;
import com.lilu.base.widget.statuslayout.core.LoadSir;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

    LoadService status;

    Toolbar base_toolbar;
    TextView base_title_tv;
    FrameLayout base_frame;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //加入栈管理
        ActivityUtils.getManager().addActivity(this);
        //设置父布局内容
        super.setContentView(R.layout.layout_base);
        //获取标题栏
        base_toolbar = findViewById(R.id.base_toolbar);
        //获取标题
        base_title_tv = findViewById(R.id.base_title_tv);
        //获取状态布局
        base_frame = findViewById(R.id.base_frame);

        //沉浸式状态栏的设置
        ImmersionBar.with(this)
                .statusBarDarkFont(true,0.2f)
                .init();

        //初始化设置Toolbar
        setSupportActionBar(base_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);        //隐藏默认标题
//        base_toolbar.setNavigationIcon(R.drawable.ic_title_back);
        base_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
        View contentView = View.inflate(this,getContentView(),null);
        base_frame.addView(contentView);

        status = LoadSir.getDefault().register(base_frame, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onErrorReload();
            }
        });
        //子类初始化
        init(savedInstanceState);

    }

    /**
     * 是否显示actionBar
     * 默认是显示的
     * 针对主页，主页可能不需要actionbar，会在主页
     * @param isShow 是否显示标识
     */
    protected void showActionBar(boolean isShow){
        if(isShow){
            getSupportActionBar().show();
        }else{
            getSupportActionBar().hide();
        }
    }
    /**
     * 设置标题
     * 内容可以空，但是标题控件不能为空
     * 工具类设置中只需要判断控件是否为空，内容不需要；内容为空则会默认设值""
     * @param title 标题内容
     */
    protected void setTitle(String title){

        TextViewUtils.setText(base_title_tv,title);

    }


    protected void showLoading(){
        status.showCallback(LoadingCallback.class);
    }

    protected void showError(){
        status.showCallback(ErrorCallback.class);
    }

    protected void showEmpty(){
        status.showCallback(EmptyCallback.class);
    }

    protected void showSuccess(){
        status.showCallback(SuccessCallback.class);
    }

    protected void onErrorReload(){

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
        ActivityUtils.getManager().removeActivity(this);
    }
}
