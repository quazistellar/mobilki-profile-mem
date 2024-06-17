package com.example.android_laba1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText usernameInput, passwordInput;
    private AppCompatButton userBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        usernameInput = findViewById(R.id.usernameEditText);
        passwordInput = findViewById(R.id.passwordEditText);
        userBtn = findViewById(R.id.signInButton);



       userBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String username = usernameInput.getText().toString();
               String password = passwordInput.getText().toString();
               Intent intent = new Intent(MainActivity.this, Activity2.class);
               intent.putExtra("username", username);



               if (username.isEmpty() || password.isEmpty()) {
                   Toast.makeText(MainActivity.this, "Please, enter the username and password", Toast.LENGTH_SHORT).show();
               } else {
                   SharedPreferences prefs = getSharedPreferences("app_data", MODE_PRIVATE);
                   String savedUsername = prefs.getString("username", "user");

                   intent.putExtra("username", username);
                   startActivity(intent);
               }
           }
       });


    }
}