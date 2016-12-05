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
    private double speed;

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
                this.speed = 1;
                break;
            case 2: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1g);
                this.speed = 1;
                break;
            case 3: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1o);
                this.speed = 1;
                break;
            case 4: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s1r);
                this.speed = 1;
                break;
            case 5: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2b);
                this.speed = 1.125;
                break;
            case 6: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2g);
                this.speed = 1.125;
                break;
            case 7: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2o);
                this.speed = 1.125;
                break;
            case 8: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s2r);
                this.speed = 1.125;
                break;
            case 9: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3b);
                this.speed = 1.2;
                break;
            case 10: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3g);
                this.speed = 1.2;
                break;
            case 11: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3o);
                this.speed = 1.2;
                break;
            case 12: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s3r);
                this.speed = 1.2;
                break;
            case 13: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4b);
                this.speed = 1.25;
                break;
            case 14: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4g);
                this.speed = 1.25;
                break;
            case 15: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4y);
                this.speed = 1.25;
                break;
            case 16: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s4r);
                this.speed = 1.25;
                break;
            case 17: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5b);
                this.speed = 1.35;
                break;
            case 18: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5g);
                this.speed = 1.35;
                break;
            case 19: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5o);
                this.speed = 1.35;
                break;
            case 20: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s5bl);
                this.speed = 1.35;
                break;
            case 21: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6b);
                this.speed = 1.425;
                break;
            case 22: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6g);
                this.speed = 1.425;
                break;
            case 23: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6o);
                this.speed = 1.425;
                break;
            case 24: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s6bl);
                this.speed = 1.425;
                break;
            case 25: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7b);
                this.speed = 1.5;
                break;
            case 26: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7g);
                this.speed = 1.5;
                break;
            case 27: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7o);
                this.speed = 1.5;
                break;
            case 28: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s7bl);
                this.speed = 1.5;
                break;
            case 29: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s8b);
                this.speed = 1.575;
                break;
            case 30: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s8bl);
                this.speed = 1.575;
                break;
            case 31: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s8g);
                this.speed = 1.575;
                break;
            case 32: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s8o);
                this.speed = 1.575;
                break;
            case 33: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s9b);
                this.speed = 1.65;
                break;
            case 34: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s9bl);
                this.speed = 1.65;
                break;
            case 35: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s9g);
                this.speed = 1.65;
                break;
            case 36: sprite = bf.decodeResource(Constants.currentContext.getResources(), R.drawable.s9o);
                this.speed = 1.65;
                break;
        }


    }

    public Bitmap getSprite() {
        return shipSprite;
    }

    public double getSpeed() {
        return this.speed;
    }


}
