package com.geekbrains.a1l8_fragments_observer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class MainFragment extends Fragment {

    private Publisher publisher;                              // Обработчик подписок

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        publisher = Publisher.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final EditText textView = view.findViewById(R.id.editText);
        Button button = view.findViewById(R.id.button);       // По этой кнопке будем отправлять события
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textView.getText().toString();
                publisher.notify(text);                       // Отправить изменившуюся строку
            }
        });
        return view;
    }
}
