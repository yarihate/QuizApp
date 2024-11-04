package com.yarihate.quizapp.service;

import static com.yarihate.quizapp.dto.Constants.CATEGORY_STATISTIC;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.yarihate.quizapp.dto.state.CategoryStatistic;
import com.yarihate.quizapp.dto.state.QuizStatistic;
import com.yarihate.quizapp.dto.state.UpdateCategoryStatistic;
import com.yarihate.quizapp.dto.state.UserState;

import java.util.List;
import java.util.Optional;

public class UpdateCategoryStatisticService {
    private static final Gson gson = new Gson();

    public static void update(SharedPreferences preferences, UpdateCategoryStatistic updateCategoryStatistic) {
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
            CategoryStatistic categoryStatistic = new CategoryStatistic(categoryId, totalQuizCount, quizStatistic);
            categoryStatistics.add(categoryStatistic);
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(CATEGORY_STATISTIC, gson.toJson(userState));
        editor.apply();
    }
}
