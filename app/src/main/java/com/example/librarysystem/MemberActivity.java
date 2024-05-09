package com.example.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MemberActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);  // Make sure this layout name is correct

        CardView addMember = findViewById(R.id.cardMEAddMember);
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MemberActivity.this, AddMemberActivity.class);
                it.putExtra("title", "ADD BOOK");
                startActivity(it);
            }
        });

        CardView updateMember = findViewById(R.id.cardMEUpdateMember);
        updateMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, UpdateMemberActivity.class);
                it.putExtra("title", "UPDATE BOOK");
                startActivity(it);
            }
        });

        CardView removeMember = findViewById(R.id.cardMERemoveMember);
        removeMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, RemoveMemberActivity.class);
                it.putExtra("title", "REMOVE BOOK");
                startActivity(it);
            }
        });

        CardView viewMember = findViewById(R.id.cardMEViewMember);
        viewMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MemberActivity.this, ViewMemberActivity.class);
                it.putExtra("title", "VIEW BOOK");
                startActivity(it);
            }
        });
    }
}