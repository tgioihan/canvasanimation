package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.BoundOutInterpolator;

/**
 * Created by nguyenxuan on 6/30/2015.
 */
public class ScaleOutModifier extends ScaleInModifier {
    public ScaleOutModifier(Entity entity, float desScale, float duration) {
        super(entity, desScale, duration, BoundOutInterpolator.getInstance());
    }
}
