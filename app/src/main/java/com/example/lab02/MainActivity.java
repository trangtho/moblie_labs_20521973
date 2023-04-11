package com.example.lab02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button1;
    TextView textView1, textView2, textView3;
    personal_salary per;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button);
        textView1 = findViewById(R.id.name);
        textView2 = findViewById(R.id.gross_salary);
        textView3 = findViewById(R.id.result);
        button1.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        String text = textView1.getText().toString();
        int gSalary = Integer.parseInt(textView2.getText().toString());
        per = new personal_salary(text, gSalary);
        textView3.setText(textView3.getText().toString()+ "\n" + text + " - Net Salary: " + per.netSalary(gSalary));

    }
}