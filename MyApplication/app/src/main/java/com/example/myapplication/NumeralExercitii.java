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

public class NumeralExercitii extends AppCompatActivity {

    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer[] = new String[49];
        answer  = new String[]{"16", "26", "4", "18", "20", "6", "30", "25", "8", "17", "27", "24", "11",
                "seventeen", "twenty-three", "fifteen", "twenty-nine", "sixteen", "four", "eighteen", "twelve", "twenty", "ten", "two", "fourteen"
                };

        for (int i = 0; i< list.size(); i++){
            if (answer[i].equals(list.get(i).getText().toString())==false)
                greseli++;
        }

        return greseli;
    }

    private List<EditText> getList(){
        List<EditText> p = new ArrayList<>();

        p.add(0,findViewById(R.id.num1));
        p.add(1,findViewById(R.id.num2));
        p.add(2,findViewById(R.id.num3));
        p.add(3,findViewById(R.id.num4));
        p.add(4,findViewById(R.id.num5));
        p.add(5,findViewById(R.id.num6));
        p.add(6,findViewById(R.id.num7));
        p.add(7,findViewById(R.id.num8));
        p.add(8,findViewById(R.id.num9));
        p.add(9,findViewById(R.id.num10));

        p.add(10,findViewById(R.id.num11));
        p.add(11,findViewById(R.id.num12));
        p.add(12,findViewById(R.id.num13));
        p.add(13,findViewById(R.id.num14));
        p.add(14,findViewById(R.id.num15));
        p.add(15,findViewById(R.id.num16));
        p.add(16,findViewById(R.id.num17));

        p.add(17,findViewById(R.id.num18));
        p.add(18,findViewById(R.id.num19));
        p.add(19,findViewById(R.id.num20));
        p.add(20,findViewById(R.id.num21));
        p.add(21,findViewById(R.id.num22));

        p.add(22,findViewById(R.id.num23));
        p.add(23,findViewById(R.id.num24));
        p.add(24,findViewById(R.id.num25));

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
        setContentView(R.layout.activity_numeral_exercitii);

        Button finish = findViewById(R.id.finNumeral);
        TextView textView = findViewById(R.id.viewNum);

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
                    db.addRezultat(new Rezultat(0,id, "Number Charts",getStringAnswer(getList()),(25 - greseli)+ " out of 25 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("Number Charts") == true){
                            idResults = i;
                        }
                    }
                    if (idResults > 0)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"Number Charts",getStringAnswer(getList()), (25 - greseli)+ " out of 25 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "Number Charts",getStringAnswer(getList()),(25 - greseli)+ " out of 25 ",copil.getRezultate()));
                }



                Intent intent = new Intent(NumeralExercitii.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });
    }
}