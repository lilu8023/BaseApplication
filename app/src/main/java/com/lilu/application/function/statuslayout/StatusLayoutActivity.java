package com.lilu.application.function.statuslayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.lilu.application.Constance;
import com.lilu.application.R;
import com.lilu.base.activity.BaseActivity;
import com.lilu.base.http.RxHttp;
import com.lilu.base.http.transformer.ILoadingView;
import com.lilu.base.http.transformer.Transformer;
import com.lilu.base.utils.logger.Logger;

import androidx.annotation.NonNull;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 *
 * @author lilu on 2020/12/15
 * No one knows this better than me
 */
@Route(path = Constance.ACTIVITY_STATUS,extras = 1)
public class StatusLayoutActivity extends BaseActivity implements ILoadingView {


    @Override
    protected int getContentView() {
        return R.layout.activity_status;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setTitle("各种状态");

//        loadData();

        showLoading();
    }



    private void loadData(){
        RxHttp.getInstance()
                .create(TestHttpApiService.class)
                .getPublic()
                .compose(Transformer.switchSchedulers(this))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Logger.json(s);

//                        showSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();

//                        showError();
                    }

                    @Override
                    public void onComplete() {
                        Logger.i("onComplete");
                    }
                });
    }

    @Override
    public void showLoadingView() {
        Logger.i("开始请求");
        showLoading();
    }

    @Override
    public void hideLoadingView() {
//        showSuccess();
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
                showEmpty("草你嘛引入就可以");
                break;

            case R.id.status_error:
                showError("为什么引入就可以呢","要重试吗");
                break;
            case R.id.status_success:
                showSuccess();
                break;

        }

        return true;
    }

}
