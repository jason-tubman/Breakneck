package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Jason on 21/11/2016.
 */

public class Obstacle implements Entity {


    private Rect rectangle;
    private int color;
    private Rect rectangle2;

    public Obstacle(int rectangleHeight, int color, int startX, int startY, int playerGap) {
        this.color = color;
        rectangle = new Rect(0, startY, startX, startY + rectangleHeight);
        rectangle2 = new Rect(startX+playerGap, startY, Constants.screenWidth, startY + rectangleHeight);
    }

    public Rect getObstacle() {
        return rectangle;
    }

    public void moveObstacle(float y) {
        rectangle.top +=y;
        rectangle.bottom +=y;
        rectangle2.top+=y;
        rectangle2.bottom+=y;
    }

    public boolean playerCollided(Player player) {
        if (rectangle.contains(player.getRectangle().left, player.getRectangle().top)
                || rectangle.contains(player.getRectangle().right, player.getRectangle().top)
                || rectangle.contains(player.getRectangle().left, player.getRectangle().bottom)
                || rectangle.contains(player.getRectangle().right, player.getRectangle().bottom)) {
            return true;
        }
        else return false;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rectangle2, paint);
    }

    @Override
    public void update() {

    }

}
