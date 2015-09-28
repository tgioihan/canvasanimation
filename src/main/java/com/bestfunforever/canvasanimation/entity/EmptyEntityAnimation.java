package com.bestfunforever.canvasanimation.entity;

import android.graphics.Canvas;

import com.bestfunforever.canvasanimation.AnimationExecutor;

/**
 * Created by nguyenxuan on 9/27/2015.
 */
public class EmptyEntityAnimation extends Sprite {

    public EmptyEntityAnimation() {
        super(null, 0, 0, null);
    }

    @Override
    public void onDraw(Canvas canvas) {

    }

    private AnimationExecutor callback;

    public  void setCallback(AnimationExecutor callback){
        this.callback = callback;
    }

    public  void execute(){
        if(callback!=null){
            callback.execute(this);
            callback = null;
        }
    }
}