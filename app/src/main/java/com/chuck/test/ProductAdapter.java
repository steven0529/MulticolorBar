package com.chuck.test;

import com.chuck.multicolorbar.MulticolorBarAdapter;
import com.chuck.multicolorbar.MulticolorBarItem;

import java.util.List;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 13/07/2017
 */

public class ProductAdapter extends MulticolorBarAdapter<Product> {

    public ProductAdapter(List<Product> items) {
        super(items);
    }

    @Override
    protected MulticolorBarItem convertItem(final Product product) {
        return new MulticolorBarItem() {
            @Override
            public int getItemValue() {
                return product.getSalesVolume();
            }

            @Override
            public String getItemName() {
                return product.getName();
            }
        };
    }
}
