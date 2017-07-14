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

    private TextView tvTitle;
    private MulticolorBar multicolorBar;
    private boolean mShowLegend;

    public MulticolorBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MulticolorBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(LinearLayout.VERTICAL);

        tvTitle = new TextView(context);
        multicolorBar = new MulticolorBar(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.MulticolorBarView);
        tvTitle.setText(typedArray.getString(R.styleable.MulticolorBarView_title));
        tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                typedArray.getDimension(R.styleable.MulticolorBarView_titleTextSize, 14f));
        LinearLayout.LayoutParams titleLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        titleLayoutParams.setMargins(20, 0, 0, 30);

        LinearLayout.LayoutParams multicolorBarLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        multicolorBarLayoutParams.setMargins(0, 20, 0, 20);

        mShowLegend = typedArray.getBoolean(R.styleable.MulticolorBarView_showLegend, false);

        addView(tvTitle, titleLayoutParams);
        addView(multicolorBar, multicolorBarLayoutParams);
    }

    public void setShowLegend(boolean mShowLegend) {
        this.mShowLegend = mShowLegend;
        displayLegend();
    }

    public void setMulticolorBarAdapter(MulticolorBarAdapter multicolorBarAdapter) {
        multicolorBar.setMulticolorBarAdapter(multicolorBarAdapter);
        displayLegend();
    }

    private void displayLegend() {
        if (mShowLegend) {
            for (int i = 0; i < multicolorBar.getMulticolorBarAdapter().getMulticolorBarItems().size(); i++) {
                MulticolorBarItem item = (MulticolorBarItem) multicolorBar
                        .getMulticolorBarAdapter().getMulticolorBarItems().get(i);
                LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = inflater.inflate(R.layout.item_legend, null);
                TextView tvItemName = (TextView) view.findViewById(R.id.tv_item_name);
                tvItemName.setText(item.getItemName() + " (" + item.getItemValue() + ") "
                        + item.getUnit());
                ImageView ivItemColor = (ImageView) view.findViewById(R.id.iv_item_colorbox);
                ivItemColor.setBackgroundColor(Color.parseColor(item.getColorHex()));
                addView(view);
            }
        }
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

}
