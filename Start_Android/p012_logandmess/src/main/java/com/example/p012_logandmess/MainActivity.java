package com.example.p012_logandmess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        buttonSetOnClick();
    }

    private void buttonSetOnClick() {
        Log.d(TAG, "Присваиваем обработчики кнопам");
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    private void initView() {
        Log.d(TAG,"находим View-элементы");
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, "Глядим, чего жмякнули - ");
        switch (v.getId()){

            case R.id.btnOk:
                Log.d(TAG, "Обработали, сетнули текстовой вьюхе - ОК");
                tvOut.setText("Нажата кнопка OK");
                break;
            case R.id.btnCancel:
                Log.d(TAG, "Обработали, сетнули текстовой вьюхе - Cancel");
                tvOut.setText("Нажата кнопка Cancel");
                break;
        }
        Toast.makeText(this, tvOut.getText(),Toast.LENGTH_SHORT).show();
    }
}
