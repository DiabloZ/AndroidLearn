package com.example.a3_Application_Layouts_HW;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.a3_Application_Layouts_HW.SetupActivity.DATA_FOR_RETURN_KEY;
import static com.example.a3_Application_Layouts_HW.SetupActivity.DATA_FOR_RETURN_KEY_BUNDLE;
import static com.example.a3_Application_Layouts_HW.SetupActivity.DATA_KEY_CHANGE_OF_RAIN;
import static com.example.a3_Application_Layouts_HW.SetupActivity.DATA_KEY_HUMIDITY;
import static com.example.a3_Application_Layouts_HW.SetupActivity.DATA_KEY_WIND;

public class MainActivity extends AppCompatActivity {
    private final int SECOND_ACTIVITY_REQUEST_CODE = 123;

    private TextView currentCity, chanceOfRain, speedWind, humidity;

    private LinearLayout upPanelOptional;

    protected static final String SEND_SWITCH_TO_SETUP = "sendCode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        currentCity = findViewById(R.id.currentCity);
        chanceOfRain = findViewById(R.id.chanceOfRain);
        speedWind = findViewById(R.id.speedWind);
        humidity = findViewById(R.id.humidity);
        upPanelOptional = findViewById(R.id.upPanelOptional);

        Button btnSetup = findViewById(R.id.btnSetup);
        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setToSetupActivityClickBehaviour();
            }
        });
    }

    private void setToSetupActivityClickBehaviour() {
        Intent data = new Intent(MainActivity.this, SetupActivity.class);
        Bundle arguments = getSwitchBundle();
        data.putExtra(SEND_SWITCH_TO_SETUP, arguments);
        setResult(RESULT_OK);
        startActivityForResult(data, SECOND_ACTIVITY_REQUEST_CODE, arguments);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            String text = data != null ? data.getStringExtra(DATA_FOR_RETURN_KEY) : "";
            switch (resultCode) {
                case (RESULT_OK):
                    Bundle bundle = data != null ? data.getBundleExtra(DATA_FOR_RETURN_KEY_BUNDLE) : null;
                    if (bundle != null) switchChange(bundle);
                    if (!text.equals("")) currentCity.setText(text);
                    break;
                case (RESULT_CANCELED):
                    break;
            }
        }
    }

    private void switchChange(Bundle bundle) {
        boolean switchChangeOfRain = bundle.getBoolean(DATA_KEY_CHANGE_OF_RAIN);
        boolean switchWind = bundle.getBoolean(DATA_KEY_WIND);
        boolean switchHumidity = bundle.getBoolean(DATA_KEY_HUMIDITY);

        chanceOfRain.setVisibility(switchChangeOfRain ? View.VISIBLE : View.GONE);
        speedWind.setVisibility(switchWind ? View.VISIBLE : View.GONE);
        humidity.setVisibility(switchHumidity ? View.VISIBLE : View.GONE);
        upPanelOptional.setVisibility(checkVisibilityUpPanel() ? View.GONE : View.VISIBLE);
    }

    private boolean checkVisibilityUpPanel() {
        return chanceOfRain.getVisibility() == View.GONE && speedWind.getVisibility() == View.GONE && humidity.getVisibility() == View.GONE;
    }

    private Bundle getSwitchBundle() {
        Bundle arguments = new Bundle();
        arguments.putBoolean(DATA_KEY_HUMIDITY, humidity.getVisibility() == View.VISIBLE);
        arguments.putBoolean(DATA_KEY_WIND, speedWind.getVisibility() == View.VISIBLE);
        arguments.putBoolean(DATA_KEY_CHANGE_OF_RAIN, chanceOfRain.getVisibility() == View.VISIBLE);
        return arguments;
    }
}
