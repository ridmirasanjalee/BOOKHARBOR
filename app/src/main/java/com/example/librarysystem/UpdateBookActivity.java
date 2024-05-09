package com.example.librarysystem;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateBookActivity extends AppCompatActivity {

    EditText etBookId, etTitle, etPublisherName;
    Button btnUpdate;

    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        etBookId = findViewById(R.id.etBookId);
        etTitle = findViewById(R.id.etTitle);
        etPublisherName = findViewById(R.id.etPublisherName);
        btnUpdate = findViewById(R.id.btnUpdate);

        database = new Database(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBook();
            }
        });
    }

    private void updateBook() {
        String bookId = etBookId.getText().toString();
        String title = etTitle.getText().toString();
        String publisherName = etPublisherName.getText().toString();

        if (bookId.isEmpty() || title.isEmpty() || publisherName.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE", title);
        values.put("PUBLISHER_NAME", publisherName);

        int result = db.update("Book", values, "BOOK_ID = ?", new String[]{bookId});
        if (result == 0) {
            Toast.makeText(this, "Failed to update book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book updated successfully", Toast.LENGTH_SHORT).show();
            etBookId.setText("");
            etTitle.setText("");
            etPublisherName.setText("");
        }

        db.close();
    }
}
