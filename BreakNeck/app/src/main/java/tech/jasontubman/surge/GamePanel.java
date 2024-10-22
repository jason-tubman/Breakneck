package tech.jasontubman.surge;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Jason on 21/11/2016.
 */

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    private MainThread thread;

    private SceneManager manager;

    public GamePanel(Context context) {
        super(context);
        Constants.currentContext = context;
        Assets.currentContext = context;
        Assets a = new Assets();

        this.manager = new SceneManager();
        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        Assets b = new Assets();
        setFocusable(true);
    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(true) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        manager.recieveTouch(event);

        return true;
    }

    public void update() {
        manager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        manager.draw(canvas);
    }

    public SceneManager getManager() {
        return manager;
    }



}
