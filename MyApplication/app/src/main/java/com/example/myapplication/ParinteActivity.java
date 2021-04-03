package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import aplicatie.Rezultat;
import aplicatie.User;
import db.DBiLearning;

public class ParinteActivity extends AppCompatActivity {
    private String name = "idParinte";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parinte);

        Intent intent = getIntent();
        int id= intent.getIntExtra(name,0);

        DBiLearning db = new DBiLearning(this);

        Button add = findViewById(R.id.parentAdd);
        Button delete = findViewById(R.id.parentDelete);
        Button logOut = findViewById(R.id.parentLogOut);
        Button result = findViewById(R.id.parentResult);
        EditText childName = findViewById(R.id.parentKidName);
        TextView textView = findViewById(R.id.seeRes);
        textView.setText("Here you will see the results of your child");

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParinteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ParinteActivity.this, AddActivity.class);
                intent1.putExtra("type","You can add a parent or a child accounts");
                intent1.putExtra("id", id);
                intent1.putExtra("typeUser","parent");
                startActivity(intent1);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ParinteActivity.this,DeleteAccount.class);
                intent1.putExtra("type","You can delete an admin, a parent or a child account");
                intent1.putExtra("typeUser", "parent");
                startActivity(intent1);
            }
        });

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameCopil = childName.getText().toString();
                User copil = db.getUserByName(nameCopil);
                int idCopil = copil.getId();
                int idParinte = db.getByIdCopil(idCopil,"idCopil").getIdParitne();
                String grade = db.getByIdCopil(idCopil,"idCopil").getRezultate();
                List<Rezultat> rezultate = db.getResults(idCopil,"idCopil");

                if (id == idParinte){
                    String res = nameCopil + " level " + grade + "\n At grammar : \n";

                    for (int i = 0; i<rezultate.size(); i++){
                        res = res + "    to " + rezultate.get(i).getIdTest() + " section : " + rezultate.get(i).getNota()+" \n ";
                    }
                    textView.setText(res);
                }
                else
                    textView.setText("You are not the parent of this child!");

            }
        });
    }
}