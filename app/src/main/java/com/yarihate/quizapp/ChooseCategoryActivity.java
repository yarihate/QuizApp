package com.yarihate.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yarihate.quizapp.dto.Category;
import com.yarihate.quizapp.service.CategoryService;

import java.util.List;

public class ChooseCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_category_activity);
        LinearLayout categoryLayout = findViewById(R.id.category_layout);

        List<Category> categories = CategoryService.getCategories();

        categories.forEach(category -> {
            LinearLayout categoryCard = createCategoryCard(category);
            categoryCard.setTag(category);
            categoryLayout.addView(categoryCard);

            categoryCard.setOnClickListener(v -> {
                Category selectedCategory = (Category) v.getTag();
                Intent intent = new Intent(ChooseCategoryActivity.this, ChooseQuizActivity.class);
                intent.putExtra("category_title", selectedCategory.getTitle());
                startActivity(intent);
            });
        });
    }

    private LinearLayout createCategoryCard(Category category) {
        LinearLayout card = new LinearLayout(this);
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        cardParams.setMargins(0, 0, 0, 16);
        card.setLayoutParams(cardParams);
        card.setOrientation(LinearLayout.HORIZONTAL);
        card.setPadding(16, 16, 16, 16);
        card.setBackgroundResource(R.drawable.card_background);
        card.setBackgroundColor(category.getTitleColor());
        card.setBackgroundTintList(getResources().getColorStateList(category.getBackgroundColor()));

        ImageView icon = new ImageView(this);
        icon.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        icon.setImageResource(category.getIcon());

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

        card.addView(icon);
        card.addView(titleView);

        return card;
    }
}