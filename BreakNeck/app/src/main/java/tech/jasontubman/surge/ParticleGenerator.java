package tech.jasontubman.surge;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Jason on 1/12/2016.
 */

public class ParticleGenerator {

    private ArrayList<Particle> particles;

    ParticleGenerator() {
        particles = new ArrayList<Particle>();
    }

    void addParticle(int xPos, int yPos, int xOffset, boolean split) {
        if (!split) {
            if (Math.random() < 0.5) {
                xPos = xPos + (getRandomNumberInRange(0, Constants.screenWidth/22)) * -1;
            } else {
                xPos = xPos + (getRandomNumberInRange(0,  Constants.screenWidth/22));
            }
        } else {
            if (Math.random() < 0.5) {
                xPos = xPos + (getRandomNumberInRange(0,  Constants.screenWidth/35)) * -1;
            } else {
                xPos = xPos + (getRandomNumberInRange(0,  Constants.screenWidth/30));
            }
        }
        particles.add(new Particle(xPos, yPos, xOffset));
    }

    void update() {
        for (int i = particles.size()-1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.update();
            if (p.isDead()) {
                particles.remove(i);
            }
        }
    }

    void draw(Canvas canvas) {
        for (int i = particles.size()-1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.draw(canvas);
        }
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}


