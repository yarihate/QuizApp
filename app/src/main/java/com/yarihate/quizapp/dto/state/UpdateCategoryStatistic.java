package com.yarihate.quizapp.dto.state;

public class UpdateCategoryStatistic {
    private int categoryId;
    private int quizId;
    private int questionsCount;
    private int rightAnswersCount;
    private int totalQuizCount;

    private UpdateCategoryStatistic(Builder builder) {
        this.categoryId = builder.categoryId;
        this.quizId = builder.quizId;
        this.questionsCount = builder.questionsCount;
        this.rightAnswersCount = builder.rightAnswersCount;
        this.totalQuizCount = builder.totalQuizCount;
    }

    public static class Builder {
        private int categoryId;
        private int quizId;
        private int questionsCount;
        private int rightAnswersCount;
        private int totalQuizCount;

        public Builder categoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder quizId(int quizId) {
            this.quizId = quizId;
            return this;
        }

        public Builder questionsCount(int questionsCount) {
            this.questionsCount = questionsCount;
            return this;
        }

        public Builder rightAnswersCount(int rightAnswersCount) {
            this.rightAnswersCount = rightAnswersCount;
            return this;
        }

        public Builder totalQuizCount(int totalQuizCount) {
            this.totalQuizCount = totalQuizCount;
            return this;
        }

        public UpdateCategoryStatistic build() {
            return new UpdateCategoryStatistic(this);
        }
    }

    // Геттеры для полей
    public int getCategoryId() {
        return categoryId;
    }

    public int getQuizId() {
        return quizId;
    }

    public int getQuestionsCount() {
        return questionsCount;
    }

    public int getRightAnswersCount() {
        return rightAnswersCount;
    }

    public int getTotalQuizCount() {
        return totalQuizCount;
    }
}
