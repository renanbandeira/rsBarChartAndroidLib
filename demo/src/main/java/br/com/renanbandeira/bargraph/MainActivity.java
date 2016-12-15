package br.com.renanbandeira.bargraph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import br.com.renanbandeira.bargraphlib.view.BarGraph;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  BarGraph mBarGraph;
  BarGraphAdapter mAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mBarGraph = (BarGraph) findViewById(R.id.graph);
    List<Double> values = new ArrayList<>();
    values.add(10.4);
    values.add(55.13);
    values.add(5.47);
    values.add(144.0);
    values.add(200.2);
    values.add(33.1);

    mAdapter = new BarGraphAdapter(values);
    mBarGraph.setAdapter(mAdapter);
  }
}
