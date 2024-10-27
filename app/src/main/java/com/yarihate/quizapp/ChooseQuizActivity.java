package com.yarihate.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yarihate.quizapp.dto.Category;
import com.yarihate.quizapp.dto.Quiz;
import com.yarihate.quizapp.service.CategoryService;

import java.util.Collections;
import java.util.List;

public class ChooseQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_quiz_activity);

        LinearLayout quizLayout = findViewById(R.id.quiz_layout);
        int categoryId = getIntent().getIntExtra("category_id", -1);
        if (categoryId == -1) {
            //todo не знаю что тут делать
            return;
        }

        List<Category> categories = CategoryService.getCategories();
        List<Quiz> quizzes = categories.stream().filter(v -> v.getId() == categoryId)
                .findFirst()
                .map(Category::getQuizzes)
                .orElse(Collections.emptyList());

        quizzes.forEach(quiz -> {
            LinearLayout quizCard = createQuizCard(quiz);
            quizCard.setTag(quiz);
            quizLayout.addView(quizCard);

            quizCard.setOnClickListener(v -> {
                Quiz selectedQuiz = (Quiz) v.getTag();
                Intent intent = new Intent(ChooseQuizActivity.this, QuestionActivity.class);
                intent.putExtra("category_id", categoryId);
                intent.putExtra("quiz_id", selectedQuiz.getId());
                startActivity(intent);
            });
        });
    }

    private LinearLayout createQuizCard(Quiz quiz) {
        // Создаём корневой LinearLayout для карточки
        LinearLayout card = new LinearLayout(this);
        card.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setBackgroundResource(R.drawable.card_background);
        card.setBackgroundTintList(getResources().getColorStateList(quiz.getBackgroundColor()));
        card.setPadding(16, 16, 16, 16);
        card.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        ((LinearLayout.LayoutParams) card.getLayoutParams()).setMargins(0, 0, 0, 16);

        // Добавляем ImageView
        ImageView icon = new ImageView(this);
        icon.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        icon.setImageResource(quiz.getIcon());
        card.addView(icon);

        // Добавляем TextView
        TextView title = new TextView(this);
        title.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        title.setText(quiz.getTitle());
        title.setTextSize(18);
        title.setTextColor(quiz.getTitleColor());
        title.setPadding(8, 0, 0, 0);
        card.addView(title);
        return card;
    }
}