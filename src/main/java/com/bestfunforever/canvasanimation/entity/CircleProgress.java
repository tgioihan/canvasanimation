package com.bestfunforever.canvasanimation.entity;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


public class CircleProgress extends Entity {
    private static final int DEFAULT_TRIGGER = 80;
    private final float strokeWidth;
    private final int strokeBgColor;
    private final int progressColor;
    private final int boostHotColor;
    private float centerX;
    private float centerY;
    private final Paint progressPaint;
    private final Paint fillPaint;
    int progress;
    private float centerEndX;
    private float centerEndY;
    private RectF progressRect;
    private int trigger;

    public CircleProgress(float width, float strokeWidth, int strokeBgColor, int progressColor, int boostHotColor) {
        super();
        this.strokeBgColor = strokeBgColor;
        this.progressColor = progressColor;
        this.boostHotColor = boostHotColor;
        this.width = width;
        this.height = width;
        this.strokeWidth = strokeWidth;

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(strokeBgColor);
        mPaint.setStrokeWidth(strokeWidth);

        fillPaint = new Paint();
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setAntiAlias(true);
        fillPaint.setColor(progressColor);

        progressPaint = new Paint();
        progressPaint.setStyle(Paint.Style.STROKE);
        progressPaint.setAntiAlias(true);
        progressPaint.setColor(progressColor);
        progressPaint.setStrokeWidth(strokeWidth);
        progressRect = new RectF(0,0,width,width);
        trigger = DEFAULT_TRIGGER;
        setProgress(0);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        progressRect.set(x, y, x + width, y + height);
    }

    @Override
    public void onDraw(Canvas canvas) {
        centerX = x +width/2;
        centerY = y+ width/2;

        //draw progress bg
        canvas.drawCircle(centerX,centerY,width/2,mPaint);
        //drawStart
        canvas.drawCircle(x +width/2 ,y+ 0, strokeWidth / 2, fillPaint);

        //drawEnd
//        canvas.drawCircle( centerEndX,  centerEndY, strokeWidth / 2, fillPaint);

        //draw progress
        canvas.drawArc(progressRect, 270, progress, false, progressPaint);
    }

    public void setColorTrigger(int trigger){
        this.trigger = trigger;
    }

    public void setProgress(int progress){
        this.progress = progress;
        if(progress >= trigger){
            progressPaint.setColor(boostHotColor);
        }else{
            progressPaint.setColor(progressColor);
        }
        calculateValues();
    }

    private void calculateValues() {
        centerEndX = x + (float) (Math.cos(Math.toRadians(progress))*(width/2))+width/2;
        centerEndY = y + (float) (Math.sin(Math.toRadians(progress))*(width/2))+width/2;
    }
}
