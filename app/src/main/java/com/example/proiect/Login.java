package com.example.proiect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private TextInputLayout emailText, parolaText;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("admini");

        emailText = findViewById(R.id.text_input_email_login);
        parolaText = findViewById(R.id.text_input_password_login);

        Button signInButton = findViewById(R.id.email_sign_in_button);
        Button registerButton = findViewById(R.id.register_button);
        progressBar = findViewById(R.id.progress_bar_login);

        mAuth = FirebaseAuth.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (!validateEmail() | !validatePassword()){
                    return;
                }
                String emailInput = emailText.getEditText().getText().toString().trim();
                String parolaInput = parolaText.getEditText().getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(emailInput, parolaInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()){
                            final FirebaseUser user = mAuth.getCurrentUser();
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot adminSnapshot: dataSnapshot.getChildren()){
                                        Log.d("useradmin", "" + adminSnapshot.getValue());
                                        if (user.getUid().equals("" + adminSnapshot.getValue())) {
                                            Intent intent = new Intent(Login.this, MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                        }
                                    }
                                    Intent intent = new Intent(Login.this, RezervaBilet.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                        else {
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Inregistrare.class);
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
}
