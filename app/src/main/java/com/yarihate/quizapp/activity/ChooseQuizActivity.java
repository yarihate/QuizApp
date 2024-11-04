package com.yarihate.quizapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yarihate.quizapp.R;
import com.yarihate.quizapp.dto.Category;
import com.yarihate.quizapp.dto.Quiz;
import com.yarihate.quizapp.service.CategoryService;
import com.yarihate.quizapp.service.GetQuizStatisticService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChooseQuizActivity extends AppCompatActivity {
    @Inject
    CategoryService categoryService;
    @Inject
    GetQuizStatisticService getQuizStatisticService;

    private final Map<Quiz, List<ImageView>> starsViewsByQuiz = new HashMap<>();


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

        List<Category> categories = categoryService.getCategories();
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

    @Override
    protected void onResume() {
        super.onResume();
        starsViewsByQuiz.forEach((quiz, starsView) -> {
            for (int i = 0; i < starsView.size(); i++) {
                ImageView starView = starsView.get(i);

                int color;
                if (i < getQuizStatisticService.getQuizStarsQuantity(quiz)) {
                    color = Color.YELLOW;
                } else {
                    color = Color.GRAY;
                }
                starView.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            }
        });
    }

    private LinearLayout createQuizCard(Quiz quiz) {
        // Создаём корневой LinearLayout для карточки
        LinearLayout card = new LinearLayout(this);
        card.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        card.setOrientation(LinearLayout.VERTICAL); // Изменяем на вертикальное расположение
        card.setBackgroundResource(R.drawable.card_background);
        card.setBackgroundTintList(getResources().getColorStateList(quiz.getBackgroundColor()));
        card.setPadding(16, 16, 16, 16);
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

        createStarViews(card, quiz);

        return card;
    }

    private void createStarViews(LinearLayout card, Quiz quiz) {
        LinearLayout starLayout = new LinearLayout(this);
        starLayout.setOrientation(LinearLayout.HORIZONTAL);
        starLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        List<ImageView> starViews = new ArrayList<>(3);

        for (int i = 0; i < 3; i++) {
            ImageView star = new ImageView(this);
            star.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            star.setImageResource(R.drawable.ic_star);
            star.setPadding(4, 0, 4, 0);

            int color;
            if (i < getQuizStatisticService.getQuizStarsQuantity(quiz)) {
                color = Color.YELLOW;
            } else {
                color = Color.GRAY;
            }

            star.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            starViews.add(star);
            starLayout.addView(star);
        }
        starsViewsByQuiz.put(quiz, starViews);
        card.addView(starLayout);
    }
}