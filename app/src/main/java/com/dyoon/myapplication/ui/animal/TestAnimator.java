package com.dyoon.myapplication.ui.animal;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;

import com.dyoon.myapplication.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jun on 2017/4/11.
 */

public class TestAnimator extends SimpleItemAnimator {
    private static final String TAG = "TestAnimator";
    private List<ChangeInfo> mPendingChanges = new ArrayList<>();
    private List<RecyclerView.ViewHolder> mChangAnimations = new ArrayList<>();
    private List<ArrayList<ChangeInfo>> mChangeList = new ArrayList<>();
    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        return false;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
        return false;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
        Log.i(TAG, "animateChange: ");
        if (oldHolder == newHolder) {
            return animateMove(oldHolder, fromLeft, fromTop, toLeft, toTop);
        }
        RecyclerViewAdapter.MyViewHolder myViewHolder=null;
        if (newHolder instanceof RecyclerViewAdapter.MyViewHolder) {
             myViewHolder= ((RecyclerViewAdapter.MyViewHolder) newHolder);
        }
        Animator animator = new ObjectAnimator().ofFloat(myViewHolder.itemView, "scale", 1, 2, 1);
        animator.setDuration(2000);
//        animator.start();
        mPendingChanges.add(new ChangeInfo(oldHolder,newHolder,fromLeft,fromTop,toLeft,toTop));
        return false;
    }

    @Override
    public void runPendingAnimations() {


    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder item) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        if (!mPendingChanges.isEmpty()) {
            return true;
        }
        return false;
    }


    private static class ChangeInfo {
        public RecyclerView.ViewHolder oldHolder, newHolder;
        public int fromX, fromY, toX, toY;
        private ChangeInfo(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder) {
            this.oldHolder = oldHolder;
            this.newHolder = newHolder;
        }

        ChangeInfo(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder,
                   int fromX, int fromY, int toX, int toY) {
            this(oldHolder, newHolder);
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
        }

        @Override
        public String toString() {
            return "ChangeInfo{" +
                    "oldHolder=" + oldHolder +
                    ", newHolder=" + newHolder +
                    ", fromX=" + fromX +
                    ", fromY=" + fromY +
                    ", toX=" + toX +
                    ", toY=" + toY +
                    '}';
        }
    }
}
