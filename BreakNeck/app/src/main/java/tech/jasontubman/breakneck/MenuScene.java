package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Jason on 30/11/2016.
 */

public class MenuScene implements Scene {

    private StarManager manager;

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
    }

    @Override
    public void terminate() {
        SceneManager.activeScene = 1;
    }

    @Override
    public void recieveTouch(MotionEvent event) {

    }
}
