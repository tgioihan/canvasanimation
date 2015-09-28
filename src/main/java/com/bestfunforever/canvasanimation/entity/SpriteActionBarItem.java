package com.bestfunforever.canvasanimation.entity;

import android.graphics.Bitmap;
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
public class SpriteActionBarItem extends Sprite implements IShowAnimation {
    private ScaleInModifier showModifier;
    private ScaleInModifier hideModifier;

    public SpriteActionBarItem(Bitmap bitmap) {
        super(bitmap);
        init();
    }

    public SpriteActionBarItem(Paint mPaint, Bitmap bitmap) {
        super(mPaint, bitmap);
        init();
    }

    public SpriteActionBarItem(Paint mPaint, float x, float y, Bitmap bitmap) {
        super(mPaint, x, y, bitmap);
        init();
    }

    public SpriteActionBarItem(float x, float y, float width, float height, Bitmap bitmap) {
        super(x, y, width, height, bitmap);
        init();
    }

    public SpriteActionBarItem(Paint mPaint, float x, float y, float width, float height, Bitmap bitmap) {
        super(mPaint, x, y, width, height, bitmap);
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

