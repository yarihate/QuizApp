package com.yarihate.quizapp.dto.state;

import static com.yarihate.quizapp.dto.Constants.STARS_COUNT_FOR_COMPLETE_QUIZ;

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
            if (existingQuizStat.getStarsCount() == STARS_COUNT_FOR_COMPLETE_QUIZ) {
                return; //do not update quizPassedCount, if quiz was already passed
            } else {
                this.quizStatistics.put(quizStatistic.getQuizId(), quizStatistic);
                if (quizStatistic.getStarsCount() == STARS_COUNT_FOR_COMPLETE_QUIZ) {
                    quizPassedCount++;
                }
                //todo а если было 2 звезды, а стала 1, то обновлять отображение звезд?
            }
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