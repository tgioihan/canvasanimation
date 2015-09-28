package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.Interpolator;

/**
 * Created by nguyenxuan on 6/30/2015.
 */
public abstract class DurationModifier extends BaseModifier {
    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    protected float duration;

    public DurationModifier(Entity entity, float duration, Interpolator interpolator) {
        super(entity, interpolator);
        this.duration = duration;
    }

    public DurationModifier(Entity entity, float duration) {
        super(entity);
        this.duration = duration;
    }

    @Override
    protected boolean checkFinished() {
        return currentTimeElapsed>=duration;
    }

    @Override
    public void onUpdate(long timeElapsed, Entity entity) {
        if(currentTimeElapsed>duration){
            currentTimeElapsed = duration;
        }
        float percent = interpolator.update(currentTimeElapsed,duration);
        onUpdate(currentTimeElapsed,percent,entity);
    }

    protected abstract void onUpdate(float timeElapsed, float percent, Entity entity);
}
