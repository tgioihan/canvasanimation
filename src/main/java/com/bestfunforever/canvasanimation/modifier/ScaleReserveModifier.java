package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.LinearInterpolator;

public class ScaleReserveModifier extends DurationModifier {
    private final float startScale;
    private final float desScale;
    private final float maxDivScale;
    private float currentScale;
    private int direction;
    private float srcScale;

    public ScaleReserveModifier(Entity entity, float startScale, float desScale, float duration) {
        super(entity, duration, LinearInterpolator.getInstance());

        this.startScale = startScale;
        this.desScale = desScale;
        maxDivScale = desScale - startScale;
        srcScale =startScale;
        direction = 1;
        repeat = true;
    }

    public void setDirectionScale(int direction){
        this.direction = direction;
    }

    @Override
    public void reset() {
        super.reset();
        if(direction == 1){
            direction = -1;
            srcScale =desScale;
        }else{
            direction = 1;
            srcScale =startScale;
        }
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {
        currentScale = srcScale + direction * maxDivScale * percent;
        entity.setScale(currentScale);
    }
}
