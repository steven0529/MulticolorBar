package com.chuck.multicolorbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 13/07/2017
 */

public abstract class MulticolorBarAdapter<T> {

    private List<T> items;
    private List<MulticolorBarItem> multicolorBarItems;

    protected abstract MulticolorBarItem convertItem(T t);

    public MulticolorBarAdapter(List<T> items) {
        this.items = items;
        this.multicolorBarItems = new ArrayList<>();
        for (T t : items) {
            multicolorBarItems.add(convertItem(t));
        }
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public List<MulticolorBarItem> getMulticolorBarItems() {
        return multicolorBarItems;
    }

    public void setMulticolorBarItems(List<MulticolorBarItem> multicolorBarItems) {
        this.multicolorBarItems = multicolorBarItems;
    }
}
