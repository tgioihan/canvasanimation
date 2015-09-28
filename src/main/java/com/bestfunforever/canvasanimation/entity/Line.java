package com.bestfunforever.canvasanimation.entity;

import android.graphics.Canvas;

/**
 * Created by nguyenxuan on 8/11/2015.
 */
public class Line extends Entity {
    private float endX;
    private float endY;

    public Line(float x, float y, float endX, float endY,float width, int color) {
        super();
        this.x = x;
        this.y = y;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
        this.width = width;
        mPaint.setColor(color);
        mPaint.setStrokeWidth(width);
    }

    @Override
    public float getHeight() {
        return Math.abs(endY - y);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(x, y, endX, endY, mPaint);
    }

    public void setEnd(float x, float y) {
        endX = x;
        endY = y;
    }
}
