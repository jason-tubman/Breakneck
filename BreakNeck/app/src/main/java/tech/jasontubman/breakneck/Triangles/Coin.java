package tech.jasontubman.breakneck.Triangles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import tech.jasontubman.breakneck.Constants;
import tech.jasontubman.breakneck.Obstacle;
import tech.jasontubman.breakneck.Player;
import tech.jasontubman.game.R;

/**
 * Created by Jason on 6/12/2016.
 */

public class Coin implements Obstacle {
    private Rect coinRect = new Rect();
    private int x;
    private int y;

    private Bitmap coin;
    private Bitmap resizedCoin;

    public Coin(int x, int y) {
        this.x = x;
        this.y = y;

        //coin = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.coin);
        //resizedCoin = (Bitmap.createScaledBitmap(coin, Constants.screenWidth/21, Constants.screenWidth/21, false));
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

        //canvas.drawBitmap(resizedCoin, (int) (x), y, paint);

    }

    @Override
    public void update() {

        coinRect.set(x, y, x + Constants.screenWidth/21, y + Constants.screenWidth/21);
    }

    @Override
    public int getTop() {
        return this.y;
    }
}
