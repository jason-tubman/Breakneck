package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by Jason on 21/11/2016.
 */

public class MainThread extends Thread {
    public static final int MAX_FPS = 50;

    private double averageFPS;

    private int score;

    private SurfaceHolder surfaceHolder;
    private GamePanel panel;
    private boolean running;
    public static Canvas canvas;


    public MainThread(SurfaceHolder holder, GamePanel panel) {
        super();
        this.surfaceHolder = holder;
        this.panel = panel;
    }

    public void setRunning(boolean running){
        this.running = running;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTIme = 1000/MAX_FPS;

        while(running) {
            startTime = System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    this.panel.update();
                    this.panel.draw(canvas);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTIme - timeMillis;
            try {
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            totalTime +=System.nanoTime() - startTime;
            frameCount++;
            if (frameCount == MAX_FPS) {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }
}
