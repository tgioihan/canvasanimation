package com.bestfunforever.canvasanimation.callback;

import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.BaseModifier;

/**
 * Created by nguyenxuan on 6/28/2015.
 */
public interface ModifierListener {

    void onStart(Entity entity, BaseModifier modifier);
    void onUpdate(Entity entity, float timeElapsed, BaseModifier modifier);
    void onEnd(Entity entity, BaseModifier modifier);
}
