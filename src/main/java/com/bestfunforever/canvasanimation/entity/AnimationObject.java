package com.bestfunforever.canvasanimation.entity;

import android.graphics.Paint;

public class AnimationObject extends Sprite {

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    private float velocity;

    public AnimationObject(Paint mPaint) {
        super(mPaint,null);
    }

    public void update(long time){
        translateDeltaY(velocity*time/1000);
    }
}
