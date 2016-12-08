package tech.jasontubman.breakneck;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import tech.jasontubman.breakneck.Triangles.Diamond;
import tech.jasontubman.breakneck.Triangles.LeftTriangle;
import tech.jasontubman.breakneck.Triangles.RightTriangle;
import tech.jasontubman.game.R;

import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;


/**
 * Created by Jason on 21/11/2016.
 */

public class TutorialScene implements Scene {
    private float eventX =0;

    private SharedPreferences prefs = Constants.currentContext.getSharedPreferences("gameData", Context.MODE_PRIVATE);
    private SharedPreferences.Editor editor = prefs.edit();

    private int numPoints = 0;
    private Player player;
    private Point playerPoint;
    private Player player2;
    private Point playerPoint2;
    private int tutorialStage = 0;
    private Rect r = new Rect();
    private int justReset = 1;

    private int opacity = 0;

    private boolean shieldMade = false;
    private boolean shieldGrabbed = false;
    private Shield shield;

    private int elapsedTime= 0;
    private long startTime = System.currentTimeMillis();

    private boolean obstaclesBeaten = false;
    private boolean obstacles1made = false;
    private boolean obstacles2made = false;
    private boolean obstacles3made = false;

    private SceneManager sceneManager;

    private StarManager starManager;
    private boolean split;
    private boolean gameOver = false;

    private ShipSelector selector;

    private ParticleGenerator particleGenerator1;
    private ParticleGenerator particleGenerator2;

    private Obstacle left;
    private Obstacle right;
    private Obstacle diamond;


