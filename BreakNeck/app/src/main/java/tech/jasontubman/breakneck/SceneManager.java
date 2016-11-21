package tech.jasontubman.breakneck;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by Jason on 21/11/2016.
 */

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();

    public static int activeScene;

    public SceneManager() {
        activeScene = 0;
        scenes.add(new GameplayScene());
    }

    public void recieveTouch(MotionEvent event) {
        scenes.get(activeScene).recieveTouch(event);
    }

    public void update(){
        scenes.get(activeScene).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(activeScene).draw(canvas);
    }

}
