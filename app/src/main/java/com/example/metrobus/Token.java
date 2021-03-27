package com.example.metrobus;

public class Token {
   private String pp_Amount;
   private String pp_TxnRefNo;

    public Token(String pp_Amount, String pp_TxnRefNo) {
        this.pp_Amount = pp_Amount;
        this.pp_TxnRefNo = pp_TxnRefNo;
    }

    public String getPp_Amount() {
        return pp_Amount;
    }

    public String getPp_TxnRefNo() {
        return pp_TxnRefNo;
    }
}
