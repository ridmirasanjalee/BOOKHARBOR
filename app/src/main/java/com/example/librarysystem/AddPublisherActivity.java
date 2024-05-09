package com.example.librarysystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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

public class AddPublisherActivity extends AppCompatActivity {

    EditText etPublisherId, etName, etAddress, etPhoneNumber;
    Button btnSave;

    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publisher);

        etPublisherId = findViewById(R.id.etPublisherId);
        etName = findViewById(R.id.etPublisherName);
        etAddress = findViewById(R.id.etPublisherAddress);
        etPhoneNumber = findViewById(R.id.etPublisherContact);
        btnSave = findViewById(R.id.btnSave);

        database = new Database(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePublisher();
            }
        });
    }

    private void savePublisher() {
        String publisherId = etPublisherId.getText().toString();
        String name = etName.getText().toString();
        String address = etAddress.getText().toString();
        String phoneNumber = etPhoneNumber.getText().toString();

        if (publisherId.isEmpty() || name.isEmpty() || address.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PUBLISHER_ID", publisherId);
        values.put("PUBLISHER_NAME", name);
        values.put("ADDRESS", address);
        values.put("PHONE_NUMBER", phoneNumber);

        long result = db.insert("Publisher", null, values);
        if (result == -1) {
            Toast.makeText(this, "Failed to add publisher", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Publisher added successfully", Toast.LENGTH_SHORT).show();
            etPublisherId.setText("");
            etName.setText("");
            etAddress.setText("");
            etPhoneNumber.setText("");
        }

        db.close();
    }
}