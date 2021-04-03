package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import aplicatie.User;
import db.DBiLearning;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        DBiLearning db = new DBiLearning(this);

        Intent intent = getIntent();
        String text = intent.getStringExtra("type");

        TextView textView = findViewById(R.id.updateText);
        textView.setText(text);

        Button add = findViewById(R.id.updateUp);
        Button back = findViewById(R.id.updateBACK);

        EditText name = findViewById(R.id.updateName);
        EditText username = findViewById(R.id.updateNewUser);
        EditText pass = findViewById(R.id.updateNewPassword);
        EditText nameNew = findViewById(R.id.updateNewName);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.getByName(name.getText().toString()) == 1)
                    textView.setText("The person doesn't exist");
                else {
                    int id = db.getUserByName(name.getText().toString()).getId();
                    String type = db.getByIdUser(id).getTip();
                    if (type.equals("") == false){
                        if (id > 0)
                            db.updateUser(id,new User(nameNew.getText().toString(),username.getText().toString(),pass.getText().toString(),type,0));
                        else
                            textView.setText("problem to update user");

                        if (db.getByName(name.getText().toString()) == 0){
                            textView.setText("user updated");}
                    }
                    else
                        textView.setText("user was no updated");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}