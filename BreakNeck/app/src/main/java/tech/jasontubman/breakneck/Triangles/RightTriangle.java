package tech.jasontubman.breakneck.Triangles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;


import tech.jasontubman.breakneck.Constants;

import tech.jasontubman.breakneck.Obstacle;
import tech.jasontubman.breakneck.Player;

/**
 * Created by Jason on 21/11/2016.
 */

public class RightTriangle implements Obstacle {
    private Path path = new Path();
    Point point1 = new Point(Constants.screenWidth, 0);
    Point point2 = new Point(Constants.screenWidth/2, 200);
    Point point3 = new Point(Constants.screenWidth, 400);
    private int color;

    public RightTriangle(int color, int startX, int startY, double offset) {
        this.color = color;
        point1.x = Constants.screenWidth;
        point1.y = startY;
        point2.x = startX - Constants.screenWidth/2;
        point2.y = startY + 200;
        point3.x = Constants.screenWidth;
        point3.y = startY + 400;

    }

    public void moveObstacle(float y) {
        point1.y +=y;
        point2.y +=y;
        point3.y +=y;

    }

    public boolean playerCollided(Player player) {
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        canvas.drawPath(path, paint);
    }

    @Override
    public void update() {

    }

    @Override
    public int getTop() {
        return point1.y;
    }

}
