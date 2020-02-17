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

public class SetupActivity extends AppCompatActivity {


    private EditText editChangeCity;
    private Button okBtn, cancelBtn;
    protected Switch switchChangeOfRain;
    protected Switch switchWind;
    protected Switch switchHumidity;
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
                Intent data = ks.getIntentToSend(SetupActivity.this);
                data.putExtra(KeepState.DATA_FOR_RETURN_KEY, text);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(this.getClass().getSimpleName(), "обрабатываем нажатие на CANCEL");
                Intent data = ks.getIntentToSend(SetupActivity.this);
                setResult(RESULT_CANCELED, data);
                finish();
            }
        });
    }

    private void intentParse(Intent intent) {
        Log.d(this.getClass().getSimpleName(), "получаем значения из интента");
        Bundle bundle = intent.getBundleExtra(KeepState.SEND_SWITCH_TO_SETUP);
        if (bundle != null) {
            ks.switchGetState(bundle,this);
        }
    }
}
