package androidgamedemo.keven.com.widges;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by keven on 16/8/15.
 */

public class MyView extends View {

    Paint mPaint;
    private int textX = 50, textY = 50;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        mPaint = new Paint();
        mPaint.setTextSize(50);
        mPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawText("Game",textX,textY,mPaint);
        super.onDraw(canvas);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
            textY -= 2;
        }else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
            textY += 2;
        }else if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
            textX -= 2;
        }else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            textX += 2;
        }
        invalidate();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            textX = x;
            textY = y;
        }else  if(event.getAction() == MotionEvent.ACTION_MOVE){
            textX = x;
            textY = y;
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            textX = x;
            textY = y;
        }

        invalidate();
        return super.onTouchEvent(event);
    }
}
