package com.bestfunforever.canvasanimation.modifier.interpolator;

/**
 * Created by nguyenxuan on 7/16/2015.
 */
public class LinearInterpolator implements Interpolator {
    private static LinearInterpolator instance;
    public static LinearInterpolator getInstance(){
        if(instance == null){
            instance = new LinearInterpolator();
        }
        return instance;
    }

    @Override
    public float update(float timeElapsed, float duration) {
        return timeElapsed/duration;
    }
}
