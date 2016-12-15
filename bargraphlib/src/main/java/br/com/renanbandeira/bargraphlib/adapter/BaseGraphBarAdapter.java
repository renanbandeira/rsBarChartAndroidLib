package br.com.renanbandeira.bargraphlib.adapter;

import android.support.v7.widget.RecyclerView;

/***
 * Adapter that constructs each bar of the chart.
 * @param <VH> The ViewHolder to bind the views (See RecyclerView examples)
 */
public abstract class BaseGraphBarAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
  /***
   * The view height. It is used to scale the data proportionally
   */
  protected int viewHeight;

  /***
   * The highest value of the items in the chart. It is used to scale the data proportionally.
   */
  protected abstract double getHighestValue();

  /***
   * Sets the BarGraphView height
   * @param viewHeight
   */
  public void setViewHeight(int viewHeight) {
    this.viewHeight = viewHeight;
  }
}
