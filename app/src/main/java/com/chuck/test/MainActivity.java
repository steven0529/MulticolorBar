package com.chuck.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chuck.multicolorbar.MulticolorBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MulticolorBarView multicolorBar = (MulticolorBarView) findViewById(R.id.multicolorBarView);

        List<FileCategory> fileCategories = new ArrayList<>();
        fileCategories.add(new FileCategory("Documents", 6.251, "GB", "#FA5F5E"));
        fileCategories.add(new FileCategory("Music", 17.6126, "GB", "#5EACEC"));
        fileCategories.add(new FileCategory("Videos", 54.12, "GB", "#F4D851"));
        fileCategories.add(new FileCategory("Downloads", 31.8, "GB", "#C11FD7"));
        fileCategories.add(new FileCategory("Apps", 77.1, "GB", "#FFAB51"));
        fileCategories.add(new FileCategory("Others", 22.3, "GB", "#5CAD56"));

        multicolorBar.setMaxValue(250);
        multicolorBar.setItemValueFormatter(new FileSizeValueFormatter(Locale.US));
        multicolorBar.setMulticolorBarAdapter(new FileSizeAdapter(fileCategories));
        multicolorBar.setmShowLegendUnits(false);

        double total = 0;
        for (FileCategory fileCategory : fileCategories) {
            total += fileCategory.getFileSize();
        }
        multicolorBar.setTitle(String.format(Locale.US, "%.2f", total) + " GB of 250.0 GB" );
    }
}
