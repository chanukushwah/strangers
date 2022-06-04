package com.example.strangers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class signup extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore database;
    EditText emailBox, passwordBox,nameBox;
    Button loginBtn, signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        emailBox = findViewById(R.id.emailBox);
        nameBox = findViewById(R.id.nameBox);
        passwordBox = findViewById(R.id.passwordBox);
        loginBtn = findViewById(R.id.signinBtn);
        signupBtn = findViewById(R.id.createBtn);


        signupBtn.setOnClickListener(v -> {
            String email,pass,name;
            email = emailBox.getText().toString();
            pass = passwordBox.getText().toString();
            name = nameBox.getText().toString();

            User user = new User();
            user.setEmail(email);
            user.setPass(pass);
            user.setName(name);


            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(task -> {
                    if(task.isSuccessful()) {
                        database.collection("Users")
                                        .document().set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        startActivity(new Intent(signup.this,signin.class));
                                    }
                                });
                        Toast.makeText(signup.this,"account is created",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(signup.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                          }
                });

        });
        loginBtn.setOnClickListener(v -> startActivity(new Intent(signup.this,signin.class)));
    }


}