package com.geekbrains.a1l5_fragmentretaininstance;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class TimerFragment extends Fragment {
    private static final long DECSECONDS_IN_HOUR = 36000;
    private static final long DECSECONDS_IN_MUNUTE = 600;
    private static final long DECSECONDS_IN_SECOND = 10;
    private static final int DELAY = 100;
    private long decseconds = 0;     // Счетчик
    private boolean running=false;
    private TextView textHour;
    private TextView textMinute;
    private TextView textSecond;
    private TextView textDecsecond;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);

        initViews(view);
        setupStartBtn(view);
        setupStopBtn(view);
        setupResetBtn(view);

        runTimer();
    }

    private void initViews(View view) {
        textHour = view.findViewById(R.id.textHour);
        textMinute = view.findViewById(R.id.textMinute);
        textSecond = view.findViewById(R.id.textSecond);
        textDecsecond = view.findViewById(R.id.textDecsecond);
    }

    private void setupStartBtn(View view) {
        Button start = view.findViewById(R.id.buttonStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;     // Запустить таймер
            }
        });
    }

    private void setupStopBtn(View view) {
        Button stop =  view.findViewById(R.id.buttonStop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;    // Остановить таймер
            }
        });
    }

    private void setupResetBtn(View view) {
        Button reset = view.findViewById(R.id.buttonReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;    // Сброс
                decseconds = 0;
            }
        });
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long hour = decseconds / DECSECONDS_IN_HOUR;
                long minute = decseconds / DECSECONDS_IN_MUNUTE;
                long second = decseconds / DECSECONDS_IN_SECOND;
                long decsecond = decseconds % DECSECONDS_IN_SECOND;
                textHour.setText(String.format(Locale.getDefault(), "%02d", hour));
                textMinute.setText(String.format(Locale.getDefault(),"%02d", minute));
                textSecond.setText(String.format(Locale.getDefault(),"%02d", second));
                textDecsecond.setText(String.format(Locale.getDefault(),"%01d", decsecond));
                if (running) {
                    decseconds++;
                }
                handler.postDelayed(this, DELAY);
            }
        });
    }
}
