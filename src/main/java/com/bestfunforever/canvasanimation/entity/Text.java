package com.bestfunforever.canvasanimation.entity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextUtils;

/**
 * Created by nguyenxuan on 7/12/2015.
 */
public class Text extends Entity {

    public static final float WRAP = -222222222;

    private boolean wrap;

    public String getText() {
        return text;
    }

    private String text;
    private float textSize ;
    private Rect textBounds = new Rect();


    public Text(float x,float y,float textSize) {
        this(new Paint(), x, y, WRAP, textSize);
    }

    public Text(float x,float y,float width,float textSize) {
        this(new Paint(),x,y,width,textSize);
    }

    public Text(Paint mPaint,float y,float textSize) {
        this(mPaint,0,y,WRAP,textSize);
    }

    public Text(Paint mPaint,float x,float y,float textSize) {
        this(mPaint, x, y, WRAP, textSize);
    }

    public Text(Paint mPaint,float x,float y,float width,float textSize) {
        this(mPaint, x, y, width, textSize, Color.BLACK);
    }

    public Text(Paint mPaint,float x,float y,float width,float textSize,int textColor) {
        super(mPaint);
        this.textSize = textSize;
        this.mPaint.setTextSize(textSize);
        this.mPaint.setColor(textColor);
        this.setY(y);
        this.setX(x);
        if(width == WRAP){
            wrap = true;
        }else{
            this.setWidth(width);
        }
        measure();
    }

    public void setTextColor(int color){
        mPaint.setColor(color);
    }

    public void setTypeFace(Typeface typeFace){
        mPaint.setTypeface(typeFace);
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);
        mPaint.setTextSize(textSize * scale);

    }

    private void measure() {
        if(TextUtils.isEmpty(text))
            return;
        Paint paint = new Paint();
        paint.setTextSize(textSize);
        paint.getTextBounds(text, 0, text.length(), textBounds);
        if(wrap){
            width = textBounds.width();
        }
        height = textBounds.height();
    }

    public void setText(String text){
        this.text = text;
        measure();
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawText(text, getX() + +getWidth()/2 - textBounds.width()/2, getY()+textBounds.height() , mPaint);
    }
}
