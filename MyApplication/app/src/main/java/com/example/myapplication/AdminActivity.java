package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    private String name = "idAdmin";
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Intent intent = getIntent();
        id= intent.getIntExtra(name,0);

        Button add = findViewById(R.id.adminAdd);
        Button delete = findViewById(R.id.adminDelete);
        Button update = findViewById(R.id.adminUp);
        Button back = findViewById(R.id.adminBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdminActivity.this, AddActivity.class);
                intent1.putExtra("type","You can add admin or parent account");
                intent1.putExtra("id", id);
                intent1.putExtra("typeUser","admin");
                startActivity(intent1);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdminActivity.this,UpdateActivity.class);
                intent1.putExtra("type","You can update admin, parent or child account");
                startActivity(intent1);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AdminActivity.this,DeleteAccount.class);
                intent1.putExtra("type","You can delete admin, parent or child account");
                intent1.putExtra("typeUser", "admin");
                startActivity(intent1);
            }
        });
    }
}