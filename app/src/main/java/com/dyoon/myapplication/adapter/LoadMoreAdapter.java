package com.dyoon.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dyoon.myapplication.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by jun on 2017/2/20.
 */

public class LoadMoreAdapter extends BaseAdapter {
    private int selectPosition;
    private List<String >dataList;
    private LayoutInflater layoutInflater;
    /**map是用来保存是否包含的信息的
     * map保存了点击的item的位置，
     * 不要用map中的boolean值来判断，太麻烦了，加载更多的时候还要更新map
     * 用是否包含来判断
     * 点击的时候，如果有(说明已点击),就移除，如果没有，就添加。
     * 这是多选的情况，如果是单选的话，根本都不需要map的，只要一个int position就可以了
     * 不过这样的话，listView中的choiceMode根本就没什么用了
     */

    public  static HashMap<Integer, Boolean> selectMap = new HashMap<>();

    public LoadMoreAdapter(Context context, List<String> list) {
        this.dataList=list;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public String  getItem(int position) {
        return dataList.get(position);
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
            convertView = layoutInflater.inflate(R.layout.adapt_loadmore_item, null);
            viewHolder.textView = ((TextView) convertView.findViewById(R.id.textView3));
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(getItem(position));
        if (selectMap.containsKey(position)) {
            viewHolder.checkBox.setChecked(true);
        } else {
            viewHolder.checkBox.setChecked(false);
        }
        return convertView;
    }

    public static   class  ViewHolder{
        TextView textView;
        CheckBox checkBox;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
    }

    public int getSelectPosition() {
        return selectPosition;
    }
}
