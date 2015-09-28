package com.bestfunforever.canvasanimation.modifier.interpolator;

/**
 * Created by nguyenxuan on 7/16/2015.
 */
public class BoundInInterpolator implements Interpolator {

    private static BoundInInterpolator instance;

    public static BoundInInterpolator getInstance() {
        if (instance == null) {
            instance = new BoundInInterpolator();
        }
        return instance;
    }

    @Override
    public float update(float timeElapsed, float duration) {
        return getValue(timeElapsed / duration);
    }

    public static float getValue(final float pPercentage) {
        if (pPercentage < (1f / 2.75f)) {
            return 7.5625f * pPercentage * pPercentage;
        } else if (pPercentage < (2f / 2.75f)) {
            final float t = pPercentage - (1.5f / 2.75f);
            return 7.5625f * t * t + 0.75f;
        } else if (pPercentage < (2.5f / 2.75f)) {
            final float t = pPercentage - (2.25f / 2.75f);
            return 7.5625f * t * t + 0.9375f;
        } else {
            final float t = pPercentage - (2.625f / 2.75f);
            return 7.5625f * t * t + 0.984375f;
        }
    }
}
