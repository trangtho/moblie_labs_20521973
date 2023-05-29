package com.example.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.mindrot.jbcrypt.BCrypt;

public class SigninActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText username, password;
    private Button btnLogin;
    private TextView signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        username= findViewById(R.id.Input_Username);
        password= findViewById(R.id.Input_Password);
        btnLogin=findViewById(R.id.Button_Login);
        signup=findViewById(R.id.TextView_SignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();

                if(usernametxt.isEmpty()||passwordtxt.isEmpty()){
                    Toast.makeText(SigninActivity.this, "Please enter username or password", Toast.LENGTH_SHORT).show();
                }
                else{
                    CollectionReference usersRef = db.collection("User");
                    usersRef.get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    // Dữ liệu trả về từ Firestore
                                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                        String username = documentSnapshot.getString("username");
                                        String password = documentSnapshot.getString("password");
                                        // Kiểm tra và xử lý dữ liệu tại đây
                                        if( usernametxt.compareTo(username) == 0 && BCrypt.checkpw(passwordtxt, password)){
                                            Intent intent = new Intent(SigninActivity.this, HomeActivity.class);
                                            startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(SigninActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Xử lý lỗi khi truy vấn dữ liệu
                                    Toast.makeText(SigninActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}