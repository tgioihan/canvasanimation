package com.bestfunforever.canvasanimation.modifier.interpolator;

public class BackInnterpolator implements Interpolator {
    private static final float OVERSHOOT_CONSTANT = 1.70158f;
    public static BackInnterpolator getInstance(){
        return new BackInnterpolator();
    }

    @Override
    public float update(float timeElapsed, float duration) {
        return getValue(timeElapsed/duration);
    }

    public static float getValue(final float pPercentage) {
        return pPercentage * pPercentage * ((OVERSHOOT_CONSTANT + 1) * pPercentage - OVERSHOOT_CONSTANT);
    }
}