package ru.vladus177.n3tworktest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class RandCircle extends View {

    Context context;
    private Paint mPaint;

    public void setDataList(ArrayList<SimpleCircle> dataList) {
        this.dataList.clear();
        this.dataList.addAll(dataList);
        invalidate();
    }

    private ArrayList<SimpleCircle> dataList = new ArrayList<>();

    public RandCircle(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(Constant.STROKE_WIDTH);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (SimpleCircle circle : dataList) {
            int size = circle.size;
            mPaint.setAlpha(255 - size);
            canvas.drawCircle(circle.x, circle.y, circle.size, mPaint);
        }
    }

}
