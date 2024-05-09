package com.example.librarysystem;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewBranchActivity extends AppCompatActivity {
    ListView listView;
    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_branch);  // Ensure correct layout

        listView = findViewById(R.id.listViewBranch);  // Ensure ID matches layout
        database = new Database(this);  // Initialize database instance

        displayBranchesDetails();  // Call the function to display publishers
    }

    private void displayBranchesDetails() {
        SQLiteDatabase db = database.getReadableDatabase();  // Get readable database instance
        Cursor cursor = db.rawQuery("SELECT * FROM Branch", null);  // Fetch data

        if (cursor == null || cursor.getCount() == 0) {  // Check if data exists
            Toast.makeText(this, "No branches found", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String[] fromColumns = {"BRANCH_ID", "BRANCH_ADDRESS", "BRANCH_NAME"};
            int[] toViews = {R.id.etBranchId, R.id.etBranchAddress, R.id.etBranchName};  // Ensure IDs match layout

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.list_item_branch,  // Ensure correct layout
                    cursor,
                    fromColumns,
                    toViews,
                    0
            );

            listView.setAdapter(adapter);  // Set the adapter to the list view
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();  // Ensure the cursor is closed
            }
        }
    }
}
