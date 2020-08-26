package com.example.firebasefirsttutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button register;

// Intialising firebase authentication variable
    private FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

// connecting firebase authentication to java file
        auth = FirebaseAuth.getInstance();



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt_email = email.getText().toString();
                String txt_password = password.getText().toString();
                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(registerActivity.this,"EMPTY CREDENTIALS",Toast.LENGTH_SHORT).show();
                }else if(txt_password.length()<6) {
                    Toast.makeText(registerActivity.this, "PASSWORD IS SHORT", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txt_email,txt_password);}
                //bhhhh
            }
        });


    }

    private void registerUser(String txt_email, String txt_password) {
        auth.createUserWithEmailAndPassword(txt_email,txt_password).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>(){
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(registerActivity.this,"registration is done",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(registerActivity.this,MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(registerActivity.this,"registration is failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}





