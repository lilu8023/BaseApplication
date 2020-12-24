package com.lilu.application.function.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lilu.application.R;
import com.lilu.base.utils.logger.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Description:
 *
 * @author lilu on 2020/12/22
 * No one knows this better than me
 */
public class MineFragment extends TestFragment {
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main,container,false);

        return rootView;
    }

    @Override
    public void loadData() {
        Logger.i("MineFragment 进行了懒加载");
    }
}
