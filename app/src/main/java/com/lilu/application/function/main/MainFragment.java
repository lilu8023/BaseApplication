package com.lilu.application.function.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.lilu.application.Constance;
import com.lilu.application.R;
import com.lilu.application.function.main.adapter.MainMenuAdapter;
import com.lilu.application.function.main.entity.MainMenuEntity;
import com.lilu.base.utils.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Description:
 *
 * @author lilu on 2020/12/22
 * No one knows this better than me
 */
public class MainFragment extends TestFragment{

    private View rootView;

    private RecyclerView main_menu_rv;

    private List<MainMenuEntity> menuList = new ArrayList<>();
    MainMenuAdapter menuAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_main,container,false);

        main_menu_rv = rootView.findViewById(R.id.main_menu_rv);

        menuAdapter = new MainMenuAdapter(menuList);
        menuAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                switch (menuList.get(position).getId()){
                    case 1:
                        ARouter.getInstance()
                                .build(Constance.ACTIVITY_NOTIFY)
                                .navigation();
                        break;
                    case 2:
                        ARouter.getInstance()
                                .build(Constance.ACTIVITY_STATUS)
                                .navigation();
                        break;
                }
            }
        });
        main_menu_rv.setLayoutManager(new GridLayoutManager(getActivity(),4));
        main_menu_rv.setAdapter(menuAdapter);

        return rootView;
    }

    @Override
    public void loadData() {

        MainMenuEntity menu1 = new MainMenuEntity(1,"通知");
        MainMenuEntity menu2 = new MainMenuEntity(2,"状态");
        MainMenuEntity menu3 = new MainMenuEntity(3,"glide");
        MainMenuEntity menu4 = new MainMenuEntity(4,"通知");
        MainMenuEntity menu5 = new MainMenuEntity(5,"通知");


        menuList.add(menu1);
        menuList.add(menu2);
        menuList.add(menu3);
        menuList.add(menu4);
        menuList.add(menu5);

        menuAdapter.notifyDataSetChanged();
    }

}
