package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Jason on 28/11/2016.
 */

public class StarManager {

    private float speed;
    private ArrayList<Star> stars;
    private ArrayList<Planet> planets;

    public StarManager(float speed) {

        this.speed = speed;

        stars = new ArrayList<>();
        planets = new ArrayList<>();
        generateStars();

    }

    private void generateStars() {

        for (int i = 0; i < 100; i++) {
            int x = getRandomNumberInRange(20, Constants.screenWidth - 20);
            int y = getRandomNumberInRange(-500, Constants.screenHeight - 20);
            int radius = getRandomNumberInRange(2, Constants.screenWidth/200);
            stars.add(i, new Star(radius, Color.WHITE, x, y));
        }
        for (int i = 0; i < 10; i++) {
            int x = getRandomNumberInRange(20, Constants.screenWidth - 20);
            int y = getRandomNumberInRange(-500, Constants.screenHeight - 20);
            int radius = getRandomNumberInRange(10, Constants.screenWidth/150);
            int planetStyle = getRandomNumberInRange(0, 5);
            planets.add(i, new Planet(radius, planetStyle, x, y));
        }

    }

    public void draw(Canvas canvas) {

        for (Star star : stars) {
            star.draw(canvas);
        }

    }

    public void update() {
            for (Star star : stars) {
                star.Move((int) (3 * speed));
                if (star.y > Constants.screenHeight) {
                    star.y = getRandomNumberInRange(-500, 0);
                }
            }

    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
