package com.yarihate.quizapp.service;

import com.yarihate.quizapp.R;
import com.yarihate.quizapp.dto.Category;
import com.yarihate.quizapp.dto.Question;
import com.yarihate.quizapp.dto.Quiz;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CategoryService {
    @Inject
    GetCategoryStatisticService getCategoryStatisticService;
    private final List<Category> categories = new ArrayList<>();

    @Inject
    public CategoryService(GetCategoryStatisticService getCategoryStatisticService) {
        categories.add(new Category.Builder().setId(1)
                .setName("География")
                .setIcon(R.drawable.ic_geography)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(getCategoryStatisticService.getQuizCompletedQuantity(1))
                .setBackgroundColor(R.color.aqua_mint)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .setQuizList(List.of(new Quiz.Builder()
                        .setId(1)
                        .setCategoryId(1)
                        .setTitle("Страны Балтии")
                        .setIcon(R.drawable.ic_baltic)
                        .setStarsQuantity(0)
                        .setDifficulty(0)
                        .setBackgroundColor(R.color.aqua_mint) //todo наследовать из категории
                        .setTitleColor(R.color.black)
                        .setQuestions(List.of(new Question.Builder()
                                        .setId(1)
                                        //.setIcon()
                                        .setQuestionText("Рига - столица какой страны?")
                                        .setAnswerOptions(List.of("Латвия", "Литва", "Эстония", "Польша"))
                                        .setCorrectAnswer("Латвия")
                                        .setTitleColor(R.color.black)
                                        .setBackgroundColor(R.color.aqua_mint)
                                        .build(),
                                new Question.Builder()
                                        .setId(3)
                                        //.setIcon(R.drawable.ic_tallinn)
                                        .setQuestionText("Таллин - столица какой страны?")
                                        .setAnswerOptions(List.of("Латвия", "Эстония", "Литва", "Финляндия"))
                                        .setCorrectAnswer("Эстония")
                                        .setTitleColor(R.color.black)
                                        .setBackgroundColor(R.color.aqua_mint)
                                        .build(),
                                new Question.Builder()
                                        .setId(4)
                                        //.setIcon(R.drawable.ic_flag)
                                        .setQuestionText("Какой цвет отсутствует на флаге Литвы?")
                                        .setAnswerOptions(List.of("Жёлтый", "Зелёный", "Красный", "Синий"))
                                        .setCorrectAnswer("Синий")
                                        .setTitleColor(R.color.black)
                                        .setBackgroundColor(R.color.aqua_mint)
                                        .build(),
                                new Question.Builder()
                                        .setId(5)
                                        //.setIcon(R.drawable.ic_euro)
                                        .setQuestionText("Какая из стран Балтии первой ввела евро?")
                                        .setAnswerOptions(List.of("Латвия", "Литва", "Эстония", "Финляндия"))
                                        .setCorrectAnswer("Эстония")
                                        .setTitleColor(R.color.black)
                                        .setBackgroundColor(R.color.aqua_mint)
                                        .build()
                        ))
                        .build()))
                .build());
        categories.add(new Category.Builder().setId(2)
                .setName("История")
                .setIcon(R.drawable.ic_history)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(getCategoryStatisticService.getQuizCompletedQuantity(2))
                .setBackgroundColor(R.color.lavender)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .setQuizList(List.of(new Quiz.Builder()
                        .setId(1)
                        .setCategoryId(2)
                        .setTitle("Древний Египет")
                        //.setIcon(R.drawable.ic_baltic)
                        .setStarsQuantity(0)
                        .setDifficulty(0)
                        .setBackgroundColor(R.color.lavender) //todo наследовать из категории
                        .setTitleColor(R.color.black)
                        .build()))
                .build());
        categories.add(new Category.Builder().setId(3)
                .setName("Английский язык")
                .setIcon(R.drawable.ic_english_lang)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(getCategoryStatisticService.getQuizCompletedQuantity(3))
                .setBackgroundColor(R.color.blush)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .setQuizList(List.of(new Quiz.Builder()
                        .setId(1)
                        .setCategoryId(3)
                        .setTitle("B1")
                        //.setIcon(R.drawable.ic_baltic)
                        .setStarsQuantity(0)
                        .setDifficulty(0)
                        .setBackgroundColor(R.color.blush) //todo наследовать из категории
                        .setTitleColor(R.color.black)
                        .build()))
                .build());
        categories.add(new Category.Builder().setId(4)
                .setName("Флаги")
                .setIcon(R.drawable.ic_flags)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(getCategoryStatisticService.getQuizCompletedQuantity(4))
                .setBackgroundColor(R.color.minty)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .setQuizList(List.of(new Quiz.Builder()
                        .setId(1)
                        .setCategoryId(4)
                        .setTitle("Азия")
                        //.setIcon(R.drawable.ic_baltic)
                        .setStarsQuantity(0)
                        .setDifficulty(0)
                        .setBackgroundColor(R.color.minty) //todo наследовать из категории
                        .setTitleColor(R.color.black)
                        .build()))
                .build());
        categories.add(new Category.Builder().setId(5)
                .setName("Животные")
                .setIcon(R.drawable.ic_animals)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(getCategoryStatisticService.getQuizCompletedQuantity(5))
                .setBackgroundColor(R.color.goldie)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .setQuizList(List.of(new Quiz.Builder()
                        .setId(1)
                        .setCategoryId(5)
                        .setTitle("Морские обитатели")
                        //.setIcon(R.drawable.ic_baltic)
                        .setStarsQuantity(0)
                        .setDifficulty(0)
                        .setBackgroundColor(R.color.goldie) //todo наследовать из категории
                        .setTitleColor(R.color.black)
                        .build()))
                .build());
        categories.add(new Category.Builder().setId(6)
                .setName("Русский язык")
                .setIcon(R.drawable.ic_russian_lang)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(getCategoryStatisticService.getQuizCompletedQuantity(6))
                .setBackgroundColor(R.color.sky_lite)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .setQuizList(List.of(new Quiz.Builder()
                        .setId(1)
                        .setCategoryId(6)
                        .setTitle("Семантика слов")
                        //.setIcon(R.drawable.ic_baltic)
                        .setStarsQuantity(0)
                        .setDifficulty(0)
                        .setBackgroundColor(R.color.sky_lite) //todo наследовать из категории
                        .setTitleColor(R.color.black)
                        .build()))
                .build());
        categories.add(new Category.Builder().setId(7)
                .setName("Музыка")
                .setIcon(R.drawable.ic_music)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(getCategoryStatisticService.getQuizCompletedQuantity(7))
                .setBackgroundColor(R.color.lime_mist)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .setQuizList(List.of(new Quiz.Builder()
                        .setId(1)
                        .setCategoryId(7)
                        .setTitle("90-е")
                        //.setIcon(R.drawable.ic_baltic)
                        .setStarsQuantity(0)
                        .setDifficulty(0)
                        .setBackgroundColor(R.color.lime_mist) //todo наследовать из категории
                        .setTitleColor(R.color.black)
                        .build()))
                .build());
        categories.add(new Category.Builder().setId(8)
                .setName("Кино")
                .setIcon(R.drawable.ic_movie)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(getCategoryStatisticService.getQuizCompletedQuantity(8))
                .setBackgroundColor(R.color.lime_mist)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .setQuizList(List.of(new Quiz.Builder()
                        .setId(1)
                        .setCategoryId(8)
                        .setTitle("Триллеры")
                        //.setIcon(R.drawable.ic_baltic)
                        .setStarsQuantity(0)
                        .setDifficulty(0)
                        .setBackgroundColor(R.color.lime_mist) //todo наследовать из категории
                        .setTitleColor(R.color.black)
                        .build()))
                .build());
    }


    public List<Category> getCategories() {
        return categories;
    }
}
