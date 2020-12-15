package com.lilu.base.activity;

import android.os.Bundle;

import com.lilu.base.R;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Description:
 *
 * @author lilu on 2020/12/15
 * No one knows this better than me
 */
public abstract class RecyclerViewActivity extends BaseActivity {

    RecyclerView base_rv;

    abstract void initRecyclerView(RecyclerView recyclerView);

    @Override
    protected int getContentView() {
        return R.layout.layout_recyclerview;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        base_rv = findViewById(R.id.base_rv);

        initRecyclerView(base_rv);
    }
}
