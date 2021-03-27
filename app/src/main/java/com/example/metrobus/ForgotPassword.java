package com.example.metrobus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private ImageButton backbtn;
    private TextInputLayout emailEt;
    private Button recovryBtn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        backbtn=findViewById(R.id.backbtn);
        emailEt=findViewById(R.id.rec_email);
        recovryBtn=findViewById(R.id.recovryBtn);
        //initalize firebase
        firebaseAuth=FirebaseAuth.getInstance();
        // progressBar = findViewById(R.id.progressBar);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please wait....");
        progressDialog.setCanceledOnTouchOutside(false);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        recovryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // progressBar.setVisibility(View.VISIBLE);
                final String email=emailEt.getEditText().getText().toString().trim();
                if (TextUtils.isEmpty(email)){
                    emailEt.setError("enter email");
                    emailEt.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEt.setError("Invalid email address");
                    emailEt.requestFocus();
                    return;
                }
                progressDialog.setMessage("sending instruction to reset password");
                progressDialog.show();
                try {


                    firebaseAuth.sendPasswordResetEmail(emailEt.getEditText().getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    // progressBar.setVisibility(View.GONE);
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(ForgotPassword.this, "password reset instruction sent to email", Toast.LENGTH_SHORT).show();

                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(ForgotPassword.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }catch (NullPointerException ignored){

                }

            }
        });


    }


}