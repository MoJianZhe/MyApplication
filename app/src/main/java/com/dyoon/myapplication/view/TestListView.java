package com.dyoon.myapplication.view;

import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by jun on 2016/12/3.
 */

public class TestListView  {
    ListView listView;

    private void test() {
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int lastVisibleIndex=view.getLastVisiblePosition();

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }
}
