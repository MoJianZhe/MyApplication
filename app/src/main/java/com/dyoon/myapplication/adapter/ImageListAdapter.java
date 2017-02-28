package com.dyoon.myapplication.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dyoon.myapplication.R;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by jun on 2017/2/16.
 */

public class ImageListAdapter extends BaseAdapter {
    private List<String> imgURL;
    private LayoutInflater layoutInflater;
    private ImageOptions imageOptions = new ImageOptions.Builder()
            .setSize(DensityUtil.dip2px(100), DensityUtil.dip2px(100)).setRadius(DensityUtil.dip2px(100))
            .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            .setFailureDrawableId(R.mipmap.ic_launcher)
            .setLoadingDrawableId(R.mipmap.ic_launcher)
            .build();

    public ImageListAdapter(List<String> url, Context context) {
        this.imgURL=url;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return imgURL.size();
    }

    @Override
    public String getItem(int position) {
        return imgURL.get(position);
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
            convertView = layoutInflater.inflate(R.layout.adapter_img_list_item, null);
            x.view().inject(viewHolder, convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
//        x.image().bind(viewHolder.imageView,getItem(position),imageOptions);
        BitmapDrawable bitmapDrawable = ((BitmapDrawable) convertView.getContext()
                .getResources().getDrawable(R.mipmap.moon));
        //一般使用BitmapFactory.decodeStream从储存卡中读图片
        //或者是从url中读图片
        viewHolder.imageView.setImageBitmap(bitmapDrawable.getBitmap());
//        viewHolder.imageView.setImageResource(R.mipmap.androidkillapple);
        return convertView;
    }


    static class ViewHolder{
        @ViewInject(R.id.img)
        ImageView imageView;
    }

}
