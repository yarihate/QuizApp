package com.yarihate.quizapp.service;

import static android.content.Context.MODE_PRIVATE;
import static com.yarihate.quizapp.dto.Constants.CATEGORY_STATISTIC;
import static com.yarihate.quizapp.dto.Constants.USER_STATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.yarihate.quizapp.dto.Quiz;
import com.yarihate.quizapp.dto.state.CategoryStatistic;
import com.yarihate.quizapp.dto.state.QuizStatistic;
import com.yarihate.quizapp.dto.state.UserState;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class GetQuizStatisticService {
    private static final Gson gson = new Gson();
    private final SharedPreferences preferences;

    @Inject
    public GetQuizStatisticService(@ApplicationContext Context context) {
        preferences = context.getSharedPreferences(USER_STATE, MODE_PRIVATE);
    }

    public int getQuizStarsQuantity(Quiz quiz) {
        String categoryStatisticJson = preferences.getString(CATEGORY_STATISTIC, "{}");

        UserState userState = gson.fromJson(categoryStatisticJson, UserState.class);

        List<CategoryStatistic> categoryStatistics = userState.getCategoryStatistics();

        return categoryStatistics.stream()
                .filter(v -> v.getCategoryId() == quiz.getCategoryId())
                .findFirst()
                .map(v -> v.getQuizStat(quiz.getId()))
                .map(QuizStatistic::getStarsCount)
                .orElse(0);
    }
}