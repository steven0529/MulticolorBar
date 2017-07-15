package com.chuck.test;

import com.chuck.multicolorbar.MulticolorBarAdapter;
import com.chuck.multicolorbar.MulticolorBarItem;

import java.util.List;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 13/07/2017
 */

public class FileSizeAdapter extends MulticolorBarAdapter<FileCategory> {

    public FileSizeAdapter(List<FileCategory> items) {
        super(items);
    }

    @Override
    protected MulticolorBarItem convertItem(final FileCategory fileCategory) {
        return new MulticolorBarItem() {
            @Override
            public double getItemValue() {
                return fileCategory.getFileSize();
            }

            @Override
            public String getItemName() {
                return fileCategory.getName();
            }

            @Override
            public String getColorHex() {
                return fileCategory.getColor();
            }

            @Override
            public String getUnit() {
                return fileCategory.getUnit();
            }
        };
    }
}
