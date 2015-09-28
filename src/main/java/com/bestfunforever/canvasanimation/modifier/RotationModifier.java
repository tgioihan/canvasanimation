package com.bestfunforever.canvasanimation.modifier;

import com.bestfunforever.canvasanimation.entity.Entity;

public class RotationModifier extends DurationModifier {
    private float startRotateAngel;
    private float endRotateAngel;

    public RotationModifier(Entity entity, float duration) {
        this(entity, 0, 360, duration);
    }

    public RotationModifier(Entity entity,float startRotateAngel, float endRotateAngel, float duration) {
        super(entity, duration);
        this.startRotateAngel = startRotateAngel;
        this.endRotateAngel = endRotateAngel;
    }

    public void applayRotation(float startRotateAngel, float endRotateAngel){

        this.startRotateAngel = startRotateAngel;
        this.endRotateAngel = endRotateAngel;
    }

    @Override
    protected void onRepeat() {
        super.onRepeat();
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {
        entity.setRotationAngel(startRotateAngel +(endRotateAngel - startRotateAngel)*percent);
    }
}
