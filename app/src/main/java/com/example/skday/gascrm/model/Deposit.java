package com.example.skday.gascrm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by skday on 06/06/16.
 */

/*Model objek Deposit
@SerializedName() digunakan untuk menset key dari atribut dibawahnya,
atau saat menerima data berupa json akan disimpan sesuai key*/
public class Deposit {
    @SerializedName("fullname")
    private String fullname;
    @SerializedName("nominal")
    private int nominal;
    @SerializedName("status")
    private char status;
    @SerializedName("approval")
    private char approval;
    @SerializedName("tanggal")
    private String tanggal;


    public String getFullname() {
        return fullname;
    }

    public int getNominal() {
        return nominal;
    }

    public char getStatus() {
        return status;
    }

    public char getApproval() {
        return approval;
    }

    public String getTanggal() {
        return tanggal;
    }
}
