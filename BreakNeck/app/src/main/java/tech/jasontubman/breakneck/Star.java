package tech.jasontubman.breakneck;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;

import tech.jasontubman.game.R;

/**
 * Created by Jason on 28/11/2016.
 */

public class Star {

    public int radius;
    public int colour;
    public int x;
    public int y;
    public Bitmap star;
    public Bitmap resizedStar;

    public Star(int radius, int color, int x, int y) {

        this.radius = radius;
        this.colour = color;
        this.x = x;
        this.y = y;

        star = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.star2);
        resizedStar = Bitmap.createScaledBitmap(star, radius, radius, false);

    }

    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(this.colour);
        paint.setAlpha(200);
        paint.setDither(true);
        paint.setAntiAlias(true);

        canvas.drawBitmap(resizedStar, x, y, paint);

    }

    public void Move(int amount) {
        this.y += amount;
    }

}
