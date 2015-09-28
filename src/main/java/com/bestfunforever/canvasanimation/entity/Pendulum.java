package com.bestfunforever.canvasanimation.entity;

import android.util.Log;

import com.bestfunforever.canvasanimation.callback.ModifierListener;
import com.bestfunforever.canvasanimation.TranformUtil;
import com.bestfunforever.canvasanimation.modifier.BaseModifier;
import com.bestfunforever.canvasanimation.modifier.TranformModifier;
import com.bestfunforever.canvasanimation.modifier.interpolator.BackOutInterpolator;


/**
 * Created by nguyenxuan on 8/11/2015.
 */
public class Pendulum{
    private final TranformModifier pendulumModifier;
    private Line line;
    private FlipEntity flipEntity;

    private float defaultY;
    private float maxDragY;

    public Line getLine() {
        return line;
    }

    public FlipEntity getFlipEntity() {
        return flipEntity;
    }

    public Pendulum(Line line, FlipEntity flipEntity, float defaultY, float maxDragY) {
        this.line = line;
        this.flipEntity = flipEntity;
        this.defaultY = defaultY;
        this.maxDragY = maxDragY;
        pendulumModifier =  new TranformModifier(flipEntity,0,0,300, BackOutInterpolator.getInstance());
        pendulumModifier.setListener(new ModifierListener() {
            @Override
            public void onStart(Entity entity, BaseModifier modifier) {
            }

            @Override
            public void onUpdate(Entity entity, float timeElapsed, BaseModifier modifier) {
            }

            @Override
            public void onEnd(Entity entity, BaseModifier modifier) {
                entity.removeModifier(modifier);
            }
        });
    }

    public void update() {
        line.setEnd(flipEntity.getCenterX(),flipEntity.getCenterY());
    }

    public void onTouchMove(Entity entity, float xDistance, float yDistance) {
        float y = entity.getY() + yDistance;
        if(y> maxDragY){
            y = maxDragY;
        }else if(y< defaultY){
            y = defaultY;
        }
        entity.setY(y);
        Log.d("","Pedulum ontouchmove "+ entity.getY());
    }

    public void onTouchUp(Entity entity) {
        if(entity!=null && entity == flipEntity){
            if(flipEntity.getY() != defaultY){
                flipEntity.removeModifier(pendulumModifier);
                pendulumModifier.updateValue(flipEntity.getX(), defaultY);
                pendulumModifier.setDuration(TranformUtil.calculateDuration(flipEntity.getX(), flipEntity.getY(), flipEntity.getX(), defaultY, maxDragY - defaultY, 200, 600));
                flipEntity.addModifier(pendulumModifier);
                flipEntity.flip();
            }
        }
    }
}
