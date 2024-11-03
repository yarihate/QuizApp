package com.yarihate.quizapp.dto.state;

import java.util.ArrayList;
import java.util.List;

public class UserState {
    private final List<CategoryStatistic> categoryStatistics = new ArrayList<>();

    public UserState() {
    }

    public List<CategoryStatistic> getCategoryStatistics() {
        return categoryStatistics;
    }
}
