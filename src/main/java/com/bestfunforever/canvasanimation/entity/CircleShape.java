package com.bestfunforever.canvasanimation.entity;

import android.graphics.Canvas;

public class CircleShape extends Entity {
    private float radius;
    private float centerX;
    private float centerY;

    public CircleShape(float radius, float x, float y,int color) {
        super();
        mPaint.setColor(color);
        this.radius = radius;
        this.x = x;
        this.y = y;
        centerX = x + radius;
        centerY = y + radius;
        width = 2 * radius;
        height = 2 * radius;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawCircle(centerX, centerY, radius * getScale(), mPaint);
    }
}
