package com.example.metrobus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private CardView Token1,Token2,token3,Recharge_card;
    private ImageView drawable;
    private TextView  Token1_price,Token2_price,token3_price;
    int img[]={R.drawable.img1,R.drawable.img2,R.drawable.img3};
    private  FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth=FirebaseAuth.getInstance();
        viewFlipper=findViewById(R.id.view_fliper);
        Token1=findViewById(R.id.Token1);
        Token2=findViewById(R.id.Token2);
        token3=findViewById(R.id.token3);
        Recharge_card=findViewById(R.id.Recharge_card);
        Token1_price=findViewById(R.id.Token1_price);
        Token2_price=findViewById(R.id.Token2_price);
        token3_price=findViewById(R.id.token3_price);


        Token1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PaymentActivity.class);
                //startActivity(new Intent(MainActivity.this, PaymentActivity.class));
                intent.putExtra("price", Token1_price.getText().toString());

                startActivityForResult(intent, 0);
                //startActivity(i);

            }
        });

        Token2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, PaymentActivity.class);
                i.putExtra("price",Token2_price.getText().toString());

                startActivityForResult(i, 0);
            }
        });

        token3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Home.this, PaymentActivity.class);
                i.putExtra("price", token3_price.getText().toString());

                startActivityForResult(i, 0);
            }
        });

        Recharge_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(Home.this, PaymentActivity.class);
//                i.putExtra("price",Recharge_card.getText().toString());
//                startActivityForResult(i, 0);


               RechargeDialogue rechargeDialogue =new RechargeDialogue();
                rechargeDialogue.show(getSupportFragmentManager(),"dialouge");
            }
        });
        drawable=findViewById(R.id.drawable);


        drawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent=new Intent(Home.this,Drawable.class);
            startActivity(intent);
            }
        });


        
        for (int i=0;i<img.length;i++){
            setviewflipper(img[i]);
            
        }


    }

    private void setviewflipper(int i) {
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(i);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 0 && resultCode == RESULT_OK) {
            // Get String data from Intent
            String ResponseCode = data.getStringExtra("pp_ResponseCode");
            System.out.println("DateFn: ResponseCode:" + ResponseCode);
           try {
               if(ResponseCode.equals("000")) {
                   Toast.makeText(getApplicationContext(), "Payment Success", Toast.LENGTH_SHORT).show();

                   String transId=data.getStringExtra("pp_TxnRefNo");
                   String amount=data.getStringExtra("pp_Amount");
                   //add to database
                   AddToken(transId,amount);

                   Toast.makeText(this, "Trans"+transId, Toast.LENGTH_SHORT).show();
                   Toast.makeText(this, "amount"+amount, Toast.LENGTH_SHORT).show();

                   Intent intent=new Intent(Home.this,MyTokens.class);
//                intent.putExtra("transId",transId);
//                intent.putExtra("amount",amount);
                   startActivity(intent);



               }
               else
               {
                   Toast.makeText(getApplicationContext(), "Payment Failed", Toast.LENGTH_SHORT).show();
               }
           }catch (NullPointerException ignored){
               
           }
        }
    }

    private void AddToken(String transId, String amount) {
        final String timestamp=""+System.currentTimeMillis();

        Date Date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddkkmmss");
        String ToDayDate = dateFormat.format(Date);


        // Convert Date to Calendar
        Calendar c = Calendar.getInstance();
        c.setTime(Date);
        c.add(Calendar.HOUR, 1);

        // Convert calendar back to Date
        Date currentDateHourPlusOne = c.getTime();
        String expiryDate = dateFormat.format(currentDateHourPlusOne);

        Map<String,String> map=new HashMap<>();
        map.put("uuid",""+firebaseAuth.getUid());
        map.put("T_id",transId);
        map.put("p_Amount",amount);
        map.put("timestamp",timestamp);
        map.put("ToDayDate",ToDayDate);
        map.put("expiryDate",expiryDate);
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("payment");
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").child(timestamp).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Home.this, "Added to database", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Home.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}