package com.chuck.multicolorbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 14/07/2017
 */

public class MulticolorBarView extends LinearLayout {

    private AttributeSet attributeSet;
    private TextView tvTitle;
    private MulticolorBar multicolorBar;
    private ItemValueFormatter itemValueFormatter;
    private boolean mShowLegend;

    public MulticolorBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        this.attributeSet = attrs;
    }

    public MulticolorBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        this.attributeSet = attrs;
    }

    private void init(Context context, AttributeSet attrs) {
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.VERTICAL);

        tvTitle = new TextView(context);
        multicolorBar = new MulticolorBar(context, attrs);

        LayoutParams titleLayoutParams = applyTitleAttributeSet(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.MulticolorBarView);
        LinearLayout.LayoutParams multicolorBarLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        multicolorBarLayoutParams.setMargins(0, 20, 0, 20);

        mShowLegend = typedArray.getBoolean(R.styleable.MulticolorBarView_showLegend, false);

        addView(tvTitle, titleLayoutParams);
        addView(multicolorBar, multicolorBarLayoutParams);
    }

    public void setMaxValue(int max) {
        this.multicolorBar.setMax(max);
        this.multicolorBar.setHasDefinedMax(true);
    }

    public void setShowLegend(boolean mShowLegend) {
        this.mShowLegend = mShowLegend;
        displayLegend();
    }

    public void setItemValueFormatter(ItemValueFormatter itemValueFormatter) {
        this.itemValueFormatter = itemValueFormatter;
    }

    public void setMulticolorBarAdapter(MulticolorBarAdapter multicolorBarAdapter) {
        multicolorBar.setMulticolorBarAdapter(multicolorBarAdapter);
        displayLegend();
    }

    private LayoutParams applyTitleAttributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.MulticolorBarView);
        tvTitle.setText(typedArray.getString(R.styleable.MulticolorBarView_title));
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                typedArray.getDimension(R.styleable.MulticolorBarView_titleTextSize, 14f));

        int margin = typedArray.getLayoutDimension(R.styleable.MulticolorBarView_titleMargin, 0);
        int marginTop = typedArray.getLayoutDimension(R.styleable.MulticolorBarView_titleMarginTop, 0);
        int marginBottom = typedArray.getLayoutDimension(R.styleable.MulticolorBarView_titleMarginBottom, 0);
        int marginLeft = typedArray.getLayoutDimension(R.styleable.MulticolorBarView_titleMarginLeft, 0);
        int marginRight = typedArray.getLayoutDimension(R.styleable.MulticolorBarView_titleMarginRight, 0);

        LinearLayout.LayoutParams titleLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        if (margin != 0)
            titleLayoutParams.setMargins(margin, margin, margin, margin);
        else
            titleLayoutParams.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        return titleLayoutParams;
    }

    private void displayLegend() {
        if (mShowLegend) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (int i = 0; i < multicolorBar.getMulticolorBarAdapter().getMulticolorBarItems().size(); i++) {
                MulticolorBarItem item = (MulticolorBarItem) multicolorBar
                        .getMulticolorBarAdapter().getMulticolorBarItems().get(i);
                View view = inflater.inflate(R.layout.item_legend, null);
                TextView tvItemName = (TextView) view.findViewById(R.id.tv_item_name);
                if (itemValueFormatter != null) {
                    tvItemName.setText(item.getItemName() + " ("
                            + itemValueFormatter.formatItemValue(item.getItemValue()) + " " + item.getUnit()
                            + ")");
                } else {
                    tvItemName.setText(item.getItemName() + " (" + item.getItemValue() + " " +  item.getUnit()
                            + ")");
                }
                ImageView ivItemColor = (ImageView) view.findViewById(R.id.iv_item_colorbox);
                ivItemColor.setBackgroundColor(Color.parseColor(item.getColorHex()));
                addView(view);
            }
        }
    }

    public void clearItems() {
        for (int i = getChildCount() - 1 ; i > 1; i--) {
            removeViewAt(i);
        }
        requestLayout();
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

}
