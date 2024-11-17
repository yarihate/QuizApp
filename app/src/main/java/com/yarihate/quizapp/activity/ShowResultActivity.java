package com.yarihate.quizapp.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yarihate.quizapp.R;
import com.yarihate.quizapp.service.GetQuizStatisticService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ShowResultActivity extends AppCompatActivity {
    @Inject
    GetQuizStatisticService getQuizStatisticService;
    private LinearLayout starContainer;
    private Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_screen_activity);

        starContainer = findViewById(R.id.star_container);
        continueButton = findViewById(R.id.continue_button);

        int quizStarsQuantity = getIntent().getIntExtra("current_stars_count", -1);
        if (quizStarsQuantity == -1) {
            Toast.makeText(this, "Ошибка загрузки данных", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        showStars(quizStarsQuantity);
    }

    private void showStars(int stars) {
        Handler handler = new Handler();
        for (int i = 0; i < stars; i++) {
            int delay = i * 1000;
            handler.postDelayed(() -> addStar(), delay);
        }
        handler.postDelayed(() -> continueButton.setVisibility(View.VISIBLE), stars * 1000);
    }

    private void addStar() {
        ImageView star = new ImageView(this);
        star.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        star.setImageResource(R.drawable.ic_star);

        star.setScaleX(0f);
        star.setScaleY(0f);
        star.animate().scaleX(1f).scaleY(1f).setDuration(500).start();

        starContainer.addView(star);
    }
}
