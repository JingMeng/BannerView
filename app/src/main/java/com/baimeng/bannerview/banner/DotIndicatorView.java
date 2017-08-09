package com.baimeng.bannerview.banner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/8/9.
 */

public class DotIndicatorView extends View {
    private Drawable drawable ;
    public DotIndicatorView(Context context) {
        this(context,null);
    }

    public DotIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DotIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable ;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(drawable != null){
            drawable.setBounds(0,0,getMeasuredWidth(),getMeasuredHeight());
            drawable.draw(canvas);
        }
    }
}
