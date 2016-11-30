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
    private Bitmap planet;
    private Bitmap resizedPlanet;

    public Planet(int radius, int style, int x, int y) {

        this.radius = radius;
        this.style = style;
        this.x = x;
        this.y = y;
        BitmapFactory bf = new BitmapFactory();
        switch(style) {
            case 0: {
                planet = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.p1);
                break;
            }
            case 1: {
                planet = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.p2);
                break;
            }
            case 2: {
                planet = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.p6);
                break;
            }
            case 3: {
                planet = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.p7);
                break;
            }
            case 4: {
                planet = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.p9);
                break;
            }
            case 5: {
                planet = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.p10);
                break;
            }
            case 6: {
                planet = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.p3);
                break;
            }
            case 7: {
                planet = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.p4);
                break;
            }
            case 8: {
                planet = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.p8);
                break;
            }
        }
     resizedPlanet = Bitmap.createScaledBitmap(planet, radius, radius, false);

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

