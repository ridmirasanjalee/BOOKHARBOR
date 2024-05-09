package com.example.librarysystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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

public class UpdateAuthorActivity extends AppCompatActivity {

    EditText etBookId,etAuthorName;
    Button btnUpdate;

    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_author);

        etBookId = findViewById(R.id.etBookId);
        etAuthorName = findViewById(R.id.etAuthorName);
        btnUpdate = findViewById(R.id.btnUpdate);

        database = new Database(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateAuthor();
            }
        });
    }

    private void updateAuthor() {
        String bookId = etBookId.getText().toString();
        String authorname = etAuthorName.getText().toString();

        if (bookId.isEmpty() || authorname.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("AUTHOR_NAME", authorname);
        values.put("BOOK_ID", bookId);

        int result = db.update("Author", values, "BOOK_ID = ? AND ACCESS_NO", new String[]{bookId,authorname});
        if (result == 0) {
            Toast.makeText(this, "Failed to update Author", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Author updated successfully", Toast.LENGTH_SHORT).show();
            etBookId.setText("");
            etAuthorName.setText("");
        }

        db.close();
    }
}
