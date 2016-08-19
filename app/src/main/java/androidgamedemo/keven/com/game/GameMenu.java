package androidgamedemo.keven.com.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.MotionEvent;

import androidgamedemo.keven.com.widges.GameSurface;
import androidgamedemo.keven.com.widges.MySurfaceView;

/**
 * Created by keven on 16/8/19.
 */

public class GameMenu {
    //菜单背景图
    private Bitmap bmpMenu;
    //按钮图片资源
    private Bitmap bmpButton, bmpButtonPress;
    //按钮坐标
    private int btnX, btnY;
    //按钮是否按下标识位
    private Boolean isPress;

    public GameMenu(Bitmap bmpMenu, Bitmap bmpButton, Bitmap bmpButtonPress){
        this.bmpMenu = bmpMenu;
        this.bmpButton = bmpButton;
        this.bmpButtonPress = bmpButtonPress;

        btnX = GameSurface.screenW / 2 - bmpButton.getWidth() / 2;
        btnY = GameSurface.screenH - bmpButton.getHeight();
        isPress = false;

    }

    public void draw(Canvas canvas, Paint paint){
        //绘制菜单背景图

        canvas.drawBitmap(bmpMenu, 0, 0, paint);
        if(isPress){
            canvas.drawBitmap(bmpButtonPress, btnX, btnY, paint);
        }else {
            canvas.drawBitmap(bmpButton, btnX, btnY, paint);
        }
    }

    public void onTouchEvent(MotionEvent event){
        int pointX = (int) event.getX();
        int pointY = (int) event.getY();

        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
            if(pointX > btnX && pointX < btnX + bmpButton.getHeight()){
                if(pointY > btnY && pointY < btnY + bmpButton.getHeight()){
                    isPress = true;
                }else {
                    isPress = false;
                }
            }else {
                isPress = false;
            }
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            if(pointX > btnX && pointX < btnX + bmpButton.getWidth()){
                if(pointY > btnY && pointY < btnY + bmpButton.getHeight()){
                    isPress = false;
                    GameSurface.gameState = GameSurface.GAMEING;
                }
            }
        }

    }


}
