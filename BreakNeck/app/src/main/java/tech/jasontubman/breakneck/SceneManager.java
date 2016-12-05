package tech.jasontubman.breakneck;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import tech.jasontubman.game.R;

import java.util.ArrayList;

/**
 * Created by Jason on 21/11/2016.
 */

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();

    private boolean isPaused = false;
    ParticleGenerator cogGen;
    MediaPlayer backgroundMusic;
    private int sliderMin = (int) (Constants.screenWidth/7.9);
    private int sliderMax = (int) (Constants.screenWidth / 1.22);
    private int sliderX = (int) sliderMax;

    float currentVolume = 1;

    public static int activeScene;

    public SceneManager() {
        backgroundMusic = MediaPlayer.create(Constants.currentContext, R.raw.fly1);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        activeScene = 0;
        scenes.add(0, new MenuScene(this));
        cogGen = new ParticleGenerator();
    }

    public void recieveTouch(MotionEvent event) {
        scenes.get(activeScene).recieveTouch(event);
    }

    public void update() {
        if (!isPaused) {
            scenes.get(activeScene).update();
        }
    }

    public void draw(Canvas canvas) {
        scenes.get(activeScene).draw(canvas);
        if (isPaused) {
            drawOptions(canvas);
        }
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public MediaPlayer getMediaPlayer(){
        return this.backgroundMusic;
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }
    public boolean getPaused() {
        return this.isPaused;
    }

    public void drawOptions(Canvas canvas) {
        sliderX = (int) currentVolume;
        if (currentVolume == 0) {
            sliderX = sliderMin;
        }

        Paint paint3 = new Paint();
        paint3.setColor(Color.WHITE);
        RectF shopPanel = new RectF();
        shopPanel.set(Constants.screenWidth/9, Constants.screenHeight/5, Constants.screenWidth - Constants.screenWidth/9, Constants.screenHeight - (int) (Constants.screenHeight/9.7));
        canvas.drawRoundRect(shopPanel, 10, 10, paint3);
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");
        Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint.setTextSize(450 / Constants.currentContext.getResources().getDisplayMetrics().density);

        BitmapFactory bf = new BitmapFactory();
        Bitmap exit = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.exitbutton);
        Bitmap resizedExit = Bitmap.createScaledBitmap(exit, (int) (Constants.screenWidth / 12), Constants.screenHeight / 20, false);
        canvas.drawBitmap(resizedExit, (int) (Constants.screenWidth - Constants.screenWidth/7), Constants.screenHeight/6, paint);

        canvas.drawText("PAUSED", (int)(Constants.screenWidth/7.5), (int) (Constants.screenHeight/3.7), paint);
        paint.setTextSize(180 / Constants.currentContext.getResources().getDisplayMetrics().density);


        canvas.drawText("VOLUME", (int)(Constants.screenWidth/7), (int)(Constants.screenHeight/3.0), paint);

        canvas.drawText("MUTE MUSIC:", (int)(Constants.screenWidth/7), (int)(Constants.screenHeight/2.2), paint);

        Bitmap slider = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.slider);
        Bitmap resizedSlider = Bitmap.createScaledBitmap(slider, (int) (Constants.screenWidth / 1.4), Constants.screenWidth/100, false);
        canvas.drawBitmap(resizedSlider, (int) (Constants.screenWidth/7), (int) (Constants.screenHeight/2.8), paint);


        Bitmap mute = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_boxcross);
        Bitmap resizedmute = Bitmap.createScaledBitmap(mute, (int) (Constants.screenWidth / 10), Constants.screenWidth/11, false);

        Bitmap notmute = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_box);
        Bitmap resizednotmute = Bitmap.createScaledBitmap(notmute, (int) (Constants.screenWidth /10), Constants.screenWidth/11, false);

        if (currentVolume == 0) {
            canvas.drawBitmap(resizedmute, (int) (Constants.screenWidth/1.6), (int) (Constants.screenHeight/2.42), paint);
        } else {
            canvas.drawBitmap(resizednotmute, (int) (Constants.screenWidth/1.6), (int) (Constants.screenHeight/2.42), paint);
        }

        Bitmap sliderUp = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.sliderup);
        Bitmap resizedSliderUp = Bitmap.createScaledBitmap(sliderUp, (int) (Constants.screenWidth / 19), Constants.screenWidth/13, false);
        canvas.drawBitmap(resizedSliderUp, (int) (this.sliderX), (int) (Constants.screenHeight/2.75), paint);

        cogGen.addParticle((int) (Constants.screenWidth/2.07), (int) (Constants.screenHeight/1.5), 0, false);
        cogGen.update();
        cogGen.draw(canvas);

        Bitmap ship = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4b);
        Bitmap resizedShip = Bitmap.createScaledBitmap(ship, (int) (Constants.screenWidth / 4), Constants.screenHeight / 7, false);
        canvas.drawBitmap(resizedShip, (int) (Constants.screenWidth/2.8), (int) (Constants.screenHeight/1.9), paint);

    }

}
