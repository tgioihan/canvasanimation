package com.bestfunforever.canvasanimation.modifier;


import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.BackInnterpolator;
import com.bestfunforever.canvasanimation.modifier.interpolator.BoundInInterpolator;

public class DancingModifier extends SequenceModifier {
    private final TranformModifier upModifier;
    private final TranformModifier downModifer;

    public DancingModifier(Entity entity,boolean repeat) {
        super(repeat);
        upModifier = new TranformModifier(entity, entity.getX(), entity.getY() - 60, 700, BackInnterpolator.getInstance());
        downModifer = new TranformModifier(entity, entity.getX(), entity.getY(), 1000, BoundInInterpolator.getInstance());
        addModifier(upModifier);
        addModifier(downModifer );
    }

    @Override
    public void reset() {
        super.reset();
        upModifier.updateValue(entity.getX(), entity.getY(), entity.getX(), entity.getY() - 60);
        downModifer.updateValue(entity.getX(),entity.getY(),entity.getX(),entity.getY() );
    }
}
