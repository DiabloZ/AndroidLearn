package com.example.p010_listener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // найдем View-элементы
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        // создание обработчика
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnOk:
                        tvOut.setText("Нажата кнопка Ок");
                        break;
                    case R.id.btnCancel:
                        tvOut.setText("Нажата кнопка Cancel");
                        break;
                }

            }
        };
        btnOk.setOnClickListener(oclBtn);
        btnCancel.setOnClickListener(oclBtn);
    }
}
