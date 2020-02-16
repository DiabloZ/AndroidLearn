package com.example.a5_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.a5_Fragments.KeepState.DATA_FOR_RETURN_KEY;
import static com.example.a5_Fragments.KeepState.DATA_KEY_CHANGE_OF_RAIN;
import static com.example.a5_Fragments.KeepState.DATA_KEY_HUMIDITY;
import static com.example.a5_Fragments.KeepState.DATA_KEY_WIND;
import static com.example.a5_Fragments.KeepState.SEND_SWITCH_TO_SETUP;

public class SetupActivity extends AppCompatActivity {


    private EditText editChangeCity;
    private Button okBtn, cancelBtn;
    private Switch switchChangeOfRain, switchWind, switchHumidity;
    private KeepState ks = new KeepState();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(this.getClass().getSimpleName(), "запустили второе активити");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_activity);
        initViews();
        setBehaviourForMainActBtn();
        intentParse(getIntent());

    }

    private void intentParse(Intent intent) {
        Log.d(this.getClass().getSimpleName(), "получаем значения из интента");
        Bundle bundle = intent.getBundleExtra(SEND_SWITCH_TO_SETUP);
        if (bundle != null) {
            switchMemory(bundle);
        }
    }


    private void switchMemory(Bundle bundle) {
        Log.d(this.getClass().getSimpleName(), "получаем значение свичей");
        boolean changeOfRain = bundle.getBoolean(DATA_KEY_CHANGE_OF_RAIN);
        boolean wind = bundle.getBoolean(DATA_KEY_WIND);
        boolean humidity = bundle.getBoolean(DATA_KEY_HUMIDITY);
        if (!changeOfRain) switchChangeOfRain.setChecked(false);
        if (!wind) switchWind.setChecked(false);
        if (!humidity) switchHumidity.setChecked(false);
    }

    private void initViews() {
        Log.d(this.getClass().getSimpleName(), "инициализируем вьюхи");
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
                Log.d(this.getClass().getSimpleName(), "обрабатываем нажатие на ОК");
                String text = editChangeCity.getText().toString();
                Intent data = ks.getIntentToSend(switchChangeOfRain,switchWind,switchHumidity);
                data.putExtra(DATA_FOR_RETURN_KEY, text);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(this.getClass().getSimpleName(), "обрабатываем нажатие на CANCEL");
                Intent data = ks.getIntentToSend(switchChangeOfRain,switchWind,switchHumidity);
                setResult(RESULT_CANCELED, data);
                finish();
            }
        });
    }
}
