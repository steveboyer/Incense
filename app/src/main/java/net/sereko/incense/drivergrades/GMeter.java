/* Copyright (C) 2012 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package net.sereko.incense.drivergrades;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * Custom view that shows a pie chart and, optionally, a label.
 */
public class GMeter extends ViewGroup {
    private final int CIRCLE_RADIUS = 25;
    private final int STARTX = 0;
    private final int STARTY = 0;
    private int xLocation, yLocation;
    private int background = Color.BLUE;
    private int viewWidth = 0, viewHeight = 0;
    private Point origin =  new Point(0,0);
    private Paint backgroundPaint = new Paint(background);
    private Paint borderPaint;
    private Paint linePaint;
    private Paint circlePaint;
    Rect backgroundRect = new Rect(0,0,0,0);
    String TAG = this.getClass().getSimpleName();
//    private GMeterView mGMeterView;
    private LinearLayout.LayoutParams lp;
    //private LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, 0);
    private Paint paint = new Paint(Color.RED);

    /**
     * Class constructor taking only a context. Use this constructor to create
     * {@link GMeter} objects from your own code.
     *
     * @param context
     */
    public GMeter(Context context) {
        super(context);
        init();
    }

    public GMeter(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public GMeter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        // Do nothing. Do not call the superclass method--that would start a layout pass
        // on this view's children. PieChart lays out its children in onSizeChanged().
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(getHeight() <= canvas.getHeight() || getWidth() <= canvas.getWidth()){

            setLayoutParams(lp);

            setMinimumHeight(getWidth());
            viewHeight = getHeight();
            viewWidth = getWidth();
            origin.set(viewWidth/2, viewHeight/2);
            xLocation = origin.x;
            yLocation = origin.y;

        }


        //backgroundRect.set(0,0,canvas.getWidth(), canvas.getHeight());

       //canvas.drawRect(backgroundRect, backgroundPaint);

        canvas.drawCircle(xLocation, yLocation + 100, CIRCLE_RADIUS, paint);
        canvas.drawLine(xLocation, yLocation + 500, xLocation, yLocation, paint);
        canvas.drawRect(0,0,viewWidth, viewHeight, borderPaint);
        canvas.drawCircle(xLocation, yLocation + 100, CIRCLE_RADIUS * 20, borderPaint);

    }


    /**
     * Initialize the control. This code is in a separate method so that it can be
     * called from both constructors.
     */
    private void init() {
        setWillNotDraw(false);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,1440);
        //LayoutParams layoutParams = new LayoutParams(300,300);
        //setLayoutParams(layoutParams);
//
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, getWidth());
//        setLayoutParams(lp);
        xLocation = STARTX;
        yLocation = STARTY;

        backgroundPaint = new Paint(Color.WHITE);
        borderPaint = new Paint(Color.BLACK);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(10);

        //setBackgroundColor(background);


        // Force the background to software rendering because otherwise the Blur
        // filter won't work.
        //setLayerToSW(this);

        // Add a child view to draw the pie. Putting this in a child view
        // makes it possible to draw it on a separate hardware layer that rotates
        // independently
//        mGMeterView = new GMeterView(getContext());
//        addView(mGMeterView);
    }

    /**
     * Internal child class that draws the pie chart onto a separate hardware layer
     * when necessary.
     */
//    class GMeterView extends View {
//        private Paint paint;
//        private int width, height;
//
//
//        /**
//         * Construct a GMeterView
//         *
//         * @param context
//         */
//        public GMeterView(Context context) {
//            super(context);
//            this.setWillNotDraw(false);
//
//            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//            setLayoutParams(lp);
//        }
//
//        @Override
//        protected void onDraw(Canvas canvas) {
//            super.onDraw(canvas);
//
//
//            canvas.drawCircle(getWidth()/2, getHeight()/2, 100, paint);
//        }
//    }
}
