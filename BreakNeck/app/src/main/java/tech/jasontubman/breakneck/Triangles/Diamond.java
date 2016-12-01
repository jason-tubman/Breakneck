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

public class Diamond implements Obstacle {
    private Point point1 = new Point(Constants.screenWidth/2, 0);
    private Point point2 = new Point(Constants.screenWidth/5, 200);
    private Point point3 = new Point(Constants.screenWidth, 400);
    private Point point4 = new Point(Constants.screenWidth/2, 0);
    private Point point5 = new Point(Constants.screenWidth/5, 200);
    private Point point6 = new Point(Constants.screenWidth, 400);
    private Path path = new Path();
    private Path path2 = new Path();
    private int startX;
    private int startY;
    private int color;

    public Diamond(int color, int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
        this.color = color;
        point1.x = startX;
        point1.y = startY;
        point2.x = startX - (int) (Constants.screenWidth/2.5);
        point2.y = startY + 200;
        point3.x = startX;
        point3.y = startY + 400;

        point4.x = startX;
        point4.y = startY;
        point5.x = startX + (int) (Constants.screenWidth/2.5);
        point5.y = startY + 200;
        point6.x = startX;
        point6.y = startY + 400;
    }


    public void moveObstacle(float y) {
        point1.y += y;
        point2.y += y;
        point3.y += y;
        point4.y += y;
        point5.y += y;
        point6.y += y;

    }

    public boolean playerCollided(Player player) {
        Region clip = new Region(startX - (int) (Constants.screenWidth/2.5), startY, Constants.screenWidth, Constants.screenHeight);

        Region region1 = new Region();
        region1.setPath(path, clip);

        Region region2 = new Region();
        region2.setPath(path2, clip);

        if (!region1.quickReject(player.getRectangle()) && region1.op(player.getRectangle(), Region.Op.INTERSECT)){
            return true;
        }
        if (!region2.quickReject(player.getRectangle()) && region2.op(player.getRectangle(), Region.Op.INTERSECT)) {
            return true;
        }

        return false;

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
        path2.reset();
        path2.moveTo(point4.x, point4.y);
        path2.lineTo(point5.x, point5.y);
        path2.lineTo(point6.x, point6.y);
        canvas.drawPath(path2, paint);
    }

    @Override
    public void update() {

    }

    @Override
    public int getTop() {
        return point1.y;
    }
}
