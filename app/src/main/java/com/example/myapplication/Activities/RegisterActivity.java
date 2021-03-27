package com.example.myapplication.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.helpers.DataStorageHelper;
import com.example.myapplication.models.UserModel;
import com.example.myapplication.helpers.FirebaseHelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText emailEt;
    private TextInputEditText passwordEt;
    private TextInputEditText passwordAgainEt;
    private TextInputEditText nameEt;
    private String userEmail;
    private String userPassword;
    private String userPasswordAgain;
    private String userName;
    FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private UserModel user;
    private Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initializeViews();
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    public boolean passwordsMatch() {
        if (emailEt.getText().toString().isEmpty() || passwordEt.getText().toString().isEmpty() || passwordAgainEt.getText().toString().isEmpty() || nameEt.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "The account cannot be created without all fields being completed", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (passwordEt.getText().toString().equals(passwordAgainEt.getText().toString())) {
                return true;
            } else {
                Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }

    public void Register() {
        if (!passwordsMatch()){
            return;
        }
        GetValues();
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Successful registration", Toast.LENGTH_LONG).show();
                    mUser = FirebaseAuth.getInstance().getCurrentUser();
                    user = new UserModel(mUser.getUid(), userEmail, userName);
                    writeNewUser();
                    DataStorageHelper.getInstance().setCurrentUser(user);
                    Intent myIntent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(myIntent);
                    finish();
                }
            }
        });
    }

    private void writeNewUser() {
        FirebaseHelper.getInstance().usersDatabase.child(user.getId()).setValue(user);
    }

    private void GetValues(){
        userEmail = emailEt.getText().toString();
        userPassword = passwordEt.getText().toString();
        userPasswordAgain = passwordAgainEt.getText().toString();
        userName = nameEt.getText().toString();
    }

    private void initializeViews() {
        emailEt = findViewById(R.id.et_personal_email);
        passwordEt = findViewById(R.id.et_personal_password);
        nameEt = findViewById(R.id.et_personal_name);
        passwordAgainEt = findViewById(R.id.et_personal_password_again);
        registerBtn = findViewById(R.id.btn_register);
    }
}