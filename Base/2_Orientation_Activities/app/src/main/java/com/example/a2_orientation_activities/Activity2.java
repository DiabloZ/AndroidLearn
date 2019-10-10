package com.example.a2_orientation_activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    private EditText editText;
    private Button toFirstActivityBtn;

    static String dataForReturnKey = "dataForReturnKey";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        initEditText();
        setBehaviourForTo1stActBtn();
    }

    private void initEditText() {
        String text = getIntent().getStringExtra(MainActivity.defaultStaticKey);
        editText.setText(text);
    }

    private void initViews() {
        editText = findViewById(R.id.editText);
        toFirstActivityBtn = findViewById(R.id.sendTo1stActBtn);
    }

    private void setBehaviourForTo1stActBtn() {
        toFirstActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                Intent data = new Intent();
                data.putExtra(dataForReturnKey, text);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}
