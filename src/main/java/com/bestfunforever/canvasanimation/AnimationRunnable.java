package com.bestfunforever.canvasanimation;

import com.bestfunforever.canvasanimation.callback.IAnimation;

import java.lang.ref.WeakReference;

/**
 * Created by nguyenxuan on 6/28/2015.
 */
public class AnimationRunnable  implements Runnable{
    public static final int UpDateTime = 0;
    private WeakReference<IAnimation> reference;

    public AnimationRunnable(IAnimation reference) {
        this.reference = new WeakReference<IAnimation>(reference);
    }

    @Override
    public void run() {
        if(reference!=null && reference.get()!=null){
            reference.get().update();
            if(reference.get().isActive())
            reference.get().post(this);
        }
    }
}