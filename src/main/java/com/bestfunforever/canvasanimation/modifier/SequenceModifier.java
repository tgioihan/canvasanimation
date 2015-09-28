package com.bestfunforever.canvasanimation.modifier;

import android.util.Log;

import com.bestfunforever.canvasanimation.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class SequenceModifier extends DurationModifier {
    protected List<DurationModifier> modifiers;
    private int currentModifierPosition;
    private DurationModifier currentModifier;

    public SequenceModifier(boolean repeat) {
        super(null,0);
        this.repeat = repeat;
        modifiers = new ArrayList<>();
        currentModifierPosition = 0;
    }

    public void addModifier(DurationModifier modifier){
        modifiers.add(modifier);
        calculateDuaration();
    }

    public void setModifiers(List<DurationModifier> modifiers){
        this.modifiers = modifiers;
        calculateDuaration();
    }

    private void calculateDuaration() {
        float duration = 0;
        for (DurationModifier modifier : modifiers){
            duration += modifier.getDuration();
        }
        setDuration(duration);

        currentModifier = modifiers.get(currentModifierPosition);
    }


    @Override
    public void setEntity(Entity entity) {
        super.setEntity(entity);
        for (DurationModifier modifier : modifiers){
            modifier.setEntity(entity);
        }
    }

    @Override
    protected void onRepeat() {
        super.onRepeat();
        currentModifierPosition = 0;
        currentModifier = modifiers.get(currentModifierPosition);
    }

    private DurationModifier getNextModifier(){
        Log.d("","SequenceModifier currentModifierPosition "+currentModifierPosition+" modifiers.size() "+modifiers.size());
        if(currentModifierPosition+1<modifiers.size()){
            currentModifierPosition++;
            return modifiers.get(currentModifierPosition);
        }else{
            if(repeat){
                currentModifierPosition = 0;
                return modifiers.get(currentModifierPosition);
            }
        }
        return null;
    }

    @Override
    public void onUpdate(long tickedTime, Entity entity) {
        if(currentModifier == null)
            return;
        currentModifier.update(tickedTime);
        listener.onUpdate(entity,tickedTime,currentModifier);
        if(currentModifier.checkFinished()){

            if(currentModifier == null  && repeat){
                listener.onEnd(entity,this);
            }else{
                currentModifier = getNextModifier();
                Log.d("","SequenceModifier "+ currentModifier);
                if(currentModifier!=null)
                currentModifier.reset();
            }
        }
    }

    @Override
    protected void onUpdate(float timeElapsed, float percent, Entity entity) {

    }

}
