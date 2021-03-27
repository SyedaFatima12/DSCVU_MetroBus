package com.example.metrobus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class RechargeDialogue extends DialogFragment {
    private TextView p1,p2,p3,p4,p5;
    private  AlertDialog.Builder builder;
    private FirebaseAuth firebaseAuth;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        firebaseAuth=FirebaseAuth.getInstance();
         builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.rechargedialouge,null);
        builder.setView(view);
        builder.setTitle("Please Select Amount");

        p1=view.findViewById(R.id.p1);
        p2=view.findViewById(R.id.p2);
        p3=view.findViewById(R.id.p3);
        p4=view.findViewById(R.id.p4);
        p5=view.findViewById(R.id.p5);

        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PaymentActivity.class);
                i.putExtra("price",p1.getText().toString());

                startActivityForResult(i, 0);
            }
        });

        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PaymentActivity.class);
                i.putExtra("price",p2.getText().toString());

                startActivityForResult(i, 0);
            }
        });


        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PaymentActivity.class);
                i.putExtra("price",p3.getText().toString());

                startActivityForResult(i, 0);
            }
        });
        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PaymentActivity.class);
                i.putExtra("price",p4.getText().toString());

                startActivityForResult(i, 0);
            }
        });
        p5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), PaymentActivity.class);
                i.putExtra("price",p5.getText().toString());

                startActivityForResult(i, 0);
            }
        });

        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 0 && resultCode == RESULT_OK) {
            // Get String data from Intent
            String ResponseCode = data.getStringExtra("pp_ResponseCode");
            System.out.println("DateFn: ResponseCode:" + ResponseCode);
            try {
                if(ResponseCode.equals("000")) {
                    // builder.setCancelable(true);
                    Toast.makeText(getContext(), "Payment Success", Toast.LENGTH_SHORT).show();
                    String transId=data.getStringExtra("pp_TxnRefNo");
                    String amount=data.getStringExtra("pp_Amount");

                    AddToken(transId,amount);
                    Toast.makeText(getContext(), "Trans"+transId, Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "amount"+amount, Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getContext(),Card_Activity.class);
//                intent.putExtra("transId",transId);
//                intent.putExtra("amount",amount);
                    startActivity(intent);



                }
                else
                {
                    Toast.makeText(getContext(), "Payment Failed", Toast.LENGTH_SHORT).show();
                }
            }catch (NullPointerException ignored){

            }
        }
    }

    private void AddToken(String transId, String amount) {
        final String timestamp=""+System.currentTimeMillis();

        Map<String,String> map=new HashMap<>();
        map.put("uuid",""+firebaseAuth.getUid());
        map.put("T_id",transId);
        map.put("p_Amount",amount);
        map.put("timestamp",timestamp);

        if (amount.equals("12000")){
            map.put("TotalBal","120");
            map.put("RemainBal","120");
            map.put("Token","4");
        }

        if (amount.equals("24000")){
            map.put("TotalBal","240");
            map.put("RemainBal","240");
            map.put("Token","8");
        }

        if (amount.equals("36000")){
            map.put("TotalBal","360");
            map.put("RemainBal","360");
            map.put("Token","12");
        }

        if (amount.equals("48000")){
            map.put("TotalBal","480");
            map.put("RemainBal","480");
            map.put("Token","16");
        }

        if (amount.equals("60000")){
            map.put("TotalBal","600");
            map.put("RemainBal","600");
            map.put("Token","20");
        }

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("payment");
        databaseReference.child(firebaseAuth.getCurrentUser().getUid()).child("all_Trans").child("Card").child(timestamp).setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getContext(), "Added to database", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
