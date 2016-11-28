package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;

/**
 * Created by Jason on 28/11/2016.
 */

public class Star {

    public int radius;
    public int colour;
    public int x;
    public int y;


    public Star(int radius, int color, int x, int y) {

        this.radius = radius;
        this.colour = color;
        this.x = x;
        this.y = y;


    }

    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(this.colour);
        paint.setDither(true);
        paint.setAntiAlias(true);

        canvas.drawCircle(x, y, radius, paint);

    }

    public void Move(int amount) {
        this.y += amount;
    }

}
