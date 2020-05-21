package com.twodev.taskapp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.twodev.taskapp.MainActivity;
import com.twodev.taskapp.R;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {
    private EditText editText;
    private EditText editCode;
    private  Button buttonPhone;
    private  Button buttonCode;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        editText = findViewById(R.id.editPhone);
        editCode = findViewById(R.id.editCode);
        buttonPhone = findViewById(R.id.buttonPhone);
        buttonCode = findViewById(R.id.buttonCode);


        editCode.setVisibility(View.GONE);
        buttonCode.setVisibility(View.GONE);

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Log.e("Phone", "onVerificationCompleted: ");
                Toast.makeText(PhoneActivity.this, "onVerificationCompleted", Toast.LENGTH_SHORT).show();
                singIn(phoneAuthCredential);
            }


            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e("Phone", "onVerificationFailed: " + e.getMessage());
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Log.e("Phone", "onCodeSent: ");
                Toast.makeText(PhoneActivity.this, "onCodeSent", Toast.LENGTH_SHORT).show();

                editText.setVisibility(View.GONE);
                buttonPhone.setVisibility(View.GONE);

                editCode.setVisibility(View.VISIBLE);
                buttonCode.setVisibility(View.VISIBLE);


            }
        };
    }

    private void singIn(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(PhoneActivity.this, MainActivity.class));
                    finish();
                } else{
                    Toast.makeText(PhoneActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onClick(View view) {
        String phone = editText.getText().toString().trim();

        PhoneAuthProvider.getInstance().verifyPhoneNumber(phone, 60, TimeUnit.SECONDS, this, callbacks);


    }
}
