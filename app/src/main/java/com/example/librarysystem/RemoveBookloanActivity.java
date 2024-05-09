package com.example.librarysystem;

import android.annotation.SuppressLint;
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

public class RemoveBookloanActivity extends AppCompatActivity {

    EditText etMemberId,etAccessNo,etBranchId,etDateOut;
    Button btnRemove;

    Database database;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_bookloan);

        etMemberId = findViewById(R.id.etMemberId);
        etBranchId = findViewById(R.id.etBranchId);
        etAccessNo = findViewById(R.id.etAccessNo);
        btnRemove = findViewById(R.id.btnRemove);
        etDateOut=findViewById(R.id.etDateOut);

        database = new Database(this);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBookloan();
            }
        });
    }

    private int removeBookloan() {
        String memberId = etMemberId.getText().toString();
        String branchId = etBranchId.getText().toString();
        String accessNo = etAccessNo.getText().toString();
        String dateout = etDateOut.getText().toString();

        if (memberId.isEmpty() || branchId.isEmpty()|| accessNo.isEmpty()||dateout.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return 0;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        int result = db.delete("Book_Loan", "ACCESS_NO = ? AND BRANCH_ID = ? AND MEMBER_ID =? AND DATE_OUT=?",
                new String[]{accessNo, branchId,memberId,dateout});
        if (result == 0) {
            Toast.makeText(this, "Failed to remove book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book removed successfully", Toast.LENGTH_SHORT).show();
            etMemberId.setText("");
            etAccessNo.setText("");
            etBranchId.setText("");
            etDateOut.setText("");
        }
        db.close();
        return result;
    }




}
