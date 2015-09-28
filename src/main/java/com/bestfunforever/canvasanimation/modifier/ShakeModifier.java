package com.bestfunforever.canvasanimation.modifier;

import com.bestfunforever.canvasanimation.callback.ModifierListener;
import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.BackOutInterpolator;

import java.util.ArrayList;
import java.util.List;


public class ShakeModifier extends SequenceModifier {
    private final TranformModifier upModifier;
    private final TranformModifier downModifier;

    public ShakeModifier(Entity entity, boolean repeat) {
        super(repeat);
        List<DurationModifier> durationModifiers = new ArrayList<>();
        upModifier = new TranformModifier(entity,entity.getX(),entity.getY()- 40,400, BackOutInterpolator.getInstance());
        downModifier =new TranformModifier(entity,entity.getX(),entity.getY(),600, BackOutInterpolator.getInstance());
        durationModifiers.add(upModifier);
        durationModifiers.add(downModifier);
        upModifier.setListener(new ModifierListener() {
            @Override
            public void onStart(Entity entity, BaseModifier modifier) {
                listener.onStart(entity, ShakeModifier.this);
            }

            @Override
            public void onUpdate(Entity entity, float timeElapsed, BaseModifier modifier) {
                listener.onUpdate(entity, timeElapsed, ShakeModifier.this);
            }

            @Override
            public void onEnd(Entity entity, BaseModifier modifier) {

            }
        });
        downModifier.setListener(new ModifierListener() {
            @Override
            public void onStart(Entity entity, BaseModifier modifier) {

            }

            @Override
            public void onUpdate(Entity entity, float timeElapsed, BaseModifier modifier) {
                listener.onUpdate(entity, timeElapsed, ShakeModifier.this);
            }

            @Override
            public void onEnd(Entity entity, BaseModifier modifier) {
            }
        });
        setModifiers(durationModifiers);
    }

    @Override
    public void reset() {
        super.reset();
        upModifier.reset();
        downModifier.reset();
    }

    @Override
    protected void onRepeat() {
        super.onRepeat();
        upModifier.onRepeat();
        downModifier.onRepeat();
    }

    @Override
    public void setEntity(Entity entity) {
        super.setEntity(entity);
        upModifier.setDestinationPosition(entity.getX(), entity.getY() - 40);
        downModifier.setDestinationPosition(entity.getX(), entity.getY());
        upModifier.setSourcePosition(entity.getX(), entity.getY());
        downModifier.setSourcePosition(entity.getX(), entity.getY() - 40);
    }

}
