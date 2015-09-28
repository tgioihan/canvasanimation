package com.bestfunforever.canvasanimation.entity;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.bestfunforever.canvasanimation.modifier.FlipModifier;


/**
 * Created by nguyenxuan on 8/11/2015.
 */
public class FlipEntity extends Sprite {

    private final FlipModifier modifier;
    private float flipAngel;
    private Camera camera;
    private Matrix matrix;
    private Paint borderPain;
    private Paint fillPain;

    public FlipEntity(float x, float y, float width, Bitmap bitmap) {
        super(new Paint(), x, y, bitmap);
        setEnable(true);
        mPaint.setAntiAlias(true);
        camera = new Camera();
        modifier = new FlipModifier(this, 400);
        matrix = new Matrix();
        this.width = width;
        this.height = width;
        borderPain = new Paint();
        borderPain.setAntiAlias(true);
        borderPain.setStyle(Paint.Style.STROKE);
        borderPain.setStrokeWidth(2);
        borderPain.setColor(Color.argb(255, 56, 181, 173));

        fillPain = new Paint();
        fillPain.setAntiAlias(true);
        fillPain.setStyle(Paint.Style.FILL);
        fillPain.setColor(Color.argb(255, 128, 128, 128));
    }

    public void flip() {
        removeModifier(modifier);
        addModifier(modifier);
    }

    public float getCenterX() {
        return x + getWidth() / 2;
    }

    public float getCenterY() {
        return y + getHeight() / 2;
    }

    public void setFlip(float flip) {
        flipAngel = flip;
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.save();
        camera.save();
        camera.rotateY(flipAngel);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        matrix.postTranslate(getWidth() / 2, getHeight() / 2);
        matrix.postTranslate(x, y);

        canvas.drawCircle(getCenterX(), getCenterY(), (getWidth() + 4) / 2, fillPain);
        canvas.drawCircle(getCenterX(), getCenterY(), (getWidth() + 4) / 2, borderPain);

        if(getBitmap() != null)
            canvas.drawBitmap(getBitmap(), matrix, mPaint);
    }

    @Override
    public void dispose() {
        modifiers.clear();
        childs.clear();
        setBitmap(null);
    }
}
