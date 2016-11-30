package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import android.view.MotionEvent;

import java.util.Timer;

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
    private boolean split;
    private boolean gameOver = false;
    private long timeEnded;

    private ShipSelector selector;


    public GameplayScene() {
        selector = new ShipSelector(18, 1);
        player = new Player(new Rect(100, 100, 235, 235), selector.getSprite(), selector.getSpeed());
        playerPoint = new Point(Constants.screenWidth/2, Constants.screenHeight-Constants.screenHeight/4);
        player.update(playerPoint);

        obstacleManager = new ObstacleManager(200, 650, 400, Color.LTGRAY, player);

        starManager = new StarManager(player.getSpeed());

        player2 = new Player(new Rect(100, 100, 235, 235), selector.getSprite(), selector.getSpeed());
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
        this.score = 0;
        numPoints  = 0;

    }
    @Override
    public void update() {
        if (!gameOver) {

            player.update(playerPoint);
            player2.update(playerPoint2);

            obstacleManager.update();

            if (!obstacleManager.getcountDown()) {
                addToScore(1);
            }

            starManager.update();

            //RESETING PLAYERPOINT 1
            if (numPoints == 0 && playerPoint.x != Constants.screenWidth/2) {
                resetPoint();
            }

            //MOVING PLAYER 1
            if (numPoints > 0 && !gameOver) {
                if (eventX < Constants.screenWidth/2) {
                    if (!(playerPoint.x < player.getRectangle().width())) {
                        if (obstacleManager.getSpeed() > 0) {
                            playerPoint.set((int) (playerPoint.x - 70 * obstacleManager.getSpeed()), playerPoint.y);
                        } else {
                                playerPoint.set(playerPoint.x - 70, playerPoint.y);
                        }
                    } else {
                        if (!split) {
                            playerPoint.set(100, playerPoint.y);
                        }
                    }
                } else if (eventX > Constants.screenWidth/2) {
                    if (!(playerPoint.x > Constants.screenWidth - player.getRectangle().width())) {
                        if (obstacleManager.getSpeed() > 0) {
                            playerPoint.set((int) (playerPoint.x + 70 * obstacleManager.getSpeed()), playerPoint.y);
                        } else {playerPoint.set(playerPoint.x + 70, playerPoint.y);}
                    }
                    else {
                        playerPoint.set(Constants.screenWidth - 100, playerPoint.y);
                    }
                }
            }
            //IF MULTI TOUCH SPLIT / RESET AT END
            if(!gameOver && numPoints >1) {
                player2.setVisible(true);
                if (!split) {
                    split = true;
                    selector.selectSprite(18, 0.5);
                    player.halfRect(player.getX() - player.getWidth()/2, player.getY() + player.getHeight()/2);
                    player2.halfRect(player2.getX() - player2.getWidth()/2, player2.getY() + player2.getHeight()/2);
                    player.updateSprite(selector.getSprite());
                    player2.updateSprite(selector.getSprite());
                }
                split();

            } else if (!gameOver && numPoints <= 1){
                resetPointTwo();
                split = false;
                player.resetRect(player.getX() - player.getWidth()/2, player.getY());
                player2.resetRect(player2.getX() - player2.getWidth()/2, player2.getY());
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


        starManager.draw(canvas);
        obstacleManager.draw(canvas);

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
            if (obstacleManager.getSpeed() > 0) {
                playerPoint.set((int) (playerPoint.x - 70 * obstacleManager.getSpeed()), playerPoint.y);
            } else {
                playerPoint.set(playerPoint.x - 70, playerPoint.y);
            }
            if (playerPoint.x <= (Constants.screenWidth / 2)) {
                playerPoint.set(Constants.screenWidth/2, playerPoint.y);
                selector.selectSprite(18, 1);
                player.updateSprite(selector.getSprite());
                player2.updateSprite(selector.getSprite());
            }
        } else if (playerPoint.x < (Constants.screenWidth / 2)) {
            if (obstacleManager.getSpeed() > 0) {
                playerPoint.set((int) (playerPoint.x + 70 * obstacleManager.getSpeed()), playerPoint.y);
            } else {
                playerPoint.set(playerPoint.x + 70, playerPoint.y);
            }
            if (playerPoint.x >= (Constants.screenWidth / 2)) {
                playerPoint.set(Constants.screenWidth/2, playerPoint.y);
                selector.selectSprite(18, 1);
                player.updateSprite(selector.getSprite());
                player2.updateSprite(selector.getSprite());
            }
        }
        eventX = 0;
        numPoints = 0;
    }
    private void resetPointTwo() {
        if (playerPoint2.x > playerPoint.x) {
            if (obstacleManager.getSpeed() > 0) {
                playerPoint2.set((int) (playerPoint2.x - 70 * obstacleManager.getSpeed()), playerPoint2.y);
            } else {
                playerPoint2.set(playerPoint2.x - 70, playerPoint2.y);
            }
            if (playerPoint2.x <= playerPoint.x) {
                playerPoint2.set(playerPoint.x, playerPoint2.y);
                selector.selectSprite(18, 1);
                player.updateSprite(selector.getSprite());
                player2.updateSprite(selector.getSprite());
            }
        } else if (playerPoint2.x < playerPoint.x) {
            if (obstacleManager.getSpeed() > 0) {
                playerPoint2.set((int) (playerPoint2.x + 70 * obstacleManager.getSpeed()), playerPoint2.y);
            } else {
                playerPoint2.set(playerPoint2.x + 70, playerPoint2.y);
            }
            if (playerPoint2.x >= playerPoint.x) {
                playerPoint2.set(playerPoint.x, playerPoint2.y);
                selector.selectSprite(18, 1);
                player.updateSprite(selector.getSprite());
                player2.updateSprite(selector.getSprite());
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
            this.score += score;
        }
    }

    private void split() {
        if (eventX < Constants.screenWidth/2) {
            if (!(playerPoint2.x > Constants.screenWidth - player.getRectangle().width())) {
                playerPoint2.set(playerPoint2.x+70, playerPoint2.y);
                playerPoint.set(player.getRectangle().width() - 40, playerPoint.y);
            } else {
                playerPoint2.set(Constants.screenWidth - 60, playerPoint2.y);
                playerPoint.set(player.getRectangle().width() - 40, playerPoint.y);
            }
        } else if (eventX > Constants.screenWidth/2) {
            if (!(playerPoint2.x < player.getRectangle().width())) {
                playerPoint2.set(playerPoint2.x-41, playerPoint2.y);
                playerPoint.set(Constants.screenWidth - 60, playerPoint.y);
            } else {
                playerPoint2.set(player.getRectangle().width() - 40, playerPoint2.y);
                playerPoint.set(Constants.screenWidth - 60, playerPoint.y);
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
