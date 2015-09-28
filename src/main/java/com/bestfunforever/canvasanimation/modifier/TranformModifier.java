package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.Interpolator;

/**
 * Created by nguyenxuan on 6/29/2015.
 */
public class TranformModifier extends DurationModifier {
    protected float initialY;
    protected float initialX;
    protected float distanceX;
    protected float distanceY;
    private float srcX;
    private float srcY;
    protected float desX;
    protected float desY;

    public TranformModifier(Entity entity, float desX, float desY, float duration) {
        this(entity,entity.getX(),entity.getY(),desX,desY,duration);
    }

    public TranformModifier(Entity entity,float srcX , float srcY, float desX, float desY, float duration) {
        super(entity,duration);
        this.srcX = srcX;
        this.srcY = srcY;
        this.desX = desX;
        this.desY = desY;
        reset();
    }

    public TranformModifier(Entity entity, float desX, float desY, float duration, Interpolator interpolator) {
        this(entity, entity.getX(), entity.getY(), desX, desY, duration, interpolator);
    }

    public TranformModifier(Entity entity,float srcX , float srcY, float desX, float desY, float duration, Interpolator interpolator) {
        super(entity,duration,interpolator);
        this.srcX = srcX;
        this.srcY = srcY;
        this.desX = desX;
        this.desY = desY;
        reset();
    }

    @Override
    public void reset() {
        super.reset();
        initialX = entity.getX();
        initialY = entity.getY();
        distanceX = desX - initialX;
        distanceY = desY - initialY;
    }

    public void setDestinationPosition(float desX, float desY){

        this.desX = desX;
        this.desY = desY;
    }

    public void setSourcePosition(float desX, float desY){

        this.srcX = desX;
        this.srcY = desY;
    }

    @Override
    protected void onRepeat() {
        initialX = srcX;
        initialY = srcY;
        distanceX = desX - initialX;
        distanceY = desY - initialY;
        finished = false;
        currentTimeElapsed = 0;
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {
        entity.setX(initialX+distanceX * percent);
        entity.setY(initialY+distanceY * percent);
    }

    public void updateValue(float desX, float desY) {
        this.desX = desX;
        this.desY = desY;
        reset();
    }

    public void updateValue(float srcX , float srcY,float desX, float desY) {
        this.srcX = srcX;
        this.srcY = srcY;
        this.desX = desX;
        this.desY = desY;
        onRepeat();
    }

    @Override
    protected void preFinished() {
        super.preFinished();
        if(entity.getX()!=desX){
            entity.setX(desX);
        }
        if(entity.getY()!=desY){
            entity.setY(desY);
        }
    }
}
