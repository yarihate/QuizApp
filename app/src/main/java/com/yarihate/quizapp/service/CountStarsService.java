package com.yarihate.quizapp.service;

import static com.yarihate.quizapp.dto.Constants.MIN_PERCENT_FOR_1_STAR;
import static com.yarihate.quizapp.dto.Constants.MIN_PERCENT_FOR_2_STARS;
import static com.yarihate.quizapp.dto.Constants.MIN_PERCENT_FOR_3_STARS;

public class CountStarsService {
    public static int countStars(float totalQuestionsCount, float rightAnswersCount) {
        if (totalQuestionsCount == 0) return 0;
        float passedPercent = (rightAnswersCount / totalQuestionsCount) * 100;
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
