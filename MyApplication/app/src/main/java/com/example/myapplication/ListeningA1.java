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

public class ListeningA1 extends AppCompatActivity {

    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer [] = new String[]{"2","3","4","1","5",
                                        "true","false","true","false","false","true",
                                         "get up!","go swimming","have breakfast","go to university","have lunch","meet friends" ,"have dinner" };

        for (int i = 0; i< list.size(); i++){
            if (answer[i].equals(list.get(i).getText().toString())==false)
                greseli++;
        }

        return greseli;
    }

    private List<EditText> getList(){
        List<EditText> p = new ArrayList<>();

        p.add(0,findViewById(R.id.listA11));
        p.add(1,findViewById(R.id.listA12));
        p.add(2,findViewById(R.id.listA13));
        p.add(3,findViewById(R.id.listA14));
        p.add(4,findViewById(R.id.listA15));
        p.add(5,findViewById(R.id.listA16));
        p.add(6,findViewById(R.id.listA17));
        p.add(7,findViewById(R.id.listA18));
        p.add(8,findViewById(R.id.listA19));
        p.add(9,findViewById(R.id.listA110));
        p.add(10,findViewById(R.id.listA111));
        p.add(11,findViewById(R.id.listA112));
        p.add(12,findViewById(R.id.listA113));
        p.add(13,findViewById(R.id.listA114));
        p.add(14,findViewById(R.id.listA115));
        p.add(15,findViewById(R.id.listA116));
        p.add(16,findViewById(R.id.listA117));
        p.add(17,findViewById(R.id.listA118));

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
        setContentView(R.layout.activity_listening_a1);

        Button play = findViewById(R.id.playListA1);
        Button stop = findViewById(R.id.stopListA1);
        Button finish = findViewById(R.id.finlistA1);


        Intent intent1 = getIntent();
        int id = intent1.getIntExtra("idChild",0);

        DBiLearning db = new DBiLearning(this);

        Copil copil = db.getByIdCopil(id,"id");
        List<Rezultat> results = db.getResults(id,"idCopil");
        final MediaPlayer player = MediaPlayer.create(this,R.raw.listeninigaunu);

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
                    db.addRezultat(new Rezultat(0,id, "ListeningA1",getStringAnswer(getList()),(18 - greseli) + " out of 18 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("ListeningA1") == true){
                            idResults = i;
                        }
                    }
                    if (idResults > 0)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"ListeningA1",getStringAnswer(getList()), (18 - greseli) + " out of 18 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "ListeningA1",getStringAnswer(getList()),(18 - greseli) + " out of 18 ",copil.getRezultate()));
                }



                Intent intent = new Intent(ListeningA1.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });






    }
}