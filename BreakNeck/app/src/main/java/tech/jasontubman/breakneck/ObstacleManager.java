package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Random;

import tech.jasontubman.breakneck.Triangles.Diamond;
import tech.jasontubman.breakneck.Triangles.LeftTriangle;
import tech.jasontubman.breakneck.Triangles.RightTriangle;

/**
 * Created by Jason on 21/11/2016.
 */

public class ObstacleManager {
    private ArrayList<Obstacle> obstacles;
    private int playerGap;
    private int obstacleGap;
    private int obstacleHeight;
    private int color;
    private float speed;
    private long startTime;
    private long initTime;
    private Player player;
    private int elapsedTime;
    private boolean countDown = true;
    private Rect r = new Rect();

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color, Player player) {
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;
        this.player = player;

        startTime = initTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();

    }

    public boolean playerCollided(Player player) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.playerCollided(player)){
                return true;
            }
        }
        return false;
    }

    private void createObstacles() {
        int obstacleY = -5*Constants.screenHeight/4;
        while (obstacleY < 0) {
            int random = getRandomNumberInRange(1, 3);
            switch (random) {

                case 1 : {
                    obstacles.add(new LeftTriangle(this.color, 0, obstacleY));
                    obstacleY += obstacleHeight + obstacleGap;
                    break;
                }
                case 2 : {
                    obstacles.add(new RightTriangle(this.color, Constants.screenWidth/4, obstacleY));
                    obstacleY += obstacleHeight + obstacleGap;
                    break;
                }
                case 3 : {
                    obstacles.add(new Diamond(this.color, Constants.screenWidth/2, obstacleY));
                    obstacleY += obstacleHeight + obstacleGap;
                    break;
                }
            }
        }
    }
    public void update(){
        elapsedTime += (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();


        if (elapsedTime > 4000) {
            if (countDown) {
                createObstacles();
                this.countDown = false;
            }
            this.speed = (float) (Math.sqrt(1.25 + (startTime - initTime) / 1500)) * player.getSpeed();
            for (Obstacle obstacle : obstacles) {
                obstacle.moveObstacle(speed * 20);
                obstacle.update();
            }
            if (obstacles.get(obstacles.size() - 1).getTop() >= Constants.screenHeight) {
                int obstacleY = obstacles.get(0).getTop() - obstacleHeight - obstacleGap;
                int random = getRandomNumberInRange(1, 5);
                switch (random) {
                    case 1: {
                        obstacles.add(0, new LeftTriangle(this.color, 0, obstacleY));
                        break;
                    }
                    case 2: {
                        obstacles.add(0, new RightTriangle(this.color, Constants.screenWidth / 4, obstacleY));
                        break;
                    }
                    case 3: {
                        obstacles.add(0, new Diamond(this.color, Constants.screenWidth / 2, obstacleY));
                        break;
                    }
                    case 4: {
                        obstacles.add(0, new LeftTriangle(this.color, 0, obstacleY));
                        break;
                    }
                    case 5: {
                        obstacles.add(0, new RightTriangle(this.color, Constants.screenWidth / 4, obstacleY));
                        break;
                    }
                }
                obstacles.remove(obstacles.size() - 1);

            }
        }
    }

    public void draw(Canvas canvas) {
        for (Obstacle obstacle: obstacles){
            obstacle.draw(canvas);
        }
        Paint paint = new Paint();
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");
        paint.setTypeface(typeface);
        paint.setTextSize(900 / Constants.currentContext.getResources().getDisplayMetrics().density);
        paint.setColor(Color.LTGRAY);
        if (elapsedTime < 1000) {
            centreText(canvas, paint, "3");
        } else if (elapsedTime < 2000 && elapsedTime >= 1000) {
            centreText(canvas, paint, "2");
        } else if (elapsedTime < 3000 && elapsedTime >= 2000) {
            centreText(canvas, paint, "1");
        } else if (elapsedTime < 4000 && elapsedTime >= 3000){
            centreText(canvas, paint, "SURGE");
        } else {

        }

    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean getcountDown(){
        return this.countDown;
    }

    private void centreText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f - r.height() / 2f - r.top;
        canvas.drawText(text, x, y, paint);
    }


}
