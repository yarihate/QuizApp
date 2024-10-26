package com.yarihate.quizapp.dto;

public class Category { //todo можно разделить на состояние интерфейса и бизнес данные
    private String title;
    private int icon;
    private int quizQuantity;
    private int quizCompletedQuantity;
    private int backgroundColor;
    private int titleColor;
    private int subtitleColor;

    private Category(Builder builder) {
        this.title = builder.name;
        this.icon = builder.icon;
        this.quizQuantity = builder.quizQuantity;
        this.quizCompletedQuantity = builder.quizCompletedQuantity;
        this.backgroundColor = builder.backgroundColor;
        this.titleColor = builder.titleColor;
        this.subtitleColor = builder.subtitleColor;
    }

    public String getTitle() {
        return title;
    }

    public int getIcon() {
        return icon;
    }

    public int getQuizQuantity() {
        return quizQuantity;
    }

    public int getQuizCompletedQuantity() {
        return quizCompletedQuantity;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public int getSubtitleColor() {
        return subtitleColor;
    }

    public static class Builder {
        private String name;
        private int icon;
        private int quizQuantity;
        private int quizCompletedQuantity;
        private int backgroundColor;
        private int titleColor;
        private int subtitleColor;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setIcon(int icon) {
            this.icon = icon;
            return this;
        }

        public Builder setQuizQuantity(int quizQuantity) {
            this.quizQuantity = quizQuantity;
            return this;
        }

        public Builder setQuizCompletedQuantity(int quizCompletedQuantity) {
            this.quizCompletedQuantity = quizCompletedQuantity;
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

        public Builder setSubtitleColor(int subtitleColor) {
            this.subtitleColor = subtitleColor;
            return this;
        }

        public Category build() {
            return new Category(this);
        }
    }
}
