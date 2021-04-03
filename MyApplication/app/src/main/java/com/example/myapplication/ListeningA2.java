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

public class ListeningA2 extends AppCompatActivity {


    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer [] = new String[]{"waste","start off, end up","turn off","look up","need",
                "2","1","5","4","3","6",
                "false","false","true","false","true","true","true","false"};

        for (int i = 0; i< list.size(); i++){
            if (answer[i].equals(list.get(i).getText().toString())==false)
                greseli++;
        }

        return greseli;
    }

    private List<EditText> getList(){
        List<EditText> p = new ArrayList<>();

        p.add(0,findViewById(R.id.listA21));
        p.add(1,findViewById(R.id.listA22));
        p.add(2,findViewById(R.id.listA23));
        p.add(3,findViewById(R.id.listA24));
        p.add(4,findViewById(R.id.listA25));
        p.add(5,findViewById(R.id.listA26));
        p.add(6,findViewById(R.id.listA27));
        p.add(7,findViewById(R.id.listA28));
        p.add(8,findViewById(R.id.listA29));
        p.add(9,findViewById(R.id.listA210));
        p.add(10,findViewById(R.id.listA211));
        p.add(11,findViewById(R.id.listA212));
        p.add(12,findViewById(R.id.listA213));
        p.add(13,findViewById(R.id.listA214));
        p.add(14,findViewById(R.id.listA215));
        p.add(15,findViewById(R.id.listA216));
        p.add(16,findViewById(R.id.listA217));
        p.add(17,findViewById(R.id.listA218));
        p.add(18,findViewById(R.id.listA219));

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
        setContentView(R.layout.activity_listening_a2);

        Button play = findViewById(R.id.playListA2);
        Button stop = findViewById(R.id.stopListA2);
        Button finish = findViewById(R.id.finlistA2);
        final MediaPlayer player = MediaPlayer.create(this,R.raw.listeningadoi);


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
                    db.addRezultat(new Rezultat(0,id, "ListeningA2",getStringAnswer(getList()),(19 - greseli) + " out of 19 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("ListeningA2") == true){
                            idResults = i;
                        }
                    }
                    if (idResults > 0)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"ListeningA2",getStringAnswer(getList()), (19 - greseli) + " out of 19 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "ListeningA2",getStringAnswer(getList()),(19 - greseli)+ " out of 19 ",copil.getRezultate()));
                }



                Intent intent = new Intent(ListeningA2.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });
    }
}