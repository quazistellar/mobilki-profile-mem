package com.example.android_laba1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity3 extends AppCompatActivity {

    private AppCompatButton backbtn;
    private Switch swapthe;

    private SharedPreferences sharedPreferences;
    private String THEME_KEY = "theme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");


        sharedPreferences = getSharedPreferences("app_settings", MODE_PRIVATE);
        backbtn = findViewById(R.id.backBtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, Activity2.class);
                startActivity(intent);
            }
        });

        swapthe = findViewById(R.id.theme_switch);
        boolean isThemeActive = sharedPreferences.getBoolean(THEME_KEY, false);
        swapthe.setChecked(isThemeActive);
        setThemeBackground(isThemeActive);

        swapthe.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(THEME_KEY, isChecked);
            editor.apply();
            setThemeBackground(isChecked);
        });
    }

    private void setThemeBackground(boolean isThemeActive) {
        int backgroundColor;
        if (isThemeActive) {
            backgroundColor = getResources().getColor(R.color.on_theme, getTheme());
        } else {
            backgroundColor = getResources().getColor(R.color.app_color1, getTheme());
        }
        findViewById(R.id.main).setBackgroundColor(backgroundColor);
    }
}
