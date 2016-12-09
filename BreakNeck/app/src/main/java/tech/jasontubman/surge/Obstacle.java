package tech.jasontubman.surge;

import android.graphics.Canvas;

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
