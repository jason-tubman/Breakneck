package tech.jasontubman.breakneck;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import tech.jasontubman.game.R;

/**
 * Created by Jason on 28/11/2016.
 */
public class Planet {

    public int radius;
    public int style;
    public int x;
    public int y;
    private Bitmap resizedPlanet;

    public Planet(int radius, int style, int x, int y) {

        this.radius = radius;
        this.style = style;
        this.x = x;
        this.y = y;
        switch(style) {
            case 0: {
                resizedPlanet = Bitmap.createScaledBitmap(Assets.planet1, radius, radius, false);
                break;
            }
            case 1: {
                resizedPlanet = Bitmap.createScaledBitmap(Assets.planet2, radius, radius, false);
                break;
            }
            case 2: {
                resizedPlanet = Bitmap.createScaledBitmap(Assets.planet3, radius, radius, false);
                break;
            }
            case 3: {
                resizedPlanet = Bitmap.createScaledBitmap(Assets.planet4, radius, radius, false);
                break;
            }
            case 4: {
                resizedPlanet = Bitmap.createScaledBitmap(Assets.planet5, radius, radius, false);
                break;
            }
            case 5: {
                resizedPlanet = Bitmap.createScaledBitmap(Assets.planet6, radius, radius, false);
                break;
            }
            case 6: {
                resizedPlanet = Bitmap.createScaledBitmap(Assets.planet7, radius, radius, false);
                break;
            }
            case 7: {
                resizedPlanet = Bitmap.createScaledBitmap(Assets.planet8, radius, radius, false);
                break;
            }
        }


    }

    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setAlpha(50);
        paint.setColor(Color.WHITE);
        paint.setDither(true);
        paint.setAntiAlias(true);


        canvas.drawBitmap(resizedPlanet, x, y, paint);

    }

    public void Move(int amount) {
        this.y += amount;
    }

}

