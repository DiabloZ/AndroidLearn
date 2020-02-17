package com.example.a5_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.a5_Fragments.KeepState.DATA_FOR_RETURN_KEY;
import static com.example.a5_Fragments.KeepState.DATA_FOR_RETURN_KEY_BUNDLE;
import static com.example.a5_Fragments.KeepState.DATA_KEY_CHANGE_OF_RAIN;
import static com.example.a5_Fragments.KeepState.DATA_KEY_HUMIDITY;
import static com.example.a5_Fragments.KeepState.DATA_KEY_WIND;
import static com.example.a5_Fragments.KeepState.SAVE_CITY_NAME;
import static com.example.a5_Fragments.KeepState.SAVE_CITY_NAME_BUNDLE;
import static com.example.a5_Fragments.KeepState.SAVE_INSTANCE_BUNDLE;
import static com.example.a5_Fragments.KeepState.SEND_SWITCH_TO_SETUP;

public class MainActivity extends AppCompatActivity {
    private final int SECOND_ACTIVITY_REQUEST_CODE = 123;

    private TextView currentCity, chanceOfRain, speedWind, humidity;

    private LinearLayout upPanelOptional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        Log.d(this.getClass().getSimpleName(), "успешно запущен");
    }

    private void initViews() {
        currentCity = findViewById(R.id.currentCity);
        chanceOfRain = findViewById(R.id.chanceOfRain);
        speedWind = findViewById(R.id.speedWind);
        humidity = findViewById(R.id.humidity);
        upPanelOptional = findViewById(R.id.upPanelOptional);
        Log.d(this.getClass().getSimpleName(), "Views успешно обработаны");

        Button btnSetup = findViewById(R.id.btnSetup);
        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAndOpenNewActivitySetResult();
                Log.i(this.getClass().getSimpleName(), "кнопка настроек нажата");
            }
        });
    }

    private void createAndOpenNewActivitySetResult() {
        Intent data = new Intent(MainActivity.this, SetupActivity.class);
        Bundle arguments = getSwitchBundleMain(humidity, speedWind, chanceOfRain);
        data.putExtra(SEND_SWITCH_TO_SETUP, arguments);
        setResult(RESULT_OK);
        startActivityForResult(data, SECOND_ACTIVITY_REQUEST_CODE, arguments);
        Log.i(this.getClass().getSimpleName(), "класс успешно отработал и создал интент");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Log.d(this.getClass().getSimpleName(), "приняли результат с второго активити");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            Log.d(this.getClass().getSimpleName(), "вошли внуть IF по реквест коду");
            String text = data != null ? data.getStringExtra(DATA_FOR_RETURN_KEY) : "";
            switch (resultCode) {
                case (RESULT_OK):
                    Log.d(this.getClass().getSimpleName(), "вошли в RESULT_OK");
                    Bundle bundle = data != null ? data.getBundleExtra(DATA_FOR_RETURN_KEY_BUNDLE) : null;
                    if (bundle != null) switchChange(bundle);
                    if (text != null && !text.equals("")) currentCity.setText(text);
                    Log.d(this.getClass().getSimpleName(), "бандл не нулевой, вернули строку - " + text);
                    break;
                case (RESULT_CANCELED):
                    Log.d(this.getClass().getSimpleName(), "вошли в RESULT_CANCELED");
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d(this.getClass().getSimpleName(), "сохраняем состояние");
        outState.putBundle(SAVE_CITY_NAME_BUNDLE, getCityName());
        outState.putBundle(SAVE_INSTANCE_BUNDLE, getSwitchBundleMain(humidity, speedWind, chanceOfRain));
        super.onSaveInstanceState(outState);
        Log.d(this.getClass().getSimpleName(), "сохранили состояние");
    }
    Bundle getSwitchBundleMain(TextView humidity, TextView speedWind, TextView chanceOfRain) {
        Log.d(this.getClass().getSimpleName(), "пакуем состояние свичей в бандл");
        Bundle arguments = new Bundle();
        arguments.putBoolean(DATA_KEY_HUMIDITY, humidity.getVisibility() == View.VISIBLE);
        arguments.putBoolean(DATA_KEY_WIND, speedWind.getVisibility() == View.VISIBLE);
        arguments.putBoolean(DATA_KEY_CHANGE_OF_RAIN, chanceOfRain.getVisibility() == View.VISIBLE);
        return arguments;
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Log.d(this.getClass().getSimpleName(), "восстанавливаем состояние");
        Bundle bundle = savedInstanceState.getBundle(SAVE_INSTANCE_BUNDLE);
        if (bundle != null) switchChange(bundle);
        bundle = savedInstanceState.getBundle(SAVE_CITY_NAME_BUNDLE);
        if (bundle != null) cityNameRestore(bundle);
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(this.getClass().getSimpleName(), "восстановили состояние");
    }

    private void switchChange(Bundle bundle) {
        Log.d(this.getClass().getSimpleName(), "сохраняем переключатели");
        boolean switchChangeOfRain = bundle.getBoolean(DATA_KEY_CHANGE_OF_RAIN);
        boolean switchWind = bundle.getBoolean(DATA_KEY_WIND);
        boolean switchHumidity = bundle.getBoolean(DATA_KEY_HUMIDITY);

        chanceOfRain.setVisibility(switchChangeOfRain ? View.VISIBLE : View.GONE);
        speedWind.setVisibility(switchWind ? View.VISIBLE : View.GONE);
        humidity.setVisibility(switchHumidity ? View.VISIBLE : View.GONE);
        upPanelOptional.setVisibility(checkVisibilityUpPanel() ? View.GONE : View.VISIBLE);
    }

    private void cityNameRestore(Bundle bundle) {
        Log.d(this.getClass().getSimpleName(), "восстанавливаем город");
        String cityName = bundle.getString(SAVE_CITY_NAME);
        currentCity.setText(cityName);

    }

    private boolean checkVisibilityUpPanel() {
        return chanceOfRain.getVisibility() == View.GONE && speedWind.getVisibility() == View.GONE && humidity.getVisibility() == View.GONE;
    }



    private Bundle getCityName() {
        Log.d(this.getClass().getSimpleName(), "сохраняем название города");
        Bundle arguments = new Bundle();
        String city = currentCity.getText() != null ? String.valueOf(currentCity.getText()) : "";
        arguments.putString(SAVE_CITY_NAME, city);
        return arguments;
    }
}