package com.lilu.application.function.main;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Description:
 *
 * @author lilu on 2020/12/22
 * No one knows this better than me
 */
public abstract class TestFragment extends Fragment {

    private String TAG = getClass().getSimpleName();

    private boolean isFirst = true;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        Log.i(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i(TAG,"onCreateView");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i(TAG,"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i(TAG,"onResume");

        if(isFirst){
            loadData();
            isFirst = false;
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.i(TAG,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        Log.i(TAG,"onDestroyView");
        isFirst = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(TAG,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();

        Log.i(TAG,"onDetach");
    }

    public abstract void loadData();
}
