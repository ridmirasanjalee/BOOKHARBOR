package com.example.librarysystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btnRegister;
    TextView tvGotoLogin;

    Database database;  // Declare the database instance at the class level

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);  // Avoid using external EdgeToEdge

        edUsername = findViewById(R.id.editTextAppfullName);  // Corrected IDs for better naming
        edEmail = findViewById(R.id.editTextAppAddress);
        edPassword = findViewById(R.id.editTextAppContactNumber);
        edConfirm = findViewById(R.id.editTextAppFees);
        btnRegister = findViewById(R.id.buttonAppBack);  // Improved variable names
        tvGotoLogin = findViewById(R.id.gotoLogin);

        // Initialize the database instance with a simpler constructor
        database = new Database(getApplicationContext());

        // Set listener for navigating to login
        tvGotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));  // Navigate to login
            }
        });

        // Set click listener for the registration button
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString().trim();  // Trim whitespace
                String email = edEmail.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                String confirm = edConfirm.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.equals(confirm)) {  // Use equals instead of compareTo for strings
                        if (isValid(password)) {
                            database.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));  // Navigate to login
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();  // Corrected message
                    }
                }
            }
        });
    }

    // Enhanced password validation logic
    public boolean isValid(String password) {
        int hasLetter = 0, hasDigit = 0, hasSpecial = 0;

        if (password.length() < 8) {  // Ensure at least 8 characters
            Toast.makeText(getApplicationContext(), "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check for at least one letter
        for (int i = 0; i < password.length(); i++) {
            if (Character.isLetter(password.charAt(i))) {
                hasLetter = 1;
                break;
            }
        }

        // Check for at least one digit
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                hasDigit = 1;
                break;
            }
        }

        // Check for at least one special character
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if ("!@#$%^&*()_+=-[]{}|;:'\",.<>/?~".indexOf(c) != -1) {
                hasSpecial = 1;
                break;
            }
        }

        if (hasLetter == 1 && hasDigit == 1 && hasSpecial == 1) {
            return true;
        }

        Toast.makeText(getApplicationContext(), "Password must contain letters, numbers, and at least one special character", Toast.LENGTH_SHORT).show();
        return false;
    }
}
