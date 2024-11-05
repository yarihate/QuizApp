package com.yarihate.quizapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.flexbox.FlexboxLayout;
import com.yarihate.quizapp.R;
import com.yarihate.quizapp.dto.Category;
import com.yarihate.quizapp.service.CategoryService;
import com.yarihate.quizapp.service.GetCategoryStatisticService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ChooseCategoryActivity extends AppCompatActivity {
    @Inject
    CategoryService categoryService;
    @Inject
    GetCategoryStatisticService getCategoryStatisticService;
    private final Map<Category, TextView> categoryStatViewsByCategory = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_category_activity);
        FlexboxLayout categoryLayout = findViewById(R.id.category_layout);

        List<Category> categories = categoryService.getCategories();

        categories.forEach(category -> {
            LinearLayout categoryCard = createCategoryCard(category);
            categoryCard.setTag(category);
            categoryLayout.addView(categoryCard);

            categoryCard.setOnClickListener(v -> {
                Category selectedCategory = (Category) v.getTag();
                Intent intent = new Intent(ChooseCategoryActivity.this, ChooseQuizActivity.class);
                intent.putExtra("category_id", selectedCategory.getId());
                startActivity(intent);
            });
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        categoryStatViewsByCategory.forEach((category, categoryStatView) -> {
            categoryStatView.setText(String.format("Пройдено %s из %s", getCategoryStatisticService.getQuizCompletedQuantity(category.getId()), category.getQuizQuantity()));
        });
    }

    private LinearLayout createCategoryCard(Category category) {
        // Внешний LinearLayout
        LinearLayout card = new LinearLayout(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                430,
                430
        );
        cardParams.setMargins(0, 0, 50, 50);
        card.setLayoutParams(cardParams);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(16, 16, 16, 16);
        card.setBackgroundResource(R.drawable.card_background);
        card.setBackgroundTintList(getResources().getColorStateList(category.getBackgroundColor()));

        // Иконка
        ImageView icon = new ImageView(this);
        icon.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        icon.setImageResource(category.getIcon());

        // Внутренний LinearLayout для titleView и categoryStatView
        LinearLayout textLayout = new LinearLayout(this);
        textLayout.setOrientation(LinearLayout.VERTICAL);
        textLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Заголовок
        TextView titleView = new TextView(this);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        titleParams.setMarginStart(8);
        titleView.setLayoutParams(titleParams);
        titleView.setText(category.getTitle());
        titleView.setTextColor(category.getTitleColor());
        titleView.setTextSize(18);

        // Статистика
        TextView categoryStatView = new TextView(this);
        categoryStatView.setLayoutParams(titleParams);
        categoryStatView.setText(String.format("Пройдено %s из %s", getCategoryStatisticService.getQuizCompletedQuantity(category.getId()), category.getQuizQuantity()));
        categoryStatView.setTextColor(category.getSubtitleColor());
        categoryStatView.setTextSize(12);
        categoryStatViewsByCategory.put(category, categoryStatView);

        // Добавляем TextViews во внутренний LinearLayout
        textLayout.addView(titleView);
        textLayout.addView(categoryStatView);

        // Добавляем иконку и внутренний LinearLayout в card
        card.addView(icon);
        card.addView(textLayout);

        return card;
    }

}