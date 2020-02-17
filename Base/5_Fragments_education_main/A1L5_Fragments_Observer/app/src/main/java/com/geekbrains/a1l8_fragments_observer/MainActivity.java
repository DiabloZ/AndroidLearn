package com.geekbrains.a1l8_fragments_observer;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Publisher publisher = Publisher.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем фрагменты
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        MainFragment mainFragment = new MainFragment();
        // Подписываем фрагменты
        publisher.subscribe(fragment1);
        publisher.subscribe(fragment2);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // Добавить фрагменты
        fragmentTransaction.add(R.id.fragment_main, mainFragment);
        fragmentTransaction.add(R.id.fragment_1, fragment1);
        fragmentTransaction.add(R.id.fragment_2, fragment2);
        // Закрыть транзакцию
        fragmentTransaction.commit();
    }
}
