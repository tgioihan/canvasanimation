package com.bestfunforever.canvasanimation.callback;

/**
 * Created by nguyenxuan on 6/28/2015.
 */
public interface IAnimation {
    void update();

    boolean postDelayed(Runnable runnable, long upDateTime);

    boolean post(Runnable animationRunnable);

    boolean isActive();
}
