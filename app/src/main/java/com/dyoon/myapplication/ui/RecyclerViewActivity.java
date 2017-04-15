package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dyoon.myapplication.adapter.RecyclerViewAdapter;
import com.dyoon.myapplication.R;
import com.dyoon.myapplication.ui.animal.RecycleViewItemAnimal;
import com.dyoon.myapplication.ui.animal.TestAnimator;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jun on 2016/12/23.
 */
@ContentView(R.layout.activity_recycler)
public class RecyclerViewActivity extends Activity {
    private static final String TAG = "RecyclerViewActivity";
    @ViewInject(R.id.recyclerview)
    private RecyclerView recyclerView;
    @ViewInject(R.id.swipe_refresh)
    private SwipeRefreshLayout swipeRefreshLayout;
    @ViewInject(R.id.txt)
    private TextView textView;
    private List<String> list = new ArrayList<>();
    private android.view.ActionMode actionMode;//目录
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
        recyclerView.setAdapter(new RecyclerViewAdapter(this,list));
        textView.setText("hello world");
        final MyActionModelCallback callback=new MyActionModelCallback();
        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                actionMode = startActionMode((android.view.ActionMode.Callback) callback);
                v.setSelected(true);
                return true;
            }
        });
        Log.i(TAG, "onCreate: 开始animator");
        recyclerView.setItemAnimator(new RecycleViewItemAnimal());
    }

    public void  getData() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<5;i++) {
                            Log.i(TAG, "run: add"+i);
                            list.add("I是"+i);
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 5000);
            }
        });
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        list.add("six");
        list.add("seven");
        list.add("eight");
        list.add("nine");
    }



    private class MyActionModelCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater menuInflater=mode.getMenuInflater();
            menuInflater.inflate(R.menu.action_model,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.test:
                    Log.i(TAG, "onActionItemClicked: you click test");
                    actionMode.finish();
                    break;
                case R.id.test2:
                    Log.i(TAG, "onActionItemClicked: you click test2");
                    actionMode.finish();
                    break;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }

}
