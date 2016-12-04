package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

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

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRGB(44, 42, 49);
        manager.draw(canvas);

        //PANEL

        drawPanel(canvas);

        drawName(canvas);

        drawButtons();




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
