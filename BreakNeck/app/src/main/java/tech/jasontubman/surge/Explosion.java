package tech.jasontubman.surge;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Jason on 1/12/2016.
 */

public class Explosion {

    private ArrayList<ExplosionParticle> particles;

    public Explosion() {
        particles = new ArrayList<ExplosionParticle>();

    }

    void addParticle(int xPos, int yPos, int xOffset, boolean split) {
        for (int i = 0; i < 100; i++) {
            particles.add(new ExplosionParticle(xPos, yPos, xOffset));
        }
    }

    void update() {
        for (int i = particles.size()-1; i >= 0; i--) {
            ExplosionParticle p = particles.get(i);
            p.update();
            if (p.isDead()) {
                particles.remove(i);
            }
        }
    }

    void draw(Canvas canvas) {
        for (int i = particles.size()-1; i >= 0; i--) {
            ExplosionParticle p = particles.get(i);
            p.draw(canvas);
        }
    }

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}


