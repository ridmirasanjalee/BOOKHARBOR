package com.example.librarysystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddAuthorActivity extends AppCompatActivity {

    EditText  etAuthorName, etBookId;
    Button btnSave;

    Database database;  // Corrected variable name

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_author);

        etBookId = findViewById(R.id.etBookId);
        etAuthorName = findViewById(R.id.etAuthorName);
        btnSave = findViewById(R.id.btnSave);

        database = new Database(this);  // Corrected initialization

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveAuthor();  // Ensured the click handler works
            }
        });
    }

    private void saveAuthor() {
        String bookId = etBookId.getText().toString();
        String authorname = etAuthorName.getText().toString();


        if (bookId.isEmpty() || authorname.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;  // Return early if any field is empty
        }

        // Use the database helper method for insertion
        long result = database.addAuthor( authorname, bookId);

        if (result == -1) {
            Toast.makeText(this, "Failed to add author", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "author added successfully", Toast.LENGTH_SHORT).show();

            // Clear input fields
            etBookId.setText("");
            etAuthorName.setText("");

        }
    }
}
