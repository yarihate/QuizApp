package com.yarihate.quizapp.dto.state;

import java.util.HashMap;
import java.util.Map;

public class CategoryStatistic {
    private final int categoryId;
    private int quizTotalCount;
    private int quizPassedCount;
    private final Map<Integer, QuizStatistic> quizStatistics;

    public CategoryStatistic(int categoryId, int quizTotalCount) {
        this.quizPassedCount = 0;
        this.categoryId = categoryId;
        this.quizTotalCount = quizTotalCount;
        this.quizStatistics = new HashMap<>();
    }

    public void addQuizStat(QuizStatistic quizStatistic) {
        QuizStatistic existingQuizStat = this.quizStatistics.get(quizStatistic.getQuizId());
        if (existingQuizStat != null) {
            if (existingQuizStat.getStarsCount() < quizStatistic.getStarsCount()) {
                this.quizStatistics.put(quizStatistic.getQuizId(), quizStatistic);
                if (quizStatistic.isPassed()) {
                    quizPassedCount++;
                }
            }
        } else {
            this.quizStatistics.put(quizStatistic.getQuizId(), quizStatistic);
            if (quizStatistic.isPassed()) {
                quizPassedCount++;
            }
        }
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getQuizTotalCount() {
        return quizTotalCount;
    }


    public int getQuizPassedCount() {
        return quizPassedCount;
    }
}