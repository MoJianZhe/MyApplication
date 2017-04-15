package com.dyoon.myapplication.ui.animal;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.AnimationSet;

import com.dyoon.myapplication.adapter.RecyclerViewAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by jun on 2017/4/8.
 */

public class RecycleViewItemAnimal extends DefaultItemAnimator {
    private static final String TAG = "RecycleViewItemAnimal";

    Map<RecyclerView.ViewHolder, Animator> likeAnimationMap = new HashMap<>();

    /**
     * 重写该方法返回TRUE，执行动画的时候可以复用viewHolder;
     * @param viewHolder
     * @param payloads
     * @return
     */
    @Override
    public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull List<Object> payloads) {
        return true;
    }

    @NonNull
    @Override
    public ItemHolderInfo recordPreLayoutInformation(@NonNull RecyclerView.State state, @NonNull RecyclerView.ViewHolder viewHolder, int changeFlags, @NonNull List<Object> payloads) {
   /*     if (changeFlags == FLAG_CHANGED) {
            for (Object payload : payloads) {
                if (payload instanceof String) {
                    return new RecycleViewItemInfo((String) payload);
                }
            }
        }*/
        return super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads);
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        Log.i(TAG, "animateAdd: ");
        return super.animateAdd(holder);
    }


    @Override
    public void runPendingAnimations() {
        Log.i(TAG, "runPendingAnimations: ");
        super.runPendingAnimations();

    }

    @Override
    public boolean animateChange(@NonNull RecyclerView.ViewHolder oldHolder, @NonNull RecyclerView.ViewHolder newHolder, @NonNull ItemHolderInfo preInfo, @NonNull ItemHolderInfo postInfo) {
        Log.i(TAG, "animateChange: simpleAnimator");
     /*   if (oldHolder != newHolder) {
            return super.animateChange(oldHolder, newHolder, preInfo, postInfo);
        }*/

        RecyclerViewAdapter.MyViewHolder myViewHolder = ((RecyclerViewAdapter.MyViewHolder) newHolder);
        animateStart(myViewHolder);
        return false;

    }

/*   @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromX, int fromY, int toX, int toY) {
        Log.i(TAG, "animateChange: DefaultItemAnimator");
        animateStart(((RecyclerViewAdapter.MyViewHolder) newHolder));
        return super.animateChange(oldHolder, newHolder, fromX, fromY, toX, toY);
    }


    }*/
    private void animateStart(final RecyclerViewAdapter.MyViewHolder holder) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView,"rotation",0,180,0);
        animator.setDuration(2000);
        animator.start();
    }


    public static class RecycleViewItemInfo extends ItemHolderInfo{
        public String text;
        public RecycleViewItemInfo(String text) {
            this.text=text;
        }

    }
}
