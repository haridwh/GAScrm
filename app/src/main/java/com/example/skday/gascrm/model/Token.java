package com.example.skday.gascrm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by skday on 14/06/16.
 */

/*Model objek Token
@SerializedName() digunakan untuk menset key dari atribut dibawahnya,
atau saat menerima data berupa json akan disimpan sesuai key*/
public class Token {
    @SerializedName("idToken")
    private String idToken;
    @SerializedName("noKtp")
    private String noKtp;
    @SerializedName("idKontrak")
    private String idKontrak;
    @SerializedName("noMeter")
    private String noMeter;
    @SerializedName("nominal")
    private String nominal;
    @SerializedName("volume")
    private String volume;

    public String getIdToken() {
        return idToken;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public String getIdKontrak() {
        return idKontrak;
    }

    public String getNoMeter() {
        return noMeter;
    }

    public String getNominal() {
        return nominal;
    }

    public String getVolume() {
        return volume;
    }
}
