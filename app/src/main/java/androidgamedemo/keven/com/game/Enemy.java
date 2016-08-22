package androidgamedemo.keven.com.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidgamedemo.keven.com.widges.GameSurface;

/**
 * Created by keven on 16/8/19.
 */

public class Enemy {

    //敌机类型
    public int type;
    //苍蝇
    public static final int TYPE_FLY = 1;
    //鸭子(从左往右)
    public static final int TYPE_DUCKL = 2;
    //鸭子(从右往左)
    public static final int TYPE_DUCKR = 3;
    //敌机图片资源
    public Bitmap bmpEnemy;
    //敌机坐标
    public int x, y;
    //敌机美帧的宽高
    public int frameW, frameH;
    private int frameIndex;
    //敌机移动速度
    private int speed;
    //敌机是否已经出屏
    public boolean isDead;

    public Enemy(Bitmap bmpEnemy,int enemyType,int x,int y){
        this.bmpEnemy = bmpEnemy;
        frameW = bmpEnemy.getWidth() / 10;
        frameH = bmpEnemy.getHeight();

        this.type = enemyType;
        this.x = x;
        this.y = y;

        switch (type){
            //苍蝇
            case TYPE_FLY:
                speed = 25;
                break;
            //鸭子
            case TYPE_DUCKL|TYPE_DUCKR:
                speed = 3;
                break;
        }
    }

    public void draw(Canvas canvas, Paint paint){
        canvas.save();
        canvas.clipRect(x ,y ,x + frameW, y + frameH);
        canvas.drawBitmap(bmpEnemy, x - frameIndex * frameW, y,paint);
        canvas.restore();
    }

    public void logic(){
        frameIndex++;
        if(frameIndex >= 10){
            frameIndex = 0;
        }

        switch (type){
            case TYPE_FLY:
                if(isDead == false){
                    speed -= 1;
                    y += speed;
                    if(y <= -200){
                        isDead = true;
                    }
                }
                break;

            case TYPE_DUCKL:
                if(isDead == false){
                    x += speed / 2;
                    y += speed;
                    if(x > GameSurface.screenW){
                        isDead = true;
                    }
                }
                break;

            case TYPE_DUCKR:
                if(isDead == false){
                    x -= speed /2;
                    y += speed;
                    if(x < -50){
                        isDead = true;
                    }
                }
                break;
        }
    }

    public boolean isCollsionWith(Bullet bullet){
        int x2 = bullet.bulletX;
        int y2 = bullet.bulletY;

        int w2 = bullet.bmpBullet.getWidth();
        int h2 = bullet.bmpBullet.getHeight();

        return
    }
}
