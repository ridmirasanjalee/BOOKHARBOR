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

public class UpdateBookcopyActivity extends AppCompatActivity {

    EditText etBookId, etBranchId, etAccessNo;
    Button btnUpdate;

    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bookcopy);

        etBookId = findViewById(R.id.etBookId);
        etBranchId = findViewById(R.id.etBranchId);
        etAccessNo = findViewById(R.id.etAccessNo);
        btnUpdate = findViewById(R.id.btnUpdate);

        database = new Database(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBookcopy();
            }
        });
    }

    private void updateBookcopy() {
        String bookId = etBookId.getText().toString();
        String branchId = etBranchId.getText().toString();
        String accessNo = etAccessNo.getText().toString();

        if (bookId.isEmpty() || accessNo.isEmpty() || branchId.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BRANCH_ID", branchId);
        values.put("BOOK_ID", branchId);
        values.put("ACCESS_NO", accessNo);

        int result = db.update("Book_Copy", values, "BRANCH_ID= ? AND ACCESS_NO", new String[]{branchId,accessNo});
        if (result == 0) {
            Toast.makeText(this, "Failed to update book_copy", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book_copy updated successfully", Toast.LENGTH_SHORT).show();
            etBookId.setText("");
            etBranchId.setText("");
            etAccessNo.setText("");
        }

        db.close();
    }
}
