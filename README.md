# MulticolorBar
A Subdivided Multicolor bar with legend for Android

<img src="https://raw.githubusercontent.com/steven0529/MulticolorBar/master/multicolorbar.png" height=100 width=48 >

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
    compile 'com.github.steven0529:MulticolorBar:0.3.0'
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
```

5. Reference multicolorBarView in your activity/fragment and set MulticolorAdapter
```java
        MulticolorBarView multicolorBar = (MulticolorBarView) findViewById(R.id.multicolorBarView);

        List<FileCategory> fileCategories = new ArrayList<>();
        fileCategories.add(new FileCategory("Documents", 6.251, "GB", "#FA5F5E"));
        fileCategories.add(new FileCategory("Music", 17.6126, "GB", "#5EACEC"));
        fileCategories.add(new FileCategory("Videos", 54.12, "GB", "#F4D851"));
        fileCategories.add(new FileCategory("Downloads", 31.8, "GB", "#C11FD7"));
        fileCategories.add(new FileCategory("Apps", 77.1, "GB", "#FFAB51"));
        fileCategories.add(new FileCategory("Others", 22.3, "GB", "#5CAD56"));

        multicolorBar.setMulticolorBarAdapter(new FileSizeAdapter(fileCategories));
```

6. [Optional] Add legend value formatter
```java
        multicolorBar.setItemValueFormatter(new FileSizeValueFormatter(Locale.US));
```

7. [Optional] Set a max value
```java
        multicolorBar.setMaxValue(250);
```
