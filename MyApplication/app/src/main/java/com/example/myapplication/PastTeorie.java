package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PastTeorie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_teorie);

        Button back = findViewById(R.id.pastBack);

        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("idCopil",0);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PastTeorie.this, GrammarActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });
    }
}