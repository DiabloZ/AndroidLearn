package com.example.a2_orientation_activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView counterTextView;
    private Button increaseCounterBtn, toSecondActivityBtn;


    private String counterDataKey = "counterDataKey";
    private int secondActivityRequestCode = 123;

    static String defaultStaticKey = "defaultStaticKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setOnIncreaseBtnClickListenerBehaviour();
        setToSecondActivityBtnClickBehavior();
        Toast.makeText(getBaseContext(), "onCreate", Toast.LENGTH_SHORT).show();
    }

    private void setToSecondActivityBtnClickBehavior() {
        toSecondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra(defaultStaticKey, "hello!");
                //startActivity(intent);
                startActivityForResult(intent, secondActivityRequestCode);
            }
        });
    }

    private void initViews(){
        counterTextView = findViewById(R.id.counterTextView);
        increaseCounterBtn = findViewById(R.id.increaseCounterButton);
        toSecondActivityBtn = findViewById(R.id.toSecondActivityBtn);
    }
    private void setOnIncreaseBtnClickListenerBehaviour(){
        increaseCounterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increaseCounter();
            }
        });
    }

    private void increaseCounter() {
        String textViewText = counterTextView.getText().toString();
        int counterValue = 0;
        try {
            counterValue = Integer.parseInt(textViewText);
        } catch (Exception e){
            e.printStackTrace();
        }
        String newCounterValue = String.valueOf(++counterValue);
        counterTextView.setText(newCounterValue);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == secondActivityRequestCode && resultCode == RESULT_OK){
            String text = data != null ? data.getStringExtra(Activity2.dataForReturnKey) : "";
            counterTextView.setText(text);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(counterDataKey, counterTextView.getText().toString());
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String text = savedInstanceState.getString(counterDataKey, "");
        counterTextView.setText(text);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getBaseContext(), "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getBaseContext(), "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getBaseContext(), "onPause", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getBaseContext(), "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getBaseContext(), "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getBaseContext(), "onRestart", Toast.LENGTH_SHORT).show();
    }
}
