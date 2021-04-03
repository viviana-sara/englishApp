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

public class exercisesPronume extends AppCompatActivity {


    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer [] = new String[]{"theirs", "mine", "ours", "his", "ours", "his", "theirs", "yours", "hers", "theirs", "hers", "theirs",
                                        "everywhere", "anything", "somewhere", "anywhere", "nothing", "nowhere", "everybody", "something", "nobody", "everything", "anybody", "someone",
                                         "anything", "nobody", "everywhere", "something", "anywhere", "everybody", "somebody", "anyone","nowhere", "nothing", "somewhere", "everything"
        };

        for (int i = 0; i< list.size(); i++){
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

        p.add(0,findViewById(R.id.pron1));
        p.add(1,findViewById(R.id.pron2));
        p.add(2,findViewById(R.id.pron3));
        p.add(3,findViewById(R.id.pron4));
        p.add(4,findViewById(R.id.pron5));
        p.add(5,findViewById(R.id.pron6));
        p.add(6,findViewById(R.id.pron7));
        p.add(7,findViewById(R.id.pron8));
        p.add(8,findViewById(R.id.pron9));
        p.add(9,findViewById(R.id.pron10));
        p.add(10,findViewById(R.id.pron11));
        p.add(11,findViewById(R.id.pron12));
        p.add(12,findViewById(R.id.pron13));
        p.add(13,findViewById(R.id.pron14));
        p.add(14,findViewById(R.id.pron15));
        p.add(15,findViewById(R.id.pron16));
        p.add(16,findViewById(R.id.pron17));
        p.add(17,findViewById(R.id.pron18));
        p.add(18,findViewById(R.id.pron19));
        p.add(19,findViewById(R.id.pron20));
        p.add(20,findViewById(R.id.pron21));
        p.add(21,findViewById(R.id.pron22));
        p.add(22,findViewById(R.id.pron23));
        p.add(23,findViewById(R.id.pron24));
        p.add(24,findViewById(R.id.pron25));
        p.add(25,findViewById(R.id.pron26));
        p.add(26,findViewById(R.id.pron27));
        p.add(27,findViewById(R.id.pron28));
        p.add(28,findViewById(R.id.pron29));
        p.add(29,findViewById(R.id.pron30));
        p.add(30,findViewById(R.id.pron31));
        p.add(31,findViewById(R.id.pron32));
        p.add(32,findViewById(R.id.pron33));
        p.add(33,findViewById(R.id.pron34));
        p.add(34,findViewById(R.id.pron35));
        p.add(35,findViewById(R.id.pron36));
        return p;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_pronume);

        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("idChild",0);

        DBiLearning db = new DBiLearning(this);

        Copil copil = db.getByIdCopil(id,"id");
        List<Rezultat> results = db.getResults(id,"idCopil");

        Button finish = findViewById(R.id.pronumeFinish);
        TextView textView = findViewById(R.id.viewPronume);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<EditText> p = getList();
                int greseli = writeOn(p);
                if (results.size()==0)
                    db.addRezultat(new Rezultat(0,id, "Pronouns",getStringAnswer(getList()),(36 - greseli)+ " out of 36 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("Pronouns") == true){
                            idResults = i;
                        }
                    }
                    if (idResults > 0)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"Pronouns",getStringAnswer(getList()), (36-greseli)+ " out of 36 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "Pronouns",getStringAnswer(getList()),(36-greseli)+ " out of 36 ",copil.getRezultate()));
                }



                Intent intent = new Intent(exercisesPronume.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });
    }
}