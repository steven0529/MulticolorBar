package com.chuck.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chuck.multicolorbar.MulticolorBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MulticolorBarView multicolorBar = (MulticolorBarView) findViewById(R.id.multicolorBarView);

        List<Product> products = new ArrayList<>();
        products.add(new Product("Prod 1", 1800, "bags", "#" + ColorDictionary.getColorByIndex(0)));
        products.add(new Product("Prod 2", 2098, "bags", "#" + ColorDictionary.getColorByIndex(1)));
        products.add(new Product("Prod 3", 10245, "bags", "#" + ColorDictionary.getColorByIndex(2)));
        products.add(new Product("Prod 4", 3278, "bags", "#" + ColorDictionary.getColorByIndex(3)));
        products.add(new Product("Prod 5", 3000, "bags", "#" + ColorDictionary.getColorByIndex(4)));
        products.add(new Product("Prod 6", 2000, "bags", "#" + ColorDictionary.getColorByIndex(5)));
        multicolorBar.setMulticolorBarAdapter(new ProductAdapter(products));

        int total = 0;
        for (Product product : products) {
            total += product.getSalesVolume();
        }
        multicolorBar.setTitle(total + "");
    }
}
