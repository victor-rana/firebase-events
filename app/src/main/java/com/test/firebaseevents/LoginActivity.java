package com.test.firebaseevents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.firebaseevents.db.PreferenceHelper;
import com.test.firebaseevents.model.User;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    AppCompatEditText etPhone,etPassword;
    AppCompatTextView btLogin,btRegister;
    private FirebaseAuth mAuth;
    DatabaseReference table_user;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User");

        user = new User();


        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etPhone.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())) {
                    return;
                }
                else {
                    onLogin();
                }
            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });

    }

    void onLogin(){
        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //check if user not exist
                if(dataSnapshot.child(etPhone.getText().toString()).exists()) {

                    User user = dataSnapshot.child(etPhone.getText().toString()).getValue(User.class);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        if (Objects.requireNonNull(user).getPassword().equals(etPassword.getText().toString()))
                        {
                            PreferenceHelper.getInstance(LoginActivity.this).put(PreferenceHelper.Key.EMAIL,user.getEmail());
                            Intent homeIntent = new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(homeIntent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Password incorrect!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "User not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
