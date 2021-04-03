package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CopilActivity extends AppCompatActivity {
    private String name= "idCopil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copil);

        Button grammar = findViewById(R.id.copilGramar);
        Button practice = findViewById(R.id.copilPractice);
        Button logOut = findViewById(R.id.copilLogOut);
        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("idCopil",0);

        grammar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CopilActivity.this,GrammarActivity.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });

        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CopilActivity.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CopilActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}