package com.bestfunforever.canvasanimation.entity;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.bestfunforever.canvasanimation.callback.IDisposeable;
import com.bestfunforever.canvasanimation.modifier.BaseModifier;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * Created by nguyenxuan on 6/27/2015.
 */
public abstract class Entity implements IDisposeable {

    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected float alpha;
    protected int id;
    protected float scaleX;
    protected float scaleY;
    protected Paint mPaint;
    protected int color;
    private boolean enable;

    private boolean dirty;
    private boolean childDirty;

    public float getTouchDelegateRank() {
        return touchDelegateRank;
    }

    public void setTouchDelegateRank(float touchDelegateRank) {
        this.touchDelegateRank = touchDelegateRank;
    }

    protected float touchDelegateRank;

    public float getRotationAngel() {
        return rotationAngel;
    }

    public void setRotationAngel(float rotationAngel) {
        this.rotationAngel = rotationAngel;
        dirty = true;
    }

    protected float rotationAngel;

    public boolean isEnableClick() {
        return enableClick;
    }

    public void setEnableClick(boolean enableClick) {
        this.enableClick = enableClick;
    }

    private boolean enableClick;

    public CopyOnWriteArrayList<Entity> childs;

    public CopyOnWriteArrayList<BaseModifier> getModifiers() {
        return modifiers;
    }

    protected CopyOnWriteArrayList<BaseModifier> modifiers;

    public Entity() {
        this(new Paint());
        enableClick = true;
    }

    public Entity(Paint mPaint) {
        this.mPaint = mPaint;
        scaleX = 1;
        scaleY = 1;
        modifiers = new CopyOnWriteArrayList<>();
        childs = new CopyOnWriteArrayList<>();
        enableClick = true;
    }

    public void addChild(Entity child) {
        childs.add(child);
    }

    public void removeChild(Entity child) {
        childs.remove(child);
    }

    public void removeChilds() {
        childs.clear();
    }

    public void addModifier(BaseModifier modifier) {
        synchronized (modifiers) {
            modifier.setEntity(this);
            modifier.reset();
            modifiers.remove(modifier);
            modifiers.add(modifier);
        }
    }

    public void removeModifier(BaseModifier modifier) {
        synchronized (modifiers) {
            modifiers.remove(modifier);
        }
    }

    public void removeModifiers() {
        synchronized (modifiers) {
            modifiers.clear();
        }
    }

    public boolean contain(float srcX, float srcY) {
        if (srcX >= getX() && srcX <= getX() + getWidth() && srcY >= getY() && srcY <= getY() + getHeight()) {
            return true;
        }
        return false;
    }

    public boolean containInDelegate(float srcX, float srcY) {
        if (srcX >= getX() - touchDelegateRank && srcX <= getX() + getWidth() + touchDelegateRank && srcY >= getY() - touchDelegateRank && srcY <= getY() + getHeight() + touchDelegateRank) {
            return true;
        }
        return false;
    }

    public void setAlphaBy(float divAlpha) {
        setAlpha(alpha + divAlpha);
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        for (Entity child : childs) {
            child.setAlpha(alpha);
        }
        mPaint.setAlpha((int) (alpha * 255));
        dirty = true;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        mPaint.setColor(color);
        dirty = true;
    }

    public void translateDeltaX(float deltaX) {
        x += deltaX;
        dirty = true;
    }

    public void translateDeltaY(float deltaY) {
        y += deltaY;
        dirty = true;
    }

    public void translateDelta(float deltaX, float deltaY) {
        x += deltaX;
        y += deltaY;
        dirty = true;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        dirty = true;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        dirty = true;
    }

    public void setWidth(float width) {
        this.width = width;
        dirty = true;
    }

    public void setHeight(float height) {
        this.height = height;
        dirty = true;
    }

    public float getScale() {
        return Math.max(scaleX,scaleY);
    }

    public void setScale(float scale) {
        this.scaleX = scale;
        this.scaleY = scale;
        dirty = true;
    }

    public void setScale(float scaleX,float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        dirty = true;
    }

    public float getScaleX() {
        return scaleX;
    }

    public void setScaleX(float scaleX) {
        this.scaleX = scaleX;
        dirty = true;
    }

    public float getScaleY() {
        return scaleY;
    }

    public void setScaleY(float scaleY) {
        this.scaleY = scaleY;
        dirty = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public boolean update(long time) {
        Iterator<BaseModifier> iter = modifiers.iterator();
        while (iter.hasNext()) {
            BaseModifier modifier = iter.next();
            modifier.update(time, this);
        }
        onUpdate(time);
        for (Entity entity : childs) {
            childDirty = entity.update(time);
            if(childDirty){
                dirty = true;
            }
        }
        childDirty = dirty;
        dirty = false;
        return childDirty;

    }

    protected void onUpdate(long time) {

    }

    @Override
    public void dispose() {
        modifiers.clear();
        childs.clear();
        onDispose();
    }

    protected void onDispose() {

    }

    public abstract void onDraw(Canvas canvas);

    public void onTouchDown() {

    }

    public void onTouchMove(float eventX, float eventY) {

    }

    public void onTouchUp() {

    }

    public void draw(Canvas canvas) {
        canvas.save();
        applyScale(canvas);
        canvas.rotate(rotationAngel, x + width / 2, y + height / 2);
        onDraw(canvas);
        canvas.translate(x, y);
        for (Entity child : childs) {
            child.draw(canvas);
        }
        canvas.restore();
    }

    private void applyScale(Canvas canvas) {
        canvas.translate(x + width / 2, y + height / 2);
        canvas.scale(getScaleX(), getScaleY());
        canvas.translate(-(x + width / 2), -(y + height / 2));

    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        dirty = true;
    }

    public void setPositionByEntity(Entity entity) {
        this.setPosition(entity.getX(), entity.getY());
    }
}