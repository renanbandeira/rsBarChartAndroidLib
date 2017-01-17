# rsBarChartAndroidLib
An android library to create custom bar charts

## Demo

![This is one example of how you can use it](example.png?raw=true "Demo")

## Instalation

```
dependencies {
    compile 'com.github.renanbandeira:br.com.renanbandeira.bargraphlib:0.0.1'
}
```

## Usage
It's very simple to use it. First of all, you need to add the View to your XML:

```
<br.com.renanbandeira.bargraphlib.view.BarGraph
      xmlns:graph="http://schemas.android.com/apk/res-auto"
      android:id="@+id/graph"
      android:layout_width="match_parent"
      android:layout_height="match_parent"
      graph:axisLineColor="@color/colorPrimary"
      graph:gridLineColor="@color/colorPrimaryDark"
      graph:gridLineWidth="1dp"
      graph:horizontalGridSpacing="20dp"
      graph:offsetBottomAxis="57dp"
      graph:verticalGridSpacing="20dp"
      />
```

Then, you need to create an adapter that inherits from `BaseGraphBarAdapter` (it's a `RecyclerView` with some extra things) and, on your Fragment/Activity you do:
```
mBarGraph.setAdapter(mAdapter);
```

*You can check/build the demo module for previewing and testing.*
