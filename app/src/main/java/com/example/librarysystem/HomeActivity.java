package com.example.librarysystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.cardview.widget.CardView;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username= sharedPreferences.getString("username","").toString();
        Toast.makeText(getApplicationContext(),"Welcome"+ username, Toast.LENGTH_SHORT).show();


        CardView exit = findViewById(R.id.cardLogout);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            }
        });


        CardView book = findViewById(R.id.Addbook);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent( HomeActivity.this,BookActivity.class));
            }
        });

        CardView publisher = findViewById(R.id.Publisher);
        publisher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent( HomeActivity.this,PublisherActivity.class));
            }
        });

        CardView branches = findViewById(R.id.Branches);
        branches.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(HomeActivity.this,BranchActivity.class));
            }
        });

        CardView member = findViewById(R.id.Member);
        member.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(HomeActivity.this,MemberActivity.class));
            }
        });


        CardView author = findViewById(R.id.Author);
        author.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(HomeActivity.this,AuthorActivity.class));
            }
        });

        CardView bookloan = findViewById(R.id.BookLoan);
        bookloan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(HomeActivity.this,BookLoanActivity.class));
            }
        });

        CardView bookcopy = findViewById(R.id.BookCopy);
        bookcopy.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(HomeActivity.this,BookCopiesActivity.class));
            }
        });







    }

}
