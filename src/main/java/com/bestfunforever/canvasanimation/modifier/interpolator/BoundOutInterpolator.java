package com.bestfunforever.canvasanimation.modifier.interpolator;

/**
 * Created by nguyenxuan on 7/16/2015.
 */
public class BoundOutInterpolator implements Interpolator {

    private static BoundOutInterpolator instance;

    public static BoundOutInterpolator getInstance() {
        if (instance == null) {
            instance = new BoundOutInterpolator();
        }
        return instance;
    }


    @Override
    public float update(float timeElapsed, float duration) {
        return 1 - BoundInInterpolator.getValue(1 - timeElapsed / duration);
    }
}
