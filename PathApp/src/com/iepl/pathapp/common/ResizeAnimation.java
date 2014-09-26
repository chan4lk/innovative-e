package com.iepl.pathapp.common;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class ResizeAnimation extends Animation {

    private int startHeight;
    private int deltaHeight;
    private View view;

   
    public ResizeAnimation (View v) {
        this.view = v;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        view.getLayoutParams().height = (int) (startHeight + deltaHeight * interpolatedTime);
        view.requestLayout();
    }
   
    public void setParams(int start, int end) {

        this.startHeight = start;
        deltaHeight = end - startHeight;
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
} 