package com.dyoon.myapplication.ui.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.dyoon.myapplication.R;
import com.dyoon.myapplication.adapter.IndexListViewAdapter;
import com.dyoon.myapplication.view.IndexBar;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2017/2/22.
 */

@ContentView(R.layout.activity_index_listview)
public class IndexListViewActivity extends Activity implements IndexBar.IndexChangeListener{
    @ViewInject(R.id.listview)
    private ListView listView;
    @ViewInject(R.id.index_bar)
    private IndexBar indexBar;
    @ViewInject(R.id.text_view)
    private TextView textView;
    private List<String >strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        listView.setAdapter(new IndexListViewAdapter(this,strings));
        initList();
        indexBar.setListener(this);
    }

    private void initList() {
        for (int i = 65; i < 91; i++) {
            strings.add(String .valueOf(((char) i)));
        }
    }

    @Override
    public void onIndexChanged(String index, boolean showIndicator) {
        textView.setText(index);
        //如果只用if(showIndicator){显示}那么当它动作离开的时候就不会隐藏
        textView.setVisibility(showIndicator?View.VISIBLE:View.GONE);
        int position=-1;
        for (String string : strings) {
            if (string.equals(index)) {
                position = strings.indexOf(string);
                break;
            }
        }
        if (position != -1) {
            listView.setSelection(position);
        }
    }
}
