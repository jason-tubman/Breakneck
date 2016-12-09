package tech.jasontubman.surge;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import tech.jasontubman.game.R;

import static com.google.ads.AdRequest.TEST_EMULATOR;

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


        // adview code
        AdView adView = new AdView(this);
        adView.setAdUnitId("ca-app-pub-4328861976010980~6735154157");
        adView.setAdSize(AdSize.SMART_BANNER);

        // ads parameter
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        adView.setLayoutParams(params1);

        RelativeLayout layout = new RelativeLayout(this);

        // layout list
        layout.addView(new GamePanel(this));
        layout.addView(adView);

        setContentView(layout);

        // ads request
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("500A7F7E5E0ACFCDF3E59A5C66E921E0")
                .build();


        adView.loadAd(request);


        /////

        //setContentView(new GamePanel(this));


    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first
        System.exit(0);

    }

}
