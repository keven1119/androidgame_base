package androidgamedemo.keven.com.widges;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

/**
 * Created by keven on 16/8/15.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder sfh;
    private Paint paint;
    private Thread th;
    private boolean flag;
    int textX = 20,textY = 20;

    public MySurfaceView(Context context) {
        this(context,null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        myDraw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        textX = (int)event.getX();
        textY = (int)event.getY();
        myDraw();
        return super.onTouchEvent(event);
    }

    public void myDraw(){
        Log.d("keven1119","my Draw ==>");
        Canvas canvas = sfh.lockCanvas();
        canvas.drawText("Game",textX,textY,paint);
        sfh.unlockCanvasAndPost(canvas);

        invalidate();
    }
}
