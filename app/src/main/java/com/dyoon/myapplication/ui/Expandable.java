package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.dyoon.myapplication.adapter.MyExpandableAdapter;
import com.dyoon.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2016/11/19.
 */

public class Expandable extends Activity {
    private ExpandableListView expandableListView ;
    private List<String>groupList=new ArrayList<>();
    private List<List<String>>childList=new ArrayList<>();
    private Handler myHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);
/*        getData();
        getChildData();*/
        expandableListView = (ExpandableListView) findViewById(R.id.detail_listView);
        MyExpandableAdapter myExpandableAdapter=new MyExpandableAdapter(this,groupList,childList);
        expandableListView.setAdapter(myExpandableAdapter);
        TextView textView = (TextView) findViewById(R.id.txt_expandable);
        if (textView != null) {
            expandableListView.setEmptyView(textView);
        } else {
            Log.e("textVeiw", "textView为null");
        }
    }

    //设置expandable的监听事件
    public void setOnGroupListener() {
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Log.e("listener", "group click method");
                return false;
            }
        });
    }

    private void getData() {
        groupList.add("火影");
        groupList.add("死神");
        groupList.add("海贼");

    }

    private void getChildData() {
        List<String> list1 = new ArrayList<>();
        list1.add("佩恩");list1.add("宇智波");
        list1.add("仙术");
        List<String> list2 = new ArrayList<>();
        list2.add("蓝染");
        list2.add("虚");
        list2.add("京乐");
        List<String> list3 = new ArrayList<>();
        list3.add("白胡子");
        list3.add("黑胡子");
        list3.add("青雉");
        childList.add(list1);
        childList.add(list2);
        childList.add(list3);
    }


}
