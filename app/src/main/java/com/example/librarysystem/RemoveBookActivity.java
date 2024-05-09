package com.example.librarysystem;

import android.database.sqlite.SQLiteDatabase;
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

public class RemoveBookActivity extends AppCompatActivity {
    EditText etBookId;
    Button btnRemove;

    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_book);

        etBookId = findViewById(R.id.etBookId);
        btnRemove = findViewById(R.id.btnRemove);

        database = new Database(this);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBook();
            }
        });
    }

    private void removeBook() {
        String bookId = etBookId.getText().toString();

        if (bookId.isEmpty()) {
            Toast.makeText(this, "Please enter Book ID", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        int result = database.removeBook (bookId);

        if (result == 0) {
            Toast.makeText(this, "Failed to remove book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book removed successfully", Toast.LENGTH_SHORT).show();
            etBookId.setText("");
        }

        db.close();
    }
}
