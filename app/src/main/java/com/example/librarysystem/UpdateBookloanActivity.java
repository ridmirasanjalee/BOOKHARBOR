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

public class UpdateBookloanActivity extends AppCompatActivity {

    EditText etMemberId, etBranchId, etAccessNo,etDateDue,etDateOut,etDateReturned;
    Button btnUpdate;

    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bookloan);

        etMemberId = findViewById(R.id.etMemberId);
        etBranchId = findViewById(R.id.etBranchId);
        etAccessNo = findViewById(R.id.etAccessNo);
        etDateDue = findViewById(R.id.etMemberId);
        etDateOut= findViewById(R.id.etBranchId);
        etDateReturned = findViewById(R.id.etAccessNo);
        btnUpdate = findViewById(R.id.btnUpdate);

        database = new Database(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBookloan();
            }
        });
    }

    private void updateBookloan() {
        String branchId = etBranchId.getText().toString();
        String accessNo= etAccessNo.getText().toString();
        String memberId = etMemberId.getText().toString();
        String datedue = etDateDue.getText().toString();
        String dateout = etDateOut.getText().toString();
        String datereturned = etDateReturned.getText().toString();

        if (memberId.isEmpty() || branchId.isEmpty() || accessNo.isEmpty()||datedue.isEmpty() || dateout.isEmpty()|| datereturned.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MEMBER_ID", memberId);
        values.put("BRANCH_ID", branchId);
        values.put("ACCESS_NO", accessNo);
        values.put("DATE_OUT", dateout);
        values.put("DATE_DUE",datedue);
        values.put("DATE_RETURNED", datereturned);

        int rowsAffected = db.update("Book_Loan", values, "ACCESS_NO = ? AND BRANCH_ID = ? AND MEMBER_ID =? AND DATEOUT =?",
                new String[]{accessNo, branchId,memberId,dateout,});

        if (rowsAffected > 0) {
            Toast.makeText(this, "Book loan updated successfully", Toast.LENGTH_SHORT).show();
            etMemberId.setText("");
            etBranchId.setText("");
            etAccessNo.setText("");
            etDateOut.setText("");
            etDateDue.setText("");
            etDateReturned.setText("");
        } else {
            Toast.makeText(this, "Failed to update bookloan", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }
}
