package androidgamedemo.keven.com.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;

import androidgamedemo.keven.com.widges.GameSurface;
import androidgamedemo.keven.com.widges.MySurfaceView;

/**
 * Created by keven on 16/8/19.
 */

public class Player {

    private int playerHp = 3;
    private Bitmap bmpPlayerHp;

    public int x,y;
    private Bitmap bmpPlayer;

    private int speed = 5;
    private boolean isUp, isDown, isLeft, isRight;

    private int noCollisionCount = 0;
    private int noCollisionTime = 60;
    private boolean isCollision;

    public Player(Bitmap bmpPlayer, Bitmap bmpPlayerHp){
        this.bmpPlayer = bmpPlayer;
        this.bmpPlayerHp = bmpPlayerHp;

        x = GameSurface.screenW / 2 - bmpPlayer.getWidth() / 2;
        y = GameSurface.screenH - bmpPlayer.getHeight();
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bmpPlayer, x, y, paint);
        for (int i = 0; i < playerHp; i++){
            canvas.drawBitmap(bmpPlayerHp, i * bmpPlayerHp.getWidth(),
                    GameSurface.screenH - bmpPlayerHp.getHeight(),paint);
        }

        //处于无敌状态闪烁
        if(isCollision){
            if(noCollisionCount % 2 ==0 ){
                canvas.drawBitmap(bmpPlayer,x,y,paint);
            }
        }else {
            canvas.drawBitmap(bmpPlayer,x,y,paint);
        }
    }

    public void onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
            isUp = true;
        }
        if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
            isDown = true;
        }
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
            isLeft = true;
        }
        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            isRight = true;
        }
    }

    public void onKeyUp(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
            isUp = false;
        }
        if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
            isDown = false;
        }
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
            isLeft = false;
        }
        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            isRight = false;
        }
    }

    public void logic(){
        if(isLeft){
            x -= speed;
        }
        if(isRight){
            x += speed;
        }
        if(isUp){
            y -= speed;
        }
        if(isDown){
            y += speed;
        }

        //判断屏幕X边界
        if(x + bmpPlayer.getWidth() >= GameSurface.screenW){
            x = GameSurface.screenW - bmpPlayer.getWidth();
        }else if(x <= 0){
            x = 0;
        }

        if(y + bmpPlayer.getHeight() >= GameSurface.screenH){
            y = GameSurface.screenH - bmpPlayer.getHeight();
        }else if(y <= 0){
            y = 0;
        }

        if(isCollision){
            noCollisionCount++;
            if(noCollisionCount >= noCollisionTime){
                isCollision = false;
                noCollisionCount = 0;
            }
        }
    }

    //设置主角血量
    public void setPlayerHp(int hp){
        this.playerHp = hp;
    }

    //获取主角血量
    public int getPlayerHp(){
        return playerHp;
    }

    //判断碰撞（主角与敌机）
    public boolean isCollsionWith(Enemy en){
        if(isCollision = false) {
            int x2 = en.x;
            int y2 = en.y;
            int w2 = en.frameW;
            int h2 = en.frameH;
            if (x >= x2 && x >= x2 + w2) {
                return false;
            } else if (x <= x2 && x + bmpPlayer.getWidth() <= x2) {
                return false;
            } else if (y >= y2 && y >= y2 + h2) {
                return false;
            }else if(y <= y2 && y + bmpPlayer.getHeight() <= y2){
                return false;
            }
            isCollision = true;
            return true;
        }else {
            return false;
        }
    }

}
