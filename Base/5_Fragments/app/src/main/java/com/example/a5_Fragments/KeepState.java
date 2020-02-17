package com.example.a5_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;

class KeepState {
    final static String DATA_FOR_RETURN_KEY = "dataForReturn";
    final static String DATA_FOR_RETURN_KEY_BUNDLE = "DATA_FOR_RETURN_KEY_BUNDLE";
    final static String DATA_KEY_CHANGE_OF_RAIN = "DATA_KEY_CHANGE_OF_RAIN";
    final static String DATA_KEY_WIND = "DATA_KEY_WIND";
    final static String DATA_KEY_HUMIDITY = "DATA_KEY_HUMIDITY";
    static final String SAVE_CITY_NAME = "saveCity";
    static final String SAVE_CITY_NAME_BUNDLE = "saveCityBundle";
    static final String SAVE_INSTANCE_BUNDLE = "saveBundle";
    static final String SEND_SWITCH_TO_SETUP = "sendCode";


    void switchGetState(Bundle bundle, SetupActivity setupActivity) {
        Log.d(this.getClass().getSimpleName(), "получаем значение свичей");
        boolean changeOfRain = bundle.getBoolean(DATA_KEY_CHANGE_OF_RAIN);
        boolean wind = bundle.getBoolean(DATA_KEY_WIND);
        boolean humidity = bundle.getBoolean(DATA_KEY_HUMIDITY);
        if (!changeOfRain) setupActivity.switchChangeOfRain.setChecked(false);
        if (!wind) setupActivity.switchWind.setChecked(false);
        if (!humidity) setupActivity.switchHumidity.setChecked(false);
    }

    Intent getIntentToSend(SetupActivity setupActivity) {
        Log.d(this.getClass().getSimpleName(), "создаем intent для отправки");
        Intent data = new Intent();
        Bundle arguments = getSwitchBundleSetup(setupActivity.switchChangeOfRain, setupActivity.switchWind, setupActivity.switchHumidity);
        data.putExtra(DATA_FOR_RETURN_KEY_BUNDLE, arguments);
        return data;

    }
    private Bundle getSwitchBundleSetup(Switch switchChangeOfRain, Switch switchWind, Switch switchHumidity) {
        Log.d(this.getClass().getSimpleName(), "отправляем новое состояние свичей");
        Bundle arguments = new Bundle();
        arguments.putBoolean(DATA_KEY_CHANGE_OF_RAIN, switchChangeOfRain.isChecked());
        arguments.putBoolean(DATA_KEY_WIND, switchWind.isChecked());
        arguments.putBoolean(DATA_KEY_HUMIDITY, switchHumidity.isChecked());
        return arguments;
    }
}
