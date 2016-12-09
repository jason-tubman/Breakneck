package tech.jasontubman.surge;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;


/**
 * Created by Jason on 1/12/2016.
 */

public class Particle {

    private float lifespan;
    private double xDir;
    private double yDir;

    private int currentX;
    private int currentY;

    Particle(int xPos, int yPos, int xOffset) {
        this.currentX = xPos;
        this.currentY = yPos + getRandomNumberInRange(-30, 10);
        this.lifespan = 30;

        yDir = getRandomNumberInRange(10, 20);

        if (xPos < Constants.screenWidth/2) {
            xDir = 1 + xOffset;
        } else if (xPos > Constants.screenWidth/2) {
            xDir = -1 + xOffset;
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

