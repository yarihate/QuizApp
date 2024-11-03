package com.yarihate.quizapp;

import static com.yarihate.quizapp.dto.Constants.CATEGORY_STATISTIC;
import static com.yarihate.quizapp.dto.Constants.USER_STATE;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.yarihate.quizapp.dto.Category;
import com.yarihate.quizapp.dto.Question;
import com.yarihate.quizapp.dto.Quiz;
import com.yarihate.quizapp.dto.state.CategoryStatistic;
import com.yarihate.quizapp.dto.state.QuizStatistic;
import com.yarihate.quizapp.dto.state.UserState;
import com.yarihate.quizapp.service.CategoryService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuestionActivity extends AppCompatActivity {
    private static final Gson gson = new Gson();
    private int categoryId;
    private int quizId;
    private int totalQuizCount;
    private int rightAnswersCount = 0;
    private int questionsCount;
    private TextView questionText;
    private LinearLayout answersContainer;
    private String selectedAnswer;
    private int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions_activity);


        categoryId = getIntent().getIntExtra("category_id", -1);
        quizId = getIntent().getIntExtra("quiz_id", -1);
        if (categoryId == -1 || quizId == -1) {
            //todo не знаю что тут делать
            return;

//            Toast.makeText(this, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show();
//            finish(); // Завершаем Activity, если данные не переданы
//            return;
        }

        List<Category> categories = CategoryService.getCategories();
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

        // Отображаем первый вопрос
        if (!questions.isEmpty()) {
            displayQuestion(questions.get(currentQuestionIndex));
        } else {
            Toast.makeText(this, "Вопросы не найдены", Toast.LENGTH_SHORT).show();
        }

        // Обработчик для кнопки "Ответить"
        submitButton.setOnClickListener(v -> {
            if (selectedAnswer == null) {
                Toast.makeText(this, "Выберите ответ", Toast.LENGTH_SHORT).show();
            } else if (selectedAnswer.equals(questions.get(currentQuestionIndex).getCorrectAnswer())) {
                rightAnswersCount++;
                Toast.makeText(this, "Правильно!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Неправильно!", Toast.LENGTH_SHORT).show();
            }

            // Переход к следующему вопросу
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                displayQuestion(questions.get(currentQuestionIndex));
            } else {
                Toast.makeText(this, "Квиз завершён!", Toast.LENGTH_SHORT).show();
                finish(); // Завершаем активити после последнего вопроса
            }
        });
    }

    private void displayQuestion(Question question) {
        questionText.setText(question.getQuestionText());

        // Очищаем контейнер перед добавлением новых кнопок
        answersContainer.removeAllViews();

        // Динамически создаём кнопки для каждого варианта ответа
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
            answerButton.setOnClickListener(v -> {
                selectedAnswer = answer;
                highlightSelectedAnswer(answerButton);
            });

            answersContainer.addView(answerButton);
        }
    }

    private void highlightSelectedAnswer(Button selectedButton) {
        // Сбрасываем фон всех кнопок
        for (int i = 0; i < answersContainer.getChildCount(); i++) {
            View child = answersContainer.getChildAt(i);
            if (child instanceof Button) {
                child.setBackgroundResource(R.drawable.answer_background);
            }
        }
        // Выделяем выбранную кнопку
        selectedButton.setBackgroundColor(Color.LTGRAY);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences preferences = getSharedPreferences(USER_STATE, MODE_PRIVATE);
        String categoryStatisticJson = preferences.getString(CATEGORY_STATISTIC, "{}");

        UserState userState = gson.fromJson(categoryStatisticJson, UserState.class);

        List<CategoryStatistic> categoryStatistics = userState.getCategoryStatistics();

        Optional<CategoryStatistic> categoryStatisticOpt = categoryStatistics.stream()
                .filter(v -> v.getCategoryId() == categoryId)
                .findFirst();
        if (categoryStatisticOpt.isPresent()) {
            CategoryStatistic categoryStatistic = categoryStatisticOpt.get();
            QuizStatistic quizStatistic = new QuizStatistic(quizId, questionsCount, rightAnswersCount);
            categoryStatistic.addQuizStat(quizStatistic);
        } else {
            QuizStatistic quizStatistic = new QuizStatistic(quizId, questionsCount, rightAnswersCount);
            CategoryStatistic categoryStatistic = new CategoryStatistic(categoryId, totalQuizCount, quizStatistic);
            categoryStatistics.add(categoryStatistic);
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CATEGORY_STATISTIC, gson.toJson(userState));
        editor.apply();
    }
}

