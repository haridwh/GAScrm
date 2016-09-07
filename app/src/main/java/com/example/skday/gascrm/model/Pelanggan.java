package com.example.skday.gascrm.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by skday on 13/06/16.
 */

/*Model objek Pelanggan
@SerializedName() digunakan untuk menset key dari atribut dibawahnya,
atau saat menerima data berupa json akan disimpan sesuai key*/
public class Pelanggan implements Serializable{
    @SerializedName("idPelanggan")
    private String idPelanggan;
    @SerializedName("noKtp")
    private String noKtp;
    @SerializedName("nama")
    private String nama;
    @SerializedName("ttl")
    private String ttl;
    @SerializedName("jk")
    private String jk;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("rtRw")
    private String rtRw;
    @SerializedName("kelDesa")
    private String kelDesa;
    @SerializedName("kecamatan")
    private String kecamatan;
    @SerializedName("kotaKab")
    private String kotaKab;
    @SerializedName("provinsi")
    private String provinsi;
    @SerializedName("agama")
    private String agama;
    @SerializedName("status")
    private String status;
    @SerializedName("pekerjaan")
    private String pekerjaan;
    @SerializedName("warganegara")
    private String warganegara;
    @SerializedName("telp")
    private String telp;
    @SerializedName("hp")
    private String hp;
    @SerializedName("email")
    private String email;

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNoKtp() {
        return noKtp;
    }

    public String getNama() {
        return nama;
    }

    public String getTtl() {
        return ttl;
    }

    public String getJk() {
        return jk;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getRtRw() {
        return rtRw;
    }

    public String getKelDesa() {
        return kelDesa;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getKotaKab() {
        return kotaKab;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public String getAgama() {
        return agama;
    }

    public String getStatus() {
        return status;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public String getWarganegara() {
        return warganegara;
    }

    public String getTelp() {
        return telp;
    }

    public String getHp() {
        return hp;
    }

    public String getEmail() {
        return email;
    }
}
