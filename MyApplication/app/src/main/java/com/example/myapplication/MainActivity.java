package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.List;

import Controll.LearController;
import aplicatie.Copil;
import aplicatie.Rezultat;
import aplicatie.User;
import db.DBiLearning;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.LogIn);
        Button visit = findViewById(R.id.vizit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogIn.class);
                startActivity(intent);
            }
        });

        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VisitActivity.class);
                startActivity(intent);
            }
        });


        User user1 = new User("ANA Pop", "ANN", "ana", "admin", 0);
        User user2 = new User("Maria Muresan", "mariaM", "mariamuresan", "parinte", 1);
        DBiLearning dBiLearning = new DBiLearning(this);
        dBiLearning.addUser(user1);
        dBiLearning.addUser(user2);
        List<User> list = dBiLearning.getAllUsers();
        List<Copil> copil = dBiLearning.getAllChildren();
        List<Rezultat> rezultat = dBiLearning.getAllResults();

        for (int i = 0; i<copil.size(); i++){
            System.out.println("copil : " + dBiLearning.getByIdUser(copil.get(i).getIdCopil()).getNameUser() + " grade = " + copil.get(i).getRezultate());
        }

        for (int i = 0; i< rezultat.size(); i++){
            System.out.println("rezultat: copil " + dBiLearning.getByIdUser(rezultat.get(i).getIdCopil()).getNameUser()+ " are "+ rezultat.get(i).getNota() +" raspunsuri corecte la "+ rezultat.get(i).getIdTest());
        }

        for (int i = 0; i< list.size(); i++){
            System.out.println(list.get(i).getId()+", "+list.get(i).getNameUser() + ", " + list.get(i).getUsername()+ ", "+ list.get(i).getTip());
        }
        System.out.println("the max id is "+ dBiLearning.getMaxId("user"));
        User user3 = dBiLearning.getByIdUser(2);
        System.out.println("get by id: " + user3.getUsername());

    }

}