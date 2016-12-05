package tech.jasontubman.breakneck.Triangles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import tech.jasontubman.breakneck.Obstacle;
import tech.jasontubman.breakneck.Player;

/**
 * Created by Jason on 6/12/2016.
 */

public class Coin implements Obstacle {
    private Rect coinRect = new Rect();
    private int x;
    private int y;
    private Coin(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void moveObstacle(float y) {
        this.y++;
    }

    @Override
    public boolean playerCollided(Player player) {
        if (coinRect.intersect(player.getRectangle())) {
            return true;
        } else {
            return  false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawRect(coinRect, paint);
    }

    @Override
    public void update() {
        coinRect.set(x, y, x + 20, y + 20);
    }

    @Override
    public int getTop() {
        return this.y;
    }
}
