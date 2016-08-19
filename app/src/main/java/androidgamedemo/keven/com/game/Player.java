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
    }

    //设置主角血量
    public void setPlayerHp(int hp){
        this.playerHp = hp;
    }

    //获取主角血量
    public int getPlayerHp(){
        return playerHp;
    }
}
