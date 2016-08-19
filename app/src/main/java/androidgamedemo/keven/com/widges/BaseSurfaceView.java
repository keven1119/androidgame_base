package androidgamedemo.keven.com.widges;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by keven on 16/8/17.
 */

public class BaseSurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    protected Paint paint;
    protected Canvas canvas;
    protected SurfaceHolder sfh;
    protected Thread th;
    private boolean flag;

    public static int screenW, screenH;

    public BaseSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BaseSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        sfh = getHolder();
        sfh.addCallback(this);
    }

    public BaseSurfaceView(Context context) {
        this(context,null,0);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenW = this.getWidth();
        screenH = this.getHeight();
        flag = true;
        th = new Thread(this);
        th.start();
        myDraw();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        flag = false;
    }

    @Override
    public void run() {
        while (flag){
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();

            try {
                if(end - start < 1000){
                    Thread.sleep(1000 - (end - start));
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    protected void myDraw(){}

    protected void logic(){}
}
