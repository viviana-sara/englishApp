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

public class firstPet extends AppCompatActivity {


    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer[] = new String[49];
        answer  = new String[]{"yes", "yes","no","yes","yes","duck", "mouse", "hippo", "monkey", "chicken",
                                "spiders", "legs", "tail", "trees", "sand","spiders" ,"chair" ,"shoe",
                                        "daughter",
                                        "yard outside",
                                        "door way"};

        for (int i = 0; i< list.size(); i++){
            if (answer[i].equals(list.get(i).getText().toString())==false)
                greseli++;
        }
        CheckBox checkBox1 = findViewById(R.id.checkStarter1);
        CheckBox checkBox2 = findViewById(R.id.checkStarter2);
        CheckBox checkBox3 = findViewById(R.id.checkStarter3);
        CheckBox checkBox4 = findViewById(R.id.checkStarter4);
        CheckBox checkBox5 = findViewById(R.id.checkStarter5);

        if (checkBox1.isChecked())
            greseli++;

        if (checkBox2.isChecked()== false)
            greseli++;
        if (checkBox3.isChecked() == false)
            greseli++;

        if (checkBox4.isChecked()== true)
            greseli++;

        if (checkBox5.isChecked()== false)
            greseli++;


        return greseli;
    }

    private List<EditText> getList(){
        List<EditText> p = new ArrayList<>();

        p.add(0,findViewById(R.id.str1));
        p.add(1,findViewById(R.id.str2));
        p.add(2,findViewById(R.id.str3));
        p.add(3,findViewById(R.id.str4));
        p.add(4,findViewById(R.id.str5));
        p.add(5,findViewById(R.id.str6));
        p.add(6,findViewById(R.id.str7));
        p.add(7,findViewById(R.id.str8));
        p.add(8,findViewById(R.id.str9));
        p.add(9,findViewById(R.id.str10));
        p.add(10,findViewById(R.id.str11));
        p.add(11,findViewById(R.id.str12));
        p.add(12,findViewById(R.id.str13));
        p.add(13,findViewById(R.id.str14));
        p.add(14,findViewById(R.id.str15));
        p.add(15,findViewById(R.id.str16));
        p.add(16,findViewById(R.id.str17));
        p.add(17,findViewById(R.id.str18));
        p.add(18,findViewById(R.id.str19));
        p.add(19,findViewById(R.id.str20));

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
        setContentView(R.layout.activity_first_pet);

        Button finish = findViewById(R.id.finStarter);
        TextView textView = findViewById(R.id.viewStarter);

        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("idChild",0);

        DBiLearning db = new DBiLearning(this);

        Copil copil = db.getByIdCopil(id,"idCopil");
        List<Rezultat> results = db.getResults(id,"idCopil");

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<EditText> p = getList();
                int greseli = writeOn(p);
                textView.setText("Numar greseli = " + greseli);

                if (results.size()==0)
                    db.addRezultat(new Rezultat(0,id, "Starter",getStringAnswer(getList()),(20-greseli)+ " of 20 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    int ok = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("Starter") == true){
                            idResults = results.get(i).getId();
                            ok =1;
                        }
                    }
                    if (ok == 1)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"Starter",getStringAnswer(getList()), (20-greseli)+ " out of 20 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "Starter",getStringAnswer(getList()),(20-greseli)+ " out of 20 ",copil.getRezultate()));
                }

                if (greseli < 3)
                    db.updateCopil(copil.getId(), new Copil(copil.getId(),copil.getIdParitne(),copil.getIdCopil(),"A1"));

                Intent intent = new Intent(firstPet.this,PracticeActivity.class);
                intent.putExtra("idCopil", id);
                startActivity(intent);
            }
        });
    }
}