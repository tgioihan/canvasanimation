package com.bestfunforever.canvasanimation.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Rectangle extends Entity {

    public Rectangle(float x, float y, float width, float height ) {
        this(new Paint(), x, y, width, height, Color.TRANSPARENT);
    }

    public Rectangle(Paint mPaint,float x, float y, float width, float height ) {
        this(mPaint, x, y, width, height, Color.TRANSPARENT);
    }

    public Rectangle(float x, float y, float width, float height,int color ) {
        this(new Paint(), x, y, width, height, color);
    }


    public Rectangle(Paint mPaint,float x, float y, float width, float height ,int color) {
        super(mPaint);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setColor(color);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawRect(x,y,width,height,mPaint);
    }
}
