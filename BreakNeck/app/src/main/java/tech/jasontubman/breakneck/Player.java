package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Jason on 21/11/2016.
 */

public class Player implements Entity{
    private int x;
    private int y;
    private Rect rectangle;
    private int color;
    private boolean visible = true;

    private float speed;
    private float scoreMulti = 1;

    public Player(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
        this.speed = Constants.screenHeight/5000.0f;
    }

    public Rect getRectangle() {
        return this.rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        if (visible == true) {
            Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawRect(rectangle, paint);
        }
    }

    @Override
    public void update() {

    }

    public void update(Point point) {
        this.x = point.x;
        this.y = point.y;
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2,
                point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getHeight(){
        return rectangle.height();
    }
    public int getWidth() {
        return rectangle.width();
    }

    public void setSpeed(double speed) {
        this.speed += speed;
    }

    public float getSpeed() {
        return this.speed;
    }
    public float getScoreMulti() {
        return this.scoreMulti;
    }

}
