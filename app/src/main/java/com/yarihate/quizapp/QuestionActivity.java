package com.yarihate.quizapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {
    private String correctAnswer = "Латвия"; // Правильный ответ
    private String selectedAnswer = null; // Выбранный ответ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_activity);
        // Находим элементы интерфейса
        Button answer1 = findViewById(R.id.answer_1);
        Button answer2 = findViewById(R.id.answer_2);
        Button answer3 = findViewById(R.id.answer_3);
        Button answer4 = findViewById(R.id.answer_4);
        Button submitButton = findViewById(R.id.submit_answer);

        // Установка обработчиков нажатий для вариантов ответов
        answer1.setOnClickListener(v -> selectedAnswer = answer1.getText().toString());
        answer2.setOnClickListener(v -> selectedAnswer = answer2.getText().toString());
        answer3.setOnClickListener(v -> selectedAnswer = answer3.getText().toString());
        answer4.setOnClickListener(v -> selectedAnswer = answer4.getText().toString());

        // Обработка нажатия на кнопку "Ответить"
        submitButton.setOnClickListener(v -> {
            if (selectedAnswer == null) {
                Toast.makeText(QuestionActivity.this, "Пожалуйста, выберите ответ", Toast.LENGTH_SHORT).show();
            } else if (selectedAnswer.equals(correctAnswer)) {
                Toast.makeText(QuestionActivity.this, "Правильный ответ!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(QuestionActivity.this, "Неправильный ответ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

