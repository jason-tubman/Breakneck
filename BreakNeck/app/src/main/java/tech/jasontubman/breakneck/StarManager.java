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
    private boolean menu;

    public StarManager(float speed, boolean menu) {
        this.menu = menu;
        this.speed = speed;

        stars = new ArrayList<>();
        planets = new ArrayList<>();
        generateStars();

    }

    private void generateStars() {
        if (!menu) {
            for (int i = 0; i < 100; i++) {
                int x = getRandomNumberInRange(20, Constants.screenWidth - 20);
                int y = getRandomNumberInRange(-500, Constants.screenHeight - 20);
                int radius = getRandomNumberInRange(2, 25);
                stars.add(i, new Star(radius, Color.WHITE, x, y));
            }
            for (int i = 0; i < 5; i++) {
                int x = getRandomNumberInRange(20, Constants.screenWidth - 20);
                int y = getRandomNumberInRange(-2000, Constants.screenHeight - 20);
                int radius = getRandomNumberInRange(20, 40);
                int planetStyle = getRandomNumberInRange(0, 8);
                planets.add(i, new Planet(radius, planetStyle, x, y));
            }
        } else {
            for (int i = 0; i < 150; i++) {
                int x = getRandomNumberInRange(20, Constants.screenWidth - 20);
                int y = getRandomNumberInRange(-500, Constants.screenHeight - 20);
                int radius = getRandomNumberInRange(2, 50);
                stars.add(i, new Star(radius, Color.WHITE, x, y));
            }
            for (int i = 0; i < 5; i++) {
                int x = getRandomNumberInRange(20, Constants.screenWidth - 20);
                int y = getRandomNumberInRange(-2000, Constants.screenHeight - 20);
                int radius = getRandomNumberInRange(20,70);
                int planetStyle = getRandomNumberInRange(0, 8);
                planets.add(i, new Planet(radius, planetStyle, x, y));
            }
        }
    }

    public void draw(Canvas canvas) {

        for (Star star : stars) {
            star.draw(canvas);
        }
        for (Planet planet : planets) {
            planet.draw(canvas);
        }

    }

    public void update() {
        for (Star star : stars) {
                star.Move((int) (3 * speed));
                if (star.y > Constants.screenHeight) {
                    int value = getRandomNumberInRange(1, 500);
                    star.y = value* -1;
                }
        }
        for (Planet planet : planets) {
            planet.Move((int) (4 * speed));
            if (planet.y > Constants.screenHeight) {
                int value = getRandomNumberInRange(1, 3000);
                planet.y = value*-1;
                int value2 = getRandomNumberInRange(1, Constants.screenWidth);
                planet.x = value2;
            }
        }

    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
