package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;


/**
 * Created by Jason on 30/11/2016.
 */

public class MenuScene implements Scene {

    private StarManager manager;
    private Rect r = new Rect();

    public MenuScene() {

        manager = new StarManager(7, true);

    }

    @Override
    public void update() {

        manager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRGB(44, 42, 49);
        manager.draw(canvas);
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");

        Paint paint2 = new Paint();
        paint2.setTextSize(100);
        paint2.setColor(Color.BLACK);
        paint2.setTypeface(typeface);
        centreText2(canvas, paint2, "Break Neck");

        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.WHITE);

        paint.setTypeface(typeface);

        centreText(canvas, paint, "Break Neck");

    }



    @Override
    public void terminate() {
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
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) / 2;
        canvas.drawText(text, x, y, paint);
    }

    private void centreText2(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left - 15;
        float y = (cHeight / 2f + r.height() / 2f - r.bottom) / 2.05f;
        canvas.drawText(text, x, y, paint);
    }

}
