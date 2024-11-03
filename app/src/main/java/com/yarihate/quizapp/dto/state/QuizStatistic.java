package com.yarihate.quizapp.dto.state;


import static com.yarihate.quizapp.dto.Constants.MIN_PERCENT_FOR_1_STAR;
import static com.yarihate.quizapp.dto.Constants.MIN_PERCENT_FOR_2_STARS;
import static com.yarihate.quizapp.dto.Constants.MIN_PERCENT_FOR_3_STARS;

public class QuizStatistic {
    private final int quizId;
    private final int starsCount;


    public QuizStatistic(int quizId, int questionsCount, int rightAnswersCount) {
        this.quizId = quizId;
        this.starsCount = countStars(questionsCount, rightAnswersCount);
    }

    public int getQuizId() {
        return quizId;
    }

    public int getStarsCount() {
        return starsCount;
    }

    private static int countStars(int totalQuestionsCount, int rightAnswersCount) {
        int passedPercent = (rightAnswersCount / totalQuestionsCount) * 100;
        if (passedPercent < MIN_PERCENT_FOR_1_STAR) {
            return 0;
        } else if (passedPercent < MIN_PERCENT_FOR_2_STARS) {
            return 1;
        } else if (passedPercent < MIN_PERCENT_FOR_3_STARS) {
            return 2;
        } else {
            return 3;
        }
    }
}