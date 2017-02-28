package com.dyoon.myapplication.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.TabLayout;


import com.dyoon.myapplication.adapter.ViewPagerAdapter;
import com.dyoon.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by jun on 2016/12/21.
 */

//如果不用AppCompatActivity,就无法使用getSupportFragmentManager();
    @ContentView(R.layout.activity_viewpage)
public class ViewPagerActivity extends AppCompatActivity {
    @ViewInject(R.id.container)
    private ViewPager viewPager;
    @ViewInject(R.id.toolbar)
    private Toolbar toolbar;
    @ViewInject(R.id.tabs)
    private TabLayout tabLayout;
    private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setSupportActionBar(toolbar);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        toolbar.setTitle("hello");
        tabLayout.setupWithViewPager(viewPager);
    }
}
