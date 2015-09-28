package com.bestfunforever.canvasanimation.modifier;

import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.Interpolator;

/**
 * Created by nguyenxuan on 7/16/2015.
 */
public class TranformByModifier extends TranformModifier {
    private float divX;
    private float divY;

    public TranformByModifier(Entity entity, float divX, float divY, float duration) {
        super(entity, 0,0, duration);
        this.divX = divX;
        this.divY = divY;
    }

    public TranformByModifier(Entity entity, float divX, float divY, float duration, Interpolator interpolator) {
        super(entity, 0,0, duration, interpolator);
        this.divX = divX;
        this.divY = divY;
    }

    @Override
    public void reset() {
        super.reset();
        initialX = entity.getX();
        initialY = entity.getY();
        desX = initialX + divX;
        desY = initialY + divY;
        distanceX = desX - initialX;
        distanceY = desY - initialY;
    }

    @Override
    public void updateValue(float desX, float desY) {
        throw new IllegalStateException("not support this method");
    }
}
