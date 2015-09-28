package com.bestfunforever.canvasanimation;

import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;

import com.bestfunforever.canvasanimation.callback.IDisposeable;
import com.bestfunforever.canvasanimation.entity.Entity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by nguyenxuan on 6/28/2015.
 */
public class TouchManager implements IDisposeable {

    private static final float DRAG_TRIGGER = 15;
    private List<Entity> entitys;
    private Entity currentActionEntity;
    private float lastTouchX;
    private float lastTouchY;
    private float initialTouchX;
    private float initialTouchY;
    private boolean dragging;
    private TouchListener touchListener;
    private VelocityTracker mVelocityTracker;
    private boolean sendTouchToOther;

    public TouchManager(TouchListener touchListener) {
        this.touchListener = touchListener;
        entitys = new ArrayList<>();
        sendTouchToOther = true;
    }

    public void addEntity(Entity entity){
        entitys.add(entity);
    }

    public void removeEntity(Entity entity){
        entitys.remove(entity);
    }


    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        switch (action & MotionEventCompat.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                }else{
                    mVelocityTracker.clear();
                }
                initialTouchX = lastTouchX = event.getX();
                initialTouchY = lastTouchY = event.getY();
                dragging = false;
                currentActionEntity = findEntity(event);
                if(currentActionEntity!=null&&currentActionEntity.isEnable()){
                    currentActionEntity.onTouchDown();
                }

                if(currentActionEntity == null || !currentActionEntity.isEnable()){
                    if(!sendTouchToOther) {
                        touchListener.onTouchDown(currentActionEntity);
                        return true;
                    }
                    else{
                        return false;
                    }
                }else{
                    touchListener.onTouchDown(currentActionEntity);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                }
                mVelocityTracker.addMovement(event);
                if(!dragging){
                    if(Math.abs(event.getX()-initialTouchX) >= DRAG_TRIGGER||Math.abs(event.getY()-initialTouchY) >= DRAG_TRIGGER){
                        dragging = true;
                    }
                }
                final float divX = event.getX()-lastTouchX;
                final float divY = event.getY()-lastTouchY;
                touchListener.onTouchMove(currentActionEntity,divX,divY);
                lastTouchX = event.getX();
                lastTouchY = event.getY();
                if(currentActionEntity!=null){
                    currentActionEntity.onTouchMove(lastTouchY,lastTouchX);
                }
                break;
            case MotionEvent.ACTION_UP:
                if(!dragging){
                    touchListener.onItemClicked(currentActionEntity);
                }else{
                    mVelocityTracker.addMovement(event);
                    touchListener.onTouchUp(currentActionEntity,mVelocityTracker);
                    if(currentActionEntity!=null){
                        currentActionEntity.onTouchUp();
                    }
                }
                dragging = false;
                break;
            default:
                break;

        }
        return true;
    }

    public Entity findEntity(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();
        for (int i = entitys.size()-1; i >=0 ; i--) {
            Entity entity = entitys.get(i);
            if(entity.contain(x,y)){
                Log.d("","findEntity "+ entity.getId());
                return entity;
            }
        }
        return null;
    }

    @Override
    public void dispose() {
        entitys.clear();
    }

    public void setSendTouchToOther(boolean sendTouchToOther) {
        this.sendTouchToOther = sendTouchToOther;
    }

    public interface TouchListener{

        void onTouchDown(Entity entity);

        void onTouchMove(Entity entity, float xDistance, float yDistance);

        void onTouchUp(Entity entity, VelocityTracker mVelocityTracker);

        void onItemClicked(Entity entity);


    }
}
