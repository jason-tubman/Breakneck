package tech.jasontubman.breakneck;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
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
    private Bitmap sprite;
    private boolean visible = true;
    private float speed;

    public Player(Rect rectangle, Bitmap sprite, double speed) {
        this.rectangle = rectangle;
        this.sprite = sprite;
        this.speed = Constants.screenHeight/5000.0f * (float) speed;
    }

    public Rect getRectangle() {
        return this.rectangle;
    }

    @Override
    public void draw(Canvas canvas) {
        if (visible == true) {
            Paint paint = new Paint();
            paint.setColor(Color.BLUE);
            int x = rectangle.centerX() - (rectangle.width()/2);
            int y = rectangle.centerY() - (rectangle.height()/2);
            canvas.drawRect(rectangle, paint);
            canvas.drawBitmap(sprite, x, y, paint);
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
    public void updateSprite(Bitmap sprite) {
        this.sprite = sprite;
    }

    public void halfRect(int x, int y) {
        this.rectangle.set(x, y, x+100, y+100);
    }
    public void resetRect(int x, int y) {
        this.rectangle.set(x, y, x+205, y+205);
    }
}
