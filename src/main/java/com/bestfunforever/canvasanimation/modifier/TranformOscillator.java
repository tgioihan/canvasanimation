package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.Interpolator;

public class TranformOscillator  extends DurationModifier {
    public void setSrcX(float srcX) {
        this.srcX = srcX;
    }

    private float srcX;
    private final float amplitude;
    private int direction;
    private int startDirection;
    private float currentX;

    public TranformOscillator(Entity entity, float srcX,float amplitude,int startDirection, float duration, Interpolator interpolator) {
        super(entity, duration, interpolator);
        this.srcX = srcX;
        currentX = srcX;
        this.amplitude = amplitude;
        this.startDirection = startDirection;
        direction = startDirection;
        repeat = true;
    }

    @Override
    public void reset() {
        super.reset();
        if(entity.getX() == srcX + amplitude){
            direction = -1;
        }else  if(entity.getX() == srcX - amplitude){
            direction = 1;
        }
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {
        currentX = srcX + direction * amplitude * percent;
        entity.setX(currentX);
    }
}
