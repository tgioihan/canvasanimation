package com.bestfunforever.canvasanimation.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.widget.FrameLayout;

import com.bestfunforever.canvasanimation.AnimationRunnable;
import com.bestfunforever.canvasanimation.TouchManager;
import com.bestfunforever.canvasanimation.callback.IAnimation;
import com.bestfunforever.canvasanimation.callback.IDisposeable;
import com.bestfunforever.canvasanimation.entity.Entity;

import java.util.concurrent.CopyOnWriteArrayList;


public abstract class AnimationViewGroup extends FrameLayout implements IDisposeable,IAnimation,TouchManager.TouchListener {
    protected TouchManager touchManager;
    protected AnimationRunnable animationRunnable;
    protected long preveousUpdate;
    protected CopyOnWriteArrayList<Entity> entities;
    private boolean isActvieAnimation;
    private boolean dirty;
    private boolean childDirty;

    protected abstract void onUpdate(long elapseTime);
    protected abstract void onInit(Context context);
    protected abstract void onDispose();

    public AnimationViewGroup(Context context) {
        super(context);
        init(context);
    }

    public AnimationViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        touchManager = new TouchManager(this);
        animationRunnable = new AnimationRunnable(this);
        preveousUpdate = System.currentTimeMillis();
        entities = new CopyOnWriteArrayList<>();
        isActvieAnimation = false;
        onInit(context);
    }

    public void addEntity(Entity entity){
        entities.remove(entity);
        entities.add(entity);
    }

    public void addEntity(Entity entity,int position) {
        entities.remove(entity);
        entities.add(position, entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void removeEntities(){
        entities.clear();
    }

    @Override
    public void update() {
        final long elapsedTime = System.currentTimeMillis() - preveousUpdate;
        for (Entity entity : entities) {
            childDirty = entity.update(elapsedTime);
            if(childDirty){
                dirty = true;
            }
        }
        onUpdate(elapsedTime);
        preveousUpdate = System.currentTimeMillis();
        if(dirty)
            postInvalidate();
        dirty = false;
    }

    @Override
    public boolean post(Runnable action) {
        boolean result = super.post(action);
        if(action instanceof AnimationRunnable){
            preveousUpdate = System.currentTimeMillis();
            isActvieAnimation = true;
        }

        return result;
    }

    @Override
    public boolean removeCallbacks(Runnable action) {
        if(action!=null &&action instanceof AnimationRunnable){
            preveousUpdate = System.currentTimeMillis();
            isActvieAnimation = false;
        }

        return super.removeCallbacks(action);
    }

    @Override
    public boolean isActive() {
        return isActvieAnimation;
    }
    @Override
    public void dispose() {
        removeCallbacks(animationRunnable);
        touchManager.dispose();
        for (Entity entity :entities){
            entity.dispose();
        }
        entities.clear();
        onDispose();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("", "draw");
        preDraw(canvas);
        for (Entity entity :entities){
            entity.draw(canvas);
        }
    }

    protected void preDraw(Canvas canvas) {

    }

    @Override
    public void onItemClicked(Entity entity) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return touchManager.onTouchEvent(event);
    }

    @Override
    public void onTouchDown(Entity entity) {

    }

    @Override
    public void onTouchMove(Entity entity, float xDistance, float yDistance) {

    }

    @Override
    public void onTouchUp(Entity entity, VelocityTracker mVelocityTracker) {

    }

}