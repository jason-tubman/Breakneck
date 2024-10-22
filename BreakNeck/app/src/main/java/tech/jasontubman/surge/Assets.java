package tech.jasontubman.surge;


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
    public static Bitmap coin;
    public static Bitmap resizedCoin;

    public static Bitmap resizedShield;

    public static Bitmap shipShield;
    public static Bitmap resizedShipShield;
    public static Bitmap resizedShipShield2;
    public static Bitmap shield;
    public static Bitmap planet7;
    public static Bitmap planet8;

    public static Bitmap cog = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.gear);
    public static Bitmap resizedCog = (Bitmap.createScaledBitmap(cog, Constants.screenWidth/12, Constants.screenWidth/12, false));

    public static Bitmap  store = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.grey_button03);
    public static Bitmap resizedstore = Bitmap.createScaledBitmap(store, (int) (Constants.screenWidth / 1.3), Constants.screenHeight / 12, false);

    public static Bitmap coin2 = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.coin);
    public static Bitmap resizedCoin2 = (Bitmap.createScaledBitmap(coin2, Constants.screenWidth/25, Constants.screenWidth/25, false));

    public static Bitmap playAgain = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.playbutton);
    public static Bitmap resizedplayAgain = Bitmap.createScaledBitmap(playAgain, (int) (Constants.screenWidth / 1.3), Constants.screenHeight / 12, false);

    public static Bitmap speedBuff = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.bolt_bronze);
    public static Bitmap resizedspeedBuff = (Bitmap.createScaledBitmap(speedBuff, Constants.screenWidth/24, Constants.screenWidth/24, false));
    
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

        coin = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.coin);
        resizedCoin = (Bitmap.createScaledBitmap(coin, Constants.screenWidth/24, Constants.screenWidth/24, false));

        shield = Constants.bf.decodeResource(Constants.currentContext.getResources(), R.drawable.shield_bronze);
        resizedShield = (Bitmap.createScaledBitmap(shield, Constants.screenWidth/24, Constants.screenWidth/24, false));

        shipShield = bf.decodeResource( currentContext.getResources(), R.drawable.shield1);
        resizedShipShield = (Bitmap.createScaledBitmap(shipShield, (int) (Constants.screenWidth/5.5), (int) (Constants.screenWidth/7.5), false));
        resizedShipShield2 = (Bitmap.createScaledBitmap(shipShield, (int) (Constants.screenWidth/11), (int) (Constants.screenWidth/15), false));
    }

}
