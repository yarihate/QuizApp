package com.yarihate.quizapp.activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yarihate.quizapp.R;
import com.yarihate.quizapp.dto.Category;
import com.yarihate.quizapp.dto.Question;
import com.yarihate.quizapp.dto.Quiz;
import com.yarihate.quizapp.dto.state.UpdateCategoryStatistic;
import com.yarihate.quizapp.service.CategoryService;
import com.yarihate.quizapp.service.UpdateCategoryStatisticService;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class QuestionActivity extends AppCompatActivity {
    @Inject
    CategoryService categoryService;
    @Inject
    UpdateCategoryStatisticService updateCategoryStatisticService;

    private int categoryId;
    private int quizId;
    private int totalQuizCount;
    private int rightAnswersCount = 0;
    private int questionsCount;
    private TextView questionText;
    private LinearLayout answersContainer;
    private String selectedAnswer;
    private int currentQuestionIndex = 0;
    private Button selectedAnswewrButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_activity);


        categoryId = getIntent().getIntExtra("category_id", -1);
        quizId = getIntent().getIntExtra("quiz_id", -1);
        if (categoryId == -1 || quizId == -1) {
            Toast.makeText(this, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        List<Category> categories = categoryService.getCategories();
        List<Quiz> quizzes = categories.stream().filter(v -> v.getId() == categoryId)
                .findFirst()
                .map(Category::getQuizzes)
                .orElse(Collections.emptyList());
        List<Question> questions = quizzes.stream().filter(v -> v.getId() == quizId)
                .findFirst()
                .map(Quiz::getQuestions)
                .orElse(Collections.emptyList());

        totalQuizCount = quizzes.size();
        questionsCount = questions.size();

        questionText = findViewById(R.id.question_text);
        answersContainer = findViewById(R.id.answers_container);
        Button submitButton = findViewById(R.id.submit_answer);

        if (!questions.isEmpty()) {
            displayQuestion(questions.get(currentQuestionIndex));
        } else {
            Toast.makeText(this, "Вопросы не найдены", Toast.LENGTH_SHORT).show();
        }

        submitButton.setOnClickListener(v -> {
            if (selectedAnswer == null) {
                Toast.makeText(this, "Выберите ответ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (selectedAnswer.equals(questions.get(currentQuestionIndex).getCorrectAnswer())) {
                rightAnswersCount++;
                selectedAnswewrButton.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            } else {
                selectedAnswewrButton.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }

            new Handler().postDelayed(() -> {
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    displayQuestion(questions.get(currentQuestionIndex));
                } else {
                    Toast.makeText(this, "Квиз завершён!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }, 1000);
        });
    }

    private void displayQuestion(Question question) {
        questionText.setText(question.getQuestionText());
        answersContainer.removeAllViews();

        for (String answer : question.getAnswerOptions()) {
            Button answerButton = new Button(this);
            answerButton.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            answerButton.setText(answer);
            answerButton.setBackgroundResource(R.drawable.answer_background);
            answerButton.setBackgroundTintList(getResources().getColorStateList(question.getBackgroundColor()));
            answerButton.setTextColor(question.getTitleColor());

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) answerButton.getLayoutParams();
            params.setMargins(0, 0, 0, 16);
            answerButton.setLayoutParams(params);

            answerButton.setOnClickListener(v -> {
                selectedAnswewrButton = answerButton;
                selectedAnswer = answer;
                highlightSelectedAnswer(answerButton, question.getBackgroundColor());
            });

            answersContainer.addView(answerButton);
        }
    }

    private void highlightSelectedAnswer(Button selectedButton, int questionBackGroundColour) {
        for (int i = 0; i < answersContainer.getChildCount(); i++) {
            View child = answersContainer.getChildAt(i);
            if (child instanceof Button) {
                Button button = (Button) child;
                button.setBackgroundResource(R.drawable.answer_background);
                button.setBackgroundTintList(getResources().getColorStateList(questionBackGroundColour));
            }
        }
        selectedButton.setBackgroundTintList(ColorStateList.valueOf(Color.CYAN));
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateCategoryStatisticService.update(new UpdateCategoryStatistic.Builder()
                .categoryId(categoryId)
                .quizId(quizId)
                .questionsCount(questionsCount)
                .rightAnswersCount(rightAnswersCount)
                .totalQuizCount(totalQuizCount)
                .build());
    }
}

