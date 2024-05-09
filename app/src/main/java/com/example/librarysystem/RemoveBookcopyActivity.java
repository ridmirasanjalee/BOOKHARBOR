package com.example.librarysystem;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveBookcopyActivity extends AppCompatActivity {

    EditText etBookId,etAccessNo,etBranchId;
    Button btnRemove;

    Database database;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_bookcopy);

        etBookId = findViewById(R.id.etBookId);
        etBranchId = findViewById(R.id.etBranchId);
        etAccessNo = findViewById(R.id.etAccessNo);
        btnRemove = findViewById(R.id.btnRemove);

        database = new Database(this);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBookcopy();
            }
        });
    }

    private int removeBookcopy() {
        String bookId = etBookId.getText().toString();
        String branchId = etBranchId.getText().toString();
        String accessNo = etAccessNo.getText().toString();

        if (bookId.isEmpty() || branchId.isEmpty()|| accessNo.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return 0;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        int result = db.delete("Book_Copy", "ACCESS_NO = ? AND BRANCH_ID = ?",
                new String[]{accessNo, branchId});
        if (result == 0) {
            Toast.makeText(this, "Failed to remove book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book removed successfully", Toast.LENGTH_SHORT).show();
            etBookId.setText("");
            etAccessNo.setText("");
        }
        db.close();
        return result;
    }




}
