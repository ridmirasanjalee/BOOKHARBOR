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

public class ViewMemberActivity extends AppCompatActivity {

    ListView listView;
    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);

        listView = findViewById(R.id.listViewMember);
        database = new Database(this);

        displayMemberDetails();
    }

    private void displayMemberDetails() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Member", null);

        try {
            String[] fromColumns = {"MEMBER_ID", "MEMBER_ADDRESS", "MEMBER_NAME","MEMBER_PHONE_NUMBER","NO_PAID_DUES"};
            int[] toViews = {R.id.etMemberId, R.id.etMemberAddress, R.id.etMemberName,R.id.etMemberPhoneNumber,R.id.etNoPaidDues};  // Corrected IDs

            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                    R.layout.list_item_member, cursor, fromColumns, toViews, 0);

            listView.setAdapter(adapter);
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();  // Ensure the cursor is closed
            }
        }
    }


}

