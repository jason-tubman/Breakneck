package tech.jasontubman.breakneck;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.media.MediaPlayer;

import tech.jasontubman.game.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.screenHeight = dm.heightPixels;
        Constants.screenWidth = dm.widthPixels;

        setContentView(new GamePanel(this));


    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        System.exit(0);

    }

}
