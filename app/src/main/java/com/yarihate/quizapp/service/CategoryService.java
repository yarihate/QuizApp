package com.yarihate.quizapp.service;

import com.yarihate.quizapp.R;
import com.yarihate.quizapp.dto.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService {
    private static final List<Category> categories;

    static {
        categories = new ArrayList<>();
        categories.add(new Category.Builder()
                .setName("География")
                .setIcon(R.drawable.ic_geography)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(0)
                .setBackgroundColor(R.color.aqua_mint)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .build());
        categories.add(new Category.Builder()
                .setName("История")
                .setIcon(R.drawable.ic_history)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(0)
                .setBackgroundColor(R.color.lavender)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .build());
        categories.add(new Category.Builder()
                .setName("Английский язык")
                .setIcon(R.drawable.ic_english_lang)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(0)
                .setBackgroundColor(R.color.blush)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .build());
        categories.add(new Category.Builder()
                .setName("Флаги")
                .setIcon(R.drawable.ic_flags)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(0)
                .setBackgroundColor(R.color.minty)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .build());
        categories.add(new Category.Builder()
                .setName("Животные")
                .setIcon(R.drawable.ic_animals)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(0)
                .setBackgroundColor(R.color.goldie)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .build());
        categories.add(new Category.Builder()
                .setName("Русский язык")
                .setIcon(R.drawable.ic_russian_lang)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(0)
                .setBackgroundColor(R.color.sky_lite)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .build());
        categories.add(new Category.Builder()
                .setName("Музыка")
                .setIcon(R.drawable.ic_music)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(0)
                .setBackgroundColor(R.color.lime_mist)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .build());
        categories.add(new Category.Builder()
                .setName("Кино")
                .setIcon(R.drawable.ic_movie)
                .setQuizQuantity(20)
                .setQuizCompletedQuantity(0)
                .setBackgroundColor(R.color.lime_mist)
                .setTitleColor(R.color.black)
                .setSubtitleColor(R.color.black)
                .build());
    }

    public static List<Category> getCategories() {
        return categories;
    }
}
