package com.example.finalyearreminderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView gotoprofile;
    FirebaseAuth Auth;
    TextInputEditText textEmail,textPassword;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        gotoprofile=findViewById(R.id.txtGoTORegister);
        textEmail=findViewById(R.id.idEdtLoginUserEmailAddress);
        textPassword=findViewById(R.id.idEdtLoginPassword);
        button=findViewById(R.id.buttonSIngUp);
        Auth = FirebaseAuth.getInstance();

        findViewById(R.id.txtGoTORegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this,Registration.class);
                startActivity(i);
            }
        });


    }
    public  void LoginUser(View v){

        String Email = textEmail.getText().toString();
        String Password = textPassword.getText().toString();

        if (!Email.equals(" ") && Password.equals(" ")){
            Auth.signInWithEmailAndPassword(Email,Password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                Toast.makeText(Login.this, "Logged In", Toast.LENGTH_SHORT).show();
                                Intent i =  new Intent(Login.this,GroupChatActivity.class);
                                startActivity(i);

                            }
                            else {
                                Toast.makeText(Login.this, "Wrong Email / Password. Try Again", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }
}