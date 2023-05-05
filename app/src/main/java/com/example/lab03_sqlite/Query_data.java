package com.example.lab03_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Query_data extends AppCompatActivity {

    //private ListView lv;
    //ArrayList<String> list;
    private Cursor cursor;
    private List<String> users;
    private DbAdapter dbAdapter;
    private Button back;
    //private SQLiteDatabase db;
    //private ArrayAdapter<String> adapter;
    //private ArrayList<String> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_data);
        back=findViewById(R.id.button4);

        dbAdapter = new DbAdapter(this);
        dbAdapter.open();

        users = getData();
        showData();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Query_data.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @SuppressLint("Range")
    private List<String> getData() {
        List<String> users = new ArrayList<>();

        cursor = dbAdapter.getAllUsers();
        while (cursor.moveToNext()) {
            users.add(cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_NAME))+" - "+cursor.getString(cursor.getColumnIndex(DbAdapter.KEY_PHONE)));
        }
        return users;
    }

    private void showData() {
        ListView lvUser = (ListView) findViewById(R.id.listUsers);
        ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(Query_data.this, R.layout.item_user, users);
        lvUser.setAdapter(userAdapter);
    }

}