package com.example.librarysystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PublisherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_publisher);

        CardView addPublisher = findViewById(R.id.cardPUAddPublisher);
        addPublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(PublisherActivity.this, AddPublisherActivity.class);
                it.putExtra("title", "ADD PUBLISHER");
                startActivity(it);
            }
        });

        CardView updatePublisher= findViewById(R.id.cardPOUpdatePublisher);
        updatePublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(PublisherActivity.this, UpdatePublisherActivity.class);
                it.putExtra("title", "UPDATE PUBLISHER");
                startActivity(it);
            }
        });

        CardView removePublisher = findViewById(R.id.cardPURemovePublisher);
        removePublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(PublisherActivity.this, RemovePublisherActivity.class);
                it.putExtra("title", "REMOVE PUBLISHER");
                startActivity(it);
            }
        });

        CardView viewPublisher = findViewById(R.id.cardPOViewPublisher);
        viewPublisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(PublisherActivity.this, ViewpPublisherActivity.class);
                it.putExtra("title", "VIEW PUBLISHERS LIST");
                startActivity(it);
            }
        });
    }
}

