package com.example.test;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.mindrot.jbcrypt.BCrypt;
public class MainActivity extends AppCompatActivity {

    private EditText fullname, phone, username, password;
    private Button signup;
    private TextView login;
    private DatabaseReference reference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reference = FirebaseDatabase.getInstance().getReference("credentials");
        fullname = findViewById(R.id.editTextTextPersonName);
        phone = findViewById(R.id.editTextPhone);
        username = findViewById(R.id.editTextTextPersonName2);
        username.setInputType(InputType.TYPE_CLASS_TEXT);
        username.setKeyListener(DigitsKeyListener.getInstance("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"));

        password = findViewById(R.id.editTextNumberPassword);
        signup = findViewById(R.id.button);
        login = findViewById(R.id.TextView_Login);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullnametxt = fullname.getText().toString();
                final String phonetxt = phone.getText().toString();
                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();

                if(fullnametxt.isEmpty()||phonetxt.isEmpty()||usernametxt.isEmpty()||passwordtxt.isEmpty()){

                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                }
                else
                    if(usernametxt.length()<6||passwordtxt.length()<6){
                        Toast.makeText(MainActivity.this, "Username and password must be at least 6 characters", Toast.LENGTH_LONG).show();
                    }
                else {
                    User user = new User(fullnametxt, phonetxt, usernametxt, BCrypt.hashpw(passwordtxt, BCrypt.gensalt()));
                    db.collection("User").add(user).addOnSuccessListener(documentReference ->
                            Log.d(TAG, "Registration Successfully"));
                    Toast.makeText(MainActivity.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                    startActivity(intent);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }
}