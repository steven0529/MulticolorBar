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
        products.add(new Product("Prod 1", 1800));
        products.add(new Product("Prod 2", 2098));
        products.add(new Product("Prod 3", 10245));
        products.add(new Product("Prod 4", 3278));
        products.add(new Product("Prod 5", 3000));
        products.add(new Product("Prod 6", 2000));
        multicolorBar.setMulticolorBarAdapter(new ProductAdapter(products));

        int total = 0;
        for (Product product : products) {
            total += product.getSalesVolume();
        }
        multicolorBar.setTitle(total + "");
    }
}
