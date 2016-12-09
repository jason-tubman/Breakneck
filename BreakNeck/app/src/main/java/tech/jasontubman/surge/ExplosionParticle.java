package tech.jasontubman.surge;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;


/**
 * Created by Jason on 1/12/2016.
 */

public class ExplosionParticle {

    private float lifespan;
    private double xDir;
    private double yDir;

    private int currentX;
    private int currentY;

    ExplosionParticle(int xPos, int yPos, int xOffset) {
        this.currentX = xPos;
        this.currentY = yPos;
        this.lifespan = 15;

        double upOrDown = Math.random();
        double leftOrRight = Math.random();

        if (upOrDown < 0.5) {
            yDir = getRandomNumberInRange(1 , 20);
        } else {
            yDir = getRandomNumberInRange(1, 20) * -1;
        }
        if (leftOrRight < 0.5) {
            xDir = getRandomNumberInRange(1, 20);
        } else {
            xDir = getRandomNumberInRange(1, 20) * -1;
        }


    }

    // Method to update position
    void update() {
        currentY+=yDir;
        currentX += xDir;
        lifespan-=1;
    }

    // Method to display
    void draw(Canvas canvas) {
        Paint paint = new Paint();

        if (lifespan < 11 && lifespan >= 0 ) {
            //Yellow
            paint.setColor(Color.rgb(249, 201, 3));
        } else if (lifespan < 15 && lifespan >=11 ) {
            //Orange
            paint.setColor(Color.rgb(198, 78, 26));
        } else if (lifespan < 22.5 && lifespan >= 15 ) {
            //Red
            paint.setColor(Color.rgb(179, 33, 34));
        } else if (lifespan < 30 && lifespan >= 22.5 ) {
            //Purple
            paint.setColor(Color.rgb(59, 65, 125));
        }
        paint.setAlpha((int) (255 / (30/lifespan)));
        canvas.drawCircle(currentX, currentY, 6, paint);
    }

    // Is the particle still useful?
    boolean isDead() {
        if (this.lifespan < 0.0) {
            return true;
        } else {
            return false;
        }
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}

