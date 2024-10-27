package com.yarihate.quizapp.dto;

import java.util.List;

public class Question {
    private final int id;
    private final int icon;
    private final String questionText;
    private final List<String> answerOptions;
    private final String correctAnswer;
    private final int titleColor;
    private final int backgroundColor;

    private Question(Builder builder) {
        this.id = builder.id;
        this.icon = builder.icon;
        this.questionText = builder.questionText;
        this.answerOptions = builder.answerOptions;
        this.correctAnswer = builder.correctAnswer;
        this.titleColor = builder.titleColor;
        this.backgroundColor = builder.backgroundColor;
    }

    public int getId() {
        return id;
    }

    public int getIcon() {
        return icon;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getAnswerOptions() {
        return answerOptions;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public static class Builder {
        private int id;
        private int icon;
        private String questionText;
        private List<String> answerOptions;
        private String correctAnswer;
        private int titleColor;
        private int backgroundColor;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder setQuestionText(String questionText) {
            this.questionText = questionText;
            return this;
        }

        public Builder setAnswerOptions(List<String> answerOptions) {
            this.answerOptions = answerOptions;
            return this;
        }

        public Builder setCorrectAnswer(String correctAnswer) {
            this.correctAnswer = correctAnswer;
            return this;
        }

        public Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Question build() {
            return new Question(this);
        }
    }
}
