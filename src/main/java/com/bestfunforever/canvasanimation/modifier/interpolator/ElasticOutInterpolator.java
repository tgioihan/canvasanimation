package com.bestfunforever.canvasanimation.modifier.interpolator;

public class ElasticOutInterpolator implements Interpolator {

    public static ElasticOutInterpolator getInstance(){
        return new ElasticOutInterpolator();
    }

    @Override
    public float update(float timeElapsed, float duration) {
        return getValue(timeElapsed,duration,timeElapsed/duration);
    }

    public static float getValue(final float pPercentage) {
        final float t = pPercentage - 1;
        return 1 + t * t * ((1.70158f + 1) * t + 1.70158f);
    }

    public static float getValue(final float pSecondsElapsed, final float pDuration, final float pPercentageDone) {
        if(pSecondsElapsed == 0) {
            return 0;
        }
        if(pSecondsElapsed == pDuration) {
            return 1;
        }

        final float p = pDuration * 0.3f;
        final float s = p / 4;

        return 1 + (float)Math.pow(2, -10 * pPercentageDone) * (float)Math.sin((float) ((pPercentageDone * pDuration - s) * Math.PI*2 / p));
    }
}