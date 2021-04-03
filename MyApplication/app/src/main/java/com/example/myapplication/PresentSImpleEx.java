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

public class PresentSImpleEx extends AppCompatActivity {

    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer [] = new String[]{"is playing",
                "do","go",
                "are","shouting",
                "is falling",
                "do","know",
                "do","drink",
                "are playing",
                "aren't looking",
                "doesn't eat",
                "are", "talking",
                "is watching",
                "do","live",
                "doe's","like",
                "are going",
                "don't ride",
                "don't go",
                "spend",
                "aren't listening",
                "doesn't read","prefers",
                "is talking"};

        for (int i = 0; i< list.size(); i++){
            if (answer[i].equals(list.get(i).getText().toString())==false)
                greseli++;
        }

        return greseli;
    }

    private List<EditText> getList(){
        List<EditText> p = new ArrayList<>();

        p.add(0,findViewById(R.id.present1));
        p.add(1,findViewById(R.id.present2));
        p.add(2,findViewById(R.id.present3));
        p.add(3,findViewById(R.id.present4));
        p.add(4,findViewById(R.id.present5));
        p.add(5,findViewById(R.id.present6));
        p.add(6,findViewById(R.id.present7));
        p.add(7,findViewById(R.id.present8));
        p.add(8,findViewById(R.id.present9));
        p.add(9,findViewById(R.id.present10));
        p.add(10,findViewById(R.id.present11));
        p.add(11,findViewById(R.id.present12));
        p.add(12,findViewById(R.id.present13));
        p.add(13,findViewById(R.id.present14));
        p.add(14,findViewById(R.id.present15));
        p.add(15,findViewById(R.id.present16));
        p.add(16,findViewById(R.id.present17));
        p.add(17,findViewById(R.id.present18));
        p.add(18,findViewById(R.id.present19));
        p.add(19,findViewById(R.id.present20));
        p.add(20,findViewById(R.id.present21));
        p.add(21,findViewById(R.id.present22));
        p.add(22,findViewById(R.id.present23));
        p.add(23,findViewById(R.id.present24));
        p.add(24,findViewById(R.id.present25));
        p.add(25,findViewById(R.id.present26));
        p.add(26,findViewById(R.id.present27));
        p.add(27,findViewById(R.id.present28));

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
        setContentView(R.layout.activity_present_s_imple_ex);

        Button finish = findViewById(R.id.finpresent);
        TextView textView = findViewById(R.id.viewPresent);

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
                    db.addRezultat(new Rezultat(0,id, "Present simple and continuous",getStringAnswer(getList()),(28 - greseli) + " out of 28 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("Present simple and continuous") == true){
                            idResults = i;
                        }
                    }
                    if (idResults > 0)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"Present simple and continuous",getStringAnswer(getList()), (28 - greseli) + " out of 28 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "Present simple and continuous",getStringAnswer(getList()),(28 - greseli) + " out of 28 ",copil.getRezultate()));
                }



                Intent intent = new Intent(PresentSImpleEx.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });

    }
}