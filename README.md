# MulticolorBar
A Subdivided Multicolor bar with legend 

# Usage
1. Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
 repositories {
    ...
    maven { url "https://jitpack.io" }
 }
}
```

2. Add the dependency
```groovy
dependencies {
    compile 'com.github.steven0529:MulticolorBar:0.2.0'
}
```

3. Add MulticolorBarView in your xml layout
```xml
    <com.chuck.multicolorbar.MulticolorBarView
        android:id="@+id/multicolorBarView"
        android:layout_width="368dp"
        android:layout_height="wrap_content"
        custom:title="Title 1"
        custom:showLegend="true"
        custom:multicolorBarSize="30dp"
        custom:titleTextSize="20dp"
        custom:legendTextSize="14dp"
        android:layout_margin="5dp"
        android:layout_centerInParent="true"/>
```

4. Create adapter and extend MulticolorBarAdapter<?>, pass your own model as generics
```java
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

            @Override
            public String getColorHex() {
                return product.getColor();
            }

            @Override
            public String getUnit() {
                return product.getUnit();
            }
        };
    }
}
```

5. Reference multicolorBarView in your activity/fragment and set MulticolorAdapter
```java
        MulticolorBarView multicolorBar = (MulticolorBarView) findViewById(R.id.multicolorBarView);
        List<Product> products = new ArrayList<>();
        products.add(new Product("Prod 1", 1800, "bags", "#FA5F5E"));
        products.add(new Product("Prod 2", 2098, "bags", "#5EACEC"));
        products.add(new Product("Prod 3", 10245, "bags", "#F4D851"));
        products.add(new Product("Prod 4", 3278, "bags", "#F2B0B1"));
        products.add(new Product("Prod 5", 3000, "bags", "#DD6133"));
        products.add(new Product("Prod 6", 2000, "bags", "#45362F"));
        multicolorBar.setMulticolorBarAdapter(new ProductAdapter(products));
```
