package tech.jasontubman.breakneck;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import tech.jasontubman.game.R;

/**
 * Created by Jason on 30/11/2016.
 */

public class ShipSelector {

    private Bitmap sprite;
    private Bitmap shipSprite;

    public ShipSelector(int ship, double shrink) {
        selectSprite(ship, shrink);
    }

    public void selectSprite(int ship, double shrink) {
        BitmapFactory bf = new BitmapFactory();

        chooseSprite(bf, ship);

        shipSprite = Bitmap.createScaledBitmap(sprite, (int) (200*shrink), (int) (200*shrink), false);
    }

    private void chooseSprite(BitmapFactory bf, int shipNum) {

        //36 ships
        switch(shipNum) {
            case 1: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1b);
                break;
            case 2: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1g);
                break;
            case 3: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1o);
                break;
            case 4: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1r);
                break;
            case 5: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2b);
                break;
            case 6: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2g);
                break;
            case 7: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2o);
                break;
            case 8: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2r);
                break;
            case 9: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3b);
                break;
            case 10: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3g);
                break;
            case 11: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3o);
                break;
            case 12: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3r);
                break;
            case 13: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4b);
                break;
            case 14: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4g);
                break;
            case 15: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4r);
                break;
            case 16: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4y);
                break;
            case 17: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5b);
                break;
            case 18: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5bl);
                break;
            case 19: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5g);
                break;
            case 20: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5o);
                break;
            case 21: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6b);
                break;
            case 22: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6bl);
                break;
            case 23: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6g);
                break;
            case 24: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6o);
                break;
            case 25: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7b);
                break;
            case 26: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7bl);
                break;
            case 27: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7g);
                break;
            case 28: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7o);
                break;
            case 29: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s8b);
                break;
            case 30: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s8bl);
                break;
            case 31: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s8g);
                break;
            case 32: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s8o);
                break;
            case 33: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s9b);
                break;
            case 34: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s9bl);
                break;
            case 35: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s9g);
                break;
            case 36: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s9o);
                break;
        }


    }

    public Bitmap getSprite() {
        return shipSprite;
    }


}
