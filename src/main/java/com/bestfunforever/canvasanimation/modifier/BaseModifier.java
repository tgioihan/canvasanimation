package com.bestfunforever.canvasanimation.modifier;

import com.bestfunforever.canvasanimation.callback.IModifier;
import com.bestfunforever.canvasanimation.callback.ModifierListener;
import com.bestfunforever.canvasanimation.entity.Entity;
import com.bestfunforever.canvasanimation.modifier.interpolator.Interpolator;
import com.bestfunforever.canvasanimation.modifier.interpolator.LinearInterpolator;

/**
 * Created by nguyenxuan on 6/28/2015.
 */
public abstract class BaseModifier implements IModifier {
    protected Entity entity;
    protected Interpolator interpolator;
    protected boolean repeat;
    protected boolean finished;
    protected float currentTimeElapsed;

    public void setListener(ModifierListener listener) {
        this.listener = listener;
    }

    protected ModifierListener listener;

    public BaseModifier(Entity entity) {
        this(entity, LinearInterpolator.getInstance());
    }

    public BaseModifier(Entity entity,Interpolator interpolator) {
        this.entity = entity;
        this.interpolator = interpolator;
        repeat = false;
        finished = false;
        currentTimeElapsed = 0;
    }


    public void reset() {
        finished = false;
        currentTimeElapsed = 0;
    }

    @Override
    public void update(long timeElapsed, Entity entity) {
        if(finished){

        }else{
            if(currentTimeElapsed == 0){
                if(listener!=null){
                    listener.onStart(entity,this);
                }
            }
            update(timeElapsed);
            if(checkFinished()){
                preFinished();
                if(repeat){
                    onRepeat();
                }else{
                    finished = true;
                    if (listener!=null){
                        listener.onEnd(entity,this);
                    }
                }
            }
        }
    }

    protected void onRepeat() {
        reset();
    }

    protected void preFinished(){

    }


    protected abstract boolean checkFinished();

    protected  void update(long tickedTime){
        currentTimeElapsed += tickedTime;
        onUpdate(tickedTime,entity);
        if (listener!=null){
            listener.onUpdate(entity,currentTimeElapsed,this);
        }
    }

    public abstract void onUpdate(long timeElapsed, Entity entity);

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public Interpolator getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }
}
