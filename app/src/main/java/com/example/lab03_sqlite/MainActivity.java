package com.example.lab03_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DbAdapter dbAdapter;
    EditText editName, editPhone;
    private Cursor cursor;
    private List<String> users;
    private Button btnQuery, btnInsert;
    private ListView lv;

    ArrayList<String> list;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = (EditText) findViewById(R.id.editTextName);
        editPhone = (EditText) findViewById(R.id.editTextPhone);
        btnInsert = (Button) findViewById(R.id.button);
        btnQuery = (Button) findViewById(R.id.button2);
        dbAdapter = new DbAdapter(this);
        dbAdapter.open();
        //dbAdapter.deleteAllUsers();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //result.setText(editName.getText().toString()+" - "+editPhone.getText().toString());
                dbAdapter.createUser(editName.getText().toString(), editPhone.getText().toString());
                //dbAdapter.createUser("Nguyen Van A", "0123456");
            }
        });
        
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Query_data.class);
                startActivity(intent);
            }
        });
    }
}