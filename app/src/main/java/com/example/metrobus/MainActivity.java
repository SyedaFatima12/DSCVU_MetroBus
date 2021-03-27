package com.example.metrobus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user==null){
                    Intent intent=new Intent(MainActivity.this,WelcomeScreen.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent=new Intent(MainActivity.this,Home.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);
    }
}