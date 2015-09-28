package com.bestfunforever.canvasanimation.entity;

import android.graphics.Paint;

import com.bestfunforever.canvasanimation.Config;
import com.bestfunforever.canvasanimation.callback.IShowAnimation;
import com.bestfunforever.canvasanimation.callback.ModifierListener;
import com.bestfunforever.canvasanimation.modifier.BaseModifier;
import com.bestfunforever.canvasanimation.modifier.ScaleInModifier;
import com.bestfunforever.canvasanimation.modifier.interpolator.LinearInterpolator;


/**
 * Created by nguyenxuan on 7/19/2015.
 */
public class TextActionBarItem extends Text implements IShowAnimation {
    private ScaleInModifier showModifier;
    private ScaleInModifier hideModifier;

    public TextActionBarItem(float x, float y, float textSize) {
        super(x, y, textSize);
        init();
    }

    public TextActionBarItem(float x, float y, float width, float textSize) {
        super(x, y, width, textSize);
        init();
    }

    public TextActionBarItem(Paint mPaint, float y, float textSize) {
        super(mPaint, y, textSize);
        init();
    }

    public TextActionBarItem(Paint mPaint, float x, float y, float textSize) {
        super(mPaint, x, y, textSize);
        init();
    }

    public TextActionBarItem(Paint mPaint, float x, float y, float width, float textSize) {
        super(mPaint, x, y, width, textSize);
        init();
    }

    private void init() {
        setScale(0);
        showModifier = new ScaleInModifier(this,0,1, Config.EFFECT_BASE_DURATION, LinearInterpolator.getInstance());
        showModifier.setListener(new ModifierListener() {
            @Override
            public void onStart(Entity entity, BaseModifier modifier) {

            }

            @Override
            public void onUpdate(Entity entity, float timeElapsed, BaseModifier modifier) {

            }

            @Override
            public void onEnd(Entity entity, BaseModifier modifier) {
                removeModifiers();
            }
        });

        hideModifier = new ScaleInModifier(this,0,Config.EFFECT_BASE_DURATION,LinearInterpolator.getInstance());
        hideModifier.setListener(new ModifierListener() {
            @Override
            public void onStart(Entity entity, BaseModifier modifier) {

            }

            @Override
            public void onUpdate(Entity entity, float timeElapsed, BaseModifier modifier) {

            }

            @Override
            public void onEnd(Entity entity, BaseModifier modifier) {
                removeModifiers();
            }
        });
    }

    @Override
    public void onShow() {
        setScale(0);
        removeModifiers();
        addModifier(showModifier);
    }

    @Override
    public void onHide() {
        removeModifiers();
        addModifier(hideModifier);
    }
}
