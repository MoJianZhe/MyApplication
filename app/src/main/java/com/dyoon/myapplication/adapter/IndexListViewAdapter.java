package com.dyoon.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dyoon.myapplication.R;

import java.util.List;

/**
 * Created by jun on 2017/2/22.
 */

public class IndexListViewAdapter extends BaseAdapter {
    private List<String >list;
    private LayoutInflater layoutInflater;
    public IndexListViewAdapter(Context context, List<String> list) {
        this.list=list;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
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
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.adpter_index_listview_item, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.textView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(getItem(position));
        return convertView;
    }
    static class ViewHolder{
        TextView textView;
    }
}
