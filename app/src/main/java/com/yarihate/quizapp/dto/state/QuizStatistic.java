package com.yarihate.quizapp.dto.state;


import static com.yarihate.quizapp.dto.Constants.STARS_COUNT_FOR_COMPLETE_QUIZ;
import static com.yarihate.quizapp.service.CountStarsService.countStars;

public class QuizStatistic {
    private final int quizId;
    private final int starsCount;


    public QuizStatistic(int quizId, int questionsCount, int rightAnswersCount) {
        this.quizId = quizId;
        this.starsCount = countStars(questionsCount, rightAnswersCount);
    }

    public int getQuizId() {
        return this.quizId;
    }

    public int getStarsCount() {
        return this.starsCount;
    }

    public boolean isPassed() {
        return this.starsCount >= STARS_COUNT_FOR_COMPLETE_QUIZ;
    }
}