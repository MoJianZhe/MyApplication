package com.dyoon.myapplication.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dyoon.myapplication.ui.fragment.Fragment1;
import com.dyoon.myapplication.ui.fragment.Fragment2;
import com.dyoon.myapplication.ui.fragment.Fragment3;

/**
 * Created by jun on 2016/12/21.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragment1();
            case 1:
                return new Fragment2();
            case 2:
                return new Fragment3();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "fragment1";
            case 1:
                return "fragment2";
            case 2:
                return "fragment3";
            default:
                break;
        }
        return null;
    }
}
