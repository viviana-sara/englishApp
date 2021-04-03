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

public class DeleteAccount extends AppCompatActivity {


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
        setContentView(R.layout.activity_delete_account);

        DBiLearning db = new DBiLearning(this);

        Intent intent = getIntent();
        String text = intent.getStringExtra("type");
        String typeUser = intent.getStringExtra("typeUser");

        TextView textView = findViewById(R.id.deleteText);
        textView.setText(text);

        Button add = findViewById(R.id.deleteDelete);
        Button back = findViewById(R.id.deleteBACK);

        EditText name = findViewById(R.id.deleteName);
        EditText username = findViewById(R.id.deketeUsername);
        EditText pass = findViewById(R.id.deleteassword);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.getByName(name.getText().toString()) == 1)
                    textView.setText("The person doesn't exist");
                else {
                    int id = db.getUserByName(name.getText().toString()).getId();
                    String type =  db.getUserByName(name.getText().toString()).getTip();
                    int ok = verify(typeUser,type);
                    if (id > 0 && ok ==1)
                        db.delete(0,"user");
                    else
                        textView.setText("problem to delete user");

                    if (db.getByName(name.getText().toString()) == 0){
                        textView.setText("user deleted");
                    }
                    else
                        textView.setText("user was not deleted");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DeleteAccount.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}