package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.Interpolator;

/**
 * Created by nguyenxuan on 7/17/2015.
 */
public class Oscillator extends DurationModifier {
    private final float srcScale;
    private final float amplitude;
    private int direction;
    private int startDirection;
    private float currentScale;

    public Oscillator(Entity entity, float srcScale,float amplitude,int startDirection, float duration, Interpolator interpolator) {
        super(entity, duration, interpolator);
        this.srcScale = srcScale;
        currentScale = srcScale;
        this.amplitude = amplitude;
        this.startDirection = startDirection;
        direction = startDirection;
        repeat = true;
    }

    @Override
    public void reset() {
        super.reset();
        if(entity.getScale() == srcScale+ amplitude){
            direction = -1;
        }else  if(entity.getScale() == srcScale- amplitude){
            direction = 1;
        }
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {
        currentScale = srcScale + direction * amplitude * percent;
        entity.setScale(currentScale);
    }
}
