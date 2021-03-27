package com.example.metrobus;

public class Model_Card {
   private String pp_Amount;
   private String pp_TxnRefNo;
   private String TotalBal;
   private String RemainBal;
   private String timestamp;
   private String Token;

    public Model_Card(String pp_Amount, String pp_TxnRefNo, String totalBal, String remainBal, String timestamp, String token) {
        this.pp_Amount = pp_Amount;
        this.pp_TxnRefNo = pp_TxnRefNo;
        TotalBal = totalBal;
        RemainBal = remainBal;
        this.timestamp = timestamp;
        Token = token;
    }


    public String getPp_Amount() {
        return pp_Amount;
    }

    public String getPp_TxnRefNo() {
        return pp_TxnRefNo;
    }

    public String getTotalBal() {
        return TotalBal;
    }

    public String getRemainBal() {
        return RemainBal;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getToken() {
        return Token;
    }
}
