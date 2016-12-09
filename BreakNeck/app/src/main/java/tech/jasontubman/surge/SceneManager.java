package tech.jasontubman.surge;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
    public ArrayList<Scene> scenes = new ArrayList<>();
    private Rect r = new Rect();
    private boolean isPaused = false;
    ParticleGenerator cogGen;

    private SharedPreferences prefs = Constants.currentContext.getSharedPreferences("gameData", Context.MODE_PRIVATE);
    private SharedPreferences.Editor editor = prefs.edit();

    public int shipChosen = 0;

    MediaPlayer backgroundMusic;
    private int sliderMin = (int) (Constants.screenWidth/7.9);
    private int sliderMax = (int) (Constants.screenWidth / 1.22);
    int sliderX = (int) sliderMax;
    private int firstRun = 0;
    boolean mute = prefs.getBoolean("mute", false);

    private ShipSelector shipSel;

    private boolean sliderMoving = false;
    float currentVolume = 1;

    public static int activeScene;

    private Bitmap exit;
    private Bitmap resizedExit;
    private Bitmap slider;
    private Bitmap resizedSlider;
    private Bitmap muteButton;
    private Bitmap resizedmute;
    private Bitmap notmute;
    private Bitmap resizednotmute;
    private Bitmap sliderUp;
    private Bitmap resizedSliderUp;
    private Bitmap ship;
    private Bitmap resizedShip;
    private Bitmap menu;
    private Bitmap resizedMenu;

    public SceneManager() {
        backgroundMusic = MediaPlayer.create(Constants.currentContext, R.raw.fly1);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        activeScene = 0;
        scenes.add(0, new MenuScene(this));
        cogGen = new ParticleGenerator();

        SharedPreferences prefs = Constants.currentContext.getSharedPreferences("gameData", Context.MODE_PRIVATE);
        shipChosen = prefs.getInt("selectedShip", 0);;

        shipSel = new ShipSelector(shipChosen+1, 1);

        exit = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.exitbutton);
        resizedExit = Bitmap.createScaledBitmap(exit, (int) (Constants.screenWidth / 12), Constants.screenHeight / 20, false);
        slider = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.slider);
        resizedSlider = Bitmap.createScaledBitmap(slider, (int) (Constants.screenWidth / 1.4), Constants.screenWidth/100, false);
        muteButton = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_boxcross);
        resizedmute = Bitmap.createScaledBitmap(muteButton, (int) (Constants.screenWidth / 10), Constants.screenWidth/11, false);
        notmute = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_box);
        resizednotmute = Bitmap.createScaledBitmap(notmute, (int) (Constants.screenWidth /10), Constants.screenWidth/11, false);
        sliderUp = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.sliderup);
        resizedSliderUp = Bitmap.createScaledBitmap(sliderUp, (int) (Constants.screenWidth / 19), Constants.screenWidth/13, false);
        ship = shipSel.getSprite();
        resizedShip = Bitmap.createScaledBitmap(ship, (int) (Constants.screenWidth / 4), Constants.screenHeight / 7, false);
        menu = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.home);
        resizedMenu = Bitmap.createScaledBitmap(menu, (int) (Constants.screenWidth / 12), Constants.screenHeight / 20, false);
    }

    public void recieveTouch(MotionEvent event) {
        if (isPaused) {
            recieveTouchOptions(event);
        }
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
            shipSel.selectSprite(shipChosen+1, 1);
            drawOptions(canvas);
            if (!mute) {
                currentVolume = (float) sliderX / sliderMax;
                backgroundMusic.setVolume(currentVolume, currentVolume);
            }
            else {
                currentVolume = 0;
                backgroundMusic.setVolume(0, 0);
            }
        }
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }
    public boolean getPaused() {
        return this.isPaused;
    }

    public void drawOptions(Canvas canvas) {
        if (firstRun == 0) {
            sliderX = (int) currentVolume * sliderMax;
            if (sliderX < sliderMin) {
                sliderX = sliderMin;
            } else if (sliderX > sliderMax) {
                sliderX = sliderMax;
            }
            if (currentVolume == 0) {
                sliderX = sliderMin;
            }
            firstRun++;
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
        paint.setTextSize(400 /8 * Constants.currentContext.getResources().getDisplayMetrics().density);
        

        canvas.drawBitmap(resizedExit, (int) (Constants.screenWidth - Constants.screenWidth/7), Constants.screenHeight/6, paint);


        centreText4(canvas, paint, "PAUSED", Constants.screenHeight/3.7f);

        paint.setTextSize(180 /8 * Constants.currentContext.getResources().getDisplayMetrics().density);

        centreText4(canvas, paint, "VOLUME", Constants.screenHeight/3.0f);
        paint.setTextSize(160 /8 * Constants.currentContext.getResources().getDisplayMetrics().density);
        centreText4(canvas, paint, "MUTE MUSIC :", Constants.screenHeight/2.13f);

        canvas.drawBitmap(resizedSlider, (int) (Constants.screenWidth/7), (int) (Constants.screenHeight/2.8), paint);


        if (this.mute) {
            canvas.drawBitmap(resizedmute, (int) (Constants.screenWidth/1.3), (int) (Constants.screenHeight/2.3), paint);
        } else {
            canvas.drawBitmap(resizednotmute, (int) (Constants.screenWidth/1.3), (int) (Constants.screenHeight/2.3), paint);
        }

        canvas.drawBitmap(resizedSliderUp, (int) (this.sliderX), (int) (Constants.screenHeight/2.75), paint);

        cogGen.addParticle((int) (Constants.screenWidth/2.07), (int) (Constants.screenHeight/1.5), 0, false);
        cogGen.update();
        cogGen.draw(canvas);
        resizedShip = Bitmap.createScaledBitmap(ship, (int) (Constants.screenWidth / 4), Constants.screenHeight / 7, false);
        canvas.drawBitmap(resizedShip, (int) (Constants.screenWidth/2.8), (int) (Constants.screenHeight/1.9), paint);


        RectF black = new RectF();
        black.set((int) (Constants.screenWidth/12),  Constants.screenHeight/6,
                (Constants.screenWidth/12) + (Constants.screenWidth / 12), Constants.screenHeight/6 + Constants.screenHeight / 20);
        canvas.drawRoundRect(black, 5, 5, paint);

        canvas.drawBitmap(resizedMenu, (int) (Constants.screenWidth/12), Constants.screenHeight/6, paint);

    }

    public void recieveTouchOptions(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (event.getY() > Constants.screenHeight/6 && event.getY() < Constants.screenHeight/6 + Constants.screenHeight / 20 &&
                        event.getX() > Constants.screenWidth - Constants.screenWidth/7 && event.getX() < Constants.screenWidth - Constants.screenWidth/7 + Constants.screenWidth / 12) {
                    isPaused = false;
                    firstRun = 0;
                    break;
                }
                if (event.getY() > (Constants.screenHeight/2.3) && event.getY() < (Constants.screenHeight/2.3) + Constants.screenWidth/11 &&
                        event.getX() > (Constants.screenWidth/1.3) && event.getX() < (Constants.screenWidth/1.3) + Constants.screenWidth /10) {
                    if (currentVolume != 0) {
                        mute = true;
                        editor.putBoolean("mute", true);
                        sliderX = sliderMin;
                        break;
                    } else {
                        sliderX = sliderMin;
                        mute = false;
                        currentVolume = sliderMin/sliderMax;
                        backgroundMusic.setVolume(currentVolume, currentVolume);
                        break;
                    }
                }
                if (event.getY() > Constants.screenHeight/2.75 && event.getY() < Constants.screenHeight/2.75 + Constants.screenWidth/13 &&
                        event.getX() > sliderX && event.getX() < sliderX + Constants.screenWidth / 19) {
                    sliderMoving = true;
                    break;
                }
                if (event.getY() > Constants.screenHeight/6 && event.getY() < Constants.screenHeight/6 + Constants.screenHeight / 20 &&
                        event.getX() > Constants.screenWidth/12 && event.getX() < Constants.screenWidth/12 + Constants.screenWidth / 12) {
                    activeScene = 0;
                    isPaused = false;
                    firstRun = 0;
                    editor.putBoolean("tutorial", true);
                    editor.commit();
                    this.scenes.remove(1);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {

                    }
                }

                break;
            }

            case MotionEvent.ACTION_UP: {
                sliderMoving = false;
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                if (sliderMoving) {
                    if (event.getX() > sliderMin && event.getX() < sliderMax) {
                        sliderX = (int) event.getX();
                    } else if (event.getX() > sliderMax) {
                        sliderX = sliderMax;
                    } else if (event.getX() < sliderMin) {
                        sliderX = sliderMin;
                    }
                    currentVolume = sliderX/sliderMax;
                }
                break;
            }
        }


    }

    private void centreText4(Canvas canvas, Paint paint, String text, float y) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left - 10;
        canvas.drawText(text, x, y-10, paint);
    }

}
