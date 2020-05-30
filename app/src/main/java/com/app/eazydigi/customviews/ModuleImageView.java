package com.app.eazydigi.customviews;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class ModuleImageView extends AppCompatImageView {

    public ModuleImageView(Context context) {
        super(context);
    }

    public ModuleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ModuleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = (int) (getMeasuredWidth()/3);

        setMeasuredDimension(width, width);

    }

}