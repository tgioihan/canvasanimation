package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.entity.FlipEntity;
import com.bestfunforever.canvasanimation.modifier.interpolator.LinearInterpolator;

/**
 * Created by nguyenxuan on 8/11/2015.
 */
public class FlipModifier  extends DurationModifier{
    public FlipModifier(FlipEntity entity, float duration) {
        super(entity, duration, LinearInterpolator.getInstance());
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {
        ((FlipEntity)entity).setFlip(360*percent);
    }
}
