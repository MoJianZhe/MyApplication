package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dyoon.myapplication.R;
import com.dyoon.myapplication.view.UnderLineLinearLayout;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2016/12/5.
 */

public class    LinearoutActivity extends Activity {
    
    private static final String TAG = "LinearoutActivity";
    private List<String>statList;
    private List<String>operationList=new ArrayList<>();
    private UnderLineLinearLayout underLineLinearLayout;
    private FloatingActionMenu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_underlinelinearlayout);
        underLineLinearLayout = (UnderLineLinearLayout) findViewById(R.id.underline_layout);
        underLineLinearLayout.setPosition(2);
        menu =(FloatingActionMenu)findViewById(R.id.multipe_actions);
        getData();
        initButton(operationList);
        initView(statList);

    }

    private void initButton(final List<String> list) {
        Log.e(TAG, "start initButton method");
        for ( int i=0;i<list.size();i++) {
            FloatingActionButton floatingActionButton = new FloatingActionButton(this);
            floatingActionButton.setLabelText(list.get(i));
            floatingActionButton.setImageResource(R.drawable.ic_edit);
            final String name = list.get(i);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnClick(name);
                }
            });
            menu.addMenuButton(floatingActionButton);
        }

    }

    private void initView(List<String> statList) {
        for(int i=0;i<statList.size();i++) {
            View v = LayoutInflater.from(LinearoutActivity.this).inflate(R.layout.item_test,underLineLinearLayout,false);
            TextView textView = (TextView) v.findViewById(R.id.tx_action);
            textView.setText(statList.get(i));
            underLineLinearLayout.addView(v);
        }
        Log.i(TAG, "initView: 完成了子view的添加");
    }

    private void getData() {
        statList = new ArrayList<>();
        statList.add("one");
        statList.add("two");
        statList.add("three");
        statList.add("four");
        statList.add("five");
        operationList.add("promote");
        operationList.add("discard");
        operationList.add("reject");
        operationList.add("publish");

    }

    private void btnClick(String name) {
        switch (name) {
            case "promote":
                Log.e(TAG, "promote");
                break;
            case "discard":
                Log.e(TAG, "discard");
                break;
            case "reject":
                Log.e(TAG, "reject");
                break;
            case "publish":
                Log.e(TAG, "publish");
                break;
            default:
                break;
        }
    }
}
