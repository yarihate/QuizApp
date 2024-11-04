package com.yarihate.quizapp.service;

import static android.content.Context.MODE_PRIVATE;
import static com.yarihate.quizapp.dto.Constants.CATEGORY_STATISTIC;
import static com.yarihate.quizapp.dto.Constants.USER_STATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.yarihate.quizapp.dto.state.CategoryStatistic;
import com.yarihate.quizapp.dto.state.UserState;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class GetCategoryStatisticService {
    private static final Gson gson = new Gson();
    private final SharedPreferences preferences;

    @Inject
    public GetCategoryStatisticService(@ApplicationContext Context context) {
        preferences = context.getSharedPreferences(USER_STATE, MODE_PRIVATE);
    }

    public int getQuizCompletedQuantity(int categoryId) { //todo слишком часто вызывается, посмотреть в дебаге почему так
        String categoryStatisticJson = preferences.getString(CATEGORY_STATISTIC, "{}");

        UserState userState = gson.fromJson(categoryStatisticJson, UserState.class);

        List<CategoryStatistic> categoryStatistics = userState.getCategoryStatistics();

        Optional<CategoryStatistic> categoryStatisticOpt = categoryStatistics.stream()
                .filter(v -> v.getCategoryId() == categoryId)
                .findFirst();

        return categoryStatisticOpt.map(CategoryStatistic::getQuizPassedCount).orElse(0);
    }
}
