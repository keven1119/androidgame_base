package androidgamedemo.keven.com.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.ParcelUuid;

import androidgamedemo.keven.com.widges.GameSurface;

/**
 * Created by Administrator on 2016/8/21 0021.
 */
public class Bullet {

    private Bitmap bmpBullet;
    private int bulletX, bulletY;
    public int speed;
    public int bulletType;

    public static final int BULLET_PLAYER = -1;
    public static final int BULLET_DUCK = 1;
    public static final int BULLET_FLY = 2;
    public static final int BULLET_BOSS = 3;

    public boolean isDead;

    public Bullet(Bitmap bmpBullet,int bulletX,int bulletY,int bulletType){
        this.bmpBullet = bmpBullet;
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        this.bulletType = bulletType;

        switch (bulletType){
            case BULLET_PLAYER:
                speed = 4;
                break;
            case BULLET_DUCK:
                speed = 3;
                break;
            case BULLET_FLY:
                speed = 4;
                break;
            case BULLET_BOSS:
                speed = 5;
                break;
        }
    }


    public void draw(Canvas canvas, Paint paint){
        canvas.drawBitmap(bmpBullet,bulletX,bulletY,paint);
    }

    public void logic(){
        switch (bulletType){
            case BULLET_PLAYER:
                bulletY -= speed;
                if(bulletY < -50){
                    isDead = true;
                }
                break;
            case BULLET_DUCK:
            case BULLET_FLY:
                bulletY += speed;
                if(bulletY > GameSurface.screenH){
                    isDead = true;
                }
                break;
            case BULLET_BOSS:
                break;
        }
    }
}
