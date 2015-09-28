package com.bestfunforever.canvasanimation.entity;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.bestfunforever.canvasanimation.Config;
import com.bestfunforever.canvasanimation.callback.IShowAnimation;
import com.bestfunforever.canvasanimation.callback.ModifierListener;
import com.bestfunforever.canvasanimation.modifier.BaseModifier;
import com.bestfunforever.canvasanimation.modifier.ScaleInModifier;
import com.bestfunforever.canvasanimation.modifier.interpolator.LinearInterpolator;


/**
 * Created by nguyenxuan on 7/19/2015.
 */
public class Shadow extends Entity implements IShowAnimation {

    private final int startColor;
    private final int endColor;
    private final ScaleInModifier showModifier;
    private final ScaleInModifier hideModifier;

    public Shadow(float x,float y, float width, float height,int startColor, int endColor) {
        super(new Paint());

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startColor = startColor;
        this.endColor = endColor;
        mPaint.setShader(new LinearGradient(x,y,x+width,y+height,startColor,endColor, Shader.TileMode.REPEAT));
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

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(x,y,x+width,y+height,mPaint);
    }
}
