package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import aplicatie.Copil;
import aplicatie.Rezultat;
import db.DBiLearning;

public class thirdPet extends AppCompatActivity {

    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer[];
        answer  = new String[]{"C","C","B","B","C","D","G","F","A","B","B","B","A","B","B","A","A",
                                "B","A","A","C","D","D","B","A",
                                "D","B","A","C","A","B","B","C","A","A","has been", "to","are created","listening","there"};

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

        p.add(0,findViewById(R.id.pr1));
        p.add(1,findViewById(R.id.pr2));
        p.add(2,findViewById(R.id.pr3));
        p.add(3,findViewById(R.id.pr4));
        p.add(4,findViewById(R.id.pr5));
        p.add(5,findViewById(R.id.pr6));
        p.add(6,findViewById(R.id.pr7));
        p.add(7,findViewById(R.id.pr8));
        p.add(8,findViewById(R.id.pr9));
        p.add(9,findViewById(R.id.pr10));
        p.add(10,findViewById(R.id.pr11));
        p.add(11,findViewById(R.id.pr12));
        p.add(12,findViewById(R.id.pr13));
        p.add(13,findViewById(R.id.pr14));
        p.add(14,findViewById(R.id.pr15));
        p.add(15,findViewById(R.id.pr16));
        p.add(16,findViewById(R.id.pr17));
        p.add(17,findViewById(R.id.pr18));
        p.add(18,findViewById(R.id.pr19));
        p.add(19,findViewById(R.id.pr20));
        p.add(20,findViewById(R.id.pr21));
        p.add(21,findViewById(R.id.pr22));
        p.add(22,findViewById(R.id.pr23));
        p.add(23,findViewById(R.id.pr24));
        p.add(24,findViewById(R.id.pr25));
        p.add(25,findViewById(R.id.pr26));
        p.add(26,findViewById(R.id.pr27));
        p.add(27,findViewById(R.id.pr28));
        p.add(28,findViewById(R.id.pr29));
        p.add(29,findViewById(R.id.pr30));
        p.add(30,findViewById(R.id.pr31));
        p.add(31,findViewById(R.id.pr32));
        p.add(32,findViewById(R.id.pr33));
        p.add(33,findViewById(R.id.pr34));
        p.add(34,findViewById(R.id.pr35));
        p.add(35,findViewById(R.id.pr36));
        p.add(36,findViewById(R.id.pr37));
        p.add(37,findViewById(R.id.pr38));
        p.add(38,findViewById(R.id.pr39));
        p.add(39,findViewById(R.id.pr40));

        return p;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_pet);

        List<EditText> p = new ArrayList<>();

        Button finish= findViewById(R.id.finPet);
        TextView textView = findViewById(R.id.viewPet);

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
                    db.updateCopil(copil.getId(), new Copil(copil.getId(),copil.getIdParitne(),copil.getIdCopil(),"B1"));

                if (results.size()==0)
                    db.addRezultat(new Rezultat(0,id, "Pet",getStringAnswer(getList()),(40-greseli)+ " out of 40 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    int ok = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("Pet") == true){
                            idResults = results.get(i).getId();
                            ok =1;
                        }
                    }
                    if (ok == 1)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"Pet",getStringAnswer(getList()), (40-greseli)+ " out of 40 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "Pet",getStringAnswer(getList()),(40-greseli)+ " out of 40 ",copil.getRezultate()));
                }



                Intent intent = new Intent(thirdPet.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });
    }
}