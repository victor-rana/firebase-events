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
import com.test.firebaseevents.model.Events;
import com.test.firebaseevents.model.User;

public class MainActivity extends AppCompatActivity {

    AppCompatEditText et_name,et_phone,etEventDesc,etEventName,etEventType;
    AppCompatTextView tvSubmitBtn,tvViewEventBtn;
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
        setContentView(R.layout.activity_main);

        initViews();

        mAuth = FirebaseAuth.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        table_user = database.getReference("Events");

        tvSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateEvent();
            }
        });

        tvViewEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AllEventsActivity.class));
            }
        });

    }

    void initViews(){
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        etEventDesc = findViewById(R.id.etEventDesc);
        etEventName = findViewById(R.id.etEventName);
        etEventType = findViewById(R.id.etEventType);
        tvSubmitBtn = findViewById(R.id.tvSubmitBtn);
        tvViewEventBtn = findViewById(R.id.tvViewEventBtn);

        et_name.addTextChangedListener(textWatcher);
        et_phone.addTextChangedListener(textWatcher);
        etEventDesc.addTextChangedListener(textWatcher);
        etEventName.addTextChangedListener(textWatcher);
        etEventType.addTextChangedListener(textWatcher);
    }

    private void checkButtonValidation() {
        tvSubmitBtn.setEnabled(false);
        if (TextUtils.isEmpty(et_name.getText().toString()) || TextUtils.isEmpty(et_phone.getText().toString()) || TextUtils.isEmpty(etEventDesc.getText().toString()) || TextUtils.isEmpty(etEventName.getText().toString()) || TextUtils.isEmpty(etEventType.getText().toString())) {
            return;
        }
        tvSubmitBtn.setEnabled(true);
    }


    void onCreateEvent(){
        table_user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                    Events events = new Events(et_name.getText().toString(),et_phone.getText().toString(),etEventDesc.getText().toString(),etEventType.getText().toString());
                    table_user.child(etEventName.getText().toString()).setValue(events);
                    Toast.makeText(MainActivity.this, "Event created successfully!", Toast.LENGTH_SHORT).show();
//                    onResetState();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    void onResetState(){
        et_name.getText().clear();
        et_phone.getText().clear();
        etEventDesc.getText().clear();
        etEventName.getText().clear();
        etEventType.getText().clear();
    }
}
