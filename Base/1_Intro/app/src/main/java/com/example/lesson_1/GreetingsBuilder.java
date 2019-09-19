package com.example.lesson_1;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;

public class GreetingsBuilder {
    private final int MORNING = 5;
    private final int AFTERNOON = 12;
    private final int EVENING = 18;
    private final int NIGHT = 21;

    String getResult (Context context) {
        //int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int currentHour = new Date(System.currentTimeMillis()).getHours();
        String result;

        if(MORNING <= currentHour && currentHour < AFTERNOON){
            return context.getString(R.string.good_morning);
            //при проверке пожалуйста уточните, почему в коде мы присваиваем значение созданной строке, а не сразу отправляем его.
        } else if (AFTERNOON <= currentHour && currentHour < EVENING){
            result = context.getString(R.string.good_afternoon);
        }else if (EVENING <= currentHour && currentHour < NIGHT){
            result = context.getString(R.string.good_evening);
        } else {
            result = context.getString(R.string.good_night);
        }
        return result;
    }
}
