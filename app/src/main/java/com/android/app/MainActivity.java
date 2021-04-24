package com.android.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
     Button createAccount;
     EditText name;
    EditText email_id;
    EditText mob_no;
    EditText password;
    EditText cnf_pass;
    Button register;
    Button log_in;
    Button button;
    private FirebaseAuth auth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      name = findViewById(R.id.Name);
        email_id = findViewById(R.id.emailid);
        mob_no = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        cnf_pass = findViewById(R.id.confirmpassword);
        register=findViewById(R.id.register);
        log_in=findViewById(R.id.log);
        log_in.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
startActivity(new Intent(MainActivity.this,login.class));
                                        }
                                    });

        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewProduct.class));
            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = email_id.getText().toString();
                String txt_password = password.getText().toString();
                if(TextUtils.isEmpty(txt_email)||TextUtils.isEmpty(txt_password)){
                    Toast.makeText(MainActivity.this, "Empty Credential", Toast.LENGTH_SHORT).show();
                }
                else if(txt_password.length()<6)
                {
                    Toast.makeText(MainActivity.this, "Password too short", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password.equals(cnf_pass)) {
                        registrUser(txt_email, txt_password);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Password Mismatch", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void registrUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,login.class));
                    finish();

                }
                else {
                    Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }}