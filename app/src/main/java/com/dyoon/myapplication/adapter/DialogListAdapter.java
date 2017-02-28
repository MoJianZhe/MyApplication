package com.dyoon.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dyoon.myapplication.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by jun on 2016/12/27.
 */

public class DialogListAdapter extends BaseAdapter {
    private List<String >listName;
    private LayoutInflater layoutInflater;

    public DialogListAdapter(Context context, List<String> list) {
        this.listName=list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listName==null?0:listName.size();
    }

    @Override
    public String getItem(int position) {
        return listName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adapter_dialog, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.userName.setText(getItem(position));
        return convertView;
    }
    private static class ViewHolder{
        @ViewInject(R.id.txt_dialog)
        TextView userName;
    }
}
