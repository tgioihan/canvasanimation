package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.BoundInInterpolator;
import com.bestfunforever.canvasanimation.modifier.interpolator.Interpolator;

public class ScaleModifier extends DurationModifier {
    private float desScaleX;
    private float srcScaleX;
    private float desScaleY;
    private float srcScaleY;
    private float scaleDivX;
    private float scaleDivY;
    private float currentScaleX ;
    private float currentScaleY ;
    private boolean setSourceScale;
    private int scaleDirection;

    public ScaleModifier(Entity entity,float srcScaleX, float srcScaleY, float desScaleX, float desScaleY,float duration, Interpolator interpolator) {
        super(entity,duration, interpolator);
        this.desScaleX = desScaleX;
        this.srcScaleX = srcScaleX;
        this.desScaleY = desScaleY;
        this.srcScaleY = srcScaleY;
        scaleDivX = desScaleX-srcScaleX;
        scaleDivY = desScaleY-srcScaleY;
        currentScaleX = srcScaleX;
        currentScaleY = srcScaleY;
        setSourceScale = false;
        scaleDirection = 1;
    }

    public ScaleModifier(Entity entity, float desScaleX,float desScaleY, float duration) {
        this(entity,desScaleX,desScaleY,duration, BoundInInterpolator.getInstance());
    }

    public ScaleModifier(Entity entity, float desScaleX,float desScaleY, float duration, Interpolator interpolator) {
        this(entity,entity.getScaleX(),entity.getScaleY(),desScaleX,desScaleY,duration, interpolator);
        setSourceScale = true;
    }

    @Override
    public void reset() {
        super.reset();
        if(setSourceScale){
            srcScaleX = entity.getScaleX();
            srcScaleY = entity.getScaleY();
        }
        scaleDivX = desScaleX-srcScaleX;
        scaleDivY = desScaleY-srcScaleY;
        currentScaleX = srcScaleX;
        currentScaleY = srcScaleY;
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {
        currentScaleX = srcScaleX + scaleDivX * percent;
        currentScaleY = srcScaleY + scaleDivY * percent;
        entity.setScale(currentScaleX,currentScaleY);
    }

    public void setDesScale(float desScaleX,float desScaleY) {
        this.desScaleX = desScaleX;
        this.desScaleY = desScaleY;
    }
}
