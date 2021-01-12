package com.lilu.base.widget.statuslayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lilu.base.R;
import com.lilu.base.utils.StringUtils;
import com.lilu.base.widget.LoadingView;

import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Description:
 *
 * @author lilu on 2021/1/6
 * No one knows this better than me
 */
public class StatusLayout extends ConstraintLayout {


    private final String CONTENT = "STATUS_CONTENT";
    private final String LOADING = "STATUS_LOADING";
    private final String EMPTY = "STATUS_EMPTY";
    private final String ERROR = "STATUS_ERROR";


    //加载中
    private View loading_layout;

    //空
    private View empty_layout;
    //空图片提示
    private ImageView empty_iv;
    //空文字提示
    private TextView empty_tv;

    //错误
    private View error_layout;
    //错误图片提示
    private ImageView error_iv;
    //错误文字提示
    private TextView error_tv;
    //错误按钮
    private Button error_bt;


    public StatusLayout(Context context) {
        this(context, null);
    }

    public StatusLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 装载加载中状态
     */
    private void inflateLoadingView(){
        if(loading_layout == null){
            loading_layout = LayoutInflater.from(getContext()).inflate(R.layout.layout_loading, this, false);
            LayoutParams loadingParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addView(loading_layout,loadingParams);
        }else{
            loading_layout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 装载暂无内容空状态
     */
    private void inflateEmptyView(){
        if(empty_layout == null){
            empty_layout = LayoutInflater.from(getContext()).inflate(R.layout.layout_empty, this, false);

            empty_iv = empty_layout.findViewById(R.id.status_empty_iv);
            empty_tv = empty_layout.findViewById(R.id.status_empty_tv);

            LayoutParams loadingParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addView(empty_layout,loadingParams);
        }else{
            empty_layout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 装载错误状态
     */
    private void inflateErrorView(){
        if(error_layout == null){
            error_layout = LayoutInflater.from(getContext()).inflate(R.layout.layout_error, this, false);

            error_iv = error_layout.findViewById(R.id.status_error_iv);
            error_tv = error_layout.findViewById(R.id.status_error_tv);
            error_bt = error_layout.findViewById(R.id.status_error_bt);

            LayoutParams loadingParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addView(error_layout,loadingParams);
        }else{
            error_layout.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 显示加载中
     */
    public void showLoading(){
        switchState(LOADING,0,null,null,null);
    }

    /**
     * 显示默认暂无内容空状态
     */
    public void showEmpty(){
        switchState(EMPTY,0,"暂无内容",null,null);
    }

    /**
     * 动态设置空状态提示语
     * @param des 空提示语
     */
    public void showEmpty(String des){
        switchState(EMPTY,0,des,null,null);
    }

    /**
     * 显示默认错误
     */
    public void showError(){
        switchState(ERROR,0,"错误了","再试一次",null);
    }

    public void showError(String des){
        switchState(ERROR,0,des,"再试一次",null);
    }
    /**
     * 显示成功状态
     */
    public void showContent(){
        switchState(CONTENT,0,null,null,null);
    }

    /**
     * 隐藏所有状态
     */
    private void hideAllState(){

        if(loading_layout != null){
            loading_layout.setVisibility(GONE);
        }

        if(empty_layout != null){
            empty_layout.setVisibility(GONE);
        }

        if(error_layout != null){
            error_layout.setVisibility(GONE);
        }
    }

    /**
     * 显示对应的状态
     * @param state 状态值
     * @param icon 图片提示
     * @param des  文字提示
     * @param buttonText 按钮文字
     * @param clickListener 按钮点击事件
     */
    private void switchState(String state,int icon,String des,String buttonText,OnClickListener clickListener){

        hideAllState();
        switch (state){
            case CONTENT:
                break;
            case LOADING:
                inflateLoadingView();
                break;
            case EMPTY:
                inflateEmptyView();
                if(!StringUtils.isEmpty(des)){
                    empty_tv.setText(des);
                }else{
                    empty_tv.setText("暂无内容");
                }
                break;
            case ERROR:
                inflateErrorView();
                if(!StringUtils.isEmpty(des)){
                    error_tv.setText(des);
                }else{
                    error_tv.setText("暂无内容");
                }
                break;
        }
    }

}
