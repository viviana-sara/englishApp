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

public class PluralEx extends AppCompatActivity {


    private int writeOn(List<EditText> list){
        int greseli=0;

        String answer[] = new String[49];
        answer  = new String[]{"brackets", "signal", "monkeys", "fries", "screen", "copy", "chores", "tax", "ray",
                "flower", "monster", "bus", "prairie", "seam", "scenes", "rivers", "curry", "elks",
                "apples", "houses", "children", "knives", "friends", "fish", "men", "families", "hamburger", "vegetables", "sheep", "mice", "hands", "wives", "buses", "teeth", "sweets", "feet", "babies",
                "There are children in front the trees.",
                "We are your best friends.",
                "There are pens under the book.",
                "They have got oranges in their hands.",
                "They have got children.",
                "My neighbours usually buy in this shopping centre.",
                "We don't like these books.",
                "Those are the best cakes I've ever eaten."
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

        p.add(0,findViewById(R.id.plural1));
        p.add(1,findViewById(R.id.plural2));
        p.add(2,findViewById(R.id.plural3));
        p.add(3,findViewById(R.id.plural4));
        p.add(4,findViewById(R.id.plural5));
        p.add(5,findViewById(R.id.plural6));
        p.add(6,findViewById(R.id.plural7));
        p.add(7,findViewById(R.id.plural8));
        p.add(8,findViewById(R.id.plural9));
        p.add(9,findViewById(R.id.plural10));
        p.add(10,findViewById(R.id.plural11));
        p.add(11,findViewById(R.id.plural12));
        p.add(12,findViewById(R.id.plural13));
        p.add(13,findViewById(R.id.plural14));
        p.add(14,findViewById(R.id.plural15));
        p.add(15,findViewById(R.id.plural16));
        p.add(16,findViewById(R.id.plural17));
        p.add(17,findViewById(R.id.plural18));
        p.add(18,findViewById(R.id.plural20));
        p.add(19,findViewById(R.id.plural21));
        p.add(20,findViewById(R.id.plural22));
        p.add(21,findViewById(R.id.plural23));
        p.add(22,findViewById(R.id.plural24));
        p.add(23,findViewById(R.id.plural25));
        p.add(24,findViewById(R.id.plural26));
        p.add(25,findViewById(R.id.plural27));
        p.add(26,findViewById(R.id.plural28));
        p.add(27,findViewById(R.id.plural29));
        p.add(28,findViewById(R.id.plural30));
        p.add(29,findViewById(R.id.plural31));
        p.add(30,findViewById(R.id.plural32));
        p.add(31,findViewById(R.id.plural33));
        p.add(32,findViewById(R.id.plural34));
        p.add(33,findViewById(R.id.plural35));
        p.add(34,findViewById(R.id.plural36));
        p.add(35,findViewById(R.id.plural37));
        p.add(36,findViewById(R.id.plural38));
        p.add(37,findViewById(R.id.plural39));
        p.add(38,findViewById(R.id.plural40));
        p.add(39,findViewById(R.id.plural41));
        p.add(40,findViewById(R.id.plural42));
        p.add(41,findViewById(R.id.plural43));
        p.add(42,findViewById(R.id.plural44));
        p.add(43,findViewById(R.id.plural45));
        p.add(44,findViewById(R.id.plural46));

        return p;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plural_ex);

        Button finish = findViewById(R.id.finplural);
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
                    db.addRezultat(new Rezultat(0,id, "Plural nouns",getStringAnswer(getList()),(46 - greseli) + " out of 46 ",copil.getRezultate()));
                else{
                    int idResults = 0;
                    for (int i = 0 ; i< results.size(); i++){
                        if (results.get(i).getIdTest().equals("Plural nouns") == true){
                            idResults = i;
                        }
                    }
                    if (idResults > 0)
                        db.updateRezultat(idResults, new Rezultat(idResults,id,"Plural nouns",getStringAnswer(getList()), (46 - greseli) + " out of 46 ",copil.getRezultate()));
                    else
                        db.addRezultat(new Rezultat(0,id, "Plural nouns",getStringAnswer(getList()),(46 - greseli) + " out of 46 ",copil.getRezultate()));
                }



                Intent intent = new Intent(PluralEx.this,PracticeActivity.class);
                intent.putExtra("idCopil",id);
                startActivity(intent);
            }
        });

    }
}