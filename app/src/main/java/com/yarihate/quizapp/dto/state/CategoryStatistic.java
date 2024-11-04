package com.yarihate.quizapp.dto.state;

import static com.yarihate.quizapp.dto.Constants.STARS_COUNT_FOR_COMPLETE_QUIZ;

import java.util.HashMap;
import java.util.Map;

public class CategoryStatistic {
    private int categoryId;
    private int quizTotalCount;
    private int quizPassedCount;
    private Map<Integer, QuizStatistic> quizStatistics;

    public CategoryStatistic(int categoryId, int quizTotalCount, QuizStatistic quizStatistics) {
        this.quizPassedCount = 0;
        this.categoryId = categoryId;
        this.quizTotalCount = quizTotalCount;
        this.quizStatistics = new HashMap<>();
        this.quizStatistics.put(quizStatistics.getQuizId(), quizStatistics);
    }

    public void addQuizStat(QuizStatistic quizStatistic) {
        QuizStatistic existingQuizStat = this.quizStatistics.get(quizStatistic.getQuizId());
        if (existingQuizStat != null) {
            if (quizStatistic.getStarsCount() == STARS_COUNT_FOR_COMPLETE_QUIZ) {
                return; //do not update quizPassedCount, if quiz was already passed
            } //todo а если было 2 звезды, а стала 1, то обновлять отображение звезд?
        } else {
            this.quizStatistics.put(quizStatistic.getQuizId(), quizStatistic);
            if (quizStatistic.getStarsCount() == STARS_COUNT_FOR_COMPLETE_QUIZ) {
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