package com.lilu.application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.lilu.application.function.main.MainFragment;
import com.lilu.application.function.main.MessageFragment;
import com.lilu.application.function.main.MineFragment;
import com.lilu.application.function.main.SocialFragment;
import com.lilu.base.activity.BaseActivity;
import com.lilu.base.adapter.ViewPagerAdapter;
import com.lilu.base.widget.bottomnavigation.NavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    ViewPager main_vp;
    NavigationBar main_nb;

    String titles[] = {"首页","社交","信息","我的"};
    int select[] = {R.drawable.tab1_selected,
            R.drawable.tab2_selected,
            R.drawable.tab3_selected,
            R.drawable.tab4_selected};

    int un_select[] = {R.drawable.tab1_normal,
            R.drawable.tab2_normal,
            R.drawable.tab3_normal,
            R.drawable.tab4_normal};

    List<Fragment> mFragments = new ArrayList<>();
    ViewPagerAdapter mAdapter;

    MainFragment mainFragment;
    MessageFragment messageFragment;
    SocialFragment socialFragment;
    MineFragment mineFragment;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

        main_vp = findViewById(R.id.main_vp);
        main_nb = findViewById(R.id.main_nb);

        //主页不显示actionbar
        showActionBar(false);

        mainFragment = new MainFragment();
        messageFragment = new MessageFragment();
        socialFragment = new SocialFragment();
        mineFragment = new MineFragment();

        mFragments.add(mainFragment);
        mFragments.add(messageFragment);
        mFragments.add(socialFragment);
        mFragments.add(mineFragment);

        mAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,mFragments);
        main_vp.setAdapter(mAdapter);

        main_nb.titleItems(titles);
        main_nb.selectIconItems(select);
        main_nb.normalIconItems(un_select);

        main_nb.setupWithViewPager(main_vp);
        main_nb.build();

    }

}