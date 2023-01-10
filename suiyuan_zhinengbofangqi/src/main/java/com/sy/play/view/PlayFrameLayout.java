package com.sy.play.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.sy.play.SYTextureView;

public class PlayFrameLayout extends FrameLayout {
    private OnTouchListener onTouchListener;

    public PlayFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public PlayFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlayFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        super.setOnTouchListener(onTouchListener);
        this.onTouchListener = onTouchListener;
    }

    public boolean onTouch(SYTextureView textureView, MotionEvent event) {
        return onTouchListener.onTouch(textureView,event);
    }
}
