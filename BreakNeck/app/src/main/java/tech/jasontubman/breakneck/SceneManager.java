package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.view.MotionEvent;
import tech.jasontubman.game.R;

import java.util.ArrayList;

/**
 * Created by Jason on 21/11/2016.
 */

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();

    private boolean isPaused = false;

    MediaPlayer backgroundMusic;

    public static int activeScene;

    public SceneManager() {
        backgroundMusic = MediaPlayer.create(Constants.currentContext, R.raw.fly1);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();
        activeScene = 0;
        scenes.add(0, new MenuScene(this));
    }

    public void recieveTouch(MotionEvent event) {
        scenes.get(activeScene).recieveTouch(event);
    }

    public void update(){
        if (!isPaused) {
            scenes.get(activeScene).update();
        }
    }

    public void draw(Canvas canvas) {
            scenes.get(activeScene).draw(canvas);
    }

    public void addScene(Scene scene) {
        scenes.add(scene);
    }

    public MediaPlayer getMediaPlayer(){
        return this.backgroundMusic;
    }

    public void setPaused(boolean paused) {
        this.isPaused = paused;
    }
    public boolean getPaused() {
        return this.isPaused;
    }

}
