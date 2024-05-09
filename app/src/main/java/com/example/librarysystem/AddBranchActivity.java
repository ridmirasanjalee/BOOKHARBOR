package com.example.librarysystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;  // Missing import

import androidx.appcompat.app.AppCompatActivity;

public class AddBranchActivity extends AppCompatActivity {

    EditText etBranchId, etAddress, etBranchName;
    Button btnSave;

    Database database;  // Corrected variable name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_branch);

        etBranchId = findViewById(R.id.etBranchId);
        etAddress = findViewById(R.id.etBranchAddress);
        etBranchName = findViewById(R.id.etBranchName);
        btnSave = findViewById(R.id.btnSave);

        database = new Database(this);  // Corrected initialization

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBranch();  // Ensured the click handler works
            }
        });
    }

    private void saveBranch() {
        String branchId = etBranchId.getText().toString();
        String address = etAddress.getText().toString();
        String branchName = etBranchName.getText().toString();

        if (branchId.isEmpty() || address.isEmpty() || branchName.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;  // Return early if any field is empty
        }

        // Use the database helper method for insertion
        long result = database.addBranch(branchId, address, branchName);

        if (result == -1) {
            Toast.makeText(this, "Failed to add book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book added successfully", Toast.LENGTH_SHORT).show();

            // Clear input fields
            etBranchId.setText("");
            etAddress.setText("");
            etBranchName.setText("");
        }
    }
}
