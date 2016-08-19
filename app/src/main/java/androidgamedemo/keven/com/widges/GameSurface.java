package androidgamedemo.keven.com.widges;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;
import java.util.Vector;

import androidgamedemo.keven.com.R;
import androidgamedemo.keven.com.game.Enemy;
import androidgamedemo.keven.com.game.GameBg;
import androidgamedemo.keven.com.game.GameMenu;
import androidgamedemo.keven.com.game.Player;

/**
 * Created by keven on 16/8/18.
 */

public class GameSurface extends BaseSurfaceView {

    public static final int GAME_MENU = 0;
    public static final int GAMEING = 1;
    public static final int GAME_WIN = 2;
    public static final int GAME_LOST = 3;
    public static final int GAME_PAUSE = -1;

    private Resources res = this.getResources();
    private Bitmap bmpBackGround;//游戏背景
    private Bitmap bmpBoom;//爆炸效果
    private Bitmap bmpBoosBoom;//Boos 爆炸效果
    private Bitmap bmpButton;//游戏开始按钮
    private Bitmap bmpButtonPress;//游戏开始按钮被点击
    private Bitmap bmpEnemyDuck;//怪物鸭子
    private Bitmap bmpEnemyFly;//怪物苍蝇
    private Bitmap bmpEnemyBoos;//怪物猪头Boos
    private Bitmap bmpGameWin;//游戏胜利背景
    private Bitmap bmpGameLost;//游戏失败背景
    private Bitmap bmpPlayer;//游戏主角飞机
    private Bitmap bmpPlayerHp;//主角飞机血量
    private Bitmap bmpMenu;//菜单背景
    private static Bitmap bmpBullet;//子弹
    private static Bitmap bmpEnemyBullet;//敌机子弹
    private static Bitmap bmpBossBullet;//Boss 子弹

    public static int gameState = GAME_MENU;

    private GameMenu gameMenu;
    private GameBg backGround;
    private Player player;

    private Vector<Enemy> vcEnemy;

    private int createEnemyTime = 50;
    private int count;

    //1和2表示敌机的种类 ,-1表示boss
    //二维数组的每一维都是一组怪物
    private int enemyArray[][] = {
            {1,2},{1,1},{1,3,1,2},{1,2},{2,3},{3,1,3},{2,2},{1,2},{2,2},
            {1,3,1,1},{2,1},{1,3},{2,1},{-1}
    };
    private int enemyArrayIndex;
    private boolean isBoss;
    private Random random;


    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GameSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GameSurface(Context context) {
        super(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);
        initGame();
    }

    private void initGame(){
        if(gameState == GAME_MENU){
            bmpBackGround = BitmapFactory.decodeResource(res, R.drawable.background);
            bmpBoom = BitmapFactory.decodeResource(res, R.drawable.boom);
            bmpBoosBoom = BitmapFactory.decodeResource(res, R.drawable.boos_boom);
            bmpButton = BitmapFactory.decodeResource(res, R.drawable.button);
            bmpButtonPress = BitmapFactory.decodeResource(res, R.drawable.button_press);
            bmpEnemyDuck = BitmapFactory.decodeResource(res, R.drawable.enemy_duck);
            bmpEnemyFly = BitmapFactory.decodeResource(res, R.drawable.enemy_fly);
            bmpEnemyBoos = BitmapFactory.decodeResource(res, R.drawable.enemy_pig);
            bmpGameWin = BitmapFactory.decodeResource(res, R.drawable.gamewin);
            bmpGameLost = BitmapFactory.decodeResource(res, R.drawable.gamelost);
            bmpPlayer = BitmapFactory.decodeResource(res, R.drawable.player);
            bmpPlayerHp = BitmapFactory.decodeResource(res, R.drawable.hp);
            bmpMenu = BitmapFactory.decodeResource(res, R.drawable.menu);
            bmpBullet = BitmapFactory.decodeResource(res, R.drawable.bullet);
            bmpEnemyBullet = BitmapFactory.decodeResource(res, R.drawable.bullet_enemy);
            bmpBossBullet = BitmapFactory.decodeResource(res, R.drawable.boosbullet);

            gameMenu = new GameMenu(bmpMenu, bmpButton, bmpButtonPress);
            backGround = new GameBg(bmpBackGround);
            player = new Player(bmpPlayer,bmpPlayerHp);

            vcEnemy = new Vector<Enemy>();
            random = new Random();
        }
    }

    @Override
    protected void myDraw() {
        super.myDraw();
        canvas = sfh.lockCanvas();
        try {
            if(null != canvas){
                switch (gameState){
                    case GAME_MENU:
                        if(null != gameMenu) {
                            gameMenu.draw(this.canvas, paint);
                        }
                        break;
                    case GAMEING:
                        if(backGround != null){
                            backGround.draw(canvas,paint);
                        }
                        if(null != player){
                            player.draw(canvas, paint);
                        }

//                        if(isBoss == false){
                            for (int i = 0;i < vcEnemy.size(); i++){
                                Enemy enemy = vcEnemy.elementAt(i);
                                if(enemy.isDead){
                                    vcEnemy.removeElementAt(i);
                                }else {
                                    enemy.draw(canvas,paint);
                                    enemy.logic();
                                }
                            }

                            //生成敌机
                            count++;
                            if(count % createEnemyTime ==0){
                                for(int i= 0; i< enemyArray[enemyArrayIndex].length;i++){
                                    //苍蝇
                                    if(enemyArray[enemyArrayIndex][i] == 1){
                                        int x = random.nextInt(screenW - 100) +50;
                                        vcEnemy.addElement(new Enemy(bmpEnemyFly,1,x,-50));
                                    }else if (enemyArray[enemyArrayIndex][i] == 2){
                                        int y = random.nextInt(20);
                                        vcEnemy.addElement(new Enemy(bmpEnemyDuck,2,-50,y));
                                    }else if(enemyArray[enemyArrayIndex][i] == 3){
                                        int y = random.nextInt(20);
                                        vcEnemy.addElement(new Enemy(bmpEnemyDuck,3,screenW+50,y));
                                    }
                                }
                            }

                            //判断boss组
                            if(enemyArrayIndex == enemyArray.length - 1){
                                isBoss = true;
                            }else {
                                enemyArrayIndex++;
                            }
//
//                        }else {
//                            //Boss逻辑
//                        }
                        break;
                    case GAME_PAUSE:
                        break;
                    case GAME_WIN:
                        break;
                    case GAME_LOST:
                        break;
                }
            }

        }catch (Exception e){

        }finally {
            if(null != canvas){
                sfh.unlockCanvasAndPost(canvas);
                canvas = null;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (gameState){
            case GAME_MENU:
                if(null != gameMenu) {
                    gameMenu.onTouchEvent(event);
                }
                break;
            case GAMEING:
                break;
            case GAME_PAUSE:
                break;
            case GAME_WIN:
                break;
            case GAME_LOST:
                break;
        }
        return true;
    }

    @Override
    protected void logic() {
        super.logic();
        switch (gameState){
            case GAME_MENU:
                break;
            case GAMEING:
                if(backGround != null){
                    backGround.logic();
                }
                if(null != player){
                    player.logic();
                }
                break;
            case GAME_WIN:
                break;
            case GAME_LOST:
                break;
        }
    }
}
