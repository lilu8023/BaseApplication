package com.lilu.application.function.notify;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lilu.application.Constance;
import com.lilu.base.activity.RecyclerViewActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Description:
 * 通知适配界面
 * @author lilu on 2020/12/15
 * No one knows this better than me
 */
@Route(path = Constance.ACTIVITY_NOTIFY)
public class NotificationActivity extends RecyclerViewActivity {


    private NotificationAdapter mAdapter;
    private List<String> mList = new ArrayList<>();

    @Override
    public RecyclerView.Adapter initAdapter() {

        mList.add("通知1");
        mList.add("通知2");
        mList.add("通知3");
        mList.add("通知4");
        mList.add("通知5");
        mList.add("通知6");
        mAdapter = new NotificationAdapter(this,mList);

        return mAdapter;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);

        setTitle("通知适配");
    }
}
