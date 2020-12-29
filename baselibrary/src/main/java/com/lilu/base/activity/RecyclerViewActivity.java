package com.lilu.base.activity;

import android.os.Bundle;

import com.lilu.base.R;
import com.lilu.base.utils.ObjectUtils;
import com.lilu.base.widget.smartrefresh.SmartRefreshLayout;
import com.lilu.base.widget.smartrefresh.api.RefreshLayout;
import com.lilu.base.widget.smartrefresh.listener.OnRefreshLoadMoreListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description:
 *
 * @author lilu on 2020/12/15
 * No one knows this better than me
 */
public abstract class RecyclerViewActivity extends BaseActivity {

    //下拉刷新 上拉加载更多
    SmartRefreshLayout base_srl;
    //列表控件
    RecyclerView base_rv;


    public abstract void setAdapter(RecyclerView baseRv);


    /**
     * 获取列表控件排列
     * @return
     */
    public RecyclerView.LayoutManager getLayoutManager(){
        //默认一个垂直排列
        return new LinearLayoutManager(this);

    }

    public void initRefreshLayout(SmartRefreshLayout srl){
    }


    @Override
    protected int getContentView() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        base_srl = findViewById(R.id.base_srl);
        //获取列表控件
        base_rv = findViewById(R.id.base_rv);
        //设置列表的排列顺序
        base_rv.setLayoutManager(getLayoutManager());
        //设置适配器
        setAdapter(base_rv);

        initRefreshLayout(base_srl);
    }
}
