package com.example.a2_weatherApp_HW;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnSetup;

    private TextView currentCity;

    private final int secondActivityRequestCode = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setToSetupActivityClickBehaviour();
    }

    private void initViews() {
        btnSetup = findViewById(R.id.btnSetup);
        currentCity = findViewById(R.id.currentCity);
    }

    private void setToSetupActivityClickBehaviour() {
        btnSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetupActivity.class);
                startActivityForResult(intent, secondActivityRequestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == secondActivityRequestCode && resultCode == RESULT_OK){
            String text = data != null ? data.getStringExtra(SetupActivity.dataForReturnKey) : "";
            currentCity.setText(text);
        }
    }
}
