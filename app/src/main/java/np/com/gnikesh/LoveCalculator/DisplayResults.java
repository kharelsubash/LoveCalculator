package np.com.gnikesh.LoveCalculator;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayResults extends AppCompatActivity {

    private TextView displayResult;
    private Button calculateAgain;
    private String horoscopeMatchText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        // Fonts of Display Screen
        Typeface displayFont = Typeface.createFromAsset(getAssets(), "fonts/love.ttf");
        Typeface displayFont2  = Typeface.createFromAsset(getAssets(), "fonts/lovefont.ttf");
        Typeface displayFont3  = Typeface.createFromAsset(getAssets(), "fonts/lovebold.ttf");
        TextView showName = (TextView)findViewById(R.id.showName);


        TextView horoscopeMatch = (TextView) findViewById(R.id.textView2);
        TextView lovePercent = (TextView) findViewById(R.id.lovePercent);
        TextView details = (TextView) findViewById(R.id.textView3);
        TextView percentBar = (TextView)findViewById(R.id.percentage);

        showName.setTypeface(displayFont2);
        horoscopeMatch.setTypeface(displayFont);
        lovePercent.setTypeface(displayFont);
        details.setTypeface(displayFont2);



        Intent i = getIntent();
        String fName = i.getStringExtra("fName");
        String sName = i.getStringExtra("sName");
        String fHoroscope = i.getStringExtra("fHoroscope");
        String sHoroscope = i.getStringExtra("sHoroscope");
        String percent = i.getStringExtra("percent");

        String displayName = fName.toUpperCase() + " and " + sName.toUpperCase() + " : ";
        String data = i.getStringExtra("details");
        data = fName.toUpperCase() + ", you are a " + fHoroscope + " and " + sName.toUpperCase() +
            " is a " + sHoroscope + ". " + data;

        showName.setText(displayName);
        percentBar.setText(percent);

        TextView MatchText = (TextView)findViewById(R.id.textView2);
        horoscopeMatchText ="Let's see what the horoscope match tells about you two.";
        MatchText.setText(horoscopeMatchText);

        calculateAgain = (Button)findViewById(R.id.calculateAgainButton);
        calculateAgain.setTypeface(displayFont3);
        calculateAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (getApplicationContext(), MainClassActivity.class);
                startActivity(i);
                finish();
            }
        });

        displayResult = (TextView)findViewById(R.id.textView3);
        displayResult.setText(data);


    }

    private static final int TIME_INTERVAL = 1000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Tap back again to exit", Toast.LENGTH_SHORT).show();
        }
        mBackPressed = System.currentTimeMillis();
    }
}



