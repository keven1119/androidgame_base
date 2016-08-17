package androidgamedemo.keven.com.widges;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidgamedemo.keven.com.R;

/**
 * Created by keven on 16/8/17.
 */

public class RgionSurface extends SurfaceView implements SurfaceHolder.Callback {

    private Rect rect = new Rect(0,0,100,100);

    private Region r = new Region(rect);

    private boolean isInclude;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder sfh;

    public RgionSurface(Context context) {
        this(context,null,0);
    }

    public RgionSurface(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RgionSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        sfh = getHolder();
        sfh.addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        paint = new Paint();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public void myDraw(){
        canvas = sfh.lockCanvas();
        try {
            if (canvas != null){

                if(isInclude){
                    canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),
                            100,50,paint);
                }
                canvas.drawRect(rect,paint);
            }
        }catch (Exception e){

        }finally {
            if(null != canvas){
                sfh.unlockCanvasAndPost(canvas);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(r.contains((int)event.getX(),(int)event.getY())){
            isInclude = true;
        }else {
            isInclude = false;
        }
        myDraw();
        return true;
    }
}
