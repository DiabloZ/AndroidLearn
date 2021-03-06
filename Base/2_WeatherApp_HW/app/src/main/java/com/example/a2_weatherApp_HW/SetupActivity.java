package com.example.a2_weatherApp_HW;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SetupActivity extends AppCompatActivity {

    protected final static String DATA_FOR_RETURN_KEY = "dataForReturn";
    protected final static String DATA_FOR_RETURN_KEY_BUNDLE = "DATA_FOR_RETURN_KEY_BUNDLE";
    protected final static String DATA_KEY_CHANGE_OF_RAIN = "DATA_KEY_CHANGE_OF_RAIN";
    protected final static String DATA_KEY_WIND = "DATA_KEY_WIND";
    protected final static String DATA_KEY_HUMIDITY = "DATA_KEY_HUMIDITY";

    private EditText editChangeCity;
    private Button okBtn, cancelBtn;
    private Switch switchChangeOfRain, switchWind, switchHumidity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_activity);
        initViews();
        setBehaviourForMainActBtn();
        intentParse(getIntent());
    }

    private void intentParse(Intent intent) {
        Bundle bundle = intent.getBundleExtra(MainActivity.SEND_SWITCH_TO_SETUP);
        if (bundle != null) {
            switchMemory(bundle);
        }
    }


    private void switchMemory(Bundle bundle) {
        boolean changeOfRain = bundle.getBoolean(DATA_KEY_CHANGE_OF_RAIN);
        boolean wind = bundle.getBoolean(DATA_KEY_WIND);
        boolean humidity = bundle.getBoolean(DATA_KEY_HUMIDITY);
        if (!changeOfRain) switchChangeOfRain.setChecked(false);
        if (!wind) switchWind.setChecked(false);
        if (!humidity) switchHumidity.setChecked(false);
    }

    private void initViews() {
        editChangeCity = findViewById(R.id.changeCity);
        okBtn = findViewById(R.id.okMenu);
        cancelBtn = findViewById(R.id.cancelMenu);
        switchChangeOfRain = findViewById(R.id.switchChanceOfRain);
        switchWind = findViewById(R.id.switchWind);
        switchHumidity = findViewById(R.id.switchHumidity);
    }

    private void setBehaviourForMainActBtn() {
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editChangeCity.getText().toString();
                Intent data = getIntentToSend();
                data.putExtra(DATA_FOR_RETURN_KEY, text);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = getIntentToSend();
                setResult(RESULT_CANCELED, data);
                finish();
            }
        });
    }

    private Intent getIntentToSend() {
        Intent data = new Intent();
        Bundle arguments = getSwitchBundle();
        data.putExtra(DATA_FOR_RETURN_KEY_BUNDLE, arguments);
        return data;
    }

    private Bundle getSwitchBundle() {
        Bundle arguments = new Bundle();
        arguments.putBoolean(DATA_KEY_CHANGE_OF_RAIN, switchChangeOfRain.isChecked());
        arguments.putBoolean(DATA_KEY_WIND, switchWind.isChecked());
        arguments.putBoolean(DATA_KEY_HUMIDITY, switchHumidity.isChecked());
        return arguments;
    }
}
