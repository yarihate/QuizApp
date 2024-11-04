package com.yarihate.quizapp.service;

import static android.content.Context.MODE_PRIVATE;
import static com.yarihate.quizapp.dto.Constants.CATEGORY_STATISTIC;
import static com.yarihate.quizapp.dto.Constants.USER_STATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.yarihate.quizapp.dto.state.CategoryStatistic;
import com.yarihate.quizapp.dto.state.QuizStatistic;
import com.yarihate.quizapp.dto.state.UpdateCategoryStatistic;
import com.yarihate.quizapp.dto.state.UserState;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class UpdateCategoryStatisticService {
    private static final Gson gson = new Gson();
    private final SharedPreferences preferences;

    @Inject
    public UpdateCategoryStatisticService(@ApplicationContext Context context) {
        preferences = context.getSharedPreferences(USER_STATE, MODE_PRIVATE);
    }

    public void update(UpdateCategoryStatistic updateCategoryStatistic) {
        int categoryId = updateCategoryStatistic.getCategoryId();
        int quizId = updateCategoryStatistic.getQuizId();
        int questionsCount = updateCategoryStatistic.getQuestionsCount();
        int rightAnswersCount = updateCategoryStatistic.getRightAnswersCount();
        int totalQuizCount = updateCategoryStatistic.getTotalQuizCount();

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
            CategoryStatistic categoryStatistic = new CategoryStatistic(categoryId, totalQuizCount);
            categoryStatistic.addQuizStat(quizStatistic);
            categoryStatistics.add(categoryStatistic);
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CATEGORY_STATISTIC, gson.toJson(userState));
        editor.apply();
    }
}
