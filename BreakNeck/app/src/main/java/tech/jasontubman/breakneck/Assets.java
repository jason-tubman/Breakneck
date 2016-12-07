package tech.jasontubman.breakneck;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import tech.jasontubman.game.R;

/**
 * Created by Jason on 7/12/2016.
 */

public class Assets {

    public static BitmapFactory bf = new BitmapFactory();
    public static Context currentContext;
    public static Bitmap star;
   
    public static Bitmap resizedStar1;
    public static Bitmap resizedStar2;
    public static Bitmap resizedStar3;
    public static Bitmap resizedStar4;
    public static Bitmap resizedStar5;
    public static Bitmap resizedStar6;
    public static Bitmap resizedStar7;
    public static Bitmap resizedStar8;
    public static Bitmap resizedStar9;


    public static Bitmap planet1;
    public static Bitmap planet2;
    public static Bitmap planet3;
    public static Bitmap planet4;
    public static Bitmap planet5;
    public static Bitmap planet6;
    public static Bitmap planet7;
    public static Bitmap planet8;
    
    public Assets() {
        star = bf.decodeResource(currentContext.getResources(), R.drawable.star2);

        resizedStar1 = Bitmap.createScaledBitmap(star, 2, 2, false);
        resizedStar2 = Bitmap.createScaledBitmap(star, 5, 5, false);
        resizedStar3 = Bitmap.createScaledBitmap(star, 9, 9, false);
         resizedStar4 = Bitmap.createScaledBitmap(star, 14, 14, false);
         resizedStar5 = Bitmap.createScaledBitmap(star, 17, 17, false);
         resizedStar6 = Bitmap.createScaledBitmap(star, 20, 20, false);
         resizedStar7 = Bitmap.createScaledBitmap(star, 25, 25, false);
         resizedStar8 = Bitmap.createScaledBitmap(star, 7, 7, false);
         resizedStar9 = Bitmap.createScaledBitmap(star, 22, 22, false);


         planet1 = bf.decodeResource( currentContext.getResources(), R.drawable.p1);
         planet2 = bf.decodeResource( currentContext.getResources(), R.drawable.p2);
         planet3 = bf.decodeResource( currentContext.getResources(), R.drawable.p3);
         planet4 = bf.decodeResource( currentContext.getResources(), R.drawable.p6);
         planet5 = bf.decodeResource( currentContext.getResources(), R.drawable.p7);
         planet6 = bf.decodeResource( currentContext.getResources(), R.drawable.p8);
         planet7 = bf.decodeResource( currentContext.getResources(), R.drawable.p9);
         planet8 = bf.decodeResource( currentContext.getResources(), R.drawable.p10);

    }

}
