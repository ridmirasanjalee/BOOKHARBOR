package com.example.librarysystem;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ViewpPublisherActivity extends AppCompatActivity {

    ListView listView;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewp_publisher);  // Ensure correct layout

        listView = findViewById(R.id.listViewPublishers);  // Ensure ID matches layout
        database = new Database(this);  // Initialize database instance

        displayPublishersDetails();  // Call the function to display publishers
    }

    private void displayPublishersDetails() {
        SQLiteDatabase db = database.getReadableDatabase();  // Get readable database instance
        Cursor cursor = db.rawQuery("SELECT * FROM Publisher", null);  // Fetch data

        if (cursor == null || cursor.getCount() == 0) {  // Check if data exists
            Toast.makeText(this, "No publishers found", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            String[] fromColumns = {"PUBLISHER_ID", "ADDRESS", "PUBLISHER_NAME", "PHONE_NUMBER"};
            int[] toViews = {R.id.etPublisherId, R.id.etAddress, R.id.etPublisherName, R.id.etPublisherContact};  // Ensure IDs match layout

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                    this,
                    R.layout.list_item_publisher,  // Ensure correct layout
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
