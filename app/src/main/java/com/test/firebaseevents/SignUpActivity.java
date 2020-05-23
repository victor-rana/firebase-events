package com.test.firebaseevents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.firebaseevents.model.User;
import com.test.firebaseevents.utils.ValidationUtil;

public class SignUpActivity extends AppCompatActivity {

    AppCompatEditText etEmailAddress, etPassword, et_name, et_phone;
    AppCompatTextView btnSignup, tvLogin;
    String password, email;
    FirebaseAuth mAuth;
    DatabaseReference table_user;

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            checkButtonValidation();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        table_user = database.getReference("User");

        btnSignup = findViewById(R.id.tv_signup_btn);
        etEmailAddress = findViewById(R.id.et_email);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_pwd);
        tvLogin = findViewById(R.id.tv_login_btn);
        etEmailAddress.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    callApiSignUp();
                }

            }
        });

    }

    private boolean validate() {
        password = etPassword.getText().toString().trim();
        email = etEmailAddress.getText().toString().trim();

        if (email.trim().length() <= 0) {

            Toast.makeText(this, getString(R.string.email_required), Toast.LENGTH_SHORT).show();


        } else if ((!ValidationUtil.isValidEmail(email))) {
            Toast.makeText(this, getString(R.string.valid_email), Toast.LENGTH_SHORT).show();


        }
        else if (password.trim().length() <= 0) {
            Toast.makeText(this, getString(R.string.password_required), Toast.LENGTH_SHORT).show();


        } else if (password.trim().length() < 8) {
            Toast.makeText(this, getString(R.string.val_password), Toast.LENGTH_SHORT).show();

        } else {
            return true;
        }
        return false;
    }


    private void checkButtonValidation() {
        btnSignup.setEnabled(false);
        if (TextUtils.isEmpty(etEmailAddress.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())) {
            return;
        }
        btnSignup.setEnabled(true);
    }


    void callApiSignUp() {

        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(et_phone.getText().toString()).exists())
                {
//                    mDialog.dismiss();
                    Toast.makeText(SignUpActivity.this, "Phone Number already register", Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    mDialog.dismiss();
                    User user = new User(etEmailAddress.getText().toString(),et_name.getText().toString(),etPassword.getText().toString());
                    table_user.child(et_phone.getText().toString()).setValue(user);
                    Toast.makeText(SignUpActivity.this, "SignUp successfully!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this,LoginActivity.class));

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
