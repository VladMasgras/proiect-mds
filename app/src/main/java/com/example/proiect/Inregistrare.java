package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Inregistrare extends AppCompatActivity {

    private TextInputLayout emailText, parolaText;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare);

        emailText = findViewById(R.id.text_input_email_inregistrare);
        parolaText = findViewById(R.id.text_input_password_inregistrare);
        TextView backToSignIn = findViewById(R.id.already_have_account);
        progressBar = findViewById(R.id.progress_bar);

        mAuth = FirebaseAuth.getInstance();

        backToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Inregistrare.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateEmail(){
        String emailInput = emailText.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()){
            emailText.setError("Field can't be empty");
            return false;
        }
        else{
            emailText.setError(null);
            return true;
        }
    }

    private boolean validatePassword(){
        String parolaInput = parolaText.getEditText().getText().toString().trim();
        if (parolaInput.isEmpty()){
            parolaText.setError("Field can't be empty");
            return false;
        }
        else{
            parolaText.setError(null);
            return true;
        }
    }

    public void confirmInput(View v){
        if (!validateEmail() | !validatePassword()){
            return;
        }
        String emailInput = emailText.getEditText().getText().toString().trim();
        String parolaInput = parolaText.getEditText().getText().toString().trim();

        Log.d("email","" + emailInput);
        Log.d("pass","" + parolaInput);

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(emailInput, parolaInput).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Log.d("tag", "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(getApplicationContext(), "User registered succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Inregistrare.this, Login.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
