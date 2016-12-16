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
import br.com.renanbandeira.bargraphlib.adapter.BaseGraphBarAdapter;

/***
 * This View is responsible to create a Bar Chart.
 * The idea is to work as a Horizontal RecyclerView
 */
public class BarGraph extends RecyclerView {
  int horizontalGridSpacing;
  int verticalGridSpacing;
  int offsetAxisX;
  int gridLineWidth;
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
      horizontalGridSpacing = ta.getDimensionPixelSize(R.styleable.BarGraph_horizontalGridSpacing, 0);
      verticalGridSpacing = ta.getDimensionPixelSize(R.styleable.BarGraph_verticalGridSpacing, 0);
      offsetAxisX = ta.getDimensionPixelSize(R.styleable.BarGraph_offsetBottomAxis, 0);
      gridLineWidth = ta.getDimensionPixelSize(R.styleable.BarGraph_gridLineWidth, 0);
      gridLineColor = ta.getColor(R.styleable.BarGraph_gridLineColor, Color.TRANSPARENT);
      axisLineColor = ta.getColor(R.styleable.BarGraph_axisLineColor, gridLineColor);
    } finally {
      ta.recycle();
    }
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    int bottom = getBottom() - offsetAxisX;
    int top = getTop();
    int left = getLeft();
    int right = getRight();

    Paint paint = new Paint();
    if(gridLineWidth != 0) {
      paint.setStrokeWidth(gridLineWidth);
    }

    if(horizontalGridSpacing != 0) {
      //Draw axis X
      paint.setColor(axisLineColor);
      canvas.drawLine(left, bottom, right, bottom, paint);

      //Draw horizontal lines
      while (bottom > top) {
        canvas.drawLine(left, bottom, right, bottom, paint);
        bottom -= horizontalGridSpacing;
      }
    }

    if(verticalGridSpacing != 0) {
      //Draw axis Y
      canvas.drawLine(left, top, left, bottom, paint);
      paint.setColor(axisLineColor);

      //Draw vertical lines
      bottom = getBottom() - offsetAxisX;
      while (right >= left) {
        canvas.drawLine(right, top, right, bottom, paint);
        right -= verticalGridSpacing;
      }
    }



    super.dispatchDraw(canvas);
  }

  @Override public void setAdapter(Adapter adapter) {
    if (!(adapter instanceof BaseGraphBarAdapter)) {
      throw new UnsupportedClassVersionError("You should set a BaseGraphBarAdapter!");
    }
    final BaseGraphBarAdapter baseAdapter = (BaseGraphBarAdapter) adapter;
    post(new Runnable() {
      @Override public void run() {
        baseAdapter.setViewHeight(getHeight() - 2* offsetAxisX);
        BarGraph.super.setAdapter(baseAdapter);
      }
    });
  }

  public int getHorizontalGridSpacing() {
    return horizontalGridSpacing;
  }

  public void setHorizontalGridSpacing(int horizontalGridSpacing) {
    this.horizontalGridSpacing = horizontalGridSpacing;
  }

  public int getVerticalGridSpacing() {
    return verticalGridSpacing;
  }

  public void setVerticalGridSpacing(int verticalGridSpacing) {
    this.verticalGridSpacing = verticalGridSpacing;
  }

  public int getOffsetAxisX() {
    return offsetAxisX;
  }

  public void setOffsetAxisX(int offsetAxisX) {
    this.offsetAxisX = offsetAxisX;
  }

  public int getGridLineWidth() {
    return gridLineWidth;
  }

  public void setGridLineWidth(int gridLineWidth) {
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