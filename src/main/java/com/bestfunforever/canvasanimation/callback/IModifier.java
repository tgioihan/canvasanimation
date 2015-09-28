package com.bestfunforever.canvasanimation.callback;

import com.bestfunforever.canvasanimation.entity.Entity;

/**
 * Created by nguyenxuan on 6/28/2015.
 */
public interface IModifier {
    void update(long timeElapsed, Entity entity);
}
