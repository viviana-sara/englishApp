package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.BlockingDeque;

import aplicatie.Copil;
import db.DBiLearning;

public class PracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);


        DBiLearning db = new DBiLearning(this);

        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("idCopil",0);
        Copil copil =  db.getByIdCopil(id,"idCopil");

        String grade = copil.getRezultate();

        Button pron = findViewById(R.id.practicePron);
        Button num = findViewById(R.id.practiceNum);
        Button plural = findViewById(R.id.practicePlural);
        Button superL = findViewById(R.id.practiceSuper);
        Button past = findViewById(R.id.practicePast);
        Button pres = findViewById(R.id.practicePresent);
        Button nextGrade = findViewById(R.id.practiceNextLevel);
        Button listening = findViewById(R.id.practiceListening);
        Button speaking = findViewById(R.id.practiceSpeaking);
        Button reading = findViewById(R.id.practiceReading);
        Button back = findViewById(R.id.practiceBack);

        plural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeActivity.this,PluralEx.class);
                intent.putExtra("idChild",id);
                startActivity(intent);
            }
        });

        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeActivity.this,NumeralExercitii.class);
                intent.putExtra("idChild",id);
                startActivity(intent);
            }
        });

        superL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeActivity.this,SuperlativExercises.class);
                intent.putExtra("idChild",id);
                startActivity(intent);
            }
        });

        pron.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeActivity.this,exercisesPronume.class);
                intent.putExtra("idChild",id);
                startActivity(intent);
            }
        });

        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeActivity.this,PastExercitii.class);
                intent.putExtra("idChild",id);
                startActivity(intent);
            }
        });

        pres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeActivity.this,PresentSImpleEx.class);
                intent.putExtra("idChild",id);
                startActivity(intent);
            }
        });


        nextGrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grade.equals("A")){
                    Intent intent = new Intent(PracticeActivity.this,firstPet.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }
                if (grade.equals("A1")){
                    Intent intent = new Intent(PracticeActivity.this,secondPet.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }
                if (grade.equals("A2")){
                    Intent intent = new Intent(PracticeActivity.this,thirdPet.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }

            }
        });

        listening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grade.equals("A")){
                    Intent intent = new Intent(PracticeActivity.this,ListeningA1.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }
                if (grade.equals("A1")){
                    Intent intent = new Intent(PracticeActivity.this,ListeningA1.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }
                if (grade.equals("A2")){
                    Intent intent = new Intent(PracticeActivity.this,ListeningA2.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }
                if (grade.equals("B1")){
                    Intent intent = new Intent(PracticeActivity.this,ListeningB1.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }

            }
        });

        reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (grade.equals("A")){
                    Intent intent = new Intent(PracticeActivity.this,ReadingA.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }
                if (grade.equals("A1")){
                    Intent intent = new Intent(PracticeActivity.this,ReadingA.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }
                if (grade.equals("A2")){
                    Intent intent = new Intent(PracticeActivity.this,ReadingB.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }
                if (grade.equals("B1")){
                    Intent intent = new Intent(PracticeActivity.this,ReadingB.class);
                    intent.putExtra("idChild",id);
                    startActivity(intent);
                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeActivity.this,CopilActivity.class);
                startActivity(intent);
            }
        });



    }
}