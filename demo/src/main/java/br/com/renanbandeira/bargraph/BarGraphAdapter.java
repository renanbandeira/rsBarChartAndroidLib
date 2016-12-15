package br.com.renanbandeira.bargraph;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.renanbandeira.bargraphlib.adapter.BaseGraphBarAdapter;
import de.hdodenhof.circleimageview.CircleImageView;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class BarGraphAdapter extends BaseGraphBarAdapter<BarGraphAdapter.ViewHolder> {

  private List<Double> values;
  private NumberFormat brCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
  private NumberFormat brNumberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

  public BarGraphAdapter(List<Double> values) {
    this.values = values;
    Collections.sort(this.values);
  }

  private int calculateHeightForValue(double value) {
    return  ((int)(viewHeight * value))/(int)getHighestValue();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.graph_line, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(final ViewHolder holder, int position) {
    Double value = values.get(position);

    holder.value.setText(brCurrencyFormat.format(value));

    int valueHeight = calculateHeightForValue(value);

    ValueAnimator va = ValueAnimator.ofInt(10, valueHeight);
    va.setDuration(1800);
    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      public void onAnimationUpdate(ValueAnimator animation) {
        Integer value = (Integer) animation.getAnimatedValue();
        holder.line.getLayoutParams().height = value.intValue();
        holder.line.requestLayout();
      }
    });
    va.start();

    ValueAnimator textValue = ValueAnimator.ofFloat(0, value.floatValue());
    textValue.setDuration(1800);
    textValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override
      public void onAnimationUpdate(ValueAnimator animation) {
        float value = (float) animation.getAnimatedValue();
        holder.value.setText(brNumberFormat.format(value));
        holder.value.requestLayout();
      }
    });
    //textValue.start();

    holder.photo.setImageResource(R.drawable.user);
  }

  @Override
  public int getItemCount() {
    return values.size();
  }

  @Override protected double getHighestValue() {
    return values.get(values.size() - 1);
  }

  public static class ViewHolder extends RecyclerView.ViewHolder {
    TextView value;
    View ring;
    View line;
    CircleImageView photo;

    public ViewHolder(View itemView) {
      super(itemView);

      value = (TextView) itemView.findViewById(R.id.value);
      ring = itemView.findViewById(R.id.ring);
      line = itemView.findViewById(R.id.line);
      photo = (CircleImageView) itemView.findViewById(R.id.friendPhoto);
    }
  }
}

