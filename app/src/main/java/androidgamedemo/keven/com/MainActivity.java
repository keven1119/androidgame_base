package androidgamedemo.keven.com;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidgamedemo.keven.com.widges.GameSurface;
import androidgamedemo.keven.com.widges.NavController;

public class MainActivity extends Activity {

    private GameSurface mGameSurface;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        mGameSurface = (GameSurface) findViewById(R.id.gamesurface);
        mNavController = (NavController) findViewById(R.id.controller);

        initView();
    }

    private void initView() {
        mNavController.setOnNavAndSpeedListener(new NavController.OnNavAndSpeedListener() {
            @Override
            public void onNavAndSpeed(float nav, float speed) {
                Log.d("keven","nav ==>" + nav + "/nspeed ==>" + speed);
            }
        });
    }
}
