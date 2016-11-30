package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Jason on 30/11/2016.
 */

public class MenuScene implements Scene {
    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRGB(44, 42, 49);
    }

    @Override
    public void terminate() {
        SceneManager.activeScene = 1;
    }

    @Override
    public void recieveTouch(MotionEvent event) {

    }
}
