package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import aplicatie.Copil;
import aplicatie.Rezultat;
import db.DBiLearning;

public class ListeningB1 extends AppCompatActivity {

    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer [] = new String[]{"e","h","b","a","c","d","f","g",
                "b","c","a","b","a","b","b","c","c","c",
                "b","f","g","h",
                "a","c","d","e"};

        for (int i = 0; i< list.size(); i++){
            if (answer[i].equals(list.get(i).getText().toString())==false)
                greseli++;
        }

        return greseli;
    }

    private List<EditText> getList(){
        List<EditText> p = new ArrayList<>();

        p.add(0,findViewById(R.id.listB11));
        p.add(1,findViewById(R.id.listB12));
        p.add(2,findViewById(R.id.listB13));
        p.add(3,findViewById(R.id.listB14));
        p.add(4,findViewById(R.id.listB15));
        p.add(5,findViewById(R.id.listB16));
        p.add(6,findViewById(R.id.listB17));
        p.add(7,findViewById(R.id.listB18));
        p.add(8,findViewById(R.id.listB19));
        p.add(9,findViewById(R.id.listB110));
        p.add(10,findViewById(R.id.listB111));
        p.add(11,findViewById(R.id.listB112));
        p.add(12,findViewById(R.id.listB113));
        p.add(13,findViewById(R.id.listB114));
        p.add(14,findViewById(R.id.listB115));
        p.add(15,findViewById(R.id.listB116));
        p.add(16,findViewById(R.id.listB117));
        p.add(17,findViewById(R.id.listB118));
        p.add(18,findViewById(R.id.listB119));
        p.add(19,findViewById(R.id.listB120));
        p.add(20,findViewById(R.id.listB121));
        p.add(21,findViewById(R.id.listB122));
        p.add(22,findViewById(R.id.listB123));
        p.add(23,findViewById(R.id.listB124));
        p.add(24,findViewById(R.id.listB125));

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
        setContentView(R.layout.activity_listening_b1);

        Button play = findViewById(R.id.playListB1);
        Button stop = findViewById(R.id.stopListB1);
        Button finish = findViewById(R.id.finlistB1);
        final MediaPlayer player = MediaPlayer.create(this,R.raw.listeningbunu);

        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("idChild",0);

        DBiLearning db = new DBiLearning(this);

        Copil copil = db.getByIdCopil(id,"id");
        List<Rezultat> results = db.getResults(id,"idCopil");

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<EditText> p = getList();
                int greseli = writeOn(p);
                if (results.size()==0)
                    db.addRezultat(new Rezultat(0,id, "ListeningB1",getStringAnswer(getList()),(25 - greseli) + " out of 25 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("ListeningB1") == true){
                            idResults = i;
                        }
                    }
                    if (idResults > 0)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"ListeningB1",getStringAnswer(getList()), (25 - greseli) + " out of 25 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "ListeningB1",getStringAnswer(getList()),(25 - greseli)+ " out of 25 ",copil.getRezultate()));
                }



                Intent intent = new Intent(ListeningB1.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });
    }
}