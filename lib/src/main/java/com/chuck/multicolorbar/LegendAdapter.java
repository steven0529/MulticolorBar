package com.chuck.multicolorbar;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 14/07/2017
 */

class LegendAdapter<T extends MulticolorBarItem> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<T> itemsList;

    LegendAdapter(List<T> itemsList) {
        this.itemsList = itemsList;
    }

    @Override
    public LegendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_legend, parent, false);
        return new LegendViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LegendViewHolder legendViewHolder = (LegendViewHolder) holder;
        String formattedNumber = NumberFormat.getNumberInstance(Locale.US).format(itemsList.get(position).getItemValue());
        legendViewHolder.tvItemTextView.setText(itemsList.get(position).getItemName()
                + " (" + formattedNumber + ")");
        legendViewHolder.ivItemColorbox.setBackgroundColor(Color.parseColor("#"
                + ColorDictionary.getColorByIndex(position)));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class LegendViewHolder extends RecyclerView.ViewHolder {

        ImageView ivItemColorbox;
        TextView tvItemTextView;

        public LegendViewHolder(View itemView) {
            super(itemView);
            ivItemColorbox = (ImageView) itemView.findViewById(R.id.iv_item_colorbox);
            tvItemTextView = (TextView) itemView.findViewById(R.id.tv_item_name);
        }
    }
}
