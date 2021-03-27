package com.example.metrobus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Drawable extends AppCompatActivity {
   private TextView logout;
   private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        logout=findViewById(R.id.txt_logout);
        firebaseAuth=FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                // this listener will be called when there is change in firebase user session
                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {

                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        if (user == null) {

                            // user auth state is changed - user is null
                            // launch login activity
                            Toast.makeText(Drawable.this,"Logout",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Drawable.this,LoginActivity.class);
                            startActivity(intent);
                            finish();

                        }

                    }
                };
                authListener.onAuthStateChanged(firebaseAuth);
            }
        });
    }

    public void Home(View view) {
        onBackPressed();
    }

    public void MyToken(View view) {
        Intent intent=new Intent(Drawable.this,MyTokens.class);
        startActivity(intent);
    }


}