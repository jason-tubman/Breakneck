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
import android.view.View;
import android.widget.Button;

import tech.jasontubman.game.R;

import static android.graphics.Color.rgb;


/**
 * Created by Jason on 30/11/2016.
 */

public class MenuScene implements Scene {

    private StarManager manager;
    private Rect r = new Rect();
    private  SceneManager sceneManager;

    public MenuScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;

        manager = new StarManager(7, true);

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
        paint2.setTextSize(200);
        paint2.setColor(Color.BLACK);
        paint2.setTypeface(typeface);
        centreText2(canvas, paint2, "SURGE");
        Paint paint = new Paint();
        paint.setTextSize(200);
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
        paint.setTextSize(220 * Constants.currentContext.getResources().getDisplayMetrics().density);
        Paint paint2 = new Paint();
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        paint2.setTextSize(220 * Constants.currentContext.getResources().getDisplayMetrics().density);
        BitmapFactory bf = new BitmapFactory();
        Bitmap playButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.playbutton);
        Bitmap resizedPlayButton = Bitmap.createScaledBitmap(playButton, (int) (Constants.screenWidth/1.35), Constants.screenHeight/6, false);
        canvas.drawBitmap(resizedPlayButton, Constants.screenWidth/8, Constants.screenHeight/5, paint);
        canvas.drawText("PLAY", (int) (Constants.screenWidth/4.25), (int) (Constants.screenHeight/3.3), paint2);
        canvas.drawText("PLAY", (int) (Constants.screenWidth/4.2), (int) (Constants.screenHeight/3.3), paint);
        //STORE
        paint.setTextSize(150);
        paint2.setTextSize(150);
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        Bitmap storeButton = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
        Bitmap resizedStoreButton = Bitmap.createScaledBitmap(storeButton, (int) (Constants.screenWidth/1.35), Constants.screenHeight/11, false);
        canvas.drawBitmap(resizedStoreButton, Constants.screenWidth/8, (int)(Constants.screenHeight/2.65), paint);
        canvas.drawText("STORE", (int) (Constants.screenWidth/3.8), (int) (Constants.screenHeight/2.7), paint2);
        canvas.drawText("STORE", (int) (Constants.screenWidth/3.85), (int) (Constants.screenHeight/2.7), paint);
        //INVENTORY
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        canvas.drawBitmap(resizedStoreButton, Constants.screenWidth/8, (int)(Constants.screenHeight/2.05), paint);
        canvas.drawText("INVENTORY", (int) (Constants.screenWidth/3.8), (int) (Constants.screenHeight/2.7), paint2);
        canvas.drawText("INVENTORY", (int) (Constants.screenWidth/3.85), (int) (Constants.screenHeight/2.7), paint);
        //OPTIONS
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        canvas.drawBitmap(resizedStoreButton, Constants.screenWidth/8, (int)(Constants.screenHeight/1.69), paint);
        canvas.drawText("OPTIONS", (int) (Constants.screenWidth/3.8), (int) (Constants.screenHeight/2.7), paint2);
        canvas.drawText("OPTIONS", (int) (Constants.screenWidth/3.85), (int) (Constants.screenHeight/2.7), paint);
        //CREDITS
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        canvas.drawBitmap(resizedStoreButton, Constants.screenWidth/8, (int)(Constants.screenHeight/1.44), paint);
        canvas.drawText("CREDITS", (int) (Constants.screenWidth/3.8), (int) (Constants.screenHeight/2.7), paint2);
        canvas.drawText("CREDITS", (int) (Constants.screenWidth/3.85), (int) (Constants.screenHeight/2.7), paint);
        //EXIT
        paint.setTypeface(typeface);
        paint.setColor(Color.DKGRAY);
        paint2.setTypeface(typeface);
        paint2.setColor(Color.BLACK);
        canvas.drawBitmap(resizedStoreButton, Constants.screenWidth/8, (int)(Constants.screenHeight/1.25), paint);
        canvas.drawText("EXIT", (int) (Constants.screenWidth/3.8), (int) (Constants.screenHeight/2.7), paint2);
        canvas.drawText("EXIT", (int) (Constants.screenWidth/3.85), (int) (Constants.screenHeight/2.7), paint);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRGB(44, 42, 49);
        manager.draw(canvas);

        //PANEL

        drawPanel(canvas);

        drawName(canvas);

        drawButtons(canvas);

    }



    @Override
    public void terminate() {
        sceneManager.addScene(new GameplayScene());
        SceneManager.activeScene = 1;
    }

    @Override
    public void recieveTouch(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                terminate();
                break;
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
