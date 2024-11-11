package com.yarihate.quizapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
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
            FrameLayout categoryCard = createCategoryCard(category);
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

    private FrameLayout createCategoryCard(Category category) {
        // Create FrameLayout to wrap card layout for more control over positioning
        FrameLayout frameLayout = new FrameLayout(this);
        FrameLayout.LayoutParams frameParams = new FrameLayout.LayoutParams(
                430,
                430
        );
        frameParams.setMargins(0, 0, 50, 50);
        frameLayout.setLayoutParams(frameParams);
        frameLayout.setPadding(16, 16, 16, 16);
        frameLayout.setBackgroundResource(R.drawable.card_background);
        frameLayout.setBackgroundTintList(getResources().getColorStateList(category.getBackgroundColor()));

        // Outer LinearLayout for card contents
        LinearLayout card = new LinearLayout(this);
        FrameLayout.LayoutParams cardParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        card.setLayoutParams(cardParams);
        card.setOrientation(LinearLayout.VERTICAL);

        // Icon
        ImageView icon = new ImageView(this);
        icon.setLayoutParams(new LinearLayout.LayoutParams(
                270,
                270
        ));
        icon.setImageResource(category.getIcon());
        card.addView(icon);

        // Inner LinearLayout for titleView and categoryStatView
        LinearLayout textLayout = new LinearLayout(this);
        textLayout.setOrientation(LinearLayout.VERTICAL);
        FrameLayout.LayoutParams textLayoutParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.BOTTOM
        );
        textLayoutParams.setMargins(0,0,0,50);
        textLayout.setLayoutParams(textLayoutParams);

        // Title
        TextView titleView = new TextView(this);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        titleParams.setMarginStart(8);
        titleView.setLayoutParams(titleParams);
        titleView.setText(category.getTitle());
        titleView.setTextColor(Color.BLACK);
        titleView.setTextSize(18);
        titleView.setTypeface(titleView.getTypeface(), Typeface.BOLD);

        // Statistics
        TextView categoryStatView = new TextView(this);
        categoryStatView.setLayoutParams(titleParams);
        categoryStatView.setText(String.format("Пройдено %s из %s", getCategoryStatisticService.getQuizCompletedQuantity(category.getId()), category.getQuizQuantity()));
        categoryStatView.setTextColor(category.getSubtitleColor());
        categoryStatView.setTextSize(12);
        categoryStatViewsByCategory.put(category, categoryStatView);

        // Add TextViews to the inner LinearLayout
        textLayout.addView(titleView);
        textLayout.addView(categoryStatView);

        // Add the card layout and textLayout to the FrameLayout
        frameLayout.addView(card);
        frameLayout.addView(textLayout);

        return frameLayout;
    }
}