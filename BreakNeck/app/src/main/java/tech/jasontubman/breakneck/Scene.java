package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Jason on 21/11/2016.
 */

public interface Scene {

    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void recieveTouch(MotionEvent event);
}
