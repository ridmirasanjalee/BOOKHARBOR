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

public class AddBookloanActivity extends AppCompatActivity {

    EditText etAccessNo, etBranchId, etMemberId,etDateOut,etDateDue,etDateReturned;
    Button btnSave;

    Database database;  // Corrected variable name

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bookloan);

        etAccessNo = findViewById(R.id.etAccessNo);
        etBranchId = findViewById(R.id.etBranchId);
        etMemberId = findViewById(R.id.etMemberId);
        etDateDue = findViewById(R.id.etDateDue);
        etDateOut = findViewById(R.id.etDateOut);
        etDateReturned = findViewById(R.id.etDateReturned);
        btnSave = findViewById(R.id.btnSave);

        database = new Database(this);  // Corrected initialization

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBookloan();  // Ensured the click handler works
            }
        });
    }

    private void saveBookloan() {
        String branchId = etBranchId.getText().toString();
        String accessNo= etAccessNo.getText().toString();
        String memberId = etMemberId.getText().toString();
        String datedue = etDateDue.getText().toString();
        String dateout = etDateOut.getText().toString();
        String datereturned = etDateReturned.getText().toString();

        if (branchId.isEmpty() || accessNo.isEmpty() || memberId.isEmpty()||datedue.isEmpty()||dateout.isEmpty()||datereturned.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;  // Return early if any field is empty
        }

        // Use the database helper method for insertion
        long result = database.addBookloan(branchId,accessNo,memberId,datedue,dateout,datereturned);

        if (result == -1) {
            Toast.makeText(this, "Failed to add bookloan", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bookloan added successfully", Toast.LENGTH_SHORT).show();

            // Clear input fields
            etAccessNo.setText("");
            etBranchId.setText("");
            etMemberId.setText("");
            etDateDue.setText("");
            etDateOut.setText("");
            etDateReturned.setText("");
        }
    }
}
