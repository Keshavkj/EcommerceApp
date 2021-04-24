package com.android.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText Email;
    EditText Password;
    Button login;
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.emailid1);
        Password = findViewById(R.id.password2);
        login = findViewById(R.id.login9);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_Email = Email.getText().toString();
                String txt_password = Password.getText().toString();
                loginUser(txt_Email,txt_password);

            }
        });
    }

    private void loginUser(String Email, String Password) {
        auth.signInWithEmailAndPassword(Email, Password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this,product.class));
                finish();


            }
        });
    }

}