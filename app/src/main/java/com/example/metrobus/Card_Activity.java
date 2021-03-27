package com.example.metrobus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Card_Activity extends AppCompatActivity {

    private RecyclerView Rec_card;
    private ArrayList<Model_Card> model_cardArrayList;
    private CardAdapter cardAdapter;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseAuth firebaseAuth;
    private ImageButton card_filterProductBtn2;
    private TextView card_filterProductTv;
    private TextView card_filterTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_);

        Rec_card=findViewById(R.id.Rec_card);
        firebaseAuth=FirebaseAuth.getInstance();
        linearLayoutManager=new LinearLayoutManager(this);
        card_filterProductBtn2=findViewById(R.id.card_filterProductBtn2);
        card_filterProductTv=findViewById(R.id.card_filterProductTv);
        card_filterTv=findViewById(R.id.card_filterTv);

        card_filterProductBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterDialouge();
            }
        });

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
                    card_filterProductTv.setText("Expired Card");
                    card_filterTv.setText("Expired Card");
                    card_filterProductTv.setTextColor(Color.parseColor("#ff0000"));
                    loadExpiredToken();

                }else if (which==1){
                    //not expired
                    card_filterProductTv.setText("Not Expired Card");
                    card_filterTv.setText("Not Expired Card");
                    addToList();

                }
            }
        }).show();
    }

    private void loadExpiredToken() {
        model_cardArrayList=new ArrayList<>();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("payment");
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").child("Card").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                model_cardArrayList.clear();
                for (DataSnapshot ds:snapshot.getChildren()){
                    //Token token=ds.getValue(Token.class);
                    try {
                        String id=ds.child("T_id").getValue().toString();
                        String p_Amount=ds.child("p_Amount").getValue().toString();
                        String RemainBal=ds.child("RemainBal").getValue().toString();
                        String TotalBal=ds.child("TotalBal").getValue().toString();
                        String timestamp=ds.child("timestamp").getValue().toString();
                        String Token=ds.child("Token").getValue().toString();
                        int remain=Integer.parseInt(RemainBal);

                        if (remain==0){
                            Model_Card model_card=new Model_Card(p_Amount,id,TotalBal,RemainBal,timestamp,Token);
                            model_cardArrayList.add(model_card);
                        }





                    }catch (NullPointerException ignored){

                    }


                }

                //setup Adapter
                cardAdapter=new CardAdapter(Card_Activity.this,model_cardArrayList);
                Rec_card.setLayoutManager(linearLayoutManager);
                Rec_card.setAdapter(cardAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addToList() {
        model_cardArrayList=new ArrayList<>();

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("payment");
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").child("Card").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                model_cardArrayList.clear();
                for (DataSnapshot ds:snapshot.getChildren()){
                    //Token token=ds.getValue(Token.class);
                    try {
                        String id=ds.child("T_id").getValue().toString();
                        String p_Amount=ds.child("p_Amount").getValue().toString();
                        String RemainBal=ds.child("RemainBal").getValue().toString();
                        String TotalBal=ds.child("TotalBal").getValue().toString();
                        String timestamp=ds.child("timestamp").getValue().toString();
                        String Token=ds.child("Token").getValue().toString();

                        int remain=Integer.parseInt(RemainBal);
                        if (remain!=0){
                            Model_Card model_card=new Model_Card(p_Amount,id,TotalBal,RemainBal,timestamp,Token);
                            model_cardArrayList.add(model_card);
                        }

                    }catch (NullPointerException ignored){

                    }


                }

                //setup Adapter
                cardAdapter=new CardAdapter(Card_Activity.this,model_cardArrayList);
                Rec_card.setLayoutManager(linearLayoutManager);
                Rec_card.setAdapter(cardAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}