package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aplicatie.Copil;
import aplicatie.Rezultat;
import db.DBiLearning;

public class SuperlativExercises extends AppCompatActivity {


    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer[] = new String[49];
        answer  = new String[]{"the biggest",
                "the highest",
                "the wisest",
                "the longest",
                "the bravest",
                "the most intelligent",
                "the largest",
                "the laziest","the most unpopular",
                "the tallest",
                "the brightest",
                "the most interesting",
                "the worst"};

        for (int i = 0; i< list.size(); i++){
            if (answer[i].equals(list.get(i).getText().toString())==false)
                greseli++;
        }

        return greseli;
    }



    private List<EditText> getList(){
        List<EditText> p = new ArrayList<>();

        p.add(0,findViewById(R.id.super1));
        p.add(1,findViewById(R.id.super2));
        p.add(2,findViewById(R.id.super3));
        p.add(3,findViewById(R.id.super4));
        p.add(4,findViewById(R.id.super5));
        p.add(5,findViewById(R.id.super6));
        p.add(6,findViewById(R.id.super7));
        p.add(7,findViewById(R.id.super8));
        p.add(8,findViewById(R.id.super9));
        p.add(9,findViewById(R.id.super10));
        p.add(10,findViewById(R.id.super11));
        p.add(11,findViewById(R.id.super12));
        p.add(12,findViewById(R.id.super13));

        return p;

    }

    private String getStringAnswer(List<EditText> list){
        String answer = "";

        for (int i = 0; i<list.size(); i++){
            answer = answer + list.get(i).getText().toString();
        }

        return answer;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superlativ_exercises);

        Button finish = findViewById(R.id.finsuper);
        TextView textView = findViewById(R.id.viewSuper);

        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("idChild",0);

        DBiLearning db = new DBiLearning(this);

        Copil copil = db.getByIdCopil(id,"id");
        List<Rezultat> results = db.getResults(id,"idCopil");

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<EditText> p = getList();
                int greseli = writeOn(p);
               // textView.setText("Numar greseli = " + greseli);

                if (results.size()==0)
                    db.addRezultat(new Rezultat(0,id, "Adjectives",getStringAnswer(getList()),(13 - greseli) + " out of 13 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("Adjectives") == true){
                            idResults = i;
                        }
                    }
                    if (idResults > 0)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"Adjectives",getStringAnswer(getList()), (13 - greseli) + " out of 13 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "Adjectives",getStringAnswer(getList()),(13 - greseli) + " out of 13 ",copil.getRezultate()));
                }



                Intent intent = new Intent(SuperlativExercises.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });
    }
}