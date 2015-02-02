package com.ntyler.haveyoubeentopunters;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*savedInstanceState().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();*/
        setContentView(R.layout.activity_main);

        View puntsButton = findViewById(R.id.punts_button);
        puntsButton.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.punts_button:
                Toast.makeText(getApplicationContext(), "You're in Punter\'s, Yay!",
                        Toast.LENGTH_SHORT).show();
        }
    }
}
