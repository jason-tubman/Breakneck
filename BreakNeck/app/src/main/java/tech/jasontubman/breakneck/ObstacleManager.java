package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Color;

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
        int obstacleY = -6*Constants.screenHeight/4;
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
            this.speed = (float) (Math.sqrt(1.5 + (startTime - initTime) / 2000)) * player.getSpeed();
            for (Obstacle obstacle : obstacles) {
                obstacle.moveObstacle(speed * 20);
            }
            if (obstacles.get(obstacles.size() - 1).getTop() >= Constants.screenHeight) {
                int obstacleY = obstacles.get(0).getTop() - obstacleHeight - obstacleGap;
                int random = getRandomNumberInRange(1, 3);

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
                        obstacles.add(new Diamond(this.color, Constants.screenWidth / 2, obstacleY));
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

}
