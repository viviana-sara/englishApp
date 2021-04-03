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

public class AddActivity extends AppCompatActivity {


    private int verify(String type, String user){
        if (type.equals("child")==true)
            return 1;
        if (type.equals("parent")== true)
            return 1;
        if (user.equals("admin")){
            if (type.equals("admin")==true)
                return 1;
        }
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        DBiLearning db = new DBiLearning(this);

        Intent intent = getIntent();
        String text = intent.getStringExtra("type");
        String typeUser = intent.getStringExtra("typeUser");

        TextView textView = findViewById(R.id.addText);
        textView.setText(text);

        Button add = findViewById(R.id.addAdd);
        Button back = findViewById(R.id.addBACK);

        EditText name = findViewById(R.id.addName);
        EditText username = findViewById(R.id.addUsername);
        EditText pass = findViewById(R.id.addPassword);
        EditText type = findViewById(R.id.addType);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verify(type.getText().toString(),typeUser)==1){
                if (db.getByName(name.getText().toString()) == 0)
                    textView.setText("The person is already exists");
                else {
                    db.addUser(new User(name.getText().toString(), username.getText().toString(), pass.getText().toString(), type.getText().toString(), 0));
                    if (db.getByName(name.getText().toString()) == 0){
                        User copil = db.getUserByName(name.getText().toString());
                        textView.setText("user added");
                        if (type.getText().toString().equals("child")==true){
                            int id = intent.getIntExtra("id",0);
                            db.addCopil(new Copil(0,id,copil.getId(),"A"));
                        }
                    }
                    else
                        textView.setText("user was not added");
                }
                }
                else
                    textView.setText("the type is incorrect");
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });


    }
}