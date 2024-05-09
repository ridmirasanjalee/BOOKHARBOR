
package com.example.librarysystem;  // Adjust the package according to your structure

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class BranchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch);  // Make sure this layout name is correct

        CardView addBranch = findViewById(R.id.cardBRAddBranch);
        addBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BranchActivity.this, AddBranchActivity.class);
                it.putExtra("title", "ADD BRANCH");
                startActivity(it);
            }
        });

        CardView updateBranch = findViewById(R.id.cardBRUpdateBranch);
        updateBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BranchActivity.this, UpdateBranchActivity.class);
                it.putExtra("title", "UPDATE BRANCH");
                startActivity(it);
            }
        });

        CardView removeBranch = findViewById(R.id.cardBRRemoveBranch);
        removeBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BranchActivity.this, RemoveBranchActivity.class);
                it.putExtra("title", "REMOVE BRANCH");
                startActivity(it);
            }
        });

        CardView viewBranch = findViewById(R.id.cardBRViewBranch);
        viewBranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BranchActivity.this, ViewBranchActivity.class);
                it.putExtra("title", "VIEW BRANCHES LIST");
                startActivity(it);
            }
        });
    }
}
