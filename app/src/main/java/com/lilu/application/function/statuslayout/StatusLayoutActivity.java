package com.lilu.application.function.statuslayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lilu.application.Constance;
import com.lilu.application.R;
import com.lilu.base.activity.BaseActivity;
import com.lilu.base.utils.logger.Logger;
import com.lilu.base.widget.statuslayout.callback.Callback;
import com.lilu.base.widget.statuslayout.callback.EmptyCallback;
import com.lilu.base.widget.statuslayout.callback.ErrorCallback;
import com.lilu.base.widget.statuslayout.callback.LoadingCallback;
import com.lilu.base.widget.statuslayout.core.LoadService;
import com.lilu.base.widget.statuslayout.core.LoadSir;

import androidx.annotation.NonNull;

/**
 * Description:
 *
 * @author lilu on 2020/12/15
 * No one knows this better than me
 */
@Route(path = Constance.ACTIVITY_STATUS)
public class StatusLayoutActivity extends BaseActivity {


    @Override
    protected int getContentView() {
        return R.layout.activity_status;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        setTitle("各种状态");

    }

    @Override
    protected void onErrorReload() {
        super.onErrorReload();

        Logger.i("重新加载数据");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.status_layout_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.status_loading:
                showLoading();
                break;

            case R.id.status_empty:
                showEmpty();
                break;

            case R.id.status_error:
                showError();
                break;

            case R.id.status_success:
                showSuccess();
                break;

        }

        return true;
    }
}
