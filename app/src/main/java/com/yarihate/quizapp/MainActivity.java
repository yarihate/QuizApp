package com.yarihate.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // загрузка интерфейса из файла activity_main.xml
        setContentView(R.layout.scroll_view_test);


//        setContentView(R.layout.second_layout);
//        TextView textView = findViewById(R.id.header);
//        // переустанавливаем у него текст
//        textView.setText("Hello from Java!");
    }
}