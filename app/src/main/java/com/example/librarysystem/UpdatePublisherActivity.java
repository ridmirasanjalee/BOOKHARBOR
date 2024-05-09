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

public class UpdatePublisherActivity extends AppCompatActivity {

    EditText etPublisherId, etAddress, etPublisherName,etPhoneNumber;
    Button btnUpdate;

    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        etPublisherId= findViewById(R.id.etPublisherId);
        etAddress= findViewById(R.id.etPublisherAddress);
        etPublisherName = findViewById(R.id.etPublisherName);
        etPhoneNumber=findViewById(R.id.etPublisherContact);
        btnUpdate = findViewById(R.id.btnUpdate);

        database = new Database(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePublisher();
            }
        });
    }

    private void updatePublisher() {
        String publisherId = etPublisherId.getText().toString();
        String address = etAddress.getText().toString();
        String publisherName = etPublisherName.getText().toString();
        String phonenumber = etPhoneNumber.getText().toString();

        if (publisherId.isEmpty() || address.isEmpty() || publisherName.isEmpty()|| phonenumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ADDRESS", address);
        values.put("PUBLISHER_NAME", publisherName);
        values.put("PHONE_NUMBER",phonenumber);


        int result = db.update("Publisher", values, "PUBLISHER_ID = ?", new String[]{publisherId});
        if (result == 0) {
            Toast.makeText(this, "Failed to update book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book updated successfully", Toast.LENGTH_SHORT).show();
            etPublisherId.setText("");
            etAddress.setText("");
            etPublisherName.setText("");
            etPhoneNumber.setText("");
        }

        db.close();
    }
}
