package com.bestfunforever.canvasanimation.modifier.interpolator;

public class BackOutInterpolator implements Interpolator {
    private static BackOutInterpolator instance;

    public static BackOutInterpolator getInstance() {
        if (instance == null) {
            instance = new BackOutInterpolator();
        }
        return instance;
    }

    @Override
    public float update(float timeElapsed, float duration) {
        return getValue(timeElapsed / duration);
    }

    public static float getValue(final float pPercentage) {
        final float t = pPercentage - 1;
        return 1 + t * t * ((1.70158f + 1) * t + 1.70158f);
    }
}
