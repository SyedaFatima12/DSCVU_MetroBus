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

import java.util.ArrayList;

public class TokenAdapter extends RecyclerView.Adapter<TokenAdapter.TokenViewHolder> {

    private Context context;
    public ArrayList<Token> tokenArrayList;

    public TokenAdapter(Context context, ArrayList<Token> tokenArrayList) {
        this.context = context;
        this.tokenArrayList = tokenArrayList;
    }

    @NonNull
    @Override
    public TokenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.token_layout,parent,false);
        return new TokenViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TokenViewHolder holder, int position) {
        Token token=tokenArrayList.get(position);
        //get data
        String pp_Amount=token.getPp_Amount();


        String pp_TxnRefNo=token.getPp_TxnRefNo();
        //set data
        holder.trans_txt.setText(pp_TxnRefNo);

    try {

    if (pp_Amount.equals("3000")){
        holder.quantity.setText("quantity:"+" "+"1");
        holder.payment.setText("Rs:30");
    }
    if (pp_Amount.equals("6000")){
        holder.quantity.setText("quantity:"+" "+"2");
        holder.payment.setText("Rs:60");
    }

    if (pp_Amount.equals("9000")){
        holder.quantity.setText("quantity:"+" "+"3");
        holder.payment.setText("Rs:90");
    }


//        if(pp_Amount.equals("11000")){
//            holder.payment.setText("Rs:110");
//            holder.trans_txt.setText(pp_TxnRefNo);
//            holder.Token_img.setImageResource(R.drawable.card);
//        }
//
//        if(pp_Amount.equals("22000")){
//            holder.payment.setText("Rs:220");
//            holder.trans_txt.setText(pp_TxnRefNo);
//            holder.Token_img.setImageResource(R.drawable.card);
//        }
//        if(pp_Amount.equals("33000")){
//            holder.payment.setText("Rs:330");
//            holder.trans_txt.setText(pp_TxnRefNo);
//            holder.Token_img.setImageResource(R.drawable.card);
//        }
//
//        if(pp_Amount.equals("44000")){
//            holder.payment.setText("Rs:4400");
//            holder.trans_txt.setText(pp_TxnRefNo);
//            holder.Token_img.setImageResource(R.drawable.card);
//        }
//        if(pp_Amount.equals("55000")){
//            holder.payment.setText("Rs:550");
//            holder.trans_txt.setText(pp_TxnRefNo);
//            holder.Token_img.setImageResource(R.drawable.card);
//        }

    }catch (NullPointerException ignored){

    }
    }

    @Override
    public int getItemCount() {
        return tokenArrayList.size();
    }

    class TokenViewHolder extends RecyclerView.ViewHolder{
        private TextView trans_txt;
        private TextView quantity;
        private TextView payment;
        private ImageView Token_img;

        public TokenViewHolder(@NonNull View itemView) {
            super(itemView);

            trans_txt=itemView.findViewById(R.id.trans_num);
            quantity=itemView.findViewById(R.id.quantity);
            payment=itemView.findViewById(R.id.payment);
            Token_img=itemView.findViewById(R.id.Token_img);
        }
    }
}


