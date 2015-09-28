package com.bestfunforever.canvasanimation.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by nguyenxuan on 7/16/2015.
 */
public class Sprite extends Entity {

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        if(bitmap!=null){
            setWidth(bitmap.getWidth()*scaleFactor);
            setHeight(bitmap.getHeight()*scaleFactor);
        }
    }

    private Bitmap bitmap;
    private float scaleFactor;

    public Sprite() {
        this(null);
    }

    public Sprite(Bitmap bitmap) {
        this(new Paint(), bitmap);
    }

    public Sprite(Paint mPaint,Bitmap bitmap) {
        this(mPaint, 0, 0, bitmap);
    }

    public Sprite(Paint mPaint,float x, float y,Bitmap bitmap) {
        super(mPaint);
        this.setX(x);
        this.setY(y);
        this.bitmap = bitmap;
        if(bitmap!=null){
            setWidth(bitmap.getWidth());
            setHeight(bitmap.getHeight());
        }
        scaleFactor = 1;
    }

    public Sprite(float x, float y,float width, float height,Bitmap bitmap) {
        this(new Paint(),x,y,width,height,bitmap);
    }

    public Sprite(Paint mPaint,float x, float y,float width, float height,Bitmap bitmap) {
        super(mPaint);
        this.setX(x);
        this.setY(y);
        this.bitmap = bitmap;
        setWidth(width);
        setHeight(height);
        scaleFactor = width/bitmap.getWidth();
    }

    @Override
    public float getHeight() {
        return super.getHeight()/scaleFactor;
    }

    @Override
    public float getScale() {
        return super.getScale()*scaleFactor;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(bitmap!=null && !bitmap.isRecycled())
        canvas.drawBitmap(bitmap,getX(),getY(),mPaint);
    }

    @Override
    public void dispose() {
        super.dispose();
        if(bitmap!=null && !bitmap.isRecycled()){
            bitmap.recycle();
        }
    }
}
