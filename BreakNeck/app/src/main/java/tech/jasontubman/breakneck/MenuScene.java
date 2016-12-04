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

    private ParticleGenerator creditGen;

    public MenuScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;

        manager = new StarManager(7, true);
        Arrays.fill(ships, Boolean.FALSE);
        ships[0] = true;
        creditGen = new ParticleGenerator();
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
        BitmapFactory bf = new BitmapFactory();
        if (play) {
            Bitmap playButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.playpushed);
            Bitmap resizedPlayButton = Bitmap.createScaledBitmap(playButton, (int) (Constants.screenWidth/1.35), Constants.screenHeight/6, false);
            canvas.drawBitmap(resizedPlayButton, Constants.screenWidth / 8, Constants.screenHeight / 5, paint);
        } else {
            Bitmap playButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.playbutton);
            Bitmap resizedPlayButton = Bitmap.createScaledBitmap(playButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 6, false);
            canvas.drawBitmap(resizedPlayButton, Constants.screenWidth / 8, Constants.screenHeight / 5, paint);
        }
        canvas.drawText("PLAY", (int) (Constants.screenWidth/4.45), (int) (Constants.screenHeight/3.24), paint2);
        canvas.drawText("PLAY", (int) (Constants.screenWidth/4.35), (int) (Constants.screenHeight/3.2), paint);

        //STORE
        paint.setTextSize(300 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint2.setTextSize(300 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        if (!store) {
            Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
            Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 2.65), paint);
        } else {
            Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button04);
            Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 2.65), paint);
        }
        canvas.drawText("STORE", (int) (Constants.screenWidth/3.6), (int) (Constants.screenHeight/2.27), paint);
        //OPTIONS
        paint.setTextSize(300 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint2.setTextSize(300 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        if (!options) {
            Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
            Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 2.05), paint);
        } else {
            Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button04);
            Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 2.05), paint);
        }
        canvas.drawText("OPTIONS", (int) (Constants.screenWidth/4.3), (int) (Constants.screenHeight/1.83), paint);
        //SCORES
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        if (!highscore) {
            Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
            Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.69), paint);
        } else {
            Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button04);
            Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.69), paint);
        }
        canvas.drawText(" SCORES", (int) (Constants.screenWidth/4.98), (int) (Constants.screenHeight/1.53), paint);
        //CREDITS
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        if (!credits) {
            Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
            Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.44), paint);
        } else {
            Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button04);
            Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.44), paint);
        }
        canvas.drawText("CREDITS", (int) (Constants.screenWidth/4.4), (int) (Constants.screenHeight/1.32), paint);
        //EXIT
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        if (!exit) {
            Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
            Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.25), paint);
        } else {
            Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button04);
            Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth / 1.35), Constants.screenHeight / 11, false);
            canvas.drawBitmap(resizedStoreButton, Constants.screenWidth / 8, (int) (Constants.screenHeight / 1.25), paint);
        }
        canvas.drawText("EXIT", (int) (Constants.screenWidth/2.8), (int) (Constants.screenHeight/1.16), paint);
    }

    public void drawShopPanels(Canvas canvas) {
        drawSelected(canvas);
        BitmapFactory bf = new BitmapFactory();
        Paint paint = new Paint();
        Bitmap exit = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.exitbutton);
        Bitmap resizedExit = Bitmap.createScaledBitmap(exit, (int) (Constants.screenWidth / 12), Constants.screenHeight / 20, false);
        canvas.drawBitmap(resizedExit, (int) (Constants.screenWidth - Constants.screenWidth/7), Constants.screenHeight/14, paint);

        //10 then 11

        Bitmap shipButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button10);
        Bitmap resizedshipButton = Bitmap.createScaledBitmap(shipButton, (int) (Constants.screenWidth / 7.5), Constants.screenHeight / 12, false);
        //ROW 1
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 8.2), (int) (Constants.screenHeight / 4.7), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 3.0), (int) (Constants.screenHeight / 4.7), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.9), (int) (Constants.screenHeight / 4.7), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.35), (int) (Constants.screenHeight / 4.7), paint);
        //ROW 2
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 8.2), (int) (Constants.screenHeight / 3.2), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 3.0), (int) (Constants.screenHeight / 3.2), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.9), (int) (Constants.screenHeight / 3.2), paint);
        canvas.drawBitmap(resizedshipButton, (int) (Constants.screenWidth / 1.35), (int) (Constants.screenHeight / 3.27), paint);
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
        BitmapFactory bf = new BitmapFactory();
        Paint paint = new Paint();
        Bitmap ship1 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1b);
        Bitmap resizedship1 = Bitmap.createScaledBitmap(ship1, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship1, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 4.5), paint);
        Bitmap ship2 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1g);
        Bitmap resizedship2 = Bitmap.createScaledBitmap(ship2, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship2, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 4.5), paint);
        Bitmap ship3 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1o);
        Bitmap resizedship3 = Bitmap.createScaledBitmap(ship3, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship3, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 4.5), paint);
        Bitmap ship4 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1r);
        Bitmap resizedship4 = Bitmap.createScaledBitmap(ship4, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship4, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 4.5), paint);


        Bitmap ship5 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2b);
        Bitmap resizedship5 = Bitmap.createScaledBitmap(ship5, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship5, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 3.1), paint);
        Bitmap ship6 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2g);
        Bitmap resizedship6 = Bitmap.createScaledBitmap(ship6, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship6, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 3.1), paint);
        Bitmap ship7 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2o);
        Bitmap resizedship7 = Bitmap.createScaledBitmap(ship7, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship7, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 3.1), paint);
        Bitmap ship8 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2r);
        Bitmap resizedship8 = Bitmap.createScaledBitmap(ship8, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship8, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 3.1), paint);

        Bitmap ship9 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3b);
        Bitmap resizedship9 = Bitmap.createScaledBitmap(ship9, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship9, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 2.37), paint);
        Bitmap ship10 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3g);
        Bitmap resizedship10 = Bitmap.createScaledBitmap(ship10, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship10, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 2.37), paint);
        Bitmap ship11 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3o);
        Bitmap resizedship11 = Bitmap.createScaledBitmap(ship11, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship11, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 2.37), paint);
        Bitmap ship12 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3r);
        Bitmap resizedship12 = Bitmap.createScaledBitmap(ship12, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship12, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 2.37), paint);

        Bitmap ship13 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4b);
        Bitmap resizedship13 = Bitmap.createScaledBitmap(ship13, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship13, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.93), paint);
        Bitmap ship14 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4g);
        Bitmap resizedship14 = Bitmap.createScaledBitmap(ship14, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship14, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.93), paint);
        Bitmap ship15 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4y);
        Bitmap resizedship15 = Bitmap.createScaledBitmap(ship15, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship15, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.93), paint);
        Bitmap ship16 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4r);
        Bitmap resizedship16 = Bitmap.createScaledBitmap(ship16, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship16, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.93), paint);

        Bitmap ship17 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5b);
        Bitmap resizedship17 = Bitmap.createScaledBitmap(ship17, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship17, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.63), paint);
        Bitmap ship18 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5g);
        Bitmap resizedship18 = Bitmap.createScaledBitmap(ship18, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship18, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.63), paint);
        Bitmap ship19 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5o);
        Bitmap resizedship19 = Bitmap.createScaledBitmap(ship19, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship19, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.63), paint);
        Bitmap ship20 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5bl);
        Bitmap resizedship20 = Bitmap.createScaledBitmap(ship20, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship20, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.63), paint);

        Bitmap ship21 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6b);
        Bitmap resizedship21 = Bitmap.createScaledBitmap(ship21, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship21, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.41), paint);
        Bitmap ship22 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6g);
        Bitmap resizedship22 = Bitmap.createScaledBitmap(ship22, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship22, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.41), paint);
        Bitmap ship23 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6o);
        Bitmap resizedship23 = Bitmap.createScaledBitmap(ship23, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship23, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.41), paint);
        Bitmap ship24 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6bl);
        Bitmap resizedship24 = Bitmap.createScaledBitmap(ship24, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship24, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.41), paint);



        Bitmap ship25 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7b);
        Bitmap resizedship25 = Bitmap.createScaledBitmap(ship25, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship25, (int) (Constants.screenWidth / 7.2), (int) (Constants.screenHeight / 1.24), paint);
        Bitmap ship26 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7g);
        Bitmap resizedship26 = Bitmap.createScaledBitmap(ship26, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship26, (int) (Constants.screenWidth / 2.85), (int) (Constants.screenHeight / 1.24), paint);
        Bitmap ship27 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7o);
        Bitmap resizedship27 = Bitmap.createScaledBitmap(ship27, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship27, (int) (Constants.screenWidth / 1.85), (int) (Constants.screenHeight / 1.24), paint);
        Bitmap ship28 = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7bl);
        Bitmap resizedship28 = Bitmap.createScaledBitmap(ship28, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
        canvas.drawBitmap(resizedship28, (int) (Constants.screenWidth / 1.32), (int) (Constants.screenHeight / 1.24), paint);

        drawLocks(canvas);

    }

    public void drawLocks(Canvas canvas) {
        BitmapFactory bf = new BitmapFactory();
        Paint paint = new Paint();
        Bitmap lock = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.locked);
        Bitmap resizedlock = Bitmap.createScaledBitmap(lock, (int) (Constants.screenWidth / 10), Constants.screenHeight / 16, false);
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
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 5:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 3.2) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 6:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 3.2) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 7:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 3.2) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 8:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 2.43) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 9:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 2.43) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 10:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 2.43) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 11:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 2.43) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;

            case 12:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 1.95) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 13:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 1.95) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 14:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 1.95) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 15:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 1.95) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;

            case 16:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 1.65) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 17:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 1.65) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 18:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 1.65) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 19:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 1.65) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;

            case 20:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 1.42) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 21:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 1.42) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 22:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 1.42) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 23:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 1.42) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;

            case 24:selected.set((int) (Constants.screenWidth / 8.2) - 5, (int) (Constants.screenHeight / 1.25) - 5,
                    (int) (Constants.screenWidth/8.2) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 25:selected.set((int) (Constants.screenWidth / 3.0) - 5, (int) (Constants.screenHeight / 1.25) - 5,
                    (int) (Constants.screenWidth/3.0) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 26:selected.set((int) (Constants.screenWidth / 1.9) - 5, (int) (Constants.screenHeight / 1.25) - 5,
                    (int) (Constants.screenWidth/1.9) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
            case 27:selected.set((int) (Constants.screenWidth / 1.35) - 5, (int) (Constants.screenHeight / 1.25) - 5,
                    (int) (Constants.screenWidth/1.35) + (int) (Constants.screenWidth / 7.5) + 5, (int) (Constants.screenHeight/4.7) + Constants.screenHeight / 12 + 5);
                break;
        }

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
        canvas.drawText("ASSETS DRAWN BY", (int)(Constants.screenWidth/6.3), Constants.screenHeight/4, paint);
        canvas.drawText("KENNEY", (int)(Constants.screenWidth/3), (int) (Constants.screenHeight/3.5), paint);
        paint.setTextSize(100 / Constants.currentContext.getResources().getDisplayMetrics().density);
        canvas.drawText("www.kenney.nl", (int) (Constants.screenWidth/3.2), (int) (Constants.screenHeight/3), paint);

        paint.setTextSize(150 / Constants.currentContext.getResources().getDisplayMetrics().density);
        canvas.drawText("MUSIC BY" , (int) (Constants.screenWidth/2.9), (int) (Constants.screenHeight/2.4), paint);
        canvas.drawText("Alexandr Zhelanov" , Constants.screenWidth/6, (int) (Constants.screenHeight/2.25), paint);

        paint.setTextSize(100 / Constants.currentContext.getResources().getDisplayMetrics().density);
        canvas.drawText("soundcloud.com/",(int) (Constants.screenWidth/3.5), (int) (Constants.screenHeight/2.05), paint);
        canvas.drawText("alexandr-zhelanov", Constants.screenWidth/4, (int) (Constants.screenHeight/1.95), paint);

        paint.setTextSize(150 / Constants.currentContext.getResources().getDisplayMetrics().density);
        canvas.drawText("SOFTWARE DEVELOPED", (int) (Constants.screenWidth/6.8), (int) (Constants.screenHeight/1.7), paint);
        canvas.drawText("BY JASON TUBMAN", (int) (Constants.screenWidth/4.9), (int) (Constants.screenHeight/1.6), paint);
        paint.setTextSize(100 / Constants.currentContext.getResources().getDisplayMetrics().density);
        canvas.drawText("www.jasontubman.tech", (int) (Constants.screenWidth/4.6), (int) (Constants.screenHeight/1.5), paint);

        BitmapFactory bf = new BitmapFactory();
        Bitmap exit = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.exitbutton);
        Bitmap resizedExit = Bitmap.createScaledBitmap(exit, (int) (Constants.screenWidth / 12), Constants.screenHeight / 20, false);
        canvas.drawBitmap(resizedExit, (int) (Constants.screenWidth - Constants.screenWidth/7), Constants.screenHeight/14, paint);

        creditGen.addParticle((int) (Constants.screenWidth/2.07), (int) (Constants.screenHeight/1.2), 0, false);
        creditGen.update();
        creditGen.draw(canvas);

        Bitmap ship = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7bl);
        Bitmap resizedShip = Bitmap.createScaledBitmap(ship, (int) (Constants.screenWidth / 4), Constants.screenHeight / 7, false);
        canvas.drawBitmap(resizedShip, (int) (Constants.screenWidth/2.8), (int) (Constants.screenHeight/1.4), paint);

    }

    public void drawOptions(Canvas canvas) {
        if (!musicMuted) {
            this.sceneManager.backgroundMusic.setVolume((float) sliderX / sliderMax, (float) sliderX / sliderMax);
        } else {
            this.sceneManager.backgroundMusic.setVolume(0, 0);
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

        BitmapFactory bf = new BitmapFactory();
        Bitmap exit = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.exitbutton);
        Bitmap resizedExit = Bitmap.createScaledBitmap(exit, (int) (Constants.screenWidth / 12), Constants.screenHeight / 20, false);
        canvas.drawBitmap(resizedExit, (int) (Constants.screenWidth - Constants.screenWidth/7), Constants.screenHeight/14, paint);

        canvas.drawText("OPTIONS", (int)(Constants.screenWidth/3.8), Constants.screenHeight/4, paint);
        paint.setTextSize(180 / Constants.currentContext.getResources().getDisplayMetrics().density);
        canvas.drawText("VOLUME", (int)(Constants.screenWidth/7), (int)(Constants.screenHeight/3.3), paint);

        canvas.drawText("MUTE MUSIC:", (int)(Constants.screenWidth/7), (int)(Constants.screenHeight/2.4), paint);

        Bitmap slider = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.slider);
        Bitmap resizedSlider = Bitmap.createScaledBitmap(slider, (int) (Constants.screenWidth / 1.4), Constants.screenWidth/100, false);
        canvas.drawBitmap(resizedSlider, (int) (Constants.screenWidth/7), (int) (Constants.screenHeight/3.1), paint);


        Bitmap mute = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_boxcross);
        Bitmap resizedmute = Bitmap.createScaledBitmap(mute, (int) (Constants.screenWidth / 10), Constants.screenWidth/11, false);

        Bitmap notmute = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_box);
        Bitmap resizednotmute = Bitmap.createScaledBitmap(notmute, (int) (Constants.screenWidth /10), Constants.screenWidth/11, false);

        if (musicMuted) {
            canvas.drawBitmap(resizedmute, (int) (Constants.screenWidth/1.6), (int) (Constants.screenHeight/2.62), paint);
        } else {
            canvas.drawBitmap(resizednotmute, (int) (Constants.screenWidth/1.6), (int) (Constants.screenHeight/2.62), paint);
        }


        Bitmap sliderUp = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.sliderup);
        Bitmap resizedSliderUp = Bitmap.createScaledBitmap(sliderUp, (int) (Constants.screenWidth / 19), Constants.screenWidth/13, false);
        canvas.drawBitmap(resizedSliderUp, (int) (this.sliderX), (int) (Constants.screenHeight/3.05), paint);


        creditGen.addParticle((int) (Constants.screenWidth/2.07), (int) (Constants.screenHeight/1.6), 0, false);
        creditGen.update();
        creditGen.draw(canvas);

        Bitmap ship = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2r);
        Bitmap resizedShip = Bitmap.createScaledBitmap(ship, (int) (Constants.screenWidth / 4), Constants.screenHeight / 7, false);
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

        BitmapFactory bf = new BitmapFactory();
        Bitmap exit = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.exitbutton);
        Bitmap resizedExit = Bitmap.createScaledBitmap(exit, (int) (Constants.screenWidth / 12), Constants.screenHeight / 20, false);
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
                    }
                    if (event.getY() > Constants.screenHeight / 2.65 && event.getY() < Constants.screenHeight / 2.65 + Constants.screenHeight / 11 && menu) {
                        store = true;
                        storePressed(canvas);
                    }
                    if (event.getY() > Constants.screenHeight / 2.05 && event.getY() < Constants.screenHeight / 2.05 + Constants.screenHeight / 11 && menu) {
                        options = true;
                        optionsPressed(canvas);
                    }
                    if (event.getY() > Constants.screenHeight / 1.69 && event.getY() < Constants.screenHeight / 1.69 + Constants.screenHeight / 11 && menu) {
                        highscore = true;
                        highscorePressed(canvas);
                    }
                    if (event.getY() > Constants.screenHeight / 1.44 && event.getY() < Constants.screenHeight / 1.44 + Constants.screenHeight / 11 && menu) {
                        credits = true;
                        creditsPressed(canvas);
                    }
                    if (event.getY() > Constants.screenHeight / 1.25 && event.getY() < Constants.screenHeight / 1.25 + Constants.screenHeight / 11 && menu) {
                        exit = true;
                        exitPressed(canvas);
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
                if (event.getY() > Constants.screenHeight / 2.62 && event.getY() < Constants.screenHeight / 2.62 + Constants.screenWidth/11
                        && event.getX() > Constants.screenWidth / 1.6 && event.getX() < Constants.screenWidth / 1.6 + Constants.screenWidth /10 && options) {
                    if (!musicMuted) {
                        musicMuted = true;
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
                    } else if (event.getX() > sliderMax) {
                        sliderX = sliderMax;
                    } else if (event.getX() < sliderMin) {
                        sliderX = sliderMin;
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

}
