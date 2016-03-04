package np.com.gnikesh.LoveCalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    private final int splashDelay = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent i = new Intent(SplashScreen.this, MainClassActivity.class);
                startActivity(i);

                finish();
            }
        }, splashDelay);

    }

}
