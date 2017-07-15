package com.chuck.test;

import com.chuck.multicolorbar.ItemValueFormatter;

import java.util.Locale;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 15/07/2017
 */

public class FileSizeValueFormatter implements ItemValueFormatter {

    private Locale mLocale;

    public FileSizeValueFormatter(Locale mLocale) {
        this.mLocale = mLocale;
    }

    @Override
    public String formatItemValue(double itemValue) {
        return String.format(mLocale, "%.2f", itemValue);
    }
}
