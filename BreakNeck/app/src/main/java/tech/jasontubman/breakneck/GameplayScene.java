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

    private Player player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private int score;
    private Rect r = new Rect();

    private boolean movingPlayer = false;
    private boolean gameOver = false;
    private long timeEnded;

    public GameplayScene() {
        player = new Player(new Rect(100, 100, 200, 200), Color.YELLOW);
        playerPoint = new Point(Constants.screenWidth/2, Constants.screenHeight/4);
        player.update(playerPoint);

        obstacleManager = new ObstacleManager(200, 650, 400, Color.BLACK);

    }

    public void reset() {
        playerPoint = new Point(Constants.screenWidth/2, Constants.screenHeight/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(200, 650, 400, Color.BLACK);
        movingPlayer = false;
    }

    @Override
    public void update() {
        if (!gameOver) {
            addToScore(1);
            player.update(playerPoint);
            obstacleManager.update();
        }
        if (obstacleManager.playerCollided(player)) {
            gameOver = true;
            timeEnded = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        Paint score = new Paint();
        score.setTextSize(50);
        score.setColor(Color.RED);
        drawScore(canvas, score, Integer.toString(this.score));

        player.draw(canvas);
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
            case MotionEvent.ACTION_DOWN:
            {
                if (!gameOver && player.getRectangle().contains((int) event.getX(), (int) event.getY())) {
                    movingPlayer = true;
                }
                if (gameOver && (System.currentTimeMillis() - timeEnded) >= 2000) {
                    reset();
                    gameOver = false;
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (movingPlayer && !gameOver) {
                    playerPoint.set((int) event.getX(), (int) event.getY());
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                movingPlayer = false;
                break;
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
