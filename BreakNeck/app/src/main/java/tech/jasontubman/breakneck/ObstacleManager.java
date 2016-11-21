package tech.jasontubman.breakneck;

import android.graphics.Canvas;

import java.util.ArrayList;

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

    public ObstacleManager(int playerGap, int obstacleGap, int obstacleHeight, int color) {
        this.playerGap = playerGap;
        this.obstacleGap = obstacleGap;
        this.obstacleHeight = obstacleHeight;
        this.color = color;


        startTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();

        createObstacles();

    }

    private void createObstacles() {
        int obstacleY = -5*Constants.screenHeight/4;
        while (obstacleY < 0) {
            int xStart = (int)(Math.random() * (Constants.screenWidth - playerGap));
            obstacles.add(new Obstacle(obstacleHeight, color, xStart, obstacleY, playerGap));
            obstacleY += obstacleHeight + obstacleGap;
        }

    }
    public void update(){
        int elapsedTime = (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        this.speed = Constants.screenHeight/10000.0f;
        for(Obstacle obstacle : obstacles) {
            obstacle.moveObstacle(speed*elapsedTime);
        }
        if (obstacles.get(obstacles.size() -1).getObstacle().top >= Constants.screenHeight) {
            int xStart = (int)(Math.random() * (Constants.screenWidth - playerGap));
            obstacles.add(0, new Obstacle(obstacleHeight, color, xStart, obstacles.get(0).getObstacle().top - obstacleHeight - obstacleGap, playerGap));
            obstacles.remove(obstacles.size() - 1);
        }

    }

    public void draw(Canvas canvas) {
        for (Obstacle obstacle: obstacles){
            obstacle.draw(canvas);
        }
    }
}
