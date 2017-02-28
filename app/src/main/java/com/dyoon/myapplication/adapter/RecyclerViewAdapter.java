package com.dyoon.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dyoon.myapplication.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by jun on 2016/12/23.
 */

//<>中填写自己实现的viewholder
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private List<String> lsit;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context, List<String> myList) {
        this.context=context;
        this.lsit=myList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(layoutInflater.inflate(R.layout.adapter_recycler,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e("holder", holder.textView.toString());
        holder.textView.setText(lsit.get(position));
    }


    @Override
    public int getItemCount() {
        return lsit==null ? 0:lsit.size();
    }


    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.txt_view)
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("test", "you click me ");
                }
            });

        }
    }


}
