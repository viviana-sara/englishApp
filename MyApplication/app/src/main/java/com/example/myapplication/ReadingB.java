package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReadingB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_b);

        Button back = findViewById(R.id.finReadB);

        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("idChild",0);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReadingB.this, CopilActivity.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });
    }
}