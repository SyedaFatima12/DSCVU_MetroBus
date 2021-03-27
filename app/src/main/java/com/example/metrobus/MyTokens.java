package com.example.metrobus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyTokens extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Token> tokenArrayList;
    private TokenAdapter tokenAdapter;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseAuth firebaseAuth;
    private ImageButton filterProductBtn2;
    private TextView filterProductTv;
    private TextView filterTv;
    private TextView tabToken,TabCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tokens);
        recyclerView=findViewById(R.id.Rec_Token);
        filterProductBtn2=findViewById(R.id.filterProductBtn2);
        filterProductTv=findViewById(R.id.filterProductTv);
        tabToken=findViewById(R.id.tabToken);
        TabCard=findViewById(R.id.tabCard);
        filterTv=findViewById(R.id.filterTv);
        linearLayoutManager=new LinearLayoutManager(this);
        firebaseAuth=FirebaseAuth.getInstance();

        tabToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tabToken.setTextColor(getResources().getColor(R.color.black));
                tabToken.setBackgroundResource(R.drawable.shape_rect04);

                TabCard.setTextColor(getResources().getColor(R.color.white));
                TabCard.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                addToList();
            }
        });
        TabCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabToken.setTextColor(getResources().getColor(R.color.white));
                tabToken.setBackgroundColor(getResources().getColor(android.R.color.transparent));

                TabCard.setTextColor(getResources().getColor(R.color.black));
                TabCard.setBackgroundResource(R.drawable.shape_rect04);
                Intent intent=new Intent(MyTokens.this,Card_Activity.class);
                startActivity(intent);
            }
        });


        filterProductBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterDialouge();
            }
        });
//        Intent intent=getIntent();
//        String Id=intent.getStringExtra("transId");
//        String T_amount=intent.getStringExtra("amount");
//       // AddToken(T_amount,Id);
        addToList();

    }

    private void FilterDialouge() {
        String Options[]={"Expired","Not Expired"};
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Filter Promotion Code");
        builder.setItems(Options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which==0){
                    //All
                    //expired
                    filterProductTv.setText("Expired Tokens");
                    filterTv.setText("Expired Token");
                    filterProductTv.setTextColor(Color.parseColor("#ff0000"));
                    loadExpiredPromoCode();

                }else if (which==1){
                    //not expired
                    filterProductTv.setText("Not Expired Tokens");
                    filterTv.setText("Not Expired Token");
                    addToList();

                }
            }
        }).show();
    }

    private void loadExpiredPromoCode() {
        tokenArrayList=new ArrayList<>();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("payment");
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tokenArrayList.clear();
                for (DataSnapshot ds:snapshot.getChildren()){
                    //Token token=ds.getValue(Token.class);
                    try {
                        String id=ds.child("T_id").getValue().toString();
                        String p_Amount=ds.child("p_Amount").getValue().toString();
                       // String currentDate=ds.child("ToDayDate").getValue().toString();
                        String exp=ds.child("expiryDate").getValue().toString();

                        Date Date = new Date();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddkkmmss");
                        String currentDate = dateFormat.format(Date);


                        if (exp.compareTo(currentDate)>0){
                            //date 1 occur after date 2


                        }else if (exp.compareTo(currentDate)<0){
                            //date 1 occur before date 2
                            Token token=new Token(p_Amount,id);
                            tokenArrayList.add(token);



                        }else if (exp.compareTo(currentDate)==0){
                            //both date equal

                        }

                    }catch (NullPointerException ignored){

                    }


                }

                //setup Adapter
                tokenAdapter=new TokenAdapter(MyTokens.this,tokenArrayList);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(tokenAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void addToList() {
        tokenArrayList=new ArrayList<>();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("payment");
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tokenArrayList.clear();
                for (DataSnapshot ds:snapshot.getChildren()){
                    //Token token=ds.getValue(Token.class);
                   try {
                       String id=ds.child("T_id").getValue().toString();
                       String p_Amount=ds.child("p_Amount").getValue().toString();
                      // String currentDate=ds.child("ToDayDate").getValue().toString();
                       String exp=ds.child("expiryDate").getValue().toString();

                       Date Date = new Date();
                       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddkkmmss");
                       String currentDate = dateFormat.format(Date);


                       if (exp.compareTo(currentDate)>0){
                           //date 1 occur after date 2
                           Token token=new Token(p_Amount,id);
                           tokenArrayList.add(token);


                       }else if (exp.compareTo(currentDate)<0){
                           //date 1 occur before date 2



                       }else if (exp.compareTo(currentDate)==0){
                           //both date equal
                           Token token=new Token(p_Amount,id);
                           tokenArrayList.add(token);

                       }

                   }catch (NullPointerException ignored){

                   }


                }

                //setup Adapter
                tokenAdapter=new TokenAdapter(MyTokens.this,tokenArrayList);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(tokenAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

//    private void AddToken(String id, String t_amount) {
//        Map<String,String> map=new HashMap<>();
//        map.put("uuid",""+firebaseAuth.getUid());
//       map.put("T_id",id);
//        map.put("p_Amount",t_amount);
//        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("payment");
//        databaseReference.child(firebaseAuth.getUid()).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(MyTokens.this, "Added to database", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(MyTokens.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }


}