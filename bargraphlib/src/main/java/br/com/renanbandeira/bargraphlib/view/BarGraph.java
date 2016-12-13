package br.com.renanbandeira.bargraphlib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import br.com.renanbandeira.bargraphlib.R;

/***
 * This View is responsible to create a Bar Chart.
 * The idea is to work as a Horizontal RecyclerView
 */
public class BarGraph extends RecyclerView {
  float horizontalGridSpacing;
  float verticalGridSpacing;
  float offsetAxisX;
  float gridLineWidth;
  int gridLineColor;
  int axisLineColor;
  LinearLayoutManager mGraphLayoutManager;

  public BarGraph(Context context) {
    super(context);
    initLayoutManager(context);
  }

  public BarGraph(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context, attrs);
  }

  public BarGraph(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  /***
   * Creates the LayoutManager for the GraphView to make it behave like a horizontal ListView
   * @param context the Context to create the LinearLayoutManager
   */
  private void initLayoutManager(Context context) {
    mGraphLayoutManager = new LinearLayoutManager(context, OrientationHelper.HORIZONTAL, false);
    setLayoutManager(mGraphLayoutManager);
  }

  /***
   * Initialize the data from the attrs to draw the view
   * @param context
   * @param attrs
   */
  private void init(Context context, AttributeSet attrs) {
    initLayoutManager(context);
    TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BarGraph, 0, 0);
    try {
      horizontalGridSpacing = ta.getDimension(R.styleable.BarGraph_horizontalGridSpacing, 0.0f);
      verticalGridSpacing = ta.getDimension(R.styleable.BarGraph_verticalGridSpacing, 0.0f);
      offsetAxisX = ta.getDimension(R.styleable.BarGraph_offsetAxisX, 0.0f);
      gridLineWidth = ta.getDimension(R.styleable.BarGraph_offsetAxisX, 0.0f);
      gridLineColor = ta.getColor(R.styleable.BarGraph_gridLineColor, Color.TRANSPARENT);
      axisLineColor = ta.getColor(R.styleable.BarGraph_axisLineColor, gridLineColor);
    } finally {
      ta.recycle();
    }
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    float bottom = getBottom() - offsetAxisX;
    int top = getTop();
    int left = getLeft();
    int right = getRight();

    Paint paint = new Paint();

    //Draw axis X
    paint.setColor(axisLineColor);
    paint.setStrokeWidth(gridLineWidth);
    canvas.drawLine(left, bottom, right, bottom, paint);

    //Draw axis Y
    canvas.drawLine(left, getTop(), left, bottom, paint);

    paint.setColor(gridLineColor);
    //Draw horizontal lines
    while (bottom > 0) {
      canvas.drawLine(left, bottom, right, bottom, paint);
      bottom -= horizontalGridSpacing;
    }

    //Draw vertical lines
    bottom = getBottom() - offsetAxisX;
    while (right < 0) {
      canvas.drawLine(right, top, right, bottom, paint);
      right -= verticalGridSpacing;
    }

    super.dispatchDraw(canvas);
  }

  public float getHorizontalGridSpacing() {
    return horizontalGridSpacing;
  }

  public void setHorizontalGridSpacing(float horizontalGridSpacing) {
    this.horizontalGridSpacing = horizontalGridSpacing;
  }

  public float getVerticalGridSpacing() {
    return verticalGridSpacing;
  }

  public void setVerticalGridSpacing(float verticalGridSpacing) {
    this.verticalGridSpacing = verticalGridSpacing;
  }

  public float getOffsetAxisX() {
    return offsetAxisX;
  }

  public void setOffsetAxisX(float offsetAxisX) {
    this.offsetAxisX = offsetAxisX;
  }

  public float getGridLineWidth() {
    return gridLineWidth;
  }

  public void setGridLineWidth(float gridLineWidth) {
    this.gridLineWidth = gridLineWidth;
  }

  public int getGridLineColor() {
    return gridLineColor;
  }

  public void setGridLineColor(int gridLineColor) {
    this.gridLineColor = gridLineColor;
  }

  public int getAxisLineColor() {
    return axisLineColor;
  }

  public void setAxisLineColor(int axisLineColor) {
    this.axisLineColor = axisLineColor;
  }

  public LinearLayoutManager getGraphLayoutManager() {
    return mGraphLayoutManager;
  }

  public void setGraphLayoutManager(LinearLayoutManager mGraphLayoutManager) {
    this.mGraphLayoutManager = mGraphLayoutManager;
  }
}