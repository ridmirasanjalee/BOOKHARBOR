package com.example.librarysystem;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewBookcopyActivity extends AppCompatActivity {

    ListView listView;
    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookcopy);

        listView = findViewById(R.id.listViewBookcopies);
        database = new Database(this);

        displayBookDetails();
    }

    private void displayBookDetails() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Book_Copy", null);

        try {
            String[] fromColumns = {"BOOK_ID", "BRANCH_ID", "ACCESS_NO"};
            int[] toViews = {R.id.etBookId, R.id.etBranchId, R.id.etAccessNo};  // Corrected IDs

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    R.layout.list_item_bookcopies, cursor, fromColumns, toViews, 0);

            listView.setAdapter(adapter);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();  // Ensure the cursor is closed
            }
        }
    }


}