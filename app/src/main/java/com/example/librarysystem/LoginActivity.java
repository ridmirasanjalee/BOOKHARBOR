package com.example.librarysystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;  // Reduced unnecessary imports

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btnLogin;
    TextView tvNewUser;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextLoginusername);
        edPassword = findViewById(R.id.editTextLoginpassword);
        btnLogin = findViewById(R.id.buttonLogin);
        tvNewUser = findViewById(R.id.TextviewNewUser);

        // Initialize the database instance
        database = new Database(getApplicationContext());  // Simpler initialization

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString().trim();  // Trim for extra spaces
                String password = edPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Call the login method
                    int loginResult = database.login(username, password);
                    if (loginResult == 1) {
                        Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));  // Proceed to the next activity
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();  // Corrected message
                    }
                }
            }
        });

        tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));  // Navigate to the registration activity
            }
        });
    }
}
