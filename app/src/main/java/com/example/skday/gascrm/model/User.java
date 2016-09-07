package com.example.skday.gascrm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by skday on 28/06/16.
 */

/*Model objek User
@SerializedName() digunakan untuk menset key dari atribut dibawahnya,
atau saat menerima data berupa json akan disimpan sesuai key*/
public class User {
    @SerializedName("id")
    public String id;
    @SerializedName("email")
    public String email;
    @SerializedName("userlogin")
    public String userLogin;
    @SerializedName("fullname")
    public String fullname;
    @SerializedName("ktp")
    public String ktp;
    @SerializedName("role")
    public String role;
    @SerializedName("created")
    public String created;
    @SerializedName("update")
    public String update;
    @SerializedName("status")
    public String status;
}
