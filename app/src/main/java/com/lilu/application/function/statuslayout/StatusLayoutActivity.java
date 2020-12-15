package com.lilu.application.function.statuslayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lilu.application.R;
import com.lilu.base.activity.BaseActivity;
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
public class StatusLayoutActivity extends BaseActivity {

    LoadService status;

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        status = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {

            }
        });
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
                status.showCallback(LoadingCallback.class);
                break;

            case R.id.status_empty:
                status.showCallback(EmptyCallback.class);
                break;

            case R.id.status_error:
                status.showCallback(ErrorCallback.class);
                break;

            case R.id.status_success:
                status.showSuccess();
                break;

        }

        return true;
    }
}
