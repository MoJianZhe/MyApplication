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
 * Created by jun on 2017/1/3.
 */

public class ActionModeAdapter extends BaseAdapter {
    private List<String> list;
    private LayoutInflater layoutInflater;

    public ActionModeAdapter(Context context, List<String> lll) {
        this.list=lll;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
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
            convertView = layoutInflater.inflate(R.layout.adapter_aciton_mode, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.txt_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(getItem(position));
        return convertView;
    }

    private static class ViewHolder{
        TextView textView;
    }
}
