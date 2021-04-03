package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aplicatie.Copil;
import aplicatie.Rezultat;
import db.DBiLearning;

public class secondPet extends AppCompatActivity {

    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer[] = new String[49];
        answer  = new String[]{"a fridge", "salt", "knives", "cupboards", "hockey", "limes", "an apartment", "volleyball", "vegetables", "meals", "yes", "no", "yes", "no", "no", "no",
                "yes",
                "D",
                "E",
                "C",
                "G",
                "F",
                "sleeping",
                "outside",
                "opened",
                "ran",
                "bowl",
                "was a baby",
                "next to",
                "picnic",
                "it is sunny",
                "swings",
                "couldn't find",
                "Treasure",
                "work",
                "Three",
                "them",
                "just",
                "stay",
                "have",
                "because",
                "some",
                "when",
                "who",
                "took",
                "called",
                "was",
                "when", "on"};

        for (int i = 0; i< 49; i++){
            if (answer[i].equals(list.get(i).getText().toString())==false)
                greseli++;
        }

        return greseli;
    }

    private String getStringAnswer(List<EditText> list){
        String answer = "";

        for (int i = 0; i<list.size(); i++){
            answer = answer + list.get(i).getText().toString();
        }

        return answer;
    }


    private List<EditText> getList(){
        List<EditText> p = new ArrayList<>();
        p.add(0,findViewById(R.id.pt2_1_1));
        p.add(1,findViewById(R.id.pt2_1_2));
        p.add(2,findViewById(R.id.pt2_1_3));
        p.add(3,findViewById(R.id.pt2_1_4));
        p.add(4,findViewById(R.id.pt2_1_5));
        p.add(5,findViewById(R.id.pt2_1_6));
        p.add(6,findViewById(R.id.pt2_1_7));
        p.add(7,findViewById(R.id.pt2_1_8));
        p.add(8,findViewById(R.id.pt2_1_9));
        p.add(9,findViewById(R.id.pt2_1_10));

        p.add(10,findViewById(R.id.pt2_2_1));
        p.add(11,findViewById(R.id.pt2_2_2));
        p.add(12,findViewById(R.id.pt2_2_3));
        p.add(13,findViewById(R.id.pt2_2_4));
        p.add(14,findViewById(R.id.pt2_2_5));
        p.add(15,findViewById(R.id.pt2_2_6));
        p.add(16,findViewById(R.id.pt2_2_7));

        p.add(17,findViewById(R.id.pt2_3_1));
        p.add(18,findViewById(R.id.pt2_3_2));
        p.add(19,findViewById(R.id.pt2_3_3));
        p.add(20,findViewById(R.id.pt2_3_4));
        p.add(21,findViewById(R.id.pt2_3_5));

        p.add(22,findViewById(R.id.pt2_4_1));
        p.add(23,findViewById(R.id.pt2_4_2));
        p.add(24,findViewById(R.id.pt2_4_3));
        p.add(25,findViewById(R.id.pt2_4_4));
        p.add(26,findViewById(R.id.pt2_4_5));

        p.add(27,findViewById(R.id.pt2_5_1));
        p.add(28,findViewById(R.id.pt2_5_2));
        p.add(29,findViewById(R.id.pt2_5_3));
        p.add(30,findViewById(R.id.pt2_5_4));
        p.add(31,findViewById(R.id.pt2_5_5));
        p.add(32,findViewById(R.id.pt2_5_6));
        p.add(33,findViewById(R.id.pt2_5_7));

        p.add(34,findViewById(R.id.pt2_6_1));
        p.add(35,findViewById(R.id.pt2_6_2));
        p.add(36,findViewById(R.id.pt2_6_3));
        p.add(37,findViewById(R.id.pt2_6_4));
        p.add(38,findViewById(R.id.pt2_6_5));
        p.add(39,findViewById(R.id.pt2_6_6));
        p.add(40,findViewById(R.id.pt2_6_7));
        p.add(41,findViewById(R.id.pt2_6_8));
        p.add(42,findViewById(R.id.pt2_6_9));
        p.add(43,findViewById(R.id.pt2_6_10));

        p.add(44,findViewById(R.id.pt2_7_1));
        p.add(45,findViewById(R.id.pt2_7_2));
        p.add(46,findViewById(R.id.pt2_7_3));
        p.add(47,findViewById(R.id.pt2_7_4));
        p.add(48,findViewById(R.id.pt2_7_5));

        return p;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_pet);

        List<EditText> p = new ArrayList<>();

        Button finish = findViewById(R.id.finPet2);
        TextView textView = findViewById(R.id.checkNull);
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
                textView.setText("Numar greseli = " + greseli);

                if (greseli < 7)
                    db.updateCopil(copil.getId(), new Copil(copil.getId(),copil.getIdParitne(),copil.getIdCopil(),"A2"));

                if (results.size()==0)
                    db.addRezultat(new Rezultat(0,id, "Flyers",getStringAnswer(getList()),(49-greseli)+ " out of 49 ",copil.getRezultate()));
                else{
                    int ok = 0;
                    int idResults = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("Flyers") == true){
                            idResults = results.get(i).getId();
                            ok = 1;
                        }
                    }
                    if (ok  == 1)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"Flyers",getStringAnswer(getList()), (49-greseli)+ " out of 49 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "Flyers",getStringAnswer(getList()),(49-greseli)+ " out of 49 ",copil.getRezultate()));
                }



                Intent intent = new Intent(secondPet.this,PracticeActivity.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });
    }


}