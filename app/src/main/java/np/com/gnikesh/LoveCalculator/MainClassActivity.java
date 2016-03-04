package np.com.gnikesh.LoveCalculator;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainClassActivity extends AppCompatActivity {

    private String fHoroscope = null;
    private String sHoroscope = null;
    private int fHoroscopeId;
    private int sHoroscopeId;
    private int STARTING_YEAR = 1940;
    private static Integer day[] = new Integer[30];
    private static String months [] = {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };
    private static Integer year[] = new Integer[77];
    private String details = null;
    Button calculate;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_class);

        final HoroscopeDetails mHoroscope = new HoroscopeDetails();

        // Font of main screen
        final Typeface displayFont = Typeface.createFromAsset(getAssets(), "fonts/love.ttf");
        Typeface buttonFont = Typeface.createFromAsset(getAssets(), "fonts/lovefont.ttf");
        Typeface startFont = Typeface.createFromAsset(getAssets(), "fonts/lovebold.ttf");

        //First Persons font setting
        TextView fNameDisplay = (TextView)findViewById(R.id.fName);
        TextView fDOBDisplay = (TextView)findViewById(R.id.fDOB);
        TextView letStartText = (TextView)findViewById(R.id.letsStartText);
        fNameDisplay.setTypeface(displayFont);
        fDOBDisplay.setTypeface(displayFont);
        letStartText.setTypeface(startFont);

        //Second Persons font setting
        TextView sNameDisplay = (TextView)findViewById(R.id.sName);
        TextView sDOBDisplay = (TextView) findViewById(R.id.sDOB);
        sNameDisplay.setTypeface(displayFont);
        sDOBDisplay.setTypeface(displayFont);

        //Calculate button font
        Button calcButtonFont = (Button)findViewById(R.id.calculateButton);
        calcButtonFont.setTypeface(startFont);

        SetDetailsOfMatch(mHoroscope);

        // Day and month array initialization
        for (int i = 0; i < 30; i++) day[i] = i + 1;
        for (int i = 0; i < 77; i++) {
            year[i] = STARTING_YEAR;
            STARTING_YEAR++;
        }

        //First Persons Spinner
        final Spinner fMonthSpinner = (Spinner)findViewById(R.id.spinnerMonth);
        final Spinner fDaySpinner = (Spinner)findViewById(R.id.spinnerDate);
        final Spinner fYearSpinner = (Spinner)findViewById(R.id.spinnerYear);

        // Second Person's Spinner
        final Spinner sMonthSpinner = (Spinner)findViewById(R.id.sSpinnerMonth);
        final Spinner sDaySpinner = (Spinner)findViewById(R.id.sSpinnerDate);
        final Spinner sYearSpinner = (Spinner)findViewById(R.id.sSpinnerYear);

        // Spinner Adapter
        ArrayAdapter <String> adapterMonth = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, months){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                // do whatever you want with this text view
                textView.setTextSize(20);
                textView.setTypeface(displayFont);
                return view;
            }
        };

        ArrayAdapter <Integer> adapterDay = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_dropdown_item, day){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                // do whatever you want with this text view
                textView.setTextSize(20);
                textView.setTypeface(displayFont);
                return view;
            }
        };

        ArrayAdapter <Integer> adapterYear = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_dropdown_item, year){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                // do whatever you want with this text view
                textView.setTextSize(20);
                textView.setTypeface(displayFont);
                return view;
            }
        };


        fMonthSpinner.setAdapter(adapterMonth);
        fDaySpinner.setAdapter(adapterDay);
        fYearSpinner.setAdapter(adapterYear);

        sMonthSpinner.setAdapter(adapterMonth);
        sDaySpinner.setAdapter(adapterDay);
        sYearSpinner.setAdapter(adapterYear);

        SetDetailsOfMatch(mHoroscope);

        calculate = (Button)findViewById(R.id.calculateButton);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // First persons Name
                EditText fPersonName = (EditText)findViewById(R.id.fNameInput);
                String fName = fPersonName.getText().toString().trim();
                fPersonName.setTypeface(displayFont);

                // Second persons Name
                EditText sPersonName = (EditText)findViewById(R.id.sNameInput);
                String sName = sPersonName.getText().toString().trim();
                if (isEmpty(fPersonName) == false && isEmpty(sPersonName) == false) {

                    //First persons Detail
                    // First persons Date of Birth
                    int fMonthPosition = fMonthSpinner.getSelectedItemPosition();
                    int fDayPosition = fDaySpinner.getSelectedItemPosition();
                    int fYearPosition = fYearSpinner.getSelectedItemPosition();
                    String fDOBFull = months[fMonthPosition] + " " + day[fDayPosition] + " " + year[fYearPosition];
                    // First Persons Horoscope
                    fHoroscope = mHoroscope.GetHoroscope(day[fDayPosition], GetMonthId(months[fMonthPosition]));

                    //Second persons Detail

                    // Second Persons Date of Birth
                    int sMonthPosition = sMonthSpinner.getSelectedItemPosition();
                    int sDayPosition = sDaySpinner.getSelectedItemPosition();
                    int sYearPosition = sYearSpinner.getSelectedItemPosition();
                    String sDOBFull = months[sMonthPosition] + " " + day[sDayPosition] + " " + year[sYearPosition];
                    // Second Persons Horoscope
                    sHoroscope = mHoroscope.GetHoroscope(day[sDayPosition], GetMonthId(months[sMonthPosition]));

                    // Love percent
                    int percent = mHoroscope.LovePercent(fHoroscope, sHoroscope);
                    String lovePercent = percent + " %";

                    SetDetailsOfMatch(mHoroscope);
                    Intent i = new Intent(getApplicationContext(), DisplayResults.class);
                    i.putExtra("fName", fName);
                    i.putExtra("fHoroscope", fHoroscope);
                    i.putExtra("sName", sName);
                    i.putExtra("sHoroscope", sHoroscope);
                    i.putExtra("details", details);
                    i.putExtra("percent", lovePercent);

                    startActivity(i);
                    finish();
                }
                 else {
                    Toast.makeText(getApplicationContext(), "Please complete the details", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    private int GetMonthId(String month){
        int monthId = 0;
        if (month == "Jan") monthId = 1;
        if (month == "Feb") monthId = 2;
        if (month == "Mar") monthId = 3;
        if (month == "Apr") monthId = 4;
        if (month == "May") monthId = 5;
        if (month == "Jun") monthId = 6;
        if (month == "Jul") monthId = 7;
        if (month == "Aug") monthId = 8;
        if (month == "Sep") monthId = 9;
        if (month == "Oct") monthId = 10;
        if (month == "Nov") monthId = 11;
        if (month == "Dec") monthId = 12;
        return monthId;
    }

    private void SetDetailsOfMatch(HoroscopeDetails mHoroscope) {
        fHoroscopeId = mHoroscope.HoroscopeId(fHoroscope);
        sHoroscopeId = mHoroscope.HoroscopeId(sHoroscope);

        if (fHoroscopeId < sHoroscopeId){
            int temp = fHoroscopeId;
            fHoroscopeId = sHoroscopeId;
            sHoroscopeId = temp;
        }

        if (fHoroscopeId >= sHoroscopeId) {
            if (sHoroscopeId == 0) {
                switch(fHoroscopeId){
                    case 0:
                        details = mHoroscope.mAries[0];
                        break;
                    case 1:
                        details = mHoroscope.mAries[1];
                        break;
                    case 2:
                        details = mHoroscope.mAries[2];
                        break;
                    case 3:
                        details = mHoroscope.mAries[3];
                        break;
                    case 4:
                        details = mHoroscope.mAries[4];
                        break;
                    case 5:
                        details = mHoroscope.mAries[5];
                        break;
                    case 6:
                        details = mHoroscope.mAries[6];
                        break;
                    case 7:
                        details = mHoroscope.mAries[7];
                        break;
                    case 8:
                        details = mHoroscope.mAries[8];
                        break;
                    case 9:
                        details = mHoroscope.mAries[9];
                        break;
                    case 10:
                        details = mHoroscope.mAries[10];
                        break;
                    case 11:
                        details = mHoroscope.mAries[11];
                        break;
                }
            }

            if (sHoroscopeId == 1){
                switch (fHoroscopeId){
                    case 1:
                        details = mHoroscope.mTaurus[0];
                        break;
                    case 2:
                        details = mHoroscope.mTaurus[1];
                        break;
                    case 3:
                        details = mHoroscope.mTaurus[2];
                        break;
                    case 4:
                        details = mHoroscope.mTaurus[3];
                        break;
                    case 5:
                        details = mHoroscope.mTaurus[4];
                        break;
                    case 6:
                        details = mHoroscope.mTaurus[5];
                        break;
                    case 7:
                        details = mHoroscope.mTaurus[6];
                        break;
                    case 8:
                        details = mHoroscope.mTaurus[7];
                        break;
                    case 9:
                        details = mHoroscope.mTaurus[8];
                        break;
                    case 10:
                        details = mHoroscope.mTaurus[9];
                        break;
                    case 11:
                        details = mHoroscope.mTaurus[10];
                        break;
                }

            }

            if (sHoroscopeId == 2){
                switch (fHoroscopeId){
                    case 2:
                        details = mHoroscope.mGemini[0];
                        break;
                    case 3:
                        details = mHoroscope.mGemini[1];
                        break;
                    case 4:
                        details = mHoroscope.mGemini[2];
                        break;
                    case 5:
                        details = mHoroscope.mGemini[3];
                        break;
                    case 6:
                        details = mHoroscope.mGemini[4];
                        break;
                    case 7:
                        details = mHoroscope.mGemini[5];
                        break;
                    case 8:
                        details = mHoroscope.mGemini[6];
                        break;
                    case 9:
                        details = mHoroscope.mGemini[7];
                        break;
                    case 10:
                        details = mHoroscope.mGemini[8];
                        break;
                    case 11:
                        details = mHoroscope.mGemini[9];
                        break;
                }

            }

            if (sHoroscopeId == 3){
                switch (fHoroscopeId){
                    case 3:
                        details = mHoroscope.mCancer[0];
                        break;
                    case 4:
                        details = mHoroscope.mCancer[1];
                        break;
                    case 5:
                        details = mHoroscope.mCancer[2];
                        break;
                    case 6:
                        details = mHoroscope.mCancer[3];
                        break;
                    case 7:
                        details = mHoroscope.mCancer[4];
                        break;
                    case 8:
                        details = mHoroscope.mCancer[5];
                        break;
                    case 9:
                        details = mHoroscope.mCancer[6];
                        break;
                    case 10:
                        details = mHoroscope.mCancer[7];
                        break;
                    case 11:
                        details = mHoroscope.mCancer[8];
                        break;
                }

            }

            if (sHoroscopeId == 4){
                switch (fHoroscopeId){
                    case 4:
                        details = mHoroscope.mLeo[0];
                        break;
                    case 5:
                        details = mHoroscope.mLeo[1];
                        break;
                    case 6:
                        details = mHoroscope.mLeo[2];
                        break;
                    case 7:
                        details = mHoroscope.mLeo[3];
                        break;
                    case 8:
                        details = mHoroscope.mLeo[4];
                        break;
                    case 9:
                        details = mHoroscope.mLeo[5];
                        break;
                    case 10:
                        details = mHoroscope.mLeo[6];
                        break;
                    case 11:
                        details = mHoroscope.mLeo[7];
                        break;

                }
            }

            if (sHoroscopeId == 5){
                switch (fHoroscopeId){
                    case 5:
                        details = mHoroscope.mVirgo[0];
                        break;
                    case 6:
                        details = mHoroscope.mVirgo[1];
                        break;
                    case 7:
                        details = mHoroscope.mVirgo[2];
                        break;
                    case 8:
                        details = mHoroscope.mVirgo[3];
                        break;
                    case 9:
                        details = mHoroscope.mVirgo[4];
                        break;
                    case 10:
                        details = mHoroscope.mVirgo[5];
                        break;
                    case 11:
                        details = mHoroscope.mVirgo[6];
                        break;
                }

            }

            if (sHoroscopeId == 6){
                switch (fHoroscopeId){
                    case 6:
                        details = mHoroscope.mLibra[0];
                        break;
                    case 7:
                        details = mHoroscope.mLibra[1];
                        break;
                    case 8:
                        details = mHoroscope.mLibra[2];
                        break;
                    case 9:
                        details = mHoroscope.mLibra[3];
                        break;
                    case 10:
                        details = mHoroscope.mLibra[4];
                        break;
                    case 11:
                        details = mHoroscope.mLibra[5];
                        break;
                }

            }

            if (sHoroscopeId == 7){
                switch (fHoroscopeId){
                    case 7:
                        details = mHoroscope.mScorpio[0];
                        break;
                    case 8:
                        details = mHoroscope.mScorpio[1];
                        break;
                    case 9:
                        details = mHoroscope.mScorpio[2];
                        break;
                    case 10:
                        details = mHoroscope.mScorpio[3];
                        break;
                    case 11:
                        details = mHoroscope.mScorpio[4];
                        break;

                }
            }

            if (sHoroscopeId == 8) {
                switch (fHoroscopeId){
                    case 8:
                        details = mHoroscope.mSagittarius[0];
                        break;
                    case 9:
                        details = mHoroscope.mSagittarius[1];
                        break;
                    case 10:
                        details = mHoroscope.mSagittarius[2];
                        break;
                    case 11:
                        details = mHoroscope.mSagittarius[3];
                        break;

                }
            }

            if (sHoroscopeId == 9){
                switch (fHoroscopeId){
                    case 9:
                        details = mHoroscope.mCapricorn[0];
                        break;
                    case 10:
                        details = mHoroscope.mCapricorn[0];
                        break;
                    case 11:
                        details = mHoroscope.mCapricorn[0];
                        break;
                }
            }

            if (sHoroscopeId == 10){
                switch (fHoroscopeId){
                    case 10:
                        details = mHoroscope.mAquarius[0];
                        break;
                    case 11:
                        details = mHoroscope.mAquarius[1];
                        break;
                }

            }

            if (sHoroscopeId == 11){
                if (fHoroscopeId == 11) details = mHoroscope.mPisces;
            }
        }
    }


}
