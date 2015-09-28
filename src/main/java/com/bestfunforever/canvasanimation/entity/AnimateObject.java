package com.bestfunforever.canvasanimation.entity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nguyenxuan on 6/27/2015.
 */
public class AnimateObject extends Entity {

    public static final int DEFAULT_FRAME = 100;
    public static final float DEFAULT_ALPHA = 0.5f;

    private List<Bitmap> bitmaps;
    private float frame ;

    private long preveousUpdate;
    private boolean decreaseAlpha;
    private float alphaRange ;

    private int currentBitmapPosition;

    public AnimateObject(Paint mPaint) {
        super(mPaint);
        bitmaps = new ArrayList<>();
        frame = DEFAULT_FRAME;
        setAlpha(1);
        alphaRange = DEFAULT_ALPHA;
        decreaseAlpha = true;
        preveousUpdate = System.currentTimeMillis();
    }

    @Override
    public void update(long time) {
        if(System.currentTimeMillis()-preveousUpdate >= frame){
            if(currentBitmapPosition+1<bitmaps.size()){
                currentBitmapPosition++;
            }else{
                currentBitmapPosition = 0;
            }
            preveousUpdate = System.currentTimeMillis();
        }
        if(decreaseAlpha){
            setAlphaBy(-time*alphaRange/frame);
        }else{
            setAlphaBy(time*alphaRange/frame);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(bitmaps!=null && bitmaps.size()>currentBitmapPosition){
            canvas.drawBitmap(bitmaps.get(currentBitmapPosition),getX(),getY(),mPaint);
        }
    }

    public List<Bitmap> getBitmaps() {
        return bitmaps;
    }

    public void addBitmap(Bitmap ... bitmaps) {
        this.bitmaps.addAll(Arrays.asList(bitmaps));
    }

    public float getFrame() {
        return frame;
    }

    public void setFrame(float frame) {
        this.frame = frame;
    }

    public long getPreveousUpdate() {
        return preveousUpdate;
    }

    public void setPreveousUpdate(long preveousUpdate) {
        this.preveousUpdate = preveousUpdate;
    }

    public int getCurrentBitmapPosition() {
        return currentBitmapPosition;
    }

    public void setCurrentBitmapPosition(int currentBitmapPosition) {
        this.currentBitmapPosition = currentBitmapPosition;
    }
}
