package com.example.bookrecorder;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class login_page2_otp extends AppCompatActivity {

    EditText inputnumber1, inputnumber2, inputnumber3, inputnumber4, inputnumber5, inputnumber6;

    String getotpbackend;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page2_otp);

        final Button verifybuttonclick = findViewById(R.id.verifytotpclick);

        inputnumber1 = findViewById(R.id.inputotp1);
        inputnumber2 = findViewById(R.id.inputotp2);
        inputnumber3 = findViewById(R.id.inputotp3);
        inputnumber4 = findViewById(R.id.inputotp4);
        inputnumber5 = findViewById(R.id.inputotp5);
        inputnumber6 = findViewById(R.id.inputotp6);


        TextView textView = findViewById(R.id.textmobilenumbershow);
        textView.setText(String.format(
                "+977-%s", getIntent().getStringExtra("mobile")
        ));

        getotpbackend=getIntent().getStringExtra("backendOTP");



        verifybuttonclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!inputnumber1.getText().toString().trim().isEmpty() && !inputnumber2.getText().toString().trim().isEmpty() && !inputnumber3.getText().toString().trim().isEmpty() && !inputnumber4.getText().toString().trim().isEmpty() && !inputnumber5.getText().toString().trim().isEmpty() && !inputnumber6.getText().toString().trim().isEmpty()) {

                    String entercodeotp= inputnumber1.getText().toString()+
                            inputnumber2.getText().toString()+
                            inputnumber3.getText().toString()+
                            inputnumber4.getText().toString()+
                            inputnumber5.getText().toString()+
                            inputnumber6.getText().toString();
                    if (getotpbackend!=null){

                        verifybuttonclick.setVisibility(View.VISIBLE);

                        PhoneAuthCredential phoneAuthCredential= PhoneAuthProvider.getCredential(
                                getotpbackend, entercodeotp
                        );

                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                verifybuttonclick.setVisibility(View.VISIBLE);

                                if (task.isSuccessful()){
                                    Intent intent=new Intent(getApplicationContext(),homePage.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(login_page2_otp.this, "Enter the correct OTP", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

                    }else{
                        Toast.makeText(login_page2_otp.this, "Please check your Internet Connection", Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(login_page2_otp.this, "OTP verify", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(login_page2_otp.this, "Please enter the valid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
        numberotpmove();

        TextView resendOTP=findViewById(R.id.sendagainotp);
        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+977" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        login_page2_otp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {


                                Toast.makeText(login_page2_otp.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getotpbackend=newbackendOTP;
                                Toast.makeText(login_page2_otp.this, "OTP send Sucessfully", Toast.LENGTH_SHORT).show();


                            }
                        }
                );
            }
        });
    }

    private void numberotpmove() {
        inputnumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputnumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputnumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputnumber4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputnumber5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputnumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    inputnumber6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


}
