package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Jason on 21/11/2016.
 */

public interface Obstacle {
    public void moveObstacle(float y);
    public boolean playerCollided(Player player);
    public void draw(Canvas canvas);
    public void update();
    public int getTop();

}
