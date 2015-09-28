package com.bestfunforever.canvasanimation;

public class TranformUtil {
    public static float calculateDuration( float startX, float startY, float endX, float endY, float maxDistance,int minDuaration, int maxDurationForFling) {
        float duation =  Math.min(Math.max(Math.abs((endY - startY)),Math.abs((endX - startX))) / maxDistance * maxDurationForFling, maxDurationForFling);
        if(duation<minDuaration){
            return minDuaration;
        }
        if(duation>maxDurationForFling){
            return maxDurationForFling;
        }
        return duation;
    }
}
