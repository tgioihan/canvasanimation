package com.bestfunforever.canvasanimation.entity;

import android.graphics.Paint;

import com.bestfunforever.canvasanimation.modifier.AlphaModifier;

/**
 * Created by nguyenxuan on 7/12/2015.
 */
public class TextAlpha extends Text {

    private final AlphaModifier modifier;

    public TextAlpha(Paint mPaint,float x, float y, float width, float textSize) {
        super(mPaint,x, y, width, textSize);
        modifier = new AlphaModifier(this, 1, 0, 400);
        addModifier(modifier);
    }

    public void reset() {
        modifier.reset();
    }
}
