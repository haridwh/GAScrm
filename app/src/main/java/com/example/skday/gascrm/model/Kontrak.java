package com.example.skday.gascrm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by skday on 13/06/16.
 */

/*Model objek Kontrak
@SerializedName() digunakan untuk menset key dari atribut dibawahnya,
atau saat menerima data berupa json akan disimpan sesuai key*/
public class Kontrak {
    @SerializedName("idKontrak")
    private String idKontrak;
    @SerializedName("idPelanggan")
    private String idPelanggan;
    @SerializedName("noMeter")
    private String noMeter;
    @SerializedName("wilayah")
    private String wilayah;
    @SerializedName("tempatDaftar")
    private String tempatDaftar;
    @SerializedName("tglKontrak")
    private String tglKontrak;
    @SerializedName("tipeProduk")
    private String tipeProduk;
    @SerializedName("tipeLayanan")
    private String tipeLayanan;
    @SerializedName("tarifGroup")
    private String tarifGroup;
    @SerializedName("flow")
    private String flow;
    @SerializedName("biaya")
    private String biaya;
    @SerializedName("noMrs")
    private String noMrs;
    @SerializedName("noRs")
    private String noRs;
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
    @SerializedName("status")
    private String status;

    public String getIdKontrak() {
        return idKontrak;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNoMeter() {
        return noMeter;
    }

    public String getWilayah() {
        return wilayah;
    }

    public String getTempatDaftar() {
        return tempatDaftar;
    }

    public String getTglKontrak() {
        return tglKontrak;
    }

    public String getTipeProduk() {
        return tipeProduk;
    }

    public String getTipeLayanan() {
        return tipeLayanan;
    }

    public String getTarifGroup() {
        return tarifGroup;
    }

    public String getFlow() {
        return flow;
    }

    public String getBiaya() {
        return biaya;
    }

    public String getNoMrs() {
        return noMrs;
    }

    public String getNoRs() {
        return noRs;
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

    public String getStatus() {
        return status;
    }
}
