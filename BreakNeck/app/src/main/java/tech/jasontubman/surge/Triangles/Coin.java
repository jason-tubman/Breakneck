package tech.jasontubman.surge.Triangles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import tech.jasontubman.surge.Assets;
import tech.jasontubman.surge.Constants;
import tech.jasontubman.surge.Obstacle;
import tech.jasontubman.surge.Player;

/**
 * Created by Jason on 6/12/2016.
 */

public class Coin implements Obstacle {
    private Rect coinRect = new Rect();
    private int x;
    private int y;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;


    }


    @Override
    public void moveObstacle(float y) {
        this.y += y;
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

        canvas.drawBitmap(Assets.resizedCoin, x, y, paint);

    }

    @Override
    public void update() {

        coinRect.set(x, y, x + Constants.screenWidth/21, y);
    }

    @Override
    public int getTop() {
        return this.y;
    }
}
