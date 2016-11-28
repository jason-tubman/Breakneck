package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import android.view.MotionEvent;

/**
 * Created by Jason on 21/11/2016.
 */

public class GameplayScene implements Scene {
    private float eventX =0;
    private int numPoints = 0;
    private Player player;
    private Point playerPoint;
    private Player player2;
    private Point playerPoint2;
    private ObstacleManager obstacleManager;
    private int score;
    private Rect r = new Rect();

    private StarManager starManager;

    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private long timeEnded;

    public GameplayScene() {
        player = new Player(new Rect(100, 100, 200, 200), Color.YELLOW);
        playerPoint = new Point(Constants.screenWidth/2, Constants.screenHeight-Constants.screenHeight/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(200, 650, 400, Color.LTGRAY, player);

        starManager = new StarManager(player.getSpeed());



        player2 = new Player(new Rect(100, 100, 200, 200), Color.RED);
        playerPoint2 = new Point(Constants.screenWidth/2, Constants.screenHeight-Constants.screenHeight/4);
        player2.update(playerPoint2);
        player2.setVisible(false);
    }

    public void reset() {
        playerPoint = new Point(Constants.screenWidth/2, Constants.screenHeight-Constants.screenHeight/4);
        player.update(playerPoint);

        playerPoint2 = new Point(Constants.screenWidth/2, Constants.screenHeight-Constants.screenHeight/4);
        player2.update(playerPoint2);
        player2.setVisible(false);

        obstacleManager = new ObstacleManager(200, 650, 400, Color.LTGRAY, player);
        movingPlayer = false;
        this.score = 0;
        numPoints  = 0;
    }
    @Override
    public void update() {
        if (!gameOver) {
            addToScore(1);
            player.update(playerPoint);
            player2.update(playerPoint2);
            obstacleManager.update();
            starManager.update();
            //RESETING PLAYERPOINT 1
            if (numPoints == 0 && playerPoint.x != Constants.screenWidth/2) {
                resetPoint();
            }

            //MOVING PLAYER 1
            if (numPoints > 0 && !gameOver) {
                if (eventX < Constants.screenWidth/2) {
                    if (!(playerPoint.x < player.getRectangle().width())) {
                        playerPoint.set(playerPoint.x-70, playerPoint.y);
                    }
                } else if (eventX > Constants.screenWidth/2) {
                    if (!(playerPoint.x > Constants.screenWidth - player.getRectangle().width())) {
                        playerPoint.set(playerPoint.x+70, playerPoint.y);
                    }
                }
            }
            //IF MULTI TOUCH SPLIT / RESET AT END
            if(!gameOver && numPoints >1) {
                player2.setVisible(true);
                split();
            } else if (!gameOver && numPoints <= 1){
                resetPointTwo();
                if (playerPoint2.x == playerPoint.x) {
                    player2.setVisible(false);
                }
            }

        }
        if (obstacleManager.playerCollided(player) && !gameOver || obstacleManager.playerCollided(player2) && !gameOver) {
            gameOver = true;
            timeEnded = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRGB(44, 42, 49);





        Paint score = new Paint();
        score.setTextSize(50);
        score.setColor(Color.WHITE);
        drawScore(canvas, score, Integer.toString(this.score));

        player.draw(canvas);
        player2.draw(canvas);

        obstacleManager.draw(canvas);
        starManager.draw(canvas);

        if (gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.RED);
            centreText(canvas, paint, "GAME OVER");
        }
    }

    @Override
    public void terminate() {
        SceneManager.activeScene = 0;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if (!gameOver) {
                    numPoints = event.getPointerCount();
                    eventX = event.getX(0);
                }
                if (gameOver && (System.currentTimeMillis() - timeEnded) >= 2000) {
                    reset();
                    gameOver = false;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (!gameOver) {
                    numPoints = event.getPointerCount();
                    eventX = event.getX(0);
                }
                if (gameOver && (System.currentTimeMillis() - timeEnded) >= 2000) {
                    reset();
                    gameOver = false;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                numPoints = event.getPointerCount() - 1;
                break;
            }
        }

    }
    private void resetPoint() {
        if (playerPoint.x > (Constants.screenWidth / 2)) {
            playerPoint.set(playerPoint.x - 70, playerPoint.y);
            if (playerPoint.x < (Constants.screenWidth / 2)) {
                playerPoint.set(Constants.screenWidth/2, playerPoint.y);
            }
        } else if (playerPoint.x < (Constants.screenWidth / 2)) {
            playerPoint.set(playerPoint.x + 70, playerPoint.y);
            if (playerPoint.x > (Constants.screenWidth / 2)) {
                playerPoint.set(Constants.screenWidth/2, playerPoint.y);
            }
        }
        eventX = 0;
        numPoints = 0;
    }
    private void resetPointTwo() {
        if (playerPoint2.x > playerPoint.x) {
            playerPoint2.set(playerPoint2.x - 70, playerPoint2.y);
            if (playerPoint2.x < playerPoint.x) {
                playerPoint2.set(playerPoint.x, playerPoint2.y);
            }
        } else if (playerPoint2.x < playerPoint.x) {
            playerPoint2.set(playerPoint2.x + 70, playerPoint2.y);
            if (playerPoint2.x > playerPoint.x) {
                playerPoint2.set(playerPoint.x, playerPoint2.y);
            }
        }
    }

    private void drawScore(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 1.1f - r.width() / 2f - r.left;
        float y = 80;
        canvas.drawText(text, x, y, paint);
    }

    public void addToScore(int score){
        if (!gameOver) {
            this.score += score*player.getScoreMulti();
        }
    }

    private void split() {
        if (eventX < Constants.screenWidth/2) {
            if (!(playerPoint2.x > Constants.screenWidth - player.getRectangle().width())) {
                playerPoint2.set(playerPoint2.x+70, playerPoint2.y);
            }
        } else if (eventX > Constants.screenWidth/2) {
            if (!(playerPoint2.x < player.getRectangle().width())) {
                playerPoint2.set(playerPoint2.x-70, playerPoint2.y);
            }
        }
    }

    private void centreText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }



}
