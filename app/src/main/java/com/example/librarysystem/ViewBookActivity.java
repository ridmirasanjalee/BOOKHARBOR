package com.example.librarysystem;

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

public class ViewBookActivity extends AppCompatActivity {

    ListView listView;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book);

        listView = findViewById(R.id.listViewBooks);
        database = new Database(this);

        displayBookDetails();
    }

    private void displayBookDetails() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Book", null);

        try {
            String[] fromColumns = {"BOOK_ID", "TITLE", "PUBLISHER_NAME"};
            int[] toViews = {R.id.etBookId, R.id.etTitle, R.id.etPublisherName};  // Corrected IDs

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    R.layout.list_item_book, cursor, fromColumns, toViews, 0);

            listView.setAdapter(adapter);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();  // Ensure the cursor is closed
            }
        }
    }


}

