package tech.jasontubman.breakneck;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;

import java.util.Arrays;

import tech.jasontubman.game.R;


/**
 * Created by Jason on 30/11/2016.
 */

public class MenuScene implements Scene {

    private StarManager manager;
    private Rect r = new Rect();
    private  SceneManager sceneManager;
    private  Canvas canvas;

    private boolean sliderMoving = true;

    private boolean play = false;
    private boolean store = false;
    private boolean highscore = false;
    private boolean options = false;
    private boolean credits = false;
    private boolean exit = false;
    Boolean[] ships = new Boolean[28];

    private int sliderMin = (int) (Constants.screenWidth/7.9);
    private int sliderMax = (int) (Constants.screenWidth / 1.22);
    private int sliderX = (int) sliderMax;

    private int selectedShip = 0;
    private boolean menu = true;

    private boolean musicMuted = false;
    private ShipSelector shipSel;

    private ParticleGenerator creditGen;

    private Bitmap playButton;
    private Bitmap resizedPlayButton;
    private Bitmap playButton2;
    private Bitmap resizedPlayButton2;
    private Bitmap storeButton2;
    private Bitmap resizedStoreButton2;
    private Bitmap storeButton3;
    private Bitmap resizedStoreButton3;
    private Bitmap storeButton4;
    private Bitmap resizedStoreButton4;
    private Bitmap storeButton5;
    private Bitmap resizedStoreButton5;
    private Bitmap storeButton6;
    private Bitmap resizedStoreButton6;
    private Bitmap storeButton7;
    private Bitmap resizedStoreButton7;
    private Bitmap storeButton8;
    private Bitmap resizedStoreButton8;
    private Bitmap storeButton9;
    private Bitmap resizedStoreButton9;
    private Bitmap storeButton10;
    private Bitmap resizedStoreButton10;
    private Bitmap storeButton;
    private Bitmap resizedStoreButton;
    private Bitmap exitButton;
    private Bitmap resizedExit;
    private Bitmap shipButton;
    private Bitmap resizedshipButton;
    private Bitmap lock;
    private Bitmap resizedlock;

    private Bitmap ship1;
    private Bitmap  resizedship1;
    private Bitmap ship2;
    private Bitmap  resizedship2;
    private Bitmap ship3;
    private Bitmap  resizedship3;
    private Bitmap ship4;
    private Bitmap  resizedship4;
    private Bitmap ship5;
    private Bitmap  resizedship5;
    private Bitmap ship6;
    private Bitmap  resizedship6;
    private Bitmap ship7;
    private Bitmap  resizedship7;
    private Bitmap ship8;
    private Bitmap  resizedship8;
    private Bitmap ship9;
    private Bitmap  resizedship9;
    private Bitmap ship10;
    private Bitmap  resizedship10;
    private Bitmap ship11;
    private Bitmap  resizedship11;
    private Bitmap ship12;
    private Bitmap  resizedship12;
    private Bitmap ship13;
    private Bitmap  resizedship13;
    private Bitmap ship14;
    private Bitmap  resizedship14;
    private Bitmap ship15;
    private Bitmap  resizedship15;
    private Bitmap ship16;
    private Bitmap  resizedship16;
    private Bitmap ship17;
    private Bitmap  resizedship17;
    private Bitmap ship18;
    private Bitmap  resizedship18;
    private Bitmap ship19;
    private Bitmap  resizedship19;
    private Bitmap ship20;
    private Bitmap  resizedship20;
    private Bitmap ship21;
    private Bitmap  resizedship21;
    private Bitmap ship22;
    private Bitmap  resizedship22;
    private Bitmap ship23;
    private Bitmap  resizedship23;
    private Bitmap ship24;
    private Bitmap  resizedship24;
    private Bitmap ship25;
    private Bitmap  resizedship25;
    private Bitmap ship26;
    private Bitmap  resizedship26;
    private Bitmap ship27;
    private Bitmap  resizedship27;
    private Bitmap ship28;
    private Bitmap  resizedship28;
    private Bitmap shipCred;
    private Bitmap resizedShipCred;
    private Bitmap mute;
    private Bitmap resizedmute;
    private Bitmap notmute;
    private Bitmap resizednotmute;
    private Bitmap sliderUp;
    private Bitmap resizedSliderUp;
    private Bitmap slider;
    private Bitmap resizedSlider;
    private Bitmap ship;
    private Bitmap resizedShip;

    public MenuScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;

        manager = new StarManager(7, true);
        Arrays.fill(ships, Boolean.FALSE);
        ships[0] = true;
        creditGen = new ParticleGenerator();
        shipSel = new ShipSelector(this.sceneManager.shipChosen+1, 1);

        ship = shipSel.getSprite();
        resizedShip = Bitmap.createScaledBitmap(ship, (int) (Constants.screenWidth / 4), Constants.screenHeight / 7, false);

        sliderUp = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.sliderup);
        resizedSliderUp = Bitmap.createScaledBitmap(sliderUp, (int) (Constants.screenWidth / 19), Constants.screenWidth/13, false);
        slider = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.slider);
        resizedSlider = Bitmap.createScaledBitmap(slider, (int) (Constants.screenWidth / 1.4), Constants.screenWidth/100, false);

        shipCred = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7bl);
        resizedShipCred = Bitmap.createScaledBitmap(shipCred, (int) (Constants.screenWidth / 4), Constants.screenHeight / 7, false);

        mute = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_boxcross);
        resizedmute = Bitmap.createScaledBitmap(mute, (int) (Constants.screenWidth / 10), Constants.screenWidth/11, false);

        notmute = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_box);
        resizednotmute = Bitmap.createScaledBitmap(notmute, (int) (Constants.screenWidth /10), Constants.screenWidth/11, false);

        playButton = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.playpushed);
        resizedPlayButton = Bitmap.createScaledBitmap(playButton, (int) (Constants.screenWidth/1.35), Constants.screenHeight/6, false);
        playButton2 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.playbutton);
        resizedPlayButton2 = Bitmap.createScaledBitmap(playButton2, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 6, false);
        storeButton = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
        resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
        storeButton2 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button04);
        resizedStoreButton2 = Bitmap.createScaledBitmap(storeButton2, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
        storeButton3 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
        resizedStoreButton3 = Bitmap.createScaledBitmap(storeButton3, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
        storeButton4 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button04);
        resizedStoreButton4 = Bitmap.createScaledBitmap(storeButton4, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
        storeButton5 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
        resizedStoreButton5 = Bitmap.createScaledBitmap(storeButton5, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
        storeButton6 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button04);
        resizedStoreButton6 = Bitmap.createScaledBitmap(storeButton6, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
        storeButton7 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
        resizedStoreButton7 = Bitmap.createScaledBitmap(storeButton7, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
        storeButton8 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button04);
        resizedStoreButton8 = Bitmap.createScaledBitmap(storeButton8, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
        storeButton9 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
        resizedStoreButton9 = Bitmap.createScaledBitmap(storeButton9, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
        storeButton10 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button04);
        resizedStoreButton10 = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
        exitButton = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.exitbutton);
        resizedExit = Bitmap.createScaledBitmap(exitButton, (int) (Constants.screenWidth / 12), Constants.screenHeight / 20, false);
        shipButton = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button10);
        resizedshipButton = Bitmap.createScaledBitmap(shipButton, (int) (Constants.screenWidth / 7.5), Constants.screenHeight / 12, false);
        lock = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.locked);
        resizedlock = Bitmap.createScaledBitmap(lock, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship1 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1b);
         resizedship1 = Bitmap.createScaledBitmap(ship1, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship2 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1g);
         resizedship2 = Bitmap.createScaledBitmap(ship2, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship3 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1o);
         resizedship3 = Bitmap.createScaledBitmap(ship3, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship4 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1r);
         resizedship4 = Bitmap.createScaledBitmap(ship4, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship5 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2b);
         resizedship5 = Bitmap.createScaledBitmap(ship5, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship6 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2g);
         resizedship6 = Bitmap.createScaledBitmap(ship6, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship7 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2o);
         resizedship7 = Bitmap.createScaledBitmap(ship7, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship8 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2r);
         resizedship8 = Bitmap.createScaledBitmap(ship8, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);


         ship9 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3b);
         resizedship9 = Bitmap.createScaledBitmap(ship9, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship10 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3g);
         resizedship10 = Bitmap.createScaledBitmap(ship10, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship11 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3o);
         resizedship11 = Bitmap.createScaledBitmap(ship11, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship12 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3r);
         resizedship12 = Bitmap.createScaledBitmap(ship12, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);


         ship13 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4b);
         resizedship13 = Bitmap.createScaledBitmap(ship13, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship14 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4g);
         resizedship14 = Bitmap.createScaledBitmap(ship14, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship15 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4y);
         resizedship15 = Bitmap.createScaledBitmap(ship15, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship16 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4r);
         resizedship16 = Bitmap.createScaledBitmap(ship16, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship17 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5b);
         resizedship17 = Bitmap.createScaledBitmap(ship17, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship18 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5g);
         resizedship18 = Bitmap.createScaledBitmap(ship18, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship19 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5o);
         resizedship19 = Bitmap.createScaledBitmap(ship19, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship20 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5bl);
         resizedship20 = Bitmap.createScaledBitmap(ship20, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);


         ship21 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6b);
         resizedship21 = Bitmap.createScaledBitmap(ship21, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship22 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6g);
         resizedship22 = Bitmap.createScaledBitmap(ship22, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship23 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6o);
         resizedship23 = Bitmap.createScaledBitmap(ship23, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship24 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6bl);
         resizedship24 = Bitmap.createScaledBitmap(ship24, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship25 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7b);
         resizedship25 = Bitmap.createScaledBitmap(ship25, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship26 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7g);
         resizedship26 = Bitmap.createScaledBitmap(ship26, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship27 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7o);
         resizedship27 = Bitmap.createScaledBitmap(ship27, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

         ship28 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7bl);
         resizedship28 = Bitmap.createScaledBitmap(ship28, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);

    }

    @Override
    public void update() {

        manager.update();
    }

    public void drawPanel(Canvas canvas) {

        Paint panelPaintB = new Paint();
        panelPaintB.setColor(Color.DKGRAY);
        RectF panel1 = new RectF();
        panelPaintB.setAlpha(180);
        panel1.set(Constants.screenWidth/10 - 5, Constants.screenHeight/12 - 5, Constants.screenWidth - Constants.screenWidth/10 + 5, Constants.screenHeight - Constants.screenHeight/12 + 5);
        canvas.drawRoundRect(panel1, 20, 20, panelPaintB);

        Paint panelPaint = new Paint();
        panelPaint.setColor(Color.LTGRAY);
        RectF panel = new RectF();
        panelPaint.setAlpha(180);
        panel.set(Constants.screenWidth/10, Constants.screenHeight/12, Constants.screenWidth - Constants.screenWidth/10, Constants.screenHeight - Constants.screenHeight/12);
        canvas.drawRoundRect(panel, 20, 20, panelPaint);
    }

    public void drawName(Canvas canvas) {
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");
        Paint paint2 = new Paint();
        paint2.setTextSize(450 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint2.setColor(Color.BLACK);
        paint2.setTypeface(typeface);
        centreText2(canvas, paint2, "SURGE");
        Paint paint = new Paint();
        paint.setTextSize(450 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint.setColor(Color.WHITE);
        paint.setTypeface(typeface);
        centreText(canvas, paint, "SURGE");

    }

    public void drawButtons(Canvas canvas) {
        //Play Button
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");
        Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setColor(Color.WHITE);
        paint.setTextSize(520 / Constants.currentContext.getResources().getDisplayMetrics().density);
        Paint paint2 = new Paint();
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        paint2.setTextSize(520 / Constants.currentContext.getResources().getDisplayMetrics().density);
        if (play) {
            canvas.drawBitmap(resizedPlayButton, Constants.screenWidth / 8, Constants.screenHeight / 5, paint);
        } else {
            canvas.drawBitmap(resizedPlayButton2, Constants.screenWidth / 8, Constants.screenHeight / 5, paint);
        }


        canvas.getClipBounds(r);
        int cWidth = r.width();
        paint.getTextBounds("PLAY", 0, 4, r);
        paint.setTextAlign(Paint.Align.CENTER);
        paint2.getTextBounds("PLAY", 0, 4, r);
        paint2.setTextAlign(Paint.Align.CENTER);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        centreText4(canvas, paint2, "PLAY", Constants.screenHeight/3.2f);
        centreText3(canvas, paint, "PLAY", Constants.screenHeight/3.2f);

        //STORE
        paint.setTextSize(300 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint2.setTextSize(300 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        if (!store) {
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 2.65), paint);
        } else {
            canvas.drawBitmap(resizedStoreButton2, Constants.screenWidth / 8, (int) (Constants.screenHeight / 2.65), paint);
        }
        canvas.drawText(" STORE", (int) (x), (int) (Constants.screenHeight/2.27), paint);
        //OPTIONS
        paint.setTextSize(300 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint2.setTextSize(300 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        if (!options) {
            canvas.drawBitmap(resizedStoreButton3, Constants.screenWidth / 8, (int) (Constants.screenHeight / 2.05), paint);
        } else {
            canvas.drawBitmap(resizedStoreButton4, Constants.screenWidth / 8, (int) (Constants.screenHeight / 2.05), paint);
        }
        canvas.drawText("OPTIONS", (int) (x), (int) (Constants.screenHeight/1.83), paint);
        //SCORES
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        if (!highscore) {
            canvas.drawBitmap(resizedStoreButton5, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.69), paint);
        } else {
            canvas.drawBitmap(resizedStoreButton6, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.69), paint);
        }
        canvas.drawText("SCORES", (int) (x), (int) (Constants.screenHeight/1.53), paint);
        //CREDITS
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        if (!credits) {
            canvas.drawBitmap(resizedStoreButton7, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.44), paint);
        } else {
            canvas.drawBitmap(resizedStoreButton8, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.44), paint);
        }
        canvas.drawText("CREDITS", (int) (x), (int) (Constants.screenHeight/1.32), paint);
        //EXIT
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        if (!exit) {
            canvas.drawBitmap(resizedStoreButton9, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.25), paint);
        } else {
            canvas.drawBitmap(resizedStoreButton10, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.25), paint);
        }
        canvas.drawText("   EXIT", (int) (x), (int) (Constants.screenHeight/1.16), paint);
    }

    public void drawShopPanels(Canvas canvas) {
        drawSelected(canvas);
        Paint paint = new Paint();

        canvas.drawBitmap(resizedExit, (int) (Constants.screenWidth - Constants.screenWidth/7), Constants.screenHeight/14, paint);

        //ROW 1
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 8.2), (int) (Constants.screenHeight / 4.7), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 3.0), (int) (Constants.screenHeight / 4.7), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.9), (int) (Constants.screenHeight / 4.7), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.35), (int) (Constants.screenHeight / 4.7), paint);
        //ROW 2
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 8.2), (int) (Constants.screenHeight / 3.2), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 3.0), (int) (Constants.screenHeight / 3.2), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.9), (int) (Constants.screenHeight / 3.2), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.35), (int) (Constants.screenHeight / 3.2), paint);
        //ROW 3
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 8.2), (int) (Constants.screenHeight / 2.43), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 3.0), (int) (Constants.screenHeight / 2.43), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.9), (int) (Constants.screenHeight / 2.43), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.35), (int) (Constants.screenHeight / 2.43), paint);
        //ROW 4
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 8.2), (int) (Constants.screenHeight / 1.95), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 3.0), (int) (Constants.screenHeight / 1.95), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.9), (int) (Constants.screenHeight / 1.95), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.35), (int) (Constants.screenHeight / 1.95), paint);
        //ROW 5
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 8.2), (int) (Constants.screenHeight / 1.65), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 3.0), (int) (Constants.screenHeight / 1.65), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.9), (int) (Constants.screenHeight / 1.65), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.35), (int) (Constants.screenHeight / 1.65), paint);
        //ROW 6
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 8.2), (int) (Constants.screenHeight / 1.42), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 3.0), (int) (Constants.screenHeight / 1.42), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.9), (int) (Constants.screenHeight / 1.42), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.35), (int) (Constants.screenHeight / 1.42), paint);
        //ROW 7
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 8.2), (int) (Constants.screenHeight / 1.25), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 3.0), (int) (Constants.screenHeight / 1.25), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.9), (int) (Constants.screenHeight / 1.25), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.35), (int) (Constants.screenHeight / 1.25), paint);
        drawShips(canvas);
    }

    public void drawShips(Canvas canvas) {
       
        Paint paint = new Paint();
        canvas.drawBitmap(resizedship1, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 4.5), paint);
        canvas.drawBitmap(resizedship2, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 4.5), paint);
        canvas.drawBitmap(resizedship3, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 4.5), paint);
        canvas.drawBitmap(resizedship4, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 4.5), paint);
        canvas.drawBitmap(resizedship5, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 3.1), paint);
        canvas.drawBitmap(resizedship6, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 3.1), paint);
        canvas.drawBitmap(resizedship7, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 3.1), paint);
        canvas.drawBitmap(resizedship8, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 3.1), paint);
        canvas.drawBitmap(resizedship9, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 2.37), paint);
        canvas.drawBitmap(resizedship10, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 2.37), paint);
        canvas.drawBitmap(resizedship11, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 2.37), paint);
        canvas.drawBitmap(resizedship12, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 2.37), paint);
        canvas.drawBitmap(resizedship13, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.93), paint);
        canvas.drawBitmap(resizedship14, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.93), paint);
        canvas.drawBitmap(resizedship15, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.93), paint);
        canvas.drawBitmap(resizedship16, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.93), paint);
        canvas.drawBitmap(resizedship17, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.63), paint);
        canvas.drawBitmap(resizedship18, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.63), paint);
        canvas.drawBitmap(resizedship19, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.63), paint);
        canvas.drawBitmap(resizedship20, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.63), paint);
        canvas.drawBitmap(resizedship21, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.41), paint);
        canvas.drawBitmap(resizedship22, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.41), paint);
        canvas.drawBitmap(resizedship23, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.41), paint);
        canvas.drawBitmap(resizedship24, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.41), paint);
        canvas.drawBitmap(resizedship25, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.24), paint);
        canvas.drawBitmap(resizedship26, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.24), paint);
        canvas.drawBitmap(resizedship27, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.24), paint);
        canvas.drawBitmap(resizedship28, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.24), paint);

        drawLocks(canvas);

    }

    public void drawLocks(Canvas canvas) {
        
        Paint paint = new Paint();

        if (!ships[0]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 4.5), paint);
        }
        if (!ships[1]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 4.5), paint);
        }
        if (!ships[2]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 4.5), paint);
        }
        if (!ships[3]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 4.5), paint);
        }
        if (!ships[4]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 3.15), paint);
        }
        if (!ships[5]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 3.15), paint);
        }
        if (!ships[6]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 3.15), paint);
        }
        if (!ships[7]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 3.15), paint);
        }
        if (!ships[8]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 2.4), paint);
        }
        if (!ships[9]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 2.4), paint);
        }
        if (!ships[10]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 2.4), paint);
        }
        if (!ships[11]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 2.4), paint);
        }
        if (!ships[12]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.93), paint);
        }
        if (!ships[13]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.93), paint);
        }
        if (!ships[14]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.93), paint);
        }
        if (!ships[15]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.93), paint);
        }
        if (!ships[16]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.63), paint);
        }
        if (!ships[17]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.63), paint);
        }
        if (!ships[18]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.63), paint);
        }
        if (!ships[19]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.63), paint);
        }
        if (!ships[20]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.41), paint);
        }
        if (!ships[21]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.41), paint);
        }
        if (!ships[22]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.41), paint);
        }
        if (!ships[23]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.41), paint);
        }
        if (!ships[24]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.24), paint);
        }
        if (!ships[25]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.24), paint);
        }
        if (!ships[26]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.24), paint);
        }
        if (!ships[27]) {
            canvas.drawBitmap(resizedlock, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.24), paint);
        }

    }

    public void drawSelected(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        RectF selected = new RectF();
        switch (selectedShip) {
            case 0: selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 4.7) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + (Constants.screenHeight / 12) + 5);
                break;
            case 1: selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 4.7) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 2: selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 4.7) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 3: selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 4.7) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 4:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 3.2) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/3.2) + Constants.screenHeight / 12 + 5);
                break;
            case 5:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 3.2) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/3.2) + Constants.screenHeight / 12 + 5);
                break;
            case 6:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 3.2) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/3.2) + Constants.screenHeight / 12 + 5);
                break;
            case 7:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 3.2) - 25,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/3.2) + Constants.screenHeight / 12 -5);
                break;
            case 8:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 2.43) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/2.43) + Constants.screenHeight / 12 + 5);
                break;
            case 9:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 2.43) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/2.43) + Constants.screenHeight / 12 + 5);
            break;
            case 10:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 2.43) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/2.43) + Constants.screenHeight / 12 + 5);
                break;
            case 11:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 2.43) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/2.43) + Constants.screenHeight / 12 + 5);
                break;

            case 12:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 1.95) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.95) + Constants.screenHeight / 12 + 5);
                break;
            case 13:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 1.95) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.95) + Constants.screenHeight / 12 + 5);
                break;
            case 14:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 1.95) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.95) + Constants.screenHeight / 12 + 5);
                break;
            case 15:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 1.95) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.95) + Constants.screenHeight / 12 + 5);
                break;

            case 16:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 1.65) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.65) + Constants.screenHeight / 12 + 5);
                break;
            case 17:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 1.65) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.65) + Constants.screenHeight / 12 + 5);
                break;
            case 18:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 1.65) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.65) + Constants.screenHeight / 12 + 5);
                break;
            case 19:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 1.65) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.65) + Constants.screenHeight / 12 + 5);
                break;

            case 20:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 1.42) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.42) + Constants.screenHeight / 12 + 5);
                break;
            case 21:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 1.42) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.42) + Constants.screenHeight / 12 + 5);
                break;
            case 22:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 1.42) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.42) + Constants.screenHeight / 12 + 5);
                break;
            case 23:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 1.42) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.42) + Constants.screenHeight / 12 + 5);
                break;

            case 24:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 1.25) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.25) + Constants.screenHeight / 12 + 5);
                break;
            case 25:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 1.25) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.25) + Constants.screenHeight / 12 + 5);
                break;
            case 26: selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 1.25) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.25) + Constants.screenHeight / 12 + 5);
                break;
            case 27: selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 1.25) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/1.25) + Constants.screenHeight / 12 + 5);
                break;
        }
        this.sceneManager.shipChosen = selectedShip;
        canvas.drawRoundRect(selected, 8, 8, paint);

    }

    public void drawStore(Canvas canvas) {
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");
        Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setColor(Color.LTGRAY);
        paint.setTextSize(250 / Constants.currentContext.getResources().getDisplayMetrics().density);
        Paint paint2 = new Paint();
        paint2.setTypeface(typeface);
        paint2.setColor(Color.DKGRAY);
        Paint paint3 = new Paint();
        paint3.setColor(Color.WHITE);
        RectF shopPanel = new RectF();
        shopPanel.set(Constants.screenWidth/9, Constants.screenHeight/5, Constants.screenWidth - Constants.screenWidth/9, Constants.screenHeight - (int) (Constants.screenHeight/9.7));
        canvas.drawRoundRect(shopPanel, 10, 10, paint3);
        paint2.setTextSize(250 / Constants.currentContext.getResources().getDisplayMetrics().density);
        canvas.drawText("COINS:", (int) (Constants.screenWidth/20), (int) (Constants.screenHeight/20), paint);

        drawShopPanels(canvas);
    }

    public void drawCredits(Canvas canvas) {



        Paint paint3 = new Paint();
        paint3.setColor(Color.WHITE);
        RectF shopPanel = new RectF();
        shopPanel.set(Constants.screenWidth/9, Constants.screenHeight/5, Constants.screenWidth - Constants.screenWidth/9, Constants.screenHeight - (int) (Constants.screenHeight/9.7));
        canvas.drawRoundRect(shopPanel, 10, 10, paint3);
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");
        Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint.setTextSize(180 / Constants.currentContext.getResources().getDisplayMetrics().density);

        centreText4(canvas, paint, "ASSETS DRAWN BY", Constants.screenHeight/4);

        paint.setTextAlign(Paint.Align.CENTER);
        centreText4(canvas, paint, "KENNEY", Constants.screenHeight/3.5f);

        paint.setTextSize(100 / Constants.currentContext.getResources().getDisplayMetrics().density);
        centreText4(canvas, paint, "www.kenney.nl", Constants.screenHeight/3f);

        paint.setTextSize(150 / Constants.currentContext.getResources().getDisplayMetrics().density);

        centreText4(canvas, paint, "MUSIC BY", Constants.screenHeight/2.4f);
        centreText4(canvas, paint, "Alexandr Zhelanov", Constants.screenHeight/2.25f);

        paint.setTextSize(100 / Constants.currentContext.getResources().getDisplayMetrics().density);
        centreText4(canvas, paint, "soundcloud.com/", Constants.screenHeight/2.05f);
        centreText4(canvas, paint, "alexandr-zhelanov", Constants.screenHeight/1.95f);

        paint.setTextSize(150 / Constants.currentContext.getResources().getDisplayMetrics().density);
        centreText4(canvas, paint, "SOFTWARE DEVELOPED", Constants.screenHeight/1.7f);
        centreText4(canvas, paint, "BY JASON TUBMAN", Constants.screenHeight/1.6f);

        paint.setTextSize(100 / Constants.currentContext.getResources().getDisplayMetrics().density);
        centreText4(canvas, paint, "www.jasontubman.tech", Constants.screenHeight/1.5f);


        //Bitmap exit = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.exitbutton);
        //Bitmap resizedExit = Bitmap.createScaledBitmap(exit, (int) (Constants.screenWidth / 12), Constants.screenHeight / 20, false);
        canvas.drawBitmap(resizedExit, (int) (Constants.screenWidth - Constants.screenWidth/7), Constants.screenHeight/14, paint);

        creditGen.addParticle((int) (Constants.screenWidth/2.07), (int) (Constants.screenHeight/1.2), 0, false);
        creditGen.update();
        creditGen.draw(canvas);


        canvas.drawBitmap(resizedShipCred, (int) (Constants.screenWidth/2.8), (int) (Constants.screenHeight/1.4), paint);

    }

    public void drawOptions(Canvas canvas) {
        if (!musicMuted) {
            sliderX = this.sceneManager.sliderX;
            this.sceneManager.backgroundMusic.setVolume((float) sliderX / sliderMax, (float) sliderX / sliderMax);
            this.sceneManager.currentVolume = (float) sliderX / sliderMax;
        } else {
            this.sceneManager.backgroundMusic.setVolume(0, 0);
            this.sceneManager.currentVolume = 0;
            if (this.sceneManager.currentVolume == 0) {
                sliderX = sliderMin;
            }
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
        paint.setTextSize(260 / Constants.currentContext.getResources().getDisplayMetrics().density);

        
       // Bitmap exit = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.exitbutton);
        //Bitmap resizedExit = Bitmap.createScaledBitmap(exit, (int) (Constants.screenWidth / 12), Constants.screenHeight / 20, false);
        canvas.drawBitmap(resizedExit, (int) (Constants.screenWidth - Constants.screenWidth/7), Constants.screenHeight/14, paint);

        centreText4(canvas, paint, "OPTIONS", Constants.screenHeight/4);
        paint.setTextSize(180 / Constants.currentContext.getResources().getDisplayMetrics().density);
        centreText4(canvas, paint, "CHANGE VOLUME", Constants.screenHeight/3.3f);

        centreText4(canvas, paint, "MUTE MUSIC :", Constants.screenHeight/2.23f);

        canvas.drawBitmap(resizedSlider, (int) (Constants.screenWidth/7), (int) (Constants.screenHeight/3.1), paint);


        if (musicMuted) {
            canvas.drawBitmap(resizedmute, (int) (Constants.screenWidth/1.3), (int) (Constants.screenHeight/2.45), paint);
        } else {
            canvas.drawBitmap(resizednotmute, (int) (Constants.screenWidth/1.3), (int) (Constants.screenHeight/2.45), paint);
        }



        canvas.drawBitmap(resizedSliderUp, (int) (this.sliderX), (int) (Constants.screenHeight/3.05), paint);


        creditGen.addParticle((int) (Constants.screenWidth/2.07), (int) (Constants.screenHeight/1.6), 0, false);
        creditGen.update();
        creditGen.draw(canvas);


        canvas.drawBitmap(resizedShip, (int) (Constants.screenWidth/2.8), (int) (Constants.screenHeight/1.9), paint);

    }
    public void drawScores(Canvas canvas) {
        Paint paint3 = new Paint();
        paint3.setColor(Color.WHITE);
        RectF shopPanel = new RectF();
        shopPanel.set(Constants.screenWidth/9, Constants.screenHeight/5, Constants.screenWidth - Constants.screenWidth/9, Constants.screenHeight - (int) (Constants.screenHeight/9.7));
        canvas.drawRoundRect(shopPanel, 10, 10, paint3);
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");
        Paint paint = new Paint();
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint.setTextSize(180 / Constants.currentContext.getResources().getDisplayMetrics().density);

        canvas.drawBitmap(resizedExit, (int) (Constants.screenWidth - Constants.screenWidth/7), Constants.screenHeight/14, paint);
    }

    @Override
    public void draw(Canvas canvas) {
        this.canvas = canvas;
        canvas.drawRGB(44, 42, 49);
        manager.draw(canvas);

        //PANEL

        drawPanel(canvas);

        drawName(canvas);
        if (menu) {
            drawButtons(canvas);
        } else if (store) {
            drawStore(canvas);
        } else if (options) {
            drawOptions(canvas);
        } else if (highscore) {
            drawScores(canvas);
        } else if (credits) {
            drawCredits(canvas);
        }

    }

    @Override
    public void terminate() {
        sceneManager.addScene(new GameplayScene(this.sceneManager));
        SceneManager.activeScene = 1;
    }

    public void playPressed(Canvas canvas) {
        try {
            Thread.sleep(250);
        } catch (Exception e) {
            terminate();
        }
        terminate();
    }
    public void storePressed(Canvas canvas) {
        try {
            Thread.sleep(250);
        } catch (Exception e) {

        }
        menu = false;
    }
    public void creditsPressed(Canvas canvas) {
        try {
            Thread.sleep(250);
        } catch (Exception e) {

        }
        menu = false;
    }
    public void optionsPressed(Canvas canvas) {
        try {
            Thread.sleep(250);
        } catch (Exception e) {

        }
        menu = false;
    }
    public void highscorePressed(Canvas canvas) {
        try {
            Thread.sleep(250);
        } catch (Exception e) {

        }
        menu = false;
    }
    public void exitPressed(Canvas canvas) {
        try {
            Thread.sleep(250);
        } catch (Exception e) {
            System.exit(0);
        }
        System.exit(0);
    }

    @Override
    public void recieveTouch(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {

                if (event.getX() > Constants.screenWidth / 10 && event.getX() < Constants.screenWidth - Constants.screenWidth / 10) {

                    if (event.getY() > Constants.screenHeight / 5 && event.getY() < Constants.screenHeight / 5 + Constants.screenHeight / 6 && menu) {
                        play = true;
                        playPressed(canvas);
                        break;
                    }
                    if (event.getY() > Constants.screenHeight / 2.65 && event.getY() < Constants.screenHeight / 2.65 + Constants.screenHeight / 11 && menu) {
                        store = true;
                        storePressed(canvas);
                        break;
                    }
                    if (event.getY() > Constants.screenHeight / 2.05 && event.getY() < Constants.screenHeight / 2.05 + Constants.screenHeight / 11 && menu) {
                        options = true;
                        optionsPressed(canvas);
                        break;
                    }
                    if (event.getY() > Constants.screenHeight / 1.69 && event.getY() < Constants.screenHeight / 1.69 + Constants.screenHeight / 11 && menu) {
                        highscore = true;
                        highscorePressed(canvas);
                        break;
                    }
                    if (event.getY() > Constants.screenHeight / 1.44 && event.getY() < Constants.screenHeight / 1.44 + Constants.screenHeight / 11 && menu) {
                        credits = true;
                        creditsPressed(canvas);
                        break;
                    }
                    if (event.getY() > Constants.screenHeight / 1.25 && event.getY() < Constants.screenHeight / 1.25 + Constants.screenHeight / 11 && menu) {
                        exit = true;
                        exitPressed(canvas);
                        break;
                    }

                    //STORE CLICKS ----------
                    //ROW 1
                    if (event.getY() > Constants.screenHeight / 4.7 && event.getY() < Constants.screenHeight / 4.7 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 8.2 && event.getX() < Constants.screenWidth / 8.2 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[0]) {
                            ships[0] = true;
                        } else {
                            selectedShip = 0;
                        }
                    } else if (event.getY() > Constants.screenHeight / 4.7 && event.getY() < Constants.screenHeight / 4.7 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 3.0 && event.getX() < Constants.screenWidth / 3.0 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[1]) {
                            ships[1] = true;
                        } else {
                            selectedShip = 1;
                        }
                    } else if (event.getY() > Constants.screenHeight / 4.7 && event.getY() < Constants.screenHeight / 4.7 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.9 && event.getX() < Constants.screenWidth / 1.9 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[2]) {
                            ships[2] = true;
                        } else {
                            selectedShip = 2;
                        }
                    } else if (event.getY() > Constants.screenHeight / 4.7 && event.getY() < Constants.screenHeight / 4.7 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.35 && event.getX() < Constants.screenWidth / 1.35 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[3]) {
                            ships[3] = true;
                        } else {
                            selectedShip = 3;
                        }
                    }
                    //ROW 2
                    else if (event.getY() > Constants.screenHeight / 3.2 && event.getY() < Constants.screenHeight / 3.2 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 8.2 && event.getX() < Constants.screenWidth / 8.2 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[4]) {
                            ships[4] = true;
                        } else {
                            selectedShip = 4;
                        }
                    } else if (event.getY() > Constants.screenHeight / 3.2 && event.getY() < Constants.screenHeight / 3.2 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 3.0 && event.getX() < Constants.screenWidth / 3.0 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[5]) {
                            ships[5] = true;
                        } else {
                            selectedShip = 5;
                        }
                    } else if (event.getY() > Constants.screenHeight / 3.2 && event.getY() < Constants.screenHeight / 3.2 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.9 && event.getX() < Constants.screenWidth / 1.9 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[6]) {
                            ships[6] = true;
                        } else {
                            selectedShip = 6;
                        }
                    } else if (event.getY() > Constants.screenHeight / 3.2 && event.getY() < Constants.screenHeight / 3.2 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.35 && event.getX() < Constants.screenWidth / 1.35 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[7]) {
                            ships[7] = true;
                        } else {
                            selectedShip = 7;
                        }
                    }
                    //ROW 3
                    else if (event.getY() > Constants.screenHeight / 2.43 && event.getY() < Constants.screenHeight / 2.43 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 8.2 && event.getX() < Constants.screenWidth / 8.2 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[8]) {
                            ships[8] = true;
                        } else {
                            selectedShip = 8;
                        }
                    } else if (event.getY() > Constants.screenHeight / 2.43 && event.getY() < Constants.screenHeight / 2.43 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 3.0 && event.getX() < Constants.screenWidth / 3.0 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[9]) {
                            ships[9] = true;
                        } else {
                            selectedShip = 9;
                        }
                    } else if (event.getY() > Constants.screenHeight / 2.43 && event.getY() < Constants.screenHeight / 2.43 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.9 && event.getX() < Constants.screenWidth / 1.9 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[10]) {
                            ships[10] = true;
                        } else {
                            selectedShip = 10;
                        }
                    } else if (event.getY() > Constants.screenHeight / 2.43 && event.getY() < Constants.screenHeight / 2.43 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.35 && event.getX() < Constants.screenWidth / 1.35 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[11]) {
                            ships[11] = true;
                        } else {
                            selectedShip = 11;
                        }
                    }
                    //ROW 4
                    else if (event.getY() > Constants.screenHeight / 1.95 && event.getY() < Constants.screenHeight / 1.95 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 8.2 && event.getX() < Constants.screenWidth / 8.2 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[12]) {
                            ships[12] = true;
                        } else {
                            selectedShip = 12;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.95 && event.getY() < Constants.screenHeight / 1.95 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 3.0 && event.getX() < Constants.screenWidth / 3.0 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[13]) {
                            ships[13] = true;
                        } else {
                            selectedShip = 13;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.95 && event.getY() < Constants.screenHeight / 1.95 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.9 && event.getX() < Constants.screenWidth / 1.9 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[14]) {
                            ships[14] = true;
                        } else {
                            selectedShip = 14;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.95 && event.getY() < Constants.screenHeight / 1.95 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.35 && event.getX() < Constants.screenWidth / 1.35 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[15]) {
                            ships[15] = true;
                        } else {
                            selectedShip = 15;
                        }
                    }
                    //ROW 5
                    else if (event.getY() > Constants.screenHeight / 1.65 && event.getY() < Constants.screenHeight / 1.65 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 8.2 && event.getX() < Constants.screenWidth / 8.2 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[16]) {
                            ships[16] = true;
                        } else {
                            selectedShip = 16;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.65 && event.getY() < Constants.screenHeight / 1.65 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 3.0 && event.getX() < Constants.screenWidth / 3.0 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[17]) {
                            ships[17] = true;
                        } else {
                            selectedShip = 17;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.65 && event.getY() < Constants.screenHeight / 1.65 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.9 && event.getX() < Constants.screenWidth / 1.9 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[18]) {
                            ships[18] = true;
                        } else {
                            selectedShip = 18;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.65 && event.getY() < Constants.screenHeight / 1.65 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.35 && event.getX() < Constants.screenWidth / 1.35 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[19]) {
                            ships[19] = true;
                        } else {
                            selectedShip = 19;
                        }
                    }
                    //ROW 6
                    else if (event.getY() > Constants.screenHeight / 1.42 && event.getY() < Constants.screenHeight / 1.42 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 8.2 && event.getX() < Constants.screenWidth / 8.2 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[20]) {
                            ships[20] = true;
                        } else {
                            selectedShip = 20;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.42 && event.getY() < Constants.screenHeight / 1.42 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 3.0 && event.getX() < Constants.screenWidth / 3.0 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[21]) {
                            ships[21] = true;
                        } else {
                            selectedShip = 21;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.42 && event.getY() < Constants.screenHeight / 1.42 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.9 && event.getX() < Constants.screenWidth / 1.9 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[22]) {
                            ships[22] = true;
                        } else {
                            selectedShip = 22;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.42 && event.getY() < Constants.screenHeight / 1.42 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.35 && event.getX() < Constants.screenWidth / 1.35 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[23]) {
                            ships[23] = true;
                        } else {
                            selectedShip = 23;
                        }
                    }
                    //ROW 7
                    else if (event.getY() > Constants.screenHeight / 1.25 && event.getY() < Constants.screenHeight / 1.25 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 8.2 && event.getX() < Constants.screenWidth / 8.2 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[24]) {
                            ships[24] = true;
                        } else {
                            selectedShip = 24;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.25 && event.getY() < Constants.screenHeight / 1.25 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 3.0 && event.getX() < Constants.screenWidth / 3.0 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[25]) {
                            ships[25] = true;
                        } else {
                            selectedShip = 25;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.25 && event.getY() < Constants.screenHeight / 1.25 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.9 && event.getX() < Constants.screenWidth / 1.9 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[26]) {
                            ships[26] = true;
                        } else {
                            selectedShip = 26;
                        }
                    } else if (event.getY() > Constants.screenHeight / 1.25 && event.getY() < Constants.screenHeight / 1.25 + Constants.screenHeight / 12
                            && event.getX() > Constants.screenWidth / 1.35 && event.getX() < Constants.screenWidth / 1.35 + (int) (Constants.screenWidth / 7.5) && store) {
                        if (!ships[27]) {
                            ships[27] = true;
                        } else {
                            selectedShip = 27;
                        }
                    }
                    // STORE CLICKS END ---------
                    if (event.getY() > (int) (Constants.screenHeight / 3.05) && event.getY() < (int) (Constants.screenHeight / 3.05 + Constants.screenWidth / 1) && options &&
                            event.getX() > sliderX && event.getX() < sliderX + (int) (Constants.screenWidth / 19)) {
                        sliderMoving = true;
                    }

                }
                //EXIT CLICK STORE
                if (event.getY() > Constants.screenHeight / 14 && event.getY() < Constants.screenHeight / 14 + Constants.screenHeight / 20
                        && event.getX() > Constants.screenWidth / 12 && event.getX() < Constants.screenWidth / 12 + Constants.screenWidth - Constants.screenWidth / 7 && store) {
                    menu = true;
                    store = false;
                }
                //END EXIT CLICK
                //EXIT CLICK CREDITS
                if (event.getY() > Constants.screenHeight / 14 && event.getY() < Constants.screenHeight / 14 + Constants.screenHeight / 20
                        && event.getX() > Constants.screenWidth / 12 && event.getX() < Constants.screenWidth / 12 + Constants.screenWidth - Constants.screenWidth / 7 && credits) {
                    menu = true;
                    credits = false;
                }
                //END CREDITS CLICK
                //EXIT CLICK OPTIONS
                if (event.getY() > Constants.screenHeight / 14 && event.getY() < Constants.screenHeight / 14 + Constants.screenHeight / 20
                        && event.getX() > Constants.screenWidth / 12 && event.getX() < Constants.screenWidth / 12 + Constants.screenWidth - Constants.screenWidth / 7 && options) {
                    menu = true;
                    options = false;
                }
                //END OPTIONS CLICK
                //SCORES CLICK OPTIONS
                if (event.getY() > Constants.screenHeight / 14 && event.getY() < Constants.screenHeight / 14 + Constants.screenHeight / 20
                        && event.getX() > Constants.screenWidth / 12 && event.getX() < Constants.screenWidth / 12 + Constants.screenWidth - Constants.screenWidth / 7 && highscore) {
                    menu = true;
                    highscore = false;
                }
                //END OPTIONS CLICK

                //MUTE
                if (event.getY() > Constants.screenHeight / 2.45 && event.getY() < Constants.screenHeight / 2.45 + Constants.screenWidth/11
                        && event.getX() > Constants.screenWidth / 1.3 && event.getX() < Constants.screenWidth / 1.3 + Constants.screenWidth /10 && options) {
                    if (!musicMuted) {
                        musicMuted = true;
                        this.sceneManager.mute = true;
                    } else {
                        musicMuted = false;
                    }
                }
                //MUTE END




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
                        this.sceneManager.sliderX = sliderX;
                    } else if (event.getX() > sliderMax) {
                        sliderX = sliderMax;
                        this.sceneManager.sliderX = sliderX;
                    } else if (event.getX() < sliderMin) {
                        sliderX = sliderMin;
                        this.sceneManager.sliderX = sliderX;
                    }
                }
                break;
            }
        }


    }

    private void centreText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) / 3;
        canvas.drawText(text, x, y, paint);
    }

    private void centreText2(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left - 15;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) / 3.05f;
        canvas.drawText(text, x, y, paint);
    }

    private void centreText3(Canvas canvas, Paint paint, String text, float y) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        canvas.drawText(text, x, y, paint);
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
