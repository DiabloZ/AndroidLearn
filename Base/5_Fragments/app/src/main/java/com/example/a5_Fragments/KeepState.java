package com.example.a5_Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class KeepState {
    protected final static String DATA_FOR_RETURN_KEY = "dataForReturn";
    protected final static String DATA_FOR_RETURN_KEY_BUNDLE = "DATA_FOR_RETURN_KEY_BUNDLE";
    protected final static String DATA_KEY_CHANGE_OF_RAIN = "DATA_KEY_CHANGE_OF_RAIN";
    protected final static String DATA_KEY_WIND = "DATA_KEY_WIND";
    protected final static String DATA_KEY_HUMIDITY = "DATA_KEY_HUMIDITY";
    protected static final String SAVE_CITY_NAME = "saveCity";
    protected static final String SAVE_CITY_NAME_BUNDLE = "saveCityBundle";
    protected static final String SAVE_INSTANCE_BUNDLE = "saveBundle";
    protected static final String SEND_SWITCH_TO_SETUP = "sendCode";


    Intent getIntentToSend(Switch switchChangeOfRain, Switch switchWind, Switch switchHumidity) {
        Log.d(this.getClass().getSimpleName(), "создаем intent для отправки");
        Intent data = new Intent();
        Bundle arguments = getSwitchBundleSetup(switchChangeOfRain, switchWind, switchHumidity);
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
    Bundle getSwitchBundleMain(TextView humidity, TextView speedWind, TextView chanceOfRain) {
        Log.d(this.getClass().getSimpleName(), "пакуем состояние свичей в бандл");
        Bundle arguments = new Bundle();
        arguments.putBoolean(DATA_KEY_HUMIDITY, humidity.getVisibility() == View.VISIBLE);
        arguments.putBoolean(DATA_KEY_WIND, speedWind.getVisibility() == View.VISIBLE);
        arguments.putBoolean(DATA_KEY_CHANGE_OF_RAIN, chanceOfRain.getVisibility() == View.VISIBLE);
        return arguments;
    }
}