    public TutorialScene(SceneManager manager) {
        this.sceneManager = manager;
        selector = new ShipSelector(this.sceneManager.shipChosen + 1, 1);
        player = new Player(new Rect(100, 100, 235, 235), selector.getSprite(), selector.getSpeed());
        playerPoint = new Point(Constants.screenWidth/2, Constants.screenHeight-Constants.screenHeight/4);
        player.update(playerPoint);


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

        numPoints  = 0;
    }
    @Override
    public void update() {
        if (!gameOver) {
            if (player.getShieldStatus() && !player2.getShieldStatus()) {
                player2.setShieldStatus(true);
            }
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

            player.update(playerPoint);
            player2.update(playerPoint2);


            starManager.update();

            //RESETING PLAYERPOINT 1
            if (numPoints == 0 && playerPoint.x != Constants.screenWidth/2) {
                resetPoint();
            }

            //MOVING PLAYER 1
            if (numPoints > 0 && !gameOver) {
                if (eventX < Constants.screenWidth/2) {
                    if (!(playerPoint.x < player.getRectangle().width())) {
                            playerPoint.set(playerPoint.x - 70, playerPoint.y);
                            particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, -5, false);
                            particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, -5, false);
                    } else {
                        if (!split) {
                            playerPoint.set(100, playerPoint.y);
                        }
                    }
                } else if (eventX > Constants.screenWidth/2) {
                    if (!(playerPoint.x > Constants.screenWidth - player.getRectangle().width())) {
                            playerPoint.set(playerPoint.x + 70, playerPoint.y);
                            particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, 5, false);
                            particleGenerator1.addParticle(player.getX(), player.getY() + player.getHeight()-50, 5, false);
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
        elapsedTime += (int)(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();


    }

    private void fade() {
        if (opacity < 255) {
            opacity+=15;
        }

    }

    private void runTutorial(Canvas canvas) {

        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");
        Paint paint2 = new Paint();
        paint2.setTypeface(typeface);
        paint2.setColor(Color.LTGRAY);
        paint2.setTextSize(40 * Constants.currentContext.getResources().getDisplayMetrics().density);


        if (elapsedTime < 4000) {
            tutorialStage = 0;
        } else if (elapsedTime >= 4000 && elapsedTime < 8000 ) {
            tutorialStage = 1;
        } else if (elapsedTime >= 8000 && elapsedTime < 12000) {
            tutorialStage = 2;
        } else if (elapsedTime >= 12000 && elapsedTime < 17000) {
            tutorialStage = 3;
        } else if (elapsedTime >= 17000 && elapsedTime < 21000) {
            tutorialStage = 4;
        } else if (elapsedTime >= 21000 && elapsedTime < 25000) {
            tutorialStage = 5;
        }  else if (elapsedTime >= 25000 && elapsedTime < 30000) {
            tutorialStage = 6;
            if (!shieldMade) {
               shield = new Shield(Constants.screenWidth/2 - 30, -50);
                shieldMade = true;
            }
            shield.moveObstacle(20);
            shield.update();
            if (!shieldGrabbed) {
                shield.draw(canvas);
            }
            if (shield.getTop() >= Constants.screenHeight) {
                shield = new Shield(Constants.screenWidth/2, Constants.screenHeight - 300);
            }
            if (shield.playerCollided(player)) {
                shieldGrabbed = true;
                player.setShieldStatus(true);
            }

        } else if (elapsedTime >= 30000 && elapsedTime < 35000) {
            if (!shieldGrabbed) {
                startTime += 5000;
            } else {
                tutorialStage = 7;
            }
        } else if (elapsedTime >= 35000 && elapsedTime < 37000) {
            tutorialStage = 8;
        } else if (elapsedTime >= 37000 && elapsedTime < 41000) {
            tutorialStage = 9;

            if (!obstacles1made) {
                left = new LeftTriangle(Color.LTGRAY, 0, -300);
                obstacles1made = true;
            } if (!obstacles2made) {
                right = new RightTriangle(Color.LTGRAY, Constants.screenWidth/4, -2000);
                obstacles2made = true;
            } if (!obstacles3made) {
                diamond = new Diamond(Color.LTGRAY, Constants.screenWidth/2, -3700);
                obstacles3made = true;
            }

            left.moveObstacle(20);
            left.update();

            right.moveObstacle(20);
            right.update();

            diamond.moveObstacle(20);
            diamond.update();

            right.draw(canvas);
            left.draw(canvas);
            diamond.draw(canvas);

            if (left.playerCollided(player) || right.playerCollided(player) || diamond.playerCollided(player)) {
                left = new LeftTriangle(Color.LTGRAY, 0, -300);
                right = new RightTriangle(Color.LTGRAY, Constants.screenWidth/4, -2000);
                diamond = new Diamond(Color.LTGRAY, Constants.screenWidth/2, -3700);
            }

            if (diamond.getTop() >= Constants.screenHeight) {
                obstaclesBeaten = true;
            }

        } else if (elapsedTime >= 41000 && elapsedTime < 42000) {
            if (!obstaclesBeaten) {
                startTime += 4000;
            } else {
                tutorialStage = 10;
            }
        }
        else if (elapsedTime >= 42000 && elapsedTime < 47000) {
            tutorialStage = 11;
        }
        else if (elapsedTime >= 47000 && elapsedTime < 52000) {
            tutorialStage = 12;
        }
        else if (elapsedTime >= 52000 && elapsedTime < 57000) {
            tutorialStage = 13;
            editor.putBoolean("tutorial", true);
            editor.commit();
        }  else if (elapsedTime >= 57000 && elapsedTime < 61000) {
            fade();
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            RectF rect = new RectF();
            paint.setAlpha(opacity);
            rect.set(0, 0, Constants.screenWidth, Constants.screenHeight);
            canvas.drawRoundRect(rect, 0, 0, paint);

        } else if (elapsedTime > 61000) {
            terminate();
        }

        switch(tutorialStage) {
            case 0: {
                centreText(canvas, paint2, "WELCOME", Constants.screenHeight/4);
                centreText(canvas, paint2, "TO SURGE", Constants.screenHeight/3);
                break;
            }
            case 1: {
                paint2.setTextSize(20 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "THIS QUICK", Constants.screenHeight/4);
                centreText(canvas, paint2, "TUTORIAL WILL", (int) (Constants.screenHeight/3.4));
                centreText(canvas, paint2, "SHOW YOU THE ROPES", (int) (Constants.screenHeight/3.0));
                break;
            }
            case 2: {
                paint2.setTextSize(20 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "LET'S START", Constants.screenHeight/4);
                centreText(canvas, paint2, "WITH THE CONTROLS", (int) (Constants.screenHeight/3.4));
                break;
            }
            case 3: {
                paint2.setTextSize(20 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "HOLD THE LEFT", Constants.screenHeight/4);
                centreText(canvas, paint2, "SIDE OF THE", (int) (Constants.screenHeight/3.4));
                centreText(canvas, paint2, "SCREEN TO MOVE LEFT", (int) (Constants.screenHeight/3.0));
                break;
            }
            case 4: {
                paint2.setTextSize(20 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "SIMILARLY, THE", Constants.screenHeight/4);
                centreText(canvas, paint2, "RIGHT SIDE WILL", (int) (Constants.screenHeight/3.4));
                centreText(canvas, paint2, "MOVE YOU TO THE RIGHT", (int) (Constants.screenHeight/3.0));
                break;
            }
            case 5: {
                paint2.setTextSize(20 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "AT ANY TIME JUST", Constants.screenHeight/4);
                centreText(canvas, paint2, "REMOVE BOTH FINGERS", (int) (Constants.screenHeight/3.4));
                centreText(canvas, paint2, "TO CENTRE YOURSELF", (int) (Constants.screenHeight/3.0));
                break;
            }
            case 6: {
                paint2.setTextSize(20 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "THIS IS A SHIELD", Constants.screenHeight/4);
                paint2.setTextSize(30 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "GRAB IT!", (int) (Constants.screenHeight/2.8));
                break;
            }
            case 7: {
                paint2.setTextSize(30 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "NICE!", Constants.screenHeight/4);
                paint2.setTextSize(15 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "SHIELDS WILL ", (int) (Constants.screenHeight/3.4));
                centreText(canvas, paint2, "PROTECT YOU FROM", (int) (Constants.screenHeight/3.1));
                centreText(canvas, paint2, "ANY INCOMING OBSTACLE", (int) (Constants.screenHeight/2.8));
                break;
            }
            case 8: {
                paint2.setTextSize(20 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "SPEAKING OF", Constants.screenHeight/4);
                paint2.setTextSize(30 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "WATCH OUT!", (int) (Constants.screenHeight/2.8));
                break;
            }
            case 10: {
                paint2.setTextSize(30 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "NICE!", Constants.screenHeight/4);
                break;
            }
            case 11: {
                paint2.setTextSize(20 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "LOOKS LIKE", Constants.screenHeight/4);
                centreText(canvas, paint2, "YOU'RE READY", (int) (Constants.screenHeight/3.4));
                break;
            }
            case 12: {
                paint2.setTextSize(20 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "IF AT ANY TIME", Constants.screenHeight/4);
                centreText(canvas, paint2, "YOU WANT TO RETURN", (int) (Constants.screenHeight/3.4));
                centreText(canvas, paint2, "TO THIS TUTORIAL JUST", (int) (Constants.screenHeight/3.2));
                centreText(canvas, paint2, "NAVIGATE TO THE OPTIONS PANEL", (int) (Constants.screenHeight/2.9));
                break;
            }
            case 13: {
                paint2.setTextSize(20 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "HAVE FUN PLAYING", Constants.screenHeight/4);
                paint2.setTextSize(30 * Constants.currentContext.getResources().getDisplayMetrics().density);
                centreText(canvas, paint2, "SURGE", (int) (Constants.screenHeight/3.2));
                break;
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRGB(44, 42, 49);
        runTutorial(canvas);
        Typeface typeface = Typeface.createFromAsset(Constants.currentContext.getAssets(), "spaceage.ttf");

        starManager.draw(canvas);

        particleGenerator1.draw(canvas);

        if (split) {
            particleGenerator2.draw(canvas);
        }


        player.draw(canvas);
        player2.draw(canvas);


        //DRAW COG
        Paint paint2 = new Paint();
        paint2.setTypeface(typeface);
        paint2.setColor(Color.LTGRAY);
        paint2.setTextAlign(Paint.Align.LEFT);
        paint2.setTextSize(50);
        canvas.drawBitmap(Assets.resizedCog, (int) (Constants.screenWidth/40), Constants.screenHeight/40, paint2);
        //END OF COG


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
            case MotionEvent.ACTION_UP: {
                numPoints = event.getPointerCount() - 1;
                break;
            }
        }

    }
    private void resetPoint() {
        if (playerPoint.x > (Constants.screenWidth / 2)) {
            playerPoint.set(playerPoint.x - 70, playerPoint.y);
            if (playerPoint.x <= (Constants.screenWidth / 2)) {
                playerPoint.set(Constants.screenWidth/2, playerPoint.y);
                selector.selectSprite(this.sceneManager.shipChosen + 1, 1);
                player.updateSprite(selector.getSprite());
                player2.updateSprite(selector.getSprite());
            }
        } else if (playerPoint.x < (Constants.screenWidth / 2)) {
                playerPoint.set(playerPoint.x + 70, playerPoint.y);

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
        if (playerPoint2.x > playerPoint.x) {

                playerPoint2.set(playerPoint2.x - 70, playerPoint2.y);

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
                playerPoint2.set(playerPoint2.x + 70, playerPoint2.y);

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

    private void split() {
        if (eventX < Constants.screenWidth/2) {
            if (!(playerPoint2.x > Constants.screenWidth - player.getRectangle().width())) {
                    playerPoint2.set(playerPoint2.x +70, playerPoint2.y);
                    playerPoint.set(player.getRectangle().width() - 40, playerPoint.y);
            } else {
                playerPoint2.set(Constants.screenWidth - 60, playerPoint2.y);
                playerPoint.set(player.getRectangle().width() - 40, playerPoint.y);
            }
        } else if (eventX > Constants.screenWidth/2) {
            if (!(playerPoint2.x < player.getRectangle().width())) {
                    playerPoint2.set(playerPoint2.x - (70), playerPoint2.y);
                    playerPoint.set(Constants.screenWidth - 60, playerPoint.y);
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
