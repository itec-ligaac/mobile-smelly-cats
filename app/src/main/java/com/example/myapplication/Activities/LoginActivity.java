package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.helpers.FirebaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private TextInputEditText emailEt;
    private TextInputLayout emailTil;
    private TextInputEditText passwordEt;
    private TextInputLayout passwordTil;
    private String userEmail;
    private String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
    }

    private void firebaseLogin(String email, String password){
        mAuth = FirebaseAuth.getInstance();
        if(email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "E-mail sau parolă necompletate", Toast.LENGTH_SHORT).show();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            onSignInSuccesful();
                        } else {
                            Toast.makeText(getApplicationContext(), "E-mail sau parolă incorectă",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void onSignInSuccesful()
    {
        FirebaseHelper.getInstance().usersDatabase.child(mAuth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                /*StudentModel myUser = dataSnapshot.getValue(StudentModel.class);
                UserHelper.Instance().setStudent(myUser);
                UserHelper.Instance().setFirebaseUser(mAuth.getCurrentUser());
                UserHelper.Instance().getStudent().setUserId(mAuth.getCurrentUser().getUid());
                UserHelper.Instance().setLoggedIn(true);*/
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void OnLogin(View view) {
        userEmail = emailEt.getText().toString();
        userPassword = passwordEt.getText().toString();
        firebaseLogin(userEmail, userPassword);

        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }

    public void OnRegister(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }

    private void initializeViews() {
        emailEt = findViewById(R.id.et_login_mail);
        passwordEt = findViewById(R.id.et_login_pass);
    }
}