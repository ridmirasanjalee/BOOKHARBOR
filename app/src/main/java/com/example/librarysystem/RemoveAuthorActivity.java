package com.example.librarysystem;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveAuthorActivity extends AppCompatActivity {
    EditText etBookId,etAuthorName;
    Button btnRemove;

    Database database;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_book);

        etBookId = findViewById(R.id.etBookId);
        btnRemove = findViewById(R.id.btnRemove);
        etAuthorName=findViewById(R.id.etAuthorName);

        database = new Database(this);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAuthor();
            }
        });
    }

    private int removeAuthor() {
        String bookId = etBookId.getText().toString().trim();
        String authorname = etAuthorName.getText().toString().trim();

        if (bookId.isEmpty()|| authorname.isEmpty()) {
            Toast.makeText(this, "Please enter Book ID & Author Name", Toast.LENGTH_SHORT).show();
            return 0;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        int rowsDeleted = db.delete("Author", "BOOK_ID = ? AND AUTHOR_NAME", new String[]{bookId,authorname});
        if (rowsDeleted == 0) {
            Toast.makeText(this, "No author found for the provided Book ID", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Author removed successfully", Toast.LENGTH_SHORT).show();
            etBookId.setText("");
            etAuthorName.setText("");
        }
        db.close();
        return rowsDeleted;

    }

}






