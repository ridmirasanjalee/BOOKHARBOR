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

public class VeiwBookloanActivity extends AppCompatActivity {

    ListView listView;
    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_bookloan);

        listView = findViewById(R.id.listViewBookloan);
        database = new Database(this);

        displayBookDetails();
    }

    private void displayBookDetails() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Book_Loan", null);

        try {
            String[] fromColumns = {"ACCESS_NO", "BRANCH_ID", "MEMBER_ID","DATE_OUT","DATE_DUE","DATE_RETURNED"};
            int[] toViews = {R.id.etAccessNo, R.id.etBranchId, R.id.etMemberId,R.id.etDateOut,R.id.etDateDue,R.id.etDateReturned};  // Corrected IDs

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    R.layout.list_item_bookloan, cursor, fromColumns, toViews, 0);

            listView.setAdapter(adapter);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();  // Ensure the cursor is closed
            }
        }
    }


}