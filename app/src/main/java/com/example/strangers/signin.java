package com.example.strangers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class signin extends AppCompatActivity {

    EditText emailBox, passwordBox;
    Button loginBtn, signupBtn;
    FirebaseAuth auth;

    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        auth = FirebaseAuth.getInstance();
        emailBox = findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);
        loginBtn = findViewById(R.id.signinBtn);
        signupBtn = findViewById(R.id.createBtn);

          loginBtn.setOnClickListener(v -> {
              dialog.show();
              String email,password;
              email = emailBox.getText().toString();
              password = passwordBox.getText().toString();
              auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      dialog.dismiss();
                      if (task.isSuccessful()) {

                          startActivity(new Intent(signin.this, DashboardScreen.class));
                          Toast.makeText(signin.this, "logged in", Toast.LENGTH_LONG).show();


                      } else {
                          Toast.makeText(signin.this, Objects.requireNonNull(task.getException()).getLocalizedMessage(), Toast.LENGTH_LONG).show();
                      }
                  }
               });
          });

        signupBtn.setOnClickListener(v -> startActivity(new Intent(signin.this,signup.class)));
    }
}