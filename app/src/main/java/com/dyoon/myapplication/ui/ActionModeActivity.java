package com.dyoon.myapplication.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dyoon.myapplication.adapter.ActionModeAdapter;
import com.dyoon.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by jun on 2017/1/3.
 */
@ContentView(R.layout.activity_action_mode)
public class ActionModeActivity extends Activity{
    @ViewInject(R.id.list_view)
    private ListView listView;
    private List<String> list;
    private ActionMode actionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        list = new ArrayList<>();
        list.add("one");
        list.add("two");
        ActionModeAdapter actionModeAdapter = new ActionModeAdapter(this, list);
        listView.setAdapter(actionModeAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    return false;
                }
                actionMode = startActionMode(new ActionModeCallback());
                view.setSelected(true);
                return true;
            }
        });

    }

    private class ActionModeCallback implements ActionMode.Callback{

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
            boolean flag=false;
            switch (item.getItemId()) {
                case R.id.test:
                    Log.i(TAG, "onActionItemClicked: hello test");
                    flag=true;
                    break;
                case R.id.test2:
                    Log.i(TAG, "onActionItemClicked: hell test2");
                    flag=true;
                    break;
            }
            return flag;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }
    }
}
