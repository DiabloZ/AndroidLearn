package com.example.a2_weatherApp_HW;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SetupActivity extends AppCompatActivity {
    private EditText editChangeCity;
    private Button okBtn, cancelBtn;

    final static String dataForReturnKey = "dataForReturn";
    private String isCancelString = "Вы отменили ввод города :(";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup_activity);
        initViews();
        setBehaviourForMainActBtn();
    }



    private void initViews() {
        editChangeCity = findViewById(R.id.changeCity);
        okBtn = findViewById(R.id.okMenu);
        cancelBtn = findViewById(R.id.cancelMenu);
    }
    private void setBehaviourForMainActBtn() {
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editChangeCity.getText().toString();
                Intent data = new Intent();
                data.putExtra(dataForReturnKey, text);
                setResult(RESULT_OK, data);
                finish();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = isCancelString;
                Intent data = new Intent();
                data.putExtra(dataForReturnKey, text);
                setResult(RESULT_CANCELED, data);
                finish();
            }
        });
    }
}
