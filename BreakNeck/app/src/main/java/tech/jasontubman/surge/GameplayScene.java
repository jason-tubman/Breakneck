package tech.jasontubman.surge;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import android.graphics.RectF;
import android.graphics.Typeface;
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
    private int justReset = 1;

    private boolean explosionOnce = false;

    private int coins = 0;

    //GAME OVER PAINTS
    Paint paint3 = new Paint();
    private int opacity = 0;
    private int opacity2 = 0;
    Paint paint4 = new Paint();
    //

    private SceneManager sceneManager;

    private StarManager starManager;
    private boolean split;
    private boolean gameOver = false;
    private long timeEnded;

    private ShipSelector selector;

    private ParticleGenerator particleGenerator1;
    private ParticleGenerator particleGenerator2;

    private Explosion explosion = new Explosion();

    private Boolean justHit = false;

    public GameplayScene(SceneManager manager) {
        this.sceneManager = manager;
        selector = new ShipSelector(this.sceneManager.shipChosen + 1, 1);
        player = new Player(new Rect(100, 100, 235, 235), selector.getSprite(), selector.getSpeed());
        playerPoint = new Point(Constants.screenWidth/2, Constants.screenHeight-Constants.screenHeight/4);
        player.update(playerPoint);

        obstacleManager = new ObstacleManager(200, 950, 400, Color.LTGRAY, player);

        coins = obstacleManager.getCoins();
        starManager = new StarManager(player.getSpeed(), false);

        particleGenerator1 = new ParticleGenerator();
        particleGenerator2 = new ParticleGenerator();

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
        player.setVisible(true);
        obstacleManager = new ObstacleManager(200, 950, 400, Color.LTGRAY, player);
        this.score = 0;
        numPoints  = 0;

        opacity = 0;
        opacity2 = 0;

    }
    @Override
    public void update() {
        if (!gameOver) {
            explosion.update();
            if (player.getShieldStatus() && !player2.getShieldStatus()) {
                player2.setShieldStatus(true);
            }
            if (!player.getShieldStatus() && player2.getShieldStatus()) {
                player2.setShieldStatus(false);
            }
            if (player.getSpeedStatus() && !player2.getShieldStatus()) {
                player2.setShieldStatus(true);
            }
            if (!player.getSpeedStatus() && player2.getShieldStatus()) {
                player2.setShieldStatus(false);
            }
            coins = obstacleManager.getCoins();
            if (player2.isVisible()) {
                particleGenerator2.addParticle(player2.getX(), player2.getY() + player2.getHeight()-50, 0, true);
                particleGenerator2.update();
                particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, 0, true);
                particleGenerator1.update();
            } else {
                particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, 0, false);
                particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, 0, false);
                particleGenerator1.update();
            }


            obstacleManager.update();
            player.update(playerPoint);
            player2.update(playerPoint2);



            if (!obstacleManager.getcountDown()) {
                addToScore(100);
                if (player.getSpeedStatus()) {
                    addToScore(200);
                }
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
                            particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, -5, false);
                            particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, -5, false);
                        } else {
                                playerPoint.set(playerPoint.x - 70, playerPoint.y);
                                particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, -5, false);
                                particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, -5, false);
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
                            particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, 5, false);
                            particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, 5, false);
                        } else {
                            playerPoint.set(playerPoint.x + 70, playerPoint.y);
                            particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, 5, false);
                            particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, 5, false);
                        }
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
                    selector.selectSprite(this.sceneManager.shipChosen + 1, 0.5);
                    split = true;
                    player.halfRect(player.getX() - player.getWidth()/2, player.getY());
                    player2.halfRect(player2.getX() - player2.getWidth()/2, player2.getY());
                    player.setShieldSize(true);
                    player2.setShieldSize(true);
                    playerPoint.y += player.getHeight();
                    playerPoint2.y += player2.getHeight();
                    player.updateSprite(selector.getSprite());
                    player2.updateSprite(selector.getSprite());
                }
                split();

            } else if (!gameOver && numPoints <= 1){
                resetPointTwo();
                if (!split) {

                    player.resetRect(player.getX() - player.getWidth() / 2, player.getY());
                    player2.resetRect(player2.getX() - player2.getWidth() / 2, player2.getY());
                }
                if (playerPoint2.x == playerPoint.x) {
                    player2.setVisible(false);
                    justReset =1;
                    player.setShieldSize(false);
                    player2.setShieldSize(false);
                }
            }

        }

        if (obstacleManager.playerCollided(player) && !gameOver || obstacleManager.playerCollided(player2) && !gameOver && !player.getSpeedStatus() && !player2.getShieldStatus()) {
            if (player.getShieldStatus() && player.getSpeedStatus() == false && player2.getSpeedStatus() == false) {
                justHit = true;
                explosionOnce = true;
            } else if (player.getSpeedStatus() == false) {
                gameOver = true;
                explosion.addParticle(playerPoint.x, playerPoint.y+40, 3, false);
                explosion.addParticle(playerPoint2.x, playerPoint2.y+40, 3, false);
                player.setVisible(false);
                player2.setVisible(false);
                timeEnded = System.currentTimeMillis();
            }

        } else {
            if (justHit && explosionOnce) {
                explosion.addParticle(playerPoint.x, playerPoint.y+40, 3, false);
                explosion.addParticle(playerPoint2.x, playerPoint2.y+40, 3, false);
                player.setShieldStatus(false);
                player2.setShieldStatus(false);
                explosionOnce = false;
            }
        }
    }

    public void fades() {
            if (opacity < 255) {
                opacity+=15;
            }
            if (opacity2 < 210) {
                opacity2+=15;
            }
    }

    public void saveScores(int score) {
        SharedPreferences prefs = Constants.currentContext.getSharedPreferences("gameData", Context.MODE_PRIVATE);

        int score1 = prefs.getInt("save1", 0); //0 is the default value
        int score2 = prefs.getInt("save2", 0); //0 is the default value
        int score3 = prefs.getInt("save3", 0); //0 is the default value
        int score4 = prefs.getInt("save4", 0); //0 is the default value
        int score5 = prefs.getInt("save5", 0); //0 is the default value
        int score6 = prefs.getInt("save6", 0); //0 is the default value
        int score7 = prefs.getInt("save7", 0); //0 is the default value
        int score8 = prefs.getInt("save8", 0); //0 is the default value
        int score9 = prefs.getInt("save9", 0); //0 is the default value
        int score10 = prefs.getInt("save10", 0); //0 is the default value

        SharedPreferences.Editor editor = prefs.edit();

        if (score > score1) {
            for (int i = 2; i < 11; i++) {
                editor.putInt("save" + i, prefs.getInt("save" + (i - 1), 0));
            }
            editor.putInt("save1", score);
            editor.commit();
        } else if (score > score2 && score < score1) {
            for (int i = 3; i < 11; i++) {
                editor.putInt("save" + i, prefs.getInt("save" + (i - 1), 0));
            }
            editor.putInt("save2", score);
            editor.commit();
        }  else if (score > score3 && score < score2) {
            for (int i = 4; i < 11; i++) {
                editor.putInt("save" + i, prefs.getInt("save" + (i - 1), 0));
            }
            editor.putInt("save3", score);
            editor.commit();
        } else if (score > score4 && score < score3) {
            for (int i = 5; i < 11; i++) {
                editor.putInt("save" + i, prefs.getInt("save" + (i - 1), 0));
            }
            editor.putInt("save4", score);
            editor.commit();
        } else if (score > score5 && score < score4) {
            for (int i = 6; i < 11; i++) {
                editor.putInt("save" + i, prefs.getInt("save" + (i - 1), 0));
            }
            editor.putInt("save5", score);
            editor.commit();
        } else if (score > score6 && score < score5) {
            for (int i = 7; i < 11; i++) {
                editor.putInt("save" + i, prefs.getInt("save" + (i - 1), 0));
            }
            editor.putInt("save6", score);
            editor.commit();
        } else if (score > score7 && score < score6) {
            for (int i = 8; i < 11; i++) {
                editor.putInt("save" + i, prefs.getInt("save" + (i - 1), 0));
            }
            editor.putInt("save7", score);
            editor.commit();
        } else if (score > score8 && score < score7) {
            for (int i = 9; i < 11; i++) {
                editor.putInt("save" + i, prefs.getInt("save" + (i - 1), 0));
            }
            editor.putInt("save8", score);
            editor.commit();
        } else if (score > score9 && score < score8) {
            for (int i = 10; i < 11; i++) {
                editor.putInt("save" + i, prefs.getInt("save" + (i - 1), 0));
            }
            editor.putInt("save9", score);
            editor.commit();
        } else if (score > score10 && score < score9) {
            editor.putInt("save10", score);
            editor.commit();
        } else {

        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRGB(44, 42, 49);
        explosion.draw(canvas);
        Paint score = new Paint();
        score.setTextSize(50);
        score.setColor(Color.WHITE);
        score.setTextAlign(Paint.Align.RIGHT);
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");
        score.setTypeface(typeface);
        canvas.drawText(Integer.toString(this.score), Constants.screenWidth - Constants.screenWidth/30, (int)(Constants.screenHeight/17.7), score);



       starManager.draw(canvas);

        particleGenerator1.draw(canvas);

        if (split) {
            particleGenerator2.draw(canvas);
        }


        player.draw(canvas);
        player2.draw(canvas);

        obstacleManager.draw(canvas);

        //DRAW COG
        Paint paint2 = new Paint();
        paint2.setTypeface(typeface);
        paint2.setColor(Color.LTGRAY);
        paint2.setTextAlign(Paint.Align.LEFT);
        paint2.setTextSize(50);
        canvas.drawBitmap(Assets.resizedCog, (int) (Constants.screenWidth/40), Constants.screenHeight/40, paint2);
        //END OF COG

        //Draw Coins
        canvas.drawBitmap(Assets.resizedCoin2, (int) (Constants.screenWidth/2) - Constants.screenWidth/15, Constants.screenHeight/25, paint2);
        canvas.drawText(Integer.toString(coins), (int) (Constants.screenWidth/2), (int)(Constants.screenHeight/17.5), paint2);
        //End of Coins


        if (gameOver) {
            fades();
            explosion.update();
            explosion.draw(canvas);
            //Save Score

            //Save Coins

            SharedPreferences prefs = Constants.currentContext.getSharedPreferences("gameData", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("coinValue", coins);
            editor.commit();

            //End save Coins


            RectF gameOverScreen = new RectF();
            gameOverScreen.set(Constants.screenWidth/12, Constants.screenHeight/4, Constants.screenWidth - Constants.screenWidth/12, Constants.screenHeight-Constants.screenHeight/4);

            Rect fade = new Rect();
            fade.set(0, 0, Constants.screenWidth, Constants.screenHeight);
            paint4.setColor(Color.BLACK);
            paint4.setAlpha(opacity2);
            canvas.drawRect(fade, paint4);

            paint3.setColor(Color.WHITE);
            paint3.setAlpha(opacity);
            canvas.drawRoundRect(gameOverScreen, 10, 10, paint3);
            paint3.setTextSize(150*2/8 * Constants.currentContext.getResources().getDisplayMetrics().density);
            paint3.setColor(Color.DKGRAY);
            paint3.setTypeface(typeface);
            centreText(canvas, paint3, "GAME OVER", Constants.screenHeight/3.0f);
            paint3.setTextSize(90*2  /8 * Constants.currentContext.getResources().getDisplayMetrics().density);
            centreText(canvas, paint3, "Your Score was: ", Constants.screenHeight/2.5f);
            paint3.setTextSize(140*2  /8 * Constants.currentContext.getResources().getDisplayMetrics().density);

            int score1 = prefs.getInt("save1", 0); //0 is the default value)

            if (this.score >= score1) {
                paint3.setColor(Color.rgb(255, 215, 0));
            }

            saveScores(this.score);

            centreText(canvas, paint3, Integer.toString(this.score), Constants.screenHeight/2.0f);

            paint3.setColor(Color.DKGRAY);

            canvas.drawBitmap(Assets.resizedplayAgain, (int) (Constants.screenWidth/9), (int) (Constants.screenHeight/1.8), paint3);
            paint3.setTextSize(100*2  /8 * Constants.currentContext.getResources().getDisplayMetrics().density);
            centreText(canvas, paint3, "PLAY AGAIN", Constants.screenHeight/1.66f);

            canvas.drawBitmap(Assets.resizedstore, (int) (Constants.screenWidth/9), (int) (Constants.screenHeight/1.53), paint3);
            centreText(canvas, paint3, "MENU", Constants.screenHeight/1.42f);
        }
    }

    @Override
    public void terminate() {
        SceneManager.activeScene = 0;
    }

    @Override
    public void recieveTouch(MotionEvent event) {
        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                if (!gameOver) {
                    numPoints = event.getPointerCount();
                    eventX = event.getX(0);
                    if (event.getX() > Constants.screenWidth/40 && event.getX() < Constants.screenWidth/40 + Constants.screenWidth/12 &&
                            event.getY() > Constants.screenHeight/40 && event.getY() < Constants.screenHeight/40 + Constants.screenWidth/12) {

                        if (this.sceneManager.getPaused()) {
                            this.sceneManager.setPaused(false);
                        } else {
                            this.sceneManager.setPaused(true);
                        }
                    }

                }
                if (gameOver && event.getY() > Constants.screenHeight/1.8 && event.getY() < Constants.screenHeight/1.8 + Constants.screenHeight / 12 &&
                        event.getX() > Constants.screenWidth/9 && event.getX() < Constants.screenWidth/9 + Constants.screenWidth / 1.3) {
                    reset();
                    gameOver = false;
                } else if (gameOver && event.getY() > Constants.screenHeight/1.53 && event.getY() < Constants.screenHeight/1.53 + Constants.screenHeight / 12 &&
                        event.getX() > Constants.screenWidth/9 && event.getX() < Constants.screenWidth/9 + Constants.screenWidth / 1.3) {
                    reset();
                    gameOver = false;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {

                    }
                    terminate();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (!gameOver) {
                    numPoints = event.getPointerCount();
                    eventX = event.getX(0);
                }
                break;
            }
            case MotionEvent.ACTION_POINTER_UP: {
                numPoints = event.getPointerCount() - 1;
                break;
            }
            case MotionEvent.ACTION_UP: {
                numPoints = event.getPointerCount() - 1;
                break;
            }
        }

    }
    private void resetPoint() {
        double speed  = obstacleManager.getSpeed();
        if (playerPoint.x > (Constants.screenWidth / 2)) {
            if (speed > 0) {
                playerPoint.set((int) (playerPoint.x - 70 * speed), playerPoint.y);
            } else {
                playerPoint.set(playerPoint.x - 70, playerPoint.y);
            }
            if (playerPoint.x <= (Constants.screenWidth / 2)) {
                playerPoint.set(Constants.screenWidth/2, playerPoint.y);
                selector.selectSprite(this.sceneManager.shipChosen + 1, 1);
                player.updateSprite(selector.getSprite());
                player2.updateSprite(selector.getSprite());
            }
        } else if (playerPoint.x < (Constants.screenWidth / 2)) {
            if (speed > 0) {
                playerPoint.set((int) (playerPoint.x + 70 * speed), playerPoint.y);
            } else {
                playerPoint.set(playerPoint.x + 70, playerPoint.y);
            }
            if (playerPoint.x >= (Constants.screenWidth / 2)) {
                playerPoint.set(Constants.screenWidth/2, playerPoint.y);
                selector.selectSprite(this.sceneManager.shipChosen + 1, 1);
                player.updateSprite(selector.getSprite());
                player2.updateSprite(selector.getSprite());
            }
        }
        eventX = 0;
        numPoints = 0;
    }
    private void resetPointTwo() {
        double speed  = obstacleManager.getSpeed();
        if (playerPoint2.x > playerPoint.x) {
            if (speed > 0) {
                playerPoint2.set((int) (playerPoint2.x - 70 * speed), playerPoint2.y);
            } else {
                playerPoint2.set(playerPoint2.x - 70, playerPoint2.y);
            }
            if (playerPoint2.x <= playerPoint.x) {
                split = false;
                playerPoint.set(playerPoint.x, Constants.screenHeight - Constants.screenHeight/4);
                playerPoint2.set(playerPoint.x, Constants.screenHeight - Constants.screenHeight/4);
                if (justReset == 1) {
                    selector.selectSprite(this.sceneManager.shipChosen + 1, 1);
                    player.updateSprite(selector.getSprite());
                    player2.updateSprite(selector.getSprite());
                }

            }
        } else if (playerPoint2.x < playerPoint.x) {
            if (speed > 0) {
                playerPoint2.set((int) (playerPoint2.x + 70 * speed), playerPoint2.y);
            } else {
                playerPoint2.set(playerPoint2.x + 70, playerPoint2.y);
            }
            if (playerPoint2.x >= playerPoint.x) {
                split = false;
                playerPoint.set(playerPoint.x, Constants.screenHeight - Constants.screenHeight/4);
                playerPoint2.set(playerPoint.x, Constants.screenHeight - Constants.screenHeight/4);
                if (justReset == 1) {
                    selector.selectSprite(this.sceneManager.shipChosen + 1, 1);
                    player.updateSprite(selector.getSprite());
                    player2.updateSprite(selector.getSprite());
                }
            }
        }
        justReset++;
    }

    public void addToScore(int score){
        if (!gameOver) {
            this.score += score*player.getSpeed();
        }
    }

    private void split() {
        if (eventX < Constants.screenWidth/2) {
            if (!(playerPoint2.x > Constants.screenWidth - player.getRectangle().width())) {
                if (obstacleManager.getSpeed() > 0) {
                    playerPoint2.set(playerPoint2.x + (int) (70 * obstacleManager.getSpeed()), playerPoint2.y);
                    playerPoint.set(player.getRectangle().width() - 40, playerPoint.y);
                } else {
                    playerPoint2.set(playerPoint2.x +70, playerPoint2.y);
                    playerPoint.set(player.getRectangle().width() - 40, playerPoint.y);
                }
            } else {
                playerPoint2.set(Constants.screenWidth - 60, playerPoint2.y);
                playerPoint.set(player.getRectangle().width() - 40, playerPoint.y);
            }
        } else if (eventX > Constants.screenWidth/2) {
            if (!(playerPoint2.x < player.getRectangle().width())) {
                if (obstacleManager.getSpeed() > 0) {
                    playerPoint2.set(playerPoint2.x - (int) (70 * obstacleManager.getSpeed()), playerPoint2.y);
                    playerPoint.set(Constants.screenWidth - 60, playerPoint.y);
                } else {
                    playerPoint2.set(playerPoint2.x - (70), playerPoint2.y);
                    playerPoint.set(Constants.screenWidth - 60, playerPoint.y);
                }
            } else {
                playerPoint2.set(player.getRectangle().width() - 40, playerPoint2.y);
                playerPoint.set(Constants.screenWidth - 60, playerPoint.y);
            }
        }
    }

    private void centreText(Canvas canvas, Paint paint, String text, float y) {
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");
        paint.setTypeface(typeface);
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        canvas.drawText(text, x, y, paint);
    }


}
