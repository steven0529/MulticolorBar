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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 12/07/2017
 */

public class MulticolorBar extends View {

//    private List<MulticolorBarItem> items;
    private MulticolorBarAdapter multicolorBarAdapter;
    private Rect viewRect;
    private Paint paint;

    private Map<MulticolorBarItem, Rect> itemsMap;
    private Paint backgroundPaint;
    private int max = 0;
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
//        this.items = new ArrayList<>();
        this.itemsMap = new HashMap<>();
        this.backgroundPaint = new Paint();
        this.backgroundPaint.setARGB(255, 116, 116, 117);
        this.paint = new Paint();
        this.viewRect = new Rect(getLeft(), getTop(), mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(viewRect, backgroundPaint);

        MulticolorBarItem prevItem = null;
        int idx = 0;
        for (MulticolorBarItem item: itemsMap.keySet()) {
            paint.setColor(Color.parseColor(item.getColorHex()));
            Rect rect;
            if (idx == 0) {
                rect = itemsMap.get(item);
                rect.right = computeItemDrawWidth(item);
            } else {
                rect = itemsMap.get(item);
                rect.left = itemsMap.get(prevItem).right;
                rect.right = itemsMap.get(prevItem).right + computeItemDrawWidth(item);
            }
            rect.bottom = mHeight;
            canvas.drawRect(rect, paint);
            prevItem = item;
            idx++;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth = MeasureSpec.getSize(widthMeasureSpec);
//        mHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
        viewRect.right = mWidth;
        viewRect.bottom = mHeight;
    }

    private int computeItemDrawWidth(MulticolorBarItem item) {
        int drawWidth;
        double scale = (double) mWidth / max;
        drawWidth = (int) (item.getItemValue() * scale);
        return drawWidth;
    }

    public void setBackgroundColor(String color) {
        this.backgroundPaint.setColor(Color.parseColor(color));
    }

    public MulticolorBarAdapter getMulticolorBarAdapter() {
        return multicolorBarAdapter;
    }

    public void setMulticolorBarItems(List<MulticolorBarItem> items) {
        multicolorBarAdapter.setMulticolorBarItems(items);
    }

    public void setMulticolorBarAdapter(MulticolorBarAdapter multicolorBarAdapter) {
        this.multicolorBarAdapter = multicolorBarAdapter;
        this.itemsMap.clear();
        if (max == 0) {
            for (int i = 0; i < multicolorBarAdapter.getMulticolorBarItems().size(); i++) {
                MulticolorBarItem item = (MulticolorBarItem) multicolorBarAdapter.getMulticolorBarItems().get(i);
                max += item.getItemValue();
            }
        }
        for (int i = 0; i < multicolorBarAdapter.getMulticolorBarItems().size(); i++) {
            MulticolorBarItem item = (MulticolorBarItem) multicolorBarAdapter.getMulticolorBarItems().get(i);
            itemsMap.put(item, new Rect(0, 0, 0, mHeight));
        }
        invalidate();
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

}
