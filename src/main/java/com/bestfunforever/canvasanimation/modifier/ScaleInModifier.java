package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.BoundInInterpolator;
import com.bestfunforever.canvasanimation.modifier.interpolator.Interpolator;

/**
 * Created by nguyenxuan on 6/30/2015.
 */
public class ScaleInModifier extends DurationModifier {
    private float desScale;
    private float srcScale;
    private float scaleDiv;
    private float currentScale ;
    private boolean setSourceScale;
    private int scaleDirection;

    public ScaleInModifier(Entity entity,float srcScale, float desScale, float duration, Interpolator interpolator) {
        super(entity,duration, interpolator);
        this.desScale = desScale;
        this.srcScale = srcScale;
        scaleDiv = desScale-srcScale;
        currentScale = srcScale;
        setSourceScale = false;
        scaleDirection = 1;
    }

    public ScaleInModifier(Entity entity, float desScale, float duration) {
        this(entity,desScale,duration, BoundInInterpolator.getInstance());
    }

    public ScaleInModifier(Entity entity, float desScale, float duration, Interpolator interpolator) {
        this(entity,entity.getScale(),desScale,duration, interpolator);
        setSourceScale = true;
    }

    @Override
    public void reset() {
        super.reset();
        if(setSourceScale)
         srcScale = entity.getScale();
        scaleDiv = desScale-srcScale;
        currentScale = srcScale;
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {
        currentScale = srcScale + scaleDiv * percent;
        entity.setScale(currentScale);
    }

    public void setDesScale(float desScale) {
        this.desScale = desScale;
    }
}
