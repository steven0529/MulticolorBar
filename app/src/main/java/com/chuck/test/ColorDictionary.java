package com.chuck.test;

import java.util.Random;

/**
 * Created by Steven Reyes (sreyes@stratpoint.com) on 13/07/2017
 */

public class ColorDictionary {

    private static final String[] colorsArray = new String[] {
            "bf342c", "58ba9f", "e1d94c", "eb442d", "95171f", "3f3229", "8d161e",
            "b9b245", "df482d", "14502d", "0f103b", "f43a6b", "fbb0aa", "aa7b9d",
            "53c1a4", "01768a", "89c657", "80c891", "fee4a2", "e86453", "f43b3f"
    };

    private static final String[] happyColorsArray = new String[] {
            "FA5F5E", "5EACEC", "F4D851", "C11FD7", "F2B0B1", "5CAD56", "DD6133", "45362F"
    };

    public static String getColorByIndex(int index) {
        int indexModulus = index % happyColorsArray.length;
        return happyColorsArray[indexModulus];
    }

    public static String getRandomColor() {
        Random r = new Random();
        int idx = r.nextInt(colorsArray.length);
        return colorsArray[idx];
    }

    public static String getRandomColor(String colorBefore) {
        boolean noColor = true;
        int idx = 0;
        while(noColor) {
            Random r = new Random();
            idx = r.nextInt(colorsArray.length);
            if (!colorBefore.equals(colorsArray[idx]))
                noColor = true;
        }
        return colorsArray[idx];
    }
}
