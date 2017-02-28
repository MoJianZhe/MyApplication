package com.dyoon.myapplication.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.dyoon.myapplication.R;

import java.util.List;

/**
 * Created by jun on 2016/11/19.
 */

public class MyExpandableAdapter implements ExpandableListAdapter {
    private LayoutInflater layoutInflater;
    private List<String>groupList;
    private List<List<String>>childList;

    public MyExpandableAdapter(Context context, List<String> group, List<List<String>> child) {
        this.layoutInflater = LayoutInflater.from(context);
        this.groupList=group;
        this.childList=child;
    }
    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getGroupCount() {
        return groupList==null?0:groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public String getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //是否有稳定的Id
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder=null;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = layoutInflater.inflate(R.layout.expandable, null);
            groupViewHolder.textView = (TextView) convertView.findViewById(R.id.txt_expandable_tile);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder= (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.textView.setText(getGroup(groupPosition));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder=null;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = layoutInflater.inflate(R.layout.expandable, null);
            childViewHolder.title = (TextView) convertView.findViewById(R.id.txt_expandable_tile);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder= (ChildViewHolder) convertView.getTag();
        }
        childViewHolder.title.setText(getChild(groupPosition,childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (getGroupCount()==0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onGroupExpanded(int groupPosition) {

    }

    @Override
    public void onGroupCollapsed(int groupPosition) {

    }

    @Override
    public long getCombinedChildId(long groupId, long childId) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long groupId) {
        return 0;
    }

    private static  class GroupViewHolder{
        TextView textView;
    }
    private static  class ChildViewHolder{
        TextView title;
    }

}
