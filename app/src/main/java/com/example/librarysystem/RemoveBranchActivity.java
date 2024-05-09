package com.example.librarysystem;

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

public class RemoveBranchActivity extends AppCompatActivity {
    EditText etBranchId;
    Button btnRemove;

    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_branch);

        etBranchId = findViewById(R.id.etBranchId);
        btnRemove = findViewById(R.id.btnRemove);

        database = new Database(this);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeBranch();
            }
        });
    }

    private void removeBranch() {
        String branchId = etBranchId.getText().toString();

        if (branchId.isEmpty()) {
            Toast.makeText(this, "Please enter Branch ID", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        int result = database.removeBranch(branchId);

        if (result == 0) {
            Toast.makeText(this, "Failed to remove Branch", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Branch removed successfully", Toast.LENGTH_SHORT).show();
            etBranchId.setText("");
        }

        db.close();
    }

}

