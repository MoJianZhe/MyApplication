package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dyoon.myapplication.R;

import org.w3c.dom.Text;
import org.xutils.view.annotation.ContentView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * 用来测试如果将数据在onStart方法里加载，是不是先会显示一片空白
 * Created by jun on 2017/3/9.
 */

public class LifecycleOfActivityTest extends Activity {
    private static final String TAG = "LifecycleOfActivityTest";
    private List<String > dataList;
    private ListView listView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle_test);
        listView= (ListView) findViewById(R.id.list_view);
        this.button = (Button) findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LifecycleOfActivityTest.this, ListenerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");

        getData();
        listView.setAdapter(new ThisAdapter(this,dataList));
        Log.i(TAG, "onStart: listview width:"+listView.getWidth());

    }


    public void getData() {
        dataList = new ArrayList<>();
        dataList.add("hello ");
        dataList.add("java");
        dataList.add("Android");
        dataList.add("c");
        dataList.add("python");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: listview width:"+listView.getWidth());
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "OnStop方法执行中", Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        this.test();
    }

    private void test() {
        Log.i(TAG, "test: "+"先死还是先destroy呢？");
    }

    private class ThisAdapter extends BaseAdapter{
        private List<String>list;
        private LayoutInflater layoutInflater;

        public ThisAdapter(Context context, List<String> list) {
            this.layoutInflater = LayoutInflater.from(context);
            this.list=list;
        }
        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public String  getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.adapter_thisadpter, null);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder= ((ViewHolder) convertView.getTag());
            }
            viewHolder.textView.setText(getItem(position));
            return convertView;
        }
         class ViewHolder{
            TextView textView;
        }
    }
}
