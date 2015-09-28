package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;

/**
 * Created by nguyenxuan on 6/29/2015.
 */
public class CircleAnimation extends DurationModifier {

    private final float divAngel;
    private float initialAngel;
    private float currentX;
    private float currentY;
    private float radius;
    private float currentAngel;

    private float divX ;
    private float divY;

    public CircleAnimation(Entity entity, float initialAngel, float radius, float duration) {
        super(entity,duration);
        this.radius = radius;
        currentAngel = initialAngel;
        this.initialAngel = initialAngel;
        divAngel = 360 - initialAngel;
        currentX = (float) (radius*Math.cos(Math.toRadians(currentAngel)));
        currentY = (float) (radius*Math.sin(Math.toRadians(currentAngel)));
    }

    @Override
    public void reset() {
        super.reset();
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {
        currentAngel = initialAngel + divAngel * percent;
        divX = (float) (radius*Math.cos(Math.toRadians(currentAngel))- currentX);
        divY = (float) (radius*Math.sin(Math.toRadians(currentAngel)) - currentY);
        currentX = (float) (radius*Math.cos(Math.toRadians(currentAngel)));
        currentY = (float) (radius*Math.sin(Math.toRadians(currentAngel)));
        entity.translateDeltaX(divX);
        entity.translateDeltaY(divY);
    }
}
