package com.chuck.multicolorbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 12/07/2017
 */

public class MulticolorBar extends View {

    private MulticolorBarAdapter multicolorBarAdapter;
    private Rect viewRect;
    private Paint paint;

    private List<Rect> itemsRect;
    private Rect mBackgroundRect;
    private Paint mBackgroundPaint;
    private boolean mHasDefinedMax = false;
    private int mMax = 0;
    private int mWidth;
    private int mHeight;

    public MulticolorBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.MulticolorBarView);

        mHeight = (int) typedArray.getDimension(R.styleable.MulticolorBarView_multicolorBarSize, 25.0f);
        init();
    }

    private void init() {
        this.itemsRect = new ArrayList<>();
        this.mBackgroundPaint = new Paint();
        this.mBackgroundPaint.setColor(Color.parseColor("#bababa"));
        this.paint = new Paint();
        this.viewRect = new Rect(getLeft(), getTop(), mWidth, mHeight);
        this.mBackgroundRect = new Rect(getLeft(), getTop(), mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(viewRect, mBackgroundPaint);
        List<MulticolorBarItem> multicolorBarItemList = multicolorBarAdapter.getMulticolorBarItems();
        for (int i = 0; i < multicolorBarItemList.size(); i++) {
            MulticolorBarItem item = multicolorBarItemList.get(i);

            paint.setColor(Color.parseColor(item.getColorHex()));
            Rect rect = itemsRect.get(i);
            if (i == 0) {
                rect.right = computeItemDrawWidth(item);
            } else if (i == multicolorBarItemList.size() && mHasDefinedMax) {
                rect.left = itemsRect.get(i - 1).right;
                rect.right = itemsRect.get(i - 1).right + mBackgroundRect.right;
            } else {
                rect.left = itemsRect.get(i - 1).right;
                rect.right = itemsRect.get(i - 1).right + computeItemDrawWidth(item);
            }
            rect.bottom = mHeight;
            canvas.drawRect(rect, paint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
//        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        viewRect.right = mWidth;
        viewRect.bottom = mHeight;
        mBackgroundRect.right = mWidth;
        mBackgroundRect.bottom = mHeight;
    }

    private int computeItemDrawWidth(MulticolorBarItem item) {
        int drawWidth;
        double scale = (double) mWidth / mMax;
        drawWidth = (int) (item.getItemValue() * scale);
        return drawWidth;
    }

    public void setBackgroundColor(String color) {
        this.mBackgroundPaint.setColor(Color.parseColor(color));
    }

    public MulticolorBarAdapter getMulticolorBarAdapter() {
        return multicolorBarAdapter;
    }

    public void setMulticolorBarItems(List<MulticolorBarItem> items) {
        multicolorBarAdapter.setMulticolorBarItems(items);
    }

    public void setMulticolorBarAdapter(MulticolorBarAdapter multicolorBarAdapter) {
        this.multicolorBarAdapter = multicolorBarAdapter;
        this.itemsRect.clear();
        if (mMax == 0) {
            for (int i = 0; i < multicolorBarAdapter.getMulticolorBarItems().size(); i++) {
                MulticolorBarItem item = (MulticolorBarItem) multicolorBarAdapter.getMulticolorBarItems().get(i);
                mMax += item.getItemValue();
            }
        }
        for (int i = 0; i < multicolorBarAdapter.getMulticolorBarItems().size(); i++) {
            itemsRect.add(new Rect(0, 0, 0, mHeight));
        }
        invalidate();
    }

    public void setMax(int max) {
        this.mMax = max;
    }

    public void setHasDefinedMax(boolean hasDefinedMax) {
        this.mHasDefinedMax = hasDefinedMax;
    }
}
