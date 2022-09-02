package com.example.bookrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class login extends AppCompatActivity {
    EditText enternumber;
    Button getotpbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enternumber = findViewById(R.id.input_mobile_number);
        getotpbutton = findViewById(R.id.buttongetotp);

        ProgressBar progressBar = findViewById(R.id.progressBarOTP);

        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!enternumber.getText().toString().trim().isEmpty()) {
                    if ((enternumber.getText().toString().trim()).length() == 10) {


                        progressBar.setVisibility(View.VISIBLE);
                        getotpbutton.setVisibility(View.VISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+977" + enternumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                login.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        getotpbutton.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        progressBar.setVisibility(View.GONE);
                                        getotpbutton.setVisibility(View.VISIBLE);
                                        Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                        progressBar.setVisibility(View.GONE);
                                        getotpbutton.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), login_page2_otp.class);
                                        intent.putExtra("mobile", enternumber.getText().toString());
                                        intent.putExtra("backendOTP", enternumber.getText().toString());
                                        startActivity(intent);
                                    }
                                }
                        );
                        Intent intent = new Intent(getApplicationContext(), login_page2_otp.class);
                        intent.putExtra("mobile", enternumber.getText().toString());
                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(login.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

