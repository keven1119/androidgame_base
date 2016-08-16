package androidgamedemo.keven.com.widges;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidgamedemo.keven.com.R;

/**
 * Created by keven on 16/8/16.
 */

public class WaveSurfaceView extends SurfaceView {

    private Bitmap bmp;
    private SurfaceHolder sfh;
    private int bmpX, bmpY;
    private Paint paint;


    public WaveSurfaceView(Context context) {
        this(context,null,0);
    }

    public WaveSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.wave);

        bmpX = -bmp.getWidth() + this.getWidth();
        bmpY = getHeight() - bmp.getHeight();

        sfh = getHolder();
    }

    private void myDraw(){
        Canvas canvas = sfh.lockCanvas();
        try {
            if (null != canvas){
                canvas.drawBitmap(bmp,bmpX,bmpY,paint);
            }

        }catch (Exception e){

        }
        finally {
            if(null != canvas){
                sfh.unlockCanvasAndPost(canvas);
            }
        }

    }
}
