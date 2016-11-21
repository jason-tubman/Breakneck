package tech.jasontubman.breakneck.Triangles;

import android.graphics.Canvas;

import tech.jasontubman.breakneck.Constants;
import tech.jasontubman.breakneck.Obstacle;
import tech.jasontubman.breakneck.Player;

/**
 * Created by Jason on 21/11/2016.
 */

public class Diamond implements Obstacle {
    private int color;

    public Diamond(int color, int startX, int startY, double offset) {
        this.color = color;

    }


    @Override
    public void moveObstacle(float y) {

    }

    @Override
    public boolean playerCollided(Player player) {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }

    @Override
    public int getTop() {
        return 0;
    }
}
