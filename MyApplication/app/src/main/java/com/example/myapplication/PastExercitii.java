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

public class PastExercitii extends AppCompatActivity {

    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer[] = new String[49];
        answer  = new String[]{"was preparing", "came", "rained", "were talking", "walked", "fell","broke", "entered", "was having", "drove", "was shining", "got up", "told",
                "was talking","met", "did your father arrive","arrived","was", "were you speaking","came", "was reading","heard", "left","was shining","rained", "arrived","wasn't", "was studying", "rescued","was", "were you doing", "fell","hurt","was riding", "was Peter doing","was knocking", "was wearing"
        };

        for (int i = 0; i< list.size(); i++){
            if (answer[i].equals(list.get(i).getText().toString())==false)
                greseli++;
        }

        return greseli;
    }

    private List<EditText> getList(){
        List<EditText> p = new ArrayList<>();

        p.add(0,findViewById(R.id.past1));
        p.add(1,findViewById(R.id.past2));
        p.add(2,findViewById(R.id.past3));
        p.add(3,findViewById(R.id.past4));
        p.add(4,findViewById(R.id.past5));
        p.add(5,findViewById(R.id.past6));
        p.add(6,findViewById(R.id.past7));
        p.add(7,findViewById(R.id.past8));
        p.add(8,findViewById(R.id.past9));
        p.add(9,findViewById(R.id.past10));
        p.add(10,findViewById(R.id.past11));
        p.add(11,findViewById(R.id.past12));
        p.add(12,findViewById(R.id.past13));
        p.add(13,findViewById(R.id.past14));
        p.add(14,findViewById(R.id.past15));
        p.add(15,findViewById(R.id.past16));
        p.add(16,findViewById(R.id.past17));
        p.add(17,findViewById(R.id.past18));
        p.add(18,findViewById(R.id.past19));
        p.add(19,findViewById(R.id.past20));
        p.add(20,findViewById(R.id.past21));
        p.add(21,findViewById(R.id.past22));
        p.add(22,findViewById(R.id.past23));
        p.add(23,findViewById(R.id.past24));
        p.add(24,findViewById(R.id.past25));
        p.add(25,findViewById(R.id.past26));
        p.add(26,findViewById(R.id.past27));
        p.add(27,findViewById(R.id.past28));
        p.add(28,findViewById(R.id.past29));
        p.add(29,findViewById(R.id.past30));
        p.add(30,findViewById(R.id.past31));
        p.add(31,findViewById(R.id.past32));
        p.add(32,findViewById(R.id.past33));
        p.add(33,findViewById(R.id.past34));
        p.add(34,findViewById(R.id.past35));
        p.add(35,findViewById(R.id.past36));
        p.add(36,findViewById(R.id.past37));

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
        setContentView(R.layout.activity_past_exercitii);

        Button finish = findViewById(R.id.finPast);
        TextView textView = findViewById(R.id.viewPlural);

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
                    db.addRezultat(new Rezultat(0,id, "Past simple and continuous",getStringAnswer(getList()),(37 - greseli)+ " out of 37 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("Past simple and continuous") == true){
                            idResults = i;
                        }
                    }
                    if (idResults > 0)
                            db.updateRezultat(idResults, new Rezultat(idResults,id,"Past simple and continuous",getStringAnswer(getList()), (37 - greseli)+ " out of 37 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "Past simple and continuous",getStringAnswer(getList()),(37 - greseli)+ " out of 37 ",copil.getRezultate()));
                }



                Intent intent = new Intent(PastExercitii.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });

    }
}