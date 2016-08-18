package androidgamedemo.keven.com.widges;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by keven on 16/8/18.
 */

public class GameSurface extends BaseSurfaceView {

    public static final int GAME_MENU = 0;
    public static final int GAMEING = 1;
    public static final int GAME_WIN = 2;
    public static final int GAME_LOST = 3;
    public static final int GAME_PAUSE = -1;

    public static int gameState = GAME_MENU;


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
    protected void myDraw() {
        super.myDraw();
        switch (gameState){
            case GAME_MENU:
                break;
            case GAMEING:
                break;
            case GAME_PAUSE:
                break;
        }
    }
}
