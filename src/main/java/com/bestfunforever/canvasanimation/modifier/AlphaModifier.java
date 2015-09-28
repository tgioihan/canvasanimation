package com.bestfunforever.canvasanimation.modifier;

import com.bestfunforever.canvasanimation.entity.Entity;

/**
 * Created by nguyenxuan on 7/12/2015.
 */
public class AlphaModifier extends DurationModifier {

    private final float startAlpha;
    private final float endAlpha;

    private boolean incresseAlpha;
    private float divAlpha;

    public AlphaModifier(Entity entity,float startAlpha,float endAlpha, float duration) {
        super(entity, duration);
        this.startAlpha = startAlpha;
        this.endAlpha = endAlpha;
        divAlpha = endAlpha-startAlpha;
    }

    @Override
    public void reset() {
        super.reset();
        incresseAlpha=!incresseAlpha;
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {
        entity.setAlpha(incresseAlpha?startAlpha+divAlpha*percent:startAlpha-divAlpha*percent);
    }
}
