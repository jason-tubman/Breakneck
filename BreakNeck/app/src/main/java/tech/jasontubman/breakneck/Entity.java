package tech.jasontubman.breakneck;

import android.graphics.Canvas;

/**
 * Created by Jason on 21/11/2016.
 */

public interface Entity {
    public void draw(Canvas canvas);
    public void update();
}
