package com.ntyler.haveyoubeentopunters;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;


public class MainActivity extends Activity implements OnClickListener {
    private static final String TAG = "Main_Screen";
    public static final String PREFS_NAME = "PuntsPreferences";

    Time mCurrentTime;
    Time mLastPuntsVisit;
    TextView mPuntsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_main);

        SharedPreferences data = getSharedPreferences(PREFS_NAME, 0);
        long lastPuntsVisit = data.getLong("lastPuntsVisit", 0);
        Log.d(TAG, "onCreate");
        mLastPuntsVisit = new Time();
        mLastPuntsVisit.set(lastPuntsVisit);
        Log.d(TAG, mLastPuntsVisit.toString());

        mCurrentTime = new Time();

        mPuntsText = (TextView) findViewById(R.id.punts_time);
        View puntsButton = findViewById(R.id.punts_button);
        puntsButton.setOnClickListener(this);



    }

    @Override
    protected  void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        mCurrentTime.setToNow();
        if (mLastPuntsVisit.toMillis(true) != 0) {
            double currentTime = mCurrentTime.toMillis(false);
            double lastPuntsVisit = mLastPuntsVisit.toMillis(false);
            double hoursSince = ((currentTime - lastPuntsVisit) / 3600000.0);
            mPuntsText.setText("Hours: " + ((Double)hoursSince).toString());
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.punts_button:
                mCurrentTime.setToNow();
                mLastPuntsVisit.setToNow();
                SharedPreferences data = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = data.edit();
                editor.putLong("lastPuntsVisit", mLastPuntsVisit.toMillis(true));
                editor.commit();
                double currentTime = mCurrentTime.toMillis(false);
                double lastPuntsVisit = mLastPuntsVisit.toMillis(false);
                double hoursSince = ((currentTime - lastPuntsVisit) / 3600000.0);
                String result = ((Double)hoursSince).toString();
                mPuntsText.setText("Hours: " + result);

        }
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        Log.d(TAG, "Saving Bundle");
        super.onSaveInstanceState(state);
    }
}
