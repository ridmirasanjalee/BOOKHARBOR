package com.example.librarysystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;  // Missing import

import androidx.appcompat.app.AppCompatActivity;

public class AddMemberActivity extends AppCompatActivity {

    EditText etMemberId, etMemberAddress, etMemberName,etMemberPhoneNumber,etNoPaidDues;
    Button btnSave;

    Database database;  // Corrected variable name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        etMemberId = findViewById(R.id.etMemberId);
        etMemberAddress = findViewById(R.id.etMemberAddress);
        etMemberName = findViewById(R.id.etMemberName);
        etMemberPhoneNumber=findViewById(R.id.etMemberPhoneNumber);
        etNoPaidDues=findViewById(R.id.etNoPaidDues);
        btnSave = findViewById(R.id.btnSave);

        database = new Database(this);  // Corrected initialization

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMember();  // Ensured the click handler works
            }
        });
    }

    private void saveMember() {
        String memberId = etMemberId.getText().toString();
        String memberaddress= etMemberAddress.getText().toString();
        String membername = etMemberName.getText().toString();
        String nodues=etNoPaidDues.getText().toString();
        String memberphonenumber=etMemberPhoneNumber.getText().toString();


        if (memberId.isEmpty() || memberaddress.isEmpty() || membername.isEmpty()|| nodues.isEmpty()||memberphonenumber.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;  // Return early if any field is empty
        }

        // Use the database helper method for insertion
        long result = database.addMember(memberId,memberaddress, membername,nodues,memberphonenumber);

        if (result == -1) {
            Toast.makeText(this, "Failed to add member", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book added successfully", Toast.LENGTH_SHORT).show();

            // Clear input fields
            etMemberId.setText("");
            etMemberAddress.setText("");
            etMemberName.setText("");
            etMemberPhoneNumber.setText("");
            etNoPaidDues.setText("");
        }
    }
}
