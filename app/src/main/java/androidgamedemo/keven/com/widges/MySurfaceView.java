package androidgamedemo.keven.com.widges;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import androidgamedemo.keven.com.R;

/**
 * Created by keven on 16/8/15.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback,Runnable {

    private SurfaceHolder sfh;
    private Paint paint;
    private Thread th;
    //线程消亡的标识
    private boolean flag;
    private Canvas canvas;
    private int screenW, screenH;
    int textX = 40,textY = 40;

    private Bitmap waveBmp;
    private int waveBmpX, waveBmpY;

    private Bitmap dragonBmp[] = new Bitmap[11];
    private Bitmap bmpRobot = BitmapFactory.decodeResource(getResources(),R.drawable.robot0);
    private final int DIR_LEFT = 0;
    private final int DIR_RIGHT = 1;
    int dir = DIR_RIGHT;
    int robot_x,robot_y;

    public MySurfaceView(Context context) {
        this(context,null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);


        for (int i = 0; i < dragonBmp.length; i++){
            dragonBmp[i] = BitmapFactory.decodeResource(getResources(),R.drawable.robot0+i);
        }

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        screenW = this.getWidth();
        screenH = this.getHeight();
        flag = true;

        waveBmp = BitmapFactory.decodeResource(getResources(),R.drawable.wave);
        waveBmpX = -waveBmp.getWidth() + this.getWidth();
        waveBmpY = Math.abs(getHeight() - waveBmp.getHeight());

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
    public boolean onTouchEvent(MotionEvent event) {
        textX = (int)event.getX();
        textY = (int)event.getY();
//        myDraw();
        return super.onTouchEvent(event);
    }

    public void myDraw(){
        Log.d("keven1119","my Draw ==>");
        try {
            canvas = sfh.lockCanvas();
            if(null != canvas) {
//                canvas.drawRGB(0,0,0);
//                drawCanvasSimple(canvas);

                //画图片
//                Bitmap bitmap = ((BitmapDrawable) (getResources().getDrawable(R.mipmap.ic_launcher))).getBitmap();
//                drawBitmap(canvas,bitmap);

                //画正方形
//                drawRect(canvas);

                //画波浪
//                drawWaves(canvas);￼￼￼￼￼￼￼￼￼￼￼￼
                //画龙
//                drawDragon(canvas);

                //画机器人
                drawFrame(currentFrame,canvas,paint);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (null != canvas){
                sfh.unlockCanvasAndPost(canvas);
            }
        }
    }

    private void drawCanvasSimple(Canvas canva) {
        canva.drawColor(Color.BLACK);
        canva.drawText("Game", textX, textY, paint);
        canva.drawPoint(20,40,paint);

        canva.drawPoints(new float[]{20,60,60,60},paint);

        canva.drawLine(20,80,100,80,paint);

        canva.drawLines(new float[]{20,100,100,100,140,100,220,100},paint);

        Rect rect = new Rect(20,220,120,260);
        canva.drawRect(rect,paint);

        RectF rectF = new RectF(20, 280, 120, 340);
        canva.drawRoundRect(rectF,40,40,paint);

        canva.drawCircle(40,400,40,paint);

        canva.drawArc(new RectF(300,40,400,140),0,460,true,paint);

        canva.drawOval(new RectF(300,160,360,200),paint);

        Path path = new Path();
        path.moveTo(320,300);
        path.lineTo(400,300);
        path.lineTo(360,400);
        path.close();
        canva.drawPath(path,paint);

        Path pathCircle = new Path();
        pathCircle.addCircle(260,520,40,Path.Direction.CW);

        canva.drawTextOnPath("PathText",pathCircle,20,40,paint);
    }

    @Override
    public void run() {
        while (flag){
            long start = System.currentTimeMillis();
            myDraw();
            logic();
            long end = System.currentTimeMillis();

            try {
                if(end - start < 50){
                    Thread.sleep(50 - (end - start));
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    private void logic() {
        //画波浪
//        waveBmpX+=5;
//        if(waveBmpX > 0){
//            waveBmpX = -waveBmp.getWidth() + this.getWidth();
//        }

        robot_x += 5;
        dir = DIR_RIGHT;
        //画龙
        currentFrame ++;
        if(currentFrame >= dragonBmp.length){
            currentFrame = 0;
        }
    }

    private void drawBitmap(Canvas canvas,Bitmap bitmap){
//        canvas.save();
//        canvas.rotate(30,bitmap.getWidth()/2,bitmap.getHeight()/2);
//        canvas.drawBitmap(bitmap,0,0,paint);
//
//        canvas.restore();
//        canvas.drawBitmap(bitmap,100,0,paint);

        //设置画布形状和大小
//        Path path = new Path();
//        path.addCircle(30,30,30, Path.Direction.CW);
//        canvas.clipPath(path);

        //设置画布可视区域
        Region region = new Region();
        region.op(new Rect(0,0,getWidth(),getHeight()), Region.Op.INTERSECT);
        canvas.clipRegion(region);

        Matrix matrix = new Matrix();
        matrix.setRotate(30,bitmap.getWidth()/2,bitmap.getHeight()/2);
        canvas.drawBitmap(bitmap,matrix,paint);

        matrix.setScale(-2f,2f,bitmap.getWidth()/2,bitmap.getHeight()/2);
        matrix.postTranslate(300,300);
        canvas.drawCircle(350,350,20,paint);
        paint.setAlpha(100);
        canvas.drawBitmap(bitmap,matrix,paint);

    }

    private void drawRect(Canvas canva){
//        canva.drawRect(0,0,30,30,paint);
        canva.drawCircle(200,200,200,paint);
        canva.clipRect(0,0,200,200);
    }

    private void drawWaves(Canvas canva){
        canva.drawBitmap(waveBmp,waveBmpX,waveBmpY,paint);
    }

    private int currentFrame;

    private void drawDragon(Canvas canva){
        canva.drawBitmap(dragonBmp[currentFrame],0,0,paint);
    }

    public void drawFrame(int currentFrame,Canvas canva,Paint paint){
        int frameW = bmpRobot.getWidth() / 6;
        int frameH = bmpRobot.getHeight() / 2;

        int col = bmpRobot.getWidth() / frameW;
        int x = currentFrame % col * frameW;
        int y = currentFrame / col * frameH;

        canva.save();

        canva.clipRect(robot_x,robot_y,robot_x + bmpRobot.getWidth() / 6
                ,robot_y = bmpRobot.getHeight() / 2);

        if(dir == DIR_LEFT){
            canva.scale(-1, 1, robot_x - x + bmpRobot.getWidth() / 2,
                    robot_y - y + bmpRobot.getHeight() /2);
        }
        canva.drawBitmap(bmpRobot,robot_x - x,robot_y - y ,paint);
        canva.restore();
    }

}
