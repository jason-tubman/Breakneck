package tech.jasontubman.breakneck.Triangles;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Region;


import tech.jasontubman.breakneck.Constants;

import tech.jasontubman.breakneck.Obstacle;
import tech.jasontubman.breakneck.Player;

/**
 * Created by Jason on 21/11/2016.
 */
public class RightTriangle implements Obstacle {

    private Point point1 = new Point(0, 0);
    private Point point2 = new Point(Constants.screenWidth/2, 200);
    private Point point3 = new Point(0, 400);
    private Path path = new Path();
    private int startX;
    private int startY;
    private int color;

    public RightTriangle(int color, int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        this.color = color;
        point1.x = startX + Constants.screenWidth - Constants.screenWidth/4;
        point1.y = startY;
        point2.x = startX;
        point2.y = startY + 200;
        point3.x = startX + Constants.screenWidth - Constants.screenWidth/4;
        point3.y = startY + 400;

    }


    public void moveObstacle(float y) {
        point1.y +=y;
        point2.y +=y;
        point3.y +=y;

    }

    public boolean playerCollided(Player player) {
        Region clip = new Region(startX, startY, Constants.screenWidth, Constants.screenHeight);
        Region region1 = new Region();
        region1.setPath(path, clip);

        if (!region1.quickReject(player.getRectangle()) && region1.op(player.getRectangle(), Region.Op.INTERSECT)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        path.reset();
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