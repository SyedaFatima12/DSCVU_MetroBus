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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    private TextInputLayout signet_email;
    private TextInputLayout signet_pass;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private Button button_login;
    private  String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        progressDialog=new ProgressDialog(this);
        signet_email=findViewById(R.id.signet_email);
        signet_pass=findViewById(R.id.signet_pass);
        button_login=findViewById(R.id.button_login);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog.setTitle("Please wait");
        progressDialog.setCanceledOnTouchOutside(false);


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


                progressDialog.setMessage("Creating account...");
                progressDialog.show();



//                Toast.makeText(SignUpActivity.this, "email"+email, Toast.LENGTH_SHORT).show();
//                Toast.makeText(SignUpActivity.this, "pass"+password, Toast.LENGTH_SHORT).show();

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {


                        Toast.makeText(SignUpActivity.this, "Account Created Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(SignUpActivity.this,Home.class);
                        startActivity(intent);
                        finish();

                        progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }

}