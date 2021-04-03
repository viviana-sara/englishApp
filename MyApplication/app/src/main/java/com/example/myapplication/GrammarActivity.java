package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import aplicatie.Copil;
import db.DBiLearning;

public class GrammarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);


        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("idCopil",0);


        Button plural = findViewById(R.id.grammarPlural);
        Button num = findViewById(R.id.grammarNum);
        Button superl = findViewById(R.id.grammarSuper);
        Button pron = findViewById(R.id.grammarPron);
        Button past = findViewById(R.id.grammarPast);
        Button pres = findViewById(R.id.grammarPresent);
        Button back = findViewById(R.id.grammarBack);

        plural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrammarActivity.this,PluralTeorie.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });

        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrammarActivity.this,NumeralTeorie.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });

        superl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrammarActivity.this,SuperlativTeorie.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });

        pron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrammarActivity.this,PronumeTeorie.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });

        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrammarActivity.this,PastTeorie.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });

        pres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrammarActivity.this,PresentSimpleTeorie.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GrammarActivity.this,CopilActivity.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });

    }
}