package com.example.metrobus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
   private Button button_login;
   private TextView Text_signUp;
   private TextView forgotTv;

    private TextInputLayout signet_email;
    private TextInputLayout signet_pass;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private  String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Text_signUp=findViewById(R.id.Text_signUp);
        button_login=findViewById(R.id.button_login);

        progressDialog=new ProgressDialog(this);
        signet_email=findViewById(R.id.et_email);
        signet_pass=findViewById(R.id.et_pass);
        button_login=findViewById(R.id.button_login);
        forgotTv=findViewById(R.id.forgotTv);


        forgotTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ForgotPassword.class);
                startActivity(intent);
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);


        Text_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email= signet_email.getEditText().getText().toString().trim();
                password=signet_pass.getEditText().getText().toString().trim();



                if (TextUtils.isEmpty(email)){
                    signet_email.setError("Please enter the email...");
                    signet_email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    signet_email.setError("invalid email pattren");
                    signet_email.requestFocus();
                    return;

                }



                if (TextUtils.isEmpty(password)){
                    signet_pass.setError("Please enter the Password..");
                    signet_pass.requestFocus();
                    return;
                }

                if (password.length()<6){
                    signet_pass.setError("password must be at least 6 character long..");
                    signet_pass.requestFocus();
                    return;
                }


                progressDialog.setMessage("Loading...");
                progressDialog.show();


                firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {


                        Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(LoginActivity.this,Home.class);
                        startActivity(intent);
                        finish();

                        progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });
    }


}