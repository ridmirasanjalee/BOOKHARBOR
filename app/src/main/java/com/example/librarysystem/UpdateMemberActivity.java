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

public class UpdateMemberActivity extends AppCompatActivity {

    EditText etMemberId, etMemberAddress, etMemberName,etMemberPhoneNumber,etNoPaid;
    Button btnUpdate;

    Database database;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);

        etMemberId = findViewById(R.id.etMemberId);
        etMemberAddress = findViewById(R.id.etMemberAddress);
        etMemberName = findViewById(R.id.etMemberName);
        etMemberPhoneNumber= findViewById(R.id.etMemberPhoneNumber);
        etNoPaid = findViewById(R.id.etNoPaidDues);
        btnUpdate = findViewById(R.id.btnUpdate);

        database = new Database(this);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateMember();
            }
        });
    }

    private void updateMember() {
        String memberId = etMemberId.getText().toString();
        String memberaddress = etMemberAddress.getText().toString();
        String membername = etMemberName.getText().toString();
        String memberphonenumber = etMemberPhoneNumber.getText().toString();
        String nopaiddues = etNoPaid.getText().toString();

        if (memberId.isEmpty() || memberaddress.isEmpty() || membername.isEmpty()|| memberphonenumber.isEmpty() || nopaiddues.isEmpty() ) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("MEMBER_ADDRESS", memberaddress);
        values.put("MEMBER_NAME", membername);
        values.put("MEMBER_PHONE_NUMBER", memberphonenumber);
        values.put("NO_PAID_DUES", nopaiddues);


        int result = db.update("Member", values, "MEMBER_ID = ?", new String[]{memberId});
        if (result == 0) {
            Toast.makeText(this, "Failed to update member", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Member updated successfully", Toast.LENGTH_SHORT).show();
            etMemberId.setText("");
            etMemberAddress.setText("");
            etMemberName.setText("");
            etMemberPhoneNumber.setText("");
            etNoPaid.setText("");
        }

        db.close();
    }
}
