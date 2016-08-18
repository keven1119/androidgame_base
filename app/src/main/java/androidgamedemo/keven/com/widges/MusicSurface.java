package androidgamedemo.keven.com.widges;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

import androidgamedemo.keven.com.R;

/**
 * Created by keven on 16/8/17.
 */

public class MusicSurface extends BaseSurfaceView implements MediaPlayer.OnPreparedListener {

    private int mediaSate = 0;
    private MediaPlayer mediaPlayer;
    private int currentTime;
    private int musicMaxTime;
    private int currentVol;
    private int setTime = 5000;
    private AudioManager am;


    public MusicSurface(Context context) {
        this(context, null, 0);
    }

    public MusicSurface(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MusicSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.w_start_new_year);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setLooping(true);
        musicMaxTime = mediaPlayer.getDuration();
        am = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
//        ((Activity)getContext()).setVolumeControlStream(AudioManager.STREAM_MUSIC);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void myDraw(){
        canvas = sfh.lockCanvas();
        try {
            if(null != canvas){
                canvas.drawColor(Color.WHITE);
                paint.setColor(Color.RED);
                paint.setTextSize(15);

                canvas.drawText("当前音量:" + currentVol,50,40,paint);
                canvas.drawText("当前播放的时间(毫秒)/总时间(毫秒)",50,70,paint);
                canvas.drawText(currentTime + "/" + musicMaxTime,100,100,paint);
          }
        }catch (Exception e){

        }finally {
            if(null != canvas){
                sfh.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    protected void logic() {
        super.logic();
        if(mediaPlayer != null){
            currentTime = mediaPlayer.getCurrentPosition();
            currentVol = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        }else {
            currentTime = 0;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        mediaPlayer.setOnPreparedListener(this);
//        try {
//            mediaPlayer.prepare();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        mediaPlayer.start();
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
}
