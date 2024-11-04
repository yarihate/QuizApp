package com.yarihate.quizapp.dto;

import java.util.List;

public class Quiz {
    private int id;
    private int categoryId;
    private String title;
    private int icon;
    private int starsQuantity;
    private int difficulty;
    private int backgroundColor;
    private int titleColor;
    private List<Question> questions;

    private Quiz(Builder builder) {
        this.id = builder.id;
        this.categoryId = builder.categoryId;
        this.title = builder.title;
        this.icon = builder.icon;
        this.starsQuantity = builder.starsQuantity;
        this.difficulty = builder.difficulty;
        this.backgroundColor = builder.backgroundColor;
        this.titleColor = builder.titleColor;
        this.questions = builder.questions;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

    public int getStarsQuantity() {
        return starsQuantity;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public static class Builder {
        private int id;
        private int categoryId;
        private String title;
        private int icon;
        private int starsQuantity;
        private int difficulty;
        private int backgroundColor;
        private int titleColor;
        private List<Question> questions;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder setStarsQuantity(int starsQuantity) {
            this.starsQuantity = starsQuantity;
            return this;
        }

        public Builder setDifficulty(int difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public Builder setBackgroundColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder setTitleColor(int titleColor) {
            this.titleColor = titleColor;
            return this;
        }

        public Builder setQuestions(List<Question> questions) {
            this.questions = questions;
            return this;
        }

        public Quiz build() {
            return new Quiz(this);
        }
    }
}
