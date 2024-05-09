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

public class ViewAuthorActivity extends AppCompatActivity {

    ListView listView;
    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_author);

        listView = findViewById(R.id.listViewAuthor);
        database = new Database(this);

        displayAuthorDetails();
    }

    private void displayAuthorDetails() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Author", null);

        try {
            String[] fromColumns = {"BOOK_ID", "AUTHOR_NAME"};
            int[] toViews = {R.id.etBookId, R.id.etAuthorName};  // Corrected IDs

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    R.layout.list_item_author, cursor, fromColumns, toViews, 0);

            listView.setAdapter(adapter);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();  // Ensure the cursor is closed
            }
        }
    }


}
