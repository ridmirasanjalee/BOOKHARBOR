package com.example.librarysystem;

import android.annotation.SuppressLint;
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

public class RemovePublisherActivity extends AppCompatActivity {
    EditText etPublisherId;
    Button btnRemove;

    Database database;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_publisher);

        etPublisherId = findViewById(R.id.etPublisherId);
        btnRemove = findViewById(R.id.btnRemove);

        database = new Database(this);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePublisher();
            }
        });
    }

    private void removePublisher() {
        String publisherId = etPublisherId.getText().toString();

        if (publisherId.isEmpty()) {
            Toast.makeText(this, "Please enter Book ID", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        int result = database.removePublisher(publisherId);

        if (result == 0) {
            Toast.makeText(this, "Failed to remove publisher", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book removed successfully", Toast.LENGTH_SHORT).show();
            etPublisherId.setText("");
        }

        db.close();
    }
}
