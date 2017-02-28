package com.dyoon.myapplication.ui.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dyoon.myapplication.adapter.LoadMoreAdapter;
import com.dyoon.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2017/2/20.
 */

public class LoadMoreActivity extends Activity {
    private static final String TAG = "LoadMoreActivity";
    private ListView listView;
    private List<String>list=new ArrayList<>();
    private boolean isLoading=false;
    private int itemCount;
    private View footView;
    private LoadMoreAdapter lma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_more);
        listView = ((ListView) findViewById(R.id.list_view));
        footView = LayoutInflater.from(this).inflate(R.layout.foot_view, null);
        initData();
        lma = new LoadMoreAdapter(this, list);
        listView.setAdapter(lma);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int lastIndex=view.getLastVisiblePosition();
                if (!isLoading && scrollState == this.SCROLL_STATE_IDLE&&lastIndex==itemCount-1) {
                    isLoading=true;
                    listView.addFooterView(footView);
                    loadMore();
                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                itemCount=totalItemCount;
            }
        });

     listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             //有很多人呢用viw.getTag()，可得到viewHolder,viewHolder必须是public
             if (LoadMoreAdapter.selectMap.containsKey(position)) {
                 LoadMoreAdapter.selectMap.remove(position);
             } else {
                LoadMoreAdapter.selectMap.put(position, true);
             }
             lma.notifyDataSetChanged();
         }
     });
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            list.add("test"+i);
        }
    }

    private void loadMore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < 10; i++) {
                    list.add("newText"+i);
                }
                runOnUiThread(new Runnable() {//还有一种更新ui的方法使用handler
                    @Override
                    public void run() {
                        lma.setDataList(list);
                        lma.notifyDataSetChanged();
                        loadCompleted();
                    }
                });
            }
        }).start();

    }
    private  void loadCompleted() {
        isLoading=false;
        listView.removeFooterView(footView);//注意不是removeView(footView)不然会报UnSorppotOptionException
    }
}
