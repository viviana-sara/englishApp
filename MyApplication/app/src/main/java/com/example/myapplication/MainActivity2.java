package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import aplicatie.Copil;
import aplicatie.User;
import db.DBiLearning;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DBiLearning db = new DBiLearning(this);

        EditText user = (EditText) findViewById(R.id.user);
        EditText pass = (EditText) findViewById(R.id.pass);
        TextView corect = (TextView) findViewById(R.id.corect);
        Button login = (Button) findViewById(R.id.autentif);
        

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user1 = db.getByUsername(user.getText().toString());
                String pass2 = user1.getParola();
                if (pass2.equals("") == true){
                    corect.setText("parola user este "+pass.getText().toString() + " . iar parola introdusa este " + pass2 + ", user = " + user.getText().toString());
                }
                else {
                    if (user1.getTip().equals("admin")){
                        Intent intent = new Intent(MainActivity2.this, AdminActivity.class);
                        intent.putExtra("idAdmin", user1.getId());
                        startActivity(intent);
                    }
                    if (user1.getTip().equals("child")){
                        Intent intent = new Intent(MainActivity2.this, CopilActivity.class);
                        intent.putExtra("idCopil", user1.getId());
                        startActivity(intent);
                    }
                    if (user1.getTip().equals("parent")){
                        Intent intent = new Intent(MainActivity2.this, ParinteActivity.class);
                        intent.putExtra("idParinte", user1.getId());
                        startActivity(intent);
                    }
                }
            }
        });




    }
}