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

public class UpdateBranchActivity extends AppCompatActivity {

    EditText etBranchId, etBranchAddress, etBranchName;
    Button btnUpdate;

    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_branch);

        etBranchId = findViewById(R.id.etBranchId);
        etBranchAddress = findViewById(R.id.etBranchAddress);
        etBranchName = findViewById(R.id.etBranchName);
        btnUpdate = findViewById(R.id.btnUpdate);

        database = new Database(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBranch();
            }
        });
    }

    private void updateBranch() {
        String branchId = etBranchId.getText().toString();
        String branchaddress = etBranchAddress.getText().toString();
        String branchname = etBranchName.getText().toString();

        if (branchId.isEmpty() || branchaddress.isEmpty() || branchname.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BRANCH_ADDRESS", branchaddress);
        values.put("BRANCH_NAME", branchname);
        values.put("BRANCH_ID", branchId);


        int result = db.update("Branch", values, "BRANCH_ID = ?", new String[]{branchId});
        if (result == 0) {
            Toast.makeText(this, "Failed to update branch", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Branch updated successfully", Toast.LENGTH_SHORT).show();
            etBranchId.setText("");
            etBranchAddress.setText("");
            etBranchName.setText("");
        }

        db.close();
    }
}
