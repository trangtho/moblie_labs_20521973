package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    EditText textView1, textView2;
    ListView listView1;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button);
        textView1 = findViewById(R.id.name);
        textView2 = findViewById(R.id.gross_salary);
        listView1 = findViewById(R.id.result);
        list = new ArrayList<>();
        button1.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String text = textView1.getText().toString();
        int gSalary = Integer.parseInt(textView2.getText().toString());
        personal_salary per = new personal_salary(text, gSalary);
        list.add(per.getFullName()+" - Net Salary: " + per.netSalary());
        ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, list);
        listView1.setAdapter(adapter);
    }
}