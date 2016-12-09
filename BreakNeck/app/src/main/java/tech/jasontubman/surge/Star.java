package tech.jasontubman.surge;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Jason on 28/11/2016.
 */

public class Star {

    public int colour;
    public int x;
    public int y;
    private int random;

    public Star(int color, int x, int y) {

        this.colour = color;
        this.x = x;
        this.y = y;

        random = getRandomNumberInRange(1, 7);


    }

    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(this.colour);
        paint.setAlpha(200);

        switch (random) {
            case 1: canvas.drawBitmap(Assets.resizedStar1, x, y, paint);
                break;
            case 2:canvas.drawBitmap(Assets.resizedStar2, x, y, paint);
                break;
            case 3:canvas.drawBitmap(Assets.resizedStar3, x, y, paint);
                break;
            case 4:canvas.drawBitmap(Assets.resizedStar4, x, y, paint);
                break;
            case 5:canvas.drawBitmap(Assets.resizedStar5, x, y, paint);
                break;
            case 6:canvas.drawBitmap(Assets.resizedStar6, x, y, paint);
                break;
            case 7:canvas.drawBitmap(Assets.resizedStar7, x, y, paint);
                break;
            case 8:canvas.drawBitmap(Assets.resizedStar8, x, y, paint);
                break;
            case 9:canvas.drawBitmap(Assets.resizedStar9, x, y, paint);
            break;

        }



    }

    public void Move(int amount) {
        this.y += amount;
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
