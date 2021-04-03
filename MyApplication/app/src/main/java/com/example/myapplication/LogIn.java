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

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        DBiLearning db = new DBiLearning(this);

        EditText user = (EditText) findViewById(R.id.logInUser);
        EditText pass = (EditText) findViewById(R.id.logInPass);
        TextView corect = (TextView) findViewById(R.id.logInText);
        Button login = (Button) findViewById(R.id.logInLog);
        Button create = findViewById(R.id.logInCreate);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user1 = db.getByUsername(user.getText().toString());
                String pass2 = user1.getParola();
                if (pass2.equals(pass.getText().toString()) == false){
                    corect.setText("username or password incorrect");

                }
                else {
                    if (user1.getTip().equals("admin")){
                        Intent intent = new Intent(LogIn.this, AdminActivity.class);
                        intent.putExtra("idAdmin", user1.getId());
                        startActivity(intent);
                    }
                    if (user1.getTip().equals("child")){
                        Intent intent = new Intent(LogIn.this, CopilActivity.class);
                        intent.putExtra("idCopil", user1.getId());
                        startActivity(intent);
                    }
                    if (user1.getTip().equals("parent")){
                        Intent intent = new Intent(LogIn.this, ParinteActivity.class);
                        intent.putExtra("idParinte", user1.getId());
                        startActivity(intent);
                    }
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(LogIn.this, AddActivity.class);
                intent1.putExtra("type","You can add only parent account");
                intent1.putExtra("typeUser","parent");
                startActivity(intent1);
            }
        });




    }
}