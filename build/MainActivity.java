package com.example.girlnyshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.girlnyshop.database.AppDatabase;
import com.example.girlnyshop.database.User;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private AppDatabase userDatabase;
    private AppPreferences appPreferences;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appPreferences = new AppPreferences(this);

        if (appPreferences.isLogin()) {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        title = findViewById(R.id.title);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        userDatabase = AppDatabase.getDatabase(this);

        checkAndPrepopulateDatabase();

        title.setOnClickListener(view -> loginUser());
    }

    private void checkAndPrepopulateDatabase() {
        new Thread(() -> {
            int count = userDatabase.userDao().getUserCount();
            if (count == 0) {
                User user = new User("cheeseny1975@gmail.com", "locajoyuV");
                userDatabase.userDao().insert(user);
                User user1 = new User("a@a.com", "a");
                userDatabase.userDao().insert(user1);
            }
        }).start();
    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            User user = userDatabase.userDao().getUserByCredentials(email, password);
            runOnUiThread(() -> {
                if (user != null) {
                    appPreferences.setLogin(email);
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}
