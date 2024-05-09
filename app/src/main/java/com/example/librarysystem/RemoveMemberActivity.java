package com.example.librarysystem;

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

public class RemoveMemberActivity extends AppCompatActivity {

    EditText etMemberId;
    Button btnRemove;

    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_member);

        etMemberId = findViewById(R.id.etMemberId);
        btnRemove = findViewById(R.id.btnRemove);

        database = new Database(this);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeMember();
            }
        });
    }

    private void removeMember() {
        String memberId = etMemberId.getText().toString();

        if (memberId.isEmpty()) {
            Toast.makeText(this, "Please enter Member ID", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = database.getWritableDatabase();
        int result = database.removeMember(memberId);

        if (result == 0) {
            Toast.makeText(this, "Failed to remove member", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Member removed successfully", Toast.LENGTH_SHORT).show();
            etMemberId.setText("");
        }

        db.close();
    }
}
