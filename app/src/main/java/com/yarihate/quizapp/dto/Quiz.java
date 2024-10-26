package com.yarihate.quizapp.dto;

public class Quiz {
    private String categoryTitle;
    private String title;
    private int icon;
    private int starsQuantity;
    private int difficulty;
    private int backgroundColor;
    private int titleColor;

    private Quiz(Builder builder) {
        this.categoryTitle = builder.categoryTitle;
        this.title = builder.title;
        this.icon = builder.icon;
        this.starsQuantity = builder.starsQuantity;
        this.difficulty = builder.difficulty;
        this.backgroundColor = builder.backgroundColor;
        this.titleColor = builder.titleColor;
    }

    public String getCategoryTitle() {
        return categoryTitle;
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

    public static class Builder {
        private String categoryTitle;
        private String title;
        private int icon;
        private int starsQuantity;
        private int difficulty;
        private int backgroundColor;
        private int titleColor;

        public Builder setCategoryTitle(String categoryTitle) {
            this.categoryTitle = categoryTitle;
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

        public Quiz build() {
            return new Quiz(this);
        }
    }
}
