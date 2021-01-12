package com.lilu.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.kingja.loadsir.callback.SuccessCallback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.kingja.loadsir.core.Transport;
import com.lilu.base.R;
import com.lilu.base.utils.ActivityUtils;
import com.lilu.base.utils.StringUtils;
import com.lilu.base.utils.viewutils.TextViewUtils;
import com.lilu.base.widget.statuslayout.EmptyCallback;
import com.lilu.base.widget.statuslayout.ErrorCallback;
import com.lilu.base.widget.statuslayout.LoadingCallback;
import com.lilu.base.widget.statuslayout.StatusLayout;

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


    Toolbar base_toolbar;
    TextView base_title_tv;
    protected FrameLayout content_view;

    protected LoadService statusManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //加入栈管理
        ActivityUtils.getManager().addActivity(this);
        //设置父布局内容
        View rootView = View.inflate(this,R.layout.layout_base,null);
        setContentView(rootView);
        //获取标题栏
        base_toolbar = rootView.findViewById(R.id.base_toolbar);
        //获取标题
        base_title_tv = rootView.findViewById(R.id.base_title_tv);
        //获取状态布局
        content_view = rootView.findViewById(R.id.base_frame);

        //沉浸式状态栏的设置
        ImmersionBar.with(this)
                .statusBarDarkFont(true,0.2f)
                .init();

        //初始化设置Toolbar
        setSupportActionBar(base_toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);        //隐藏默认标题
        base_toolbar.setNavigationIcon(R.drawable.ic_toolbar_back);
        base_toolbar.setNavigationOnClickListener(view -> {
                finish();
            });

        //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
        View contentView = View.inflate(this,getContentView(),null);
        if(contentView != null){
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            content_view.addView(contentView,params);

            statusManager = LoadSir.getDefault().register(content_view);

        }
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

    //界面状态管理相关
    /**
     * 显示加载中
     */
    protected void showLoading(){
        statusManager.showCallback(LoadingCallback.class);
    }

    /**
     * 显示空状态
     * @param emptyText 空状态提示
     */
    protected void showEmpty(String emptyText){
        statusManager.setCallBack(EmptyCallback.class,(context,v)->{
            if(!StringUtils.isEmpty(emptyText)){
                TextView empty_tv = v.findViewById(R.id.status_empty_tv);
                TextViewUtils.setText(empty_tv,emptyText);
            }
        });

        statusManager.showCallback(EmptyCallback.class);
    }

    /**
     * 显示错误状态
     */
    protected void showError(String errorText,String errorBt){
        statusManager.setCallBack(ErrorCallback.class, (context,v)->{
            if(!StringUtils.isEmpty(errorText)){
                TextView error_tv = v.findViewById(R.id.status_error_tv);
                TextViewUtils.setText(error_tv,errorText);
            }
            if(!StringUtils.isEmpty(errorBt)){
                Button error_bt = v.findViewById(R.id.status_error_bt);
                error_bt.setText(errorBt);
            }
        });

        statusManager.showCallback(ErrorCallback.class);
    }

    /**
     * 显示成功状态
     */
    protected void showSuccess(){
        statusManager.showCallback(SuccessCallback.class);
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
