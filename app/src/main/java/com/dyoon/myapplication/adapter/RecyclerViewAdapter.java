package com.dyoon.myapplication.adapter;

import android.content.Context;
import android.content.DialogInterface;
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
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private static final String TAG = "RecyclerViewAdapter";
    public static final String ACTION_CLICKED = "action_isClick";
    private Context context;
    private List<String> list;
    private LayoutInflater layoutInflater;

    public RecyclerViewAdapter(Context context, List<String> myList) {
        this.context=context;
        this.list=myList;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.adapter_recycler,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        setUpClickable(view, myViewHolder);
        return myViewHolder;
    }

    /**
     * 如果是在ViewHolder中设置click，因为是静态的，就无法调用notifyItemChanged(int)
     * @param view
     * @param myViewHolder
     */
    private void setUpClickable(View view, final MyViewHolder myViewHolder) {
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
                int adapterPosition=myViewHolder.getAdapterPosition();
                list.get(adapterPosition).toUpperCase();
                notifyItemChanged(adapterPosition,"click");
            }
        });
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.e("holder", holder.textView.toString());
        holder.textView.setText(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list==null ? 0:list.size();
    }



    public static  class MyViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.txt_view)
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            x.view().inject(this,itemView);
        /*    itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("test", "you click me ");

                }
            });*/

        }
    }

}
