package com.example.metrobus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private Context context;
    public ArrayList<Model_Card> model_cardArrayList;

    private FirebaseAuth firebaseAuth;

    public CardAdapter(Context context, ArrayList<Model_Card> model_cardArrayList) {
        this.context = context;
        this.model_cardArrayList = model_cardArrayList;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        firebaseAuth=FirebaseAuth.getInstance();
        View view=LayoutInflater.from(context).inflate(R.layout.card_layout,parent,false);
        return new CardViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Model_Card model_card=model_cardArrayList.get(position);
        //get data
        String pp_Amount=model_card.getPp_Amount();
        String RemainBal=model_card.getRemainBal();
        String TotalBal=model_card.getTotalBal();
        String timestamp=model_card.getTimestamp();
        String Token=model_card.getToken();

        String pp_TxnRefNo=model_card.getPp_TxnRefNo();
        //set data
        holder.card_trans_num.setText(pp_TxnRefNo);

    try {


        if(pp_Amount.equals("12000")){
           /// String convert=Integer.toString(p1);
            holder.card_payment.setText(TotalBal);
            holder.card_trans_num.setText(pp_TxnRefNo);
            holder.Card_quantity.setText(Token);
            holder.after_payment.setText(RemainBal);


        }

        if(pp_Amount.equals("24000")){
            holder.card_payment.setText(TotalBal);
            holder.card_trans_num.setText(pp_TxnRefNo);
            holder.Card_quantity.setText(Token);
            holder.after_payment.setText(RemainBal);
        }
        if(pp_Amount.equals("36000")){
            holder.card_payment.setText(TotalBal);
            holder.card_trans_num.setText(pp_TxnRefNo);
            holder.Card_quantity.setText(Token);
            holder.after_payment.setText(RemainBal);
        }

        if(pp_Amount.equals("48000")){
            holder.card_payment.setText(TotalBal);
            holder.card_trans_num.setText(pp_TxnRefNo);
            holder.Card_quantity.setText(Token);
            holder.after_payment.setText(RemainBal);
        }
        if(pp_Amount.equals("60000")){
            holder.card_payment.setText(TotalBal);
            holder.card_trans_num.setText(pp_TxnRefNo);
            holder.Card_quantity.setText(Token);
            holder.after_payment.setText(RemainBal);

        }

    }catch (NullPointerException ignored){

    }

    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (pp_Amount.equals("12000")){
               try {
                   int p1=Integer.parseInt(RemainBal);
                    int T1=Integer.parseInt(Token);

                   if (p1>0){
                       int price=p1-30;
                       int token=T1-1;
                       p1=price;
                       T1=token;
                       String updatePrice=Integer.toString(p1);
                       String UpdateToken=Integer.toString(T1);

                       Map<String,Object> map=new HashMap<>();
                       map.put("RemainBal",updatePrice);
                       map.put("Token",UpdateToken);
                       DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("payment");
                       databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").child("Card").child(timestamp).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                           }
                       });
                     //  holder.after_payment.setText(updatePrice);
                       holder.Card_quantity.setText(UpdateToken);
                   }




               }catch (NumberFormatException ex){

               }

            }
            //2
            if (pp_Amount.equals("24000")){
                try {
                    int p2=Integer.parseInt(RemainBal);
                    int T2=Integer.parseInt(Token);

                    if (p2>0){
                        int price=p2-30;
                        int token=T2-1;
                        p2=price;
                        T2=token;
                        String updatePrice=Integer.toString(p2);
                        String UpdateToken=Integer.toString(T2);

                        Map<String,Object> map=new HashMap<>();
                        map.put("RemainBal",updatePrice);
                        map.put("Token",UpdateToken);
                        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("payment");
                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").child("Card").child(timestamp).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                            }
                        });
                        //  holder.after_payment.setText(updatePrice);
                        holder.Card_quantity.setText(UpdateToken);
                    }




                }catch (NumberFormatException ex){

                }

            }


//3

            if (pp_Amount.equals("36000")){
                try {
                    int p3=Integer.parseInt(RemainBal);
                    int T3=Integer.parseInt(Token);

                    if (p3>0){
                        int price=p3-30;
                        int token=T3-1;
                        p3=price;
                        T3=token;
                        String updatePrice=Integer.toString(p3);
                        String UpdateToken=Integer.toString(T3);

                        Map<String,Object> map=new HashMap<>();
                        map.put("RemainBal",updatePrice);
                        map.put("Token",UpdateToken);
                        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("payment");
                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").child("Card").child(timestamp).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                            }
                        });
                        //  holder.after_payment.setText(updatePrice);
                        holder.Card_quantity.setText(UpdateToken);
                    }




                }catch (NumberFormatException ex){

                }

            }
//4

            if (pp_Amount.equals("48000")){
                try {
                    int p4=Integer.parseInt(RemainBal);
                    int T4=Integer.parseInt(Token);

                    if (p4>0){
                        int price=p4-30;
                        int token=T4-1;
                        p4=price;
                        T4=token;
                        String updatePrice=Integer.toString(p4);
                        String UpdateToken=Integer.toString(T4);

                        Map<String,Object> map=new HashMap<>();
                        map.put("RemainBal",updatePrice);
                        map.put("Token",UpdateToken);
                        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("payment");
                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").child("Card").child(timestamp).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                            }
                        });
                        //  holder.after_payment.setText(updatePrice);
                        holder.Card_quantity.setText(UpdateToken);
                    }




                }catch (NumberFormatException ex){

                }

            }
//5

            if (pp_Amount.equals("60000")){
                try {
                    int p5=Integer.parseInt(RemainBal);
                    int T5=Integer.parseInt(Token);

                    if (p5>0){
                        int price=p5-30;
                        int token=T5-1;
                        p5=price;
                        T5=token;
                        String updatePrice=Integer.toString(p5);
                        String UpdateToken=Integer.toString(T5);

                        Map<String,Object> map=new HashMap<>();
                        map.put("RemainBal",updatePrice);
                        map.put("Token",UpdateToken);
                        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("payment");
                        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").child("Card").child(timestamp).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
                            }
                        });
                        //  holder.after_payment.setText(updatePrice);
                        holder.Card_quantity.setText(UpdateToken);
                    }




                }catch (NumberFormatException ex){

                }

            }


        }
    });
    }

    @Override
    public int getItemCount() {
        return model_cardArrayList.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder{
        private TextView card_trans_num;
        private TextView Card_quantity;
        private TextView card_payment;
        private TextView after_payment;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            card_trans_num=itemView.findViewById(R.id.card_trans_num);
            Card_quantity=itemView.findViewById(R.id.Card_quantity);
            card_payment=itemView.findViewById(R.id.card_payment);
            after_payment=itemView.findViewById(R.id.after_payment);

        }


    }
}


