package com.example.skday.gascrm.api;

import com.example.skday.gascrm.model.Deposit;
import com.example.skday.gascrm.model.Kontrak;
import com.example.skday.gascrm.model.Pelanggan;
import com.example.skday.gascrm.model.Token;
import com.example.skday.gascrm.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by skday on 06/06/16.
 */

//Berisi API
public interface ApiInterface {

    /*
    Gunakan @FormUrlEncoded jika melakukan @POST
    @Field berupa key untuk data yang akan dikirim, key ini akan digunakan di php untuk mengakses data yg dikirim
    */

    /*jika login diakses maka akan mengakses http://cingkleung.com/crm/login.php atau baseUrl/login.php
    dan akan mengirim data username dan akan meretrun response yg digeneralisis menjadi user*/
    @FormUrlEncoded
    @POST("login.php")
    Call<User> login(@Field("username") String username);

    /*jika getDepo diakses maka akan mengakses http://cingkleung.com/crm/deposit.php atau baseUrl/deposit.php
    dan akan mengirim data id dan akan meretrun response yg digeneralisis menjadi List<Deposit>*/
    @FormUrlEncoded
    @POST("deposit.php")
    Call<List<Deposit>> getDepo(@Field("idAgent") String id);

    /*jika toUpRequest diakses maka akan mengakses http://cingkleung.com/crm/topuprequest.php
    dan akan mengirim data dan akan meretrun response yg digeneralisis menjadi ResponseBody*/
    @FormUrlEncoded
    @POST("topuprequest.php")
    Call<ResponseBody> topUpRequest(
        @Field("nominal") String nominal,
        @Field("id_agent") String id_agent
    );

    /*jika noKontrak diakses maka akan mengakses http://cingkleung.com/crm/nokontrak.php
    dan akan mengirim data dan akan meretrun response yg digeneralisis menjadi ResponseBody*/
    @FormUrlEncoded
    @POST("nokontrak.php")
    Call<ResponseBody> noKontrak(@Field("noKtp") String noKtp);

    /*jika noMeter diakses maka akan mengakses http://cingkleung.com/crm/nometer.php
    dan akan mengirim data dan akan meretrun response yg digeneralisis menjadi ResponseBody*/
    @FormUrlEncoded
    @POST("nometer.php")
    Call<ResponseBody> noMeter(@Field("noKtp") String noKtp);

    /*jika buyToken diakses maka akan mengakses http://cingkleung.com/crm/bltoken.php
    dan akan mengirim data dan akan meretrun response yg digeneralisis menjadi ResponseBody*/
    @FormUrlEncoded
    @POST("bltoken.php")
    Call<ResponseBody> buyToken(
            @Field("noKtp") String noKtp,
            @Field("idKontrak") String idKontrak,
            @Field("noMeter") String nomerMeter,
            @Field("nominal") String nominal,
            @Field("volume") String volume
    );

    /*jika getPelanggan diakses maka akan mengakses http://cingkleung.com/crm/pelanggan.php
    dan akan meretrun response yg digeneralisis menjadi List<Pelanggan>*/
    @GET("pelanggan.php")
    Call<List<Pelanggan>> getPelanggan();

    /*jika pelangganBaru diakses maka akan mengakses http://cingkleung.com/crm/pelangganbaru.php
    dan akan mengirim data dan akan meretrun response yg digeneralisis menjadi ResponseBody*/
    @FormUrlEncoded
    @POST("pelangganbaru.php")
    Call<ResponseBody> pelangganBaru(
        @Field("noKtp") String noKtp,
        @Field("nama") String nama,
        @Field("tTL") String tTL,
        @Field("jK") String jk,
        @Field("alamat") String alamat,
        @Field("rtRW") String rtRW,
        @Field("kelDesa") String kelDesa,
        @Field("kecamatan") String kecamatan,
        @Field("kotaKab") String kotaKab,
        @Field("provinsi") String provinsi,
        @Field("agama") String agama,
        @Field("status") String status,
        @Field("pekerjaan") String pekerjaan,
        @Field("wargaNegara") String warganegara,
        @Field("telp") String telp,
        @Field("hp") String hp,
        @Field("email") String email
    );

    /*jika buatKontrak diakses maka akan mengakses http://cingkleung.com/crm/buatkontrak.php
    dan akan mengirim data dan akan meretrun response yg digeneralisis menjadi ResponseBody*/
    @FormUrlEncoded
    @POST("buatkontrak.php")
    Call<ResponseBody> buatKontrak(
        @Field("idPelanggan") String idPelanggan,
        @Field("noMeter") String noMeter,
        @Field("wilayah") String wilayah,
        @Field("tmpDaftar") String tmpDaftar,
        @Field("tglKontrak") String tglKontrak,
        @Field("tipeProduk") String tipeProduk,
        @Field("tipeLayanan") String tipeLayanan,
        @Field("tarifGroup") String tarifGroup,
        @Field("flow") String flow,
        @Field("biaya") String biaya,
        @Field("noMrs") String noMres,
        @Field("noRs") String noRs,
        @Field("alamat") String alamat,
        @Field("rtRw") String rtRw,
        @Field("kelDesa") String kelDesa,
        @Field("kecamatan") String kecamatan,
        @Field("kotaKab") String kotaKab,
        @Field("provinsi") String provinsi,
        @Field("status") String status
    );

    /*jika editPelanggan diakses maka akan mengakses http://cingkleung.com/crm/editpelanggan.php
    dan akan mengirim data dan akan meretrun response yg digeneralisis menjadi ResponseBody*/
    @FormUrlEncoded
    @POST("editpelanggan.php")
    Call<ResponseBody> editPelanggan(
            @Field("idPelanggan") String idPelanggan,
            @Field("noKtp") String noKtp,
            @Field("nama") String nama,
            @Field("tTL") String tTL,
            @Field("jK") String jk,
            @Field("alamat") String alamat,
            @Field("rtRW") String rtRW,
            @Field("kelDesa") String kelDesa,
            @Field("kecamatan") String kecamatan,
            @Field("kotaKab") String kotaKab,
            @Field("provinsi") String provinsi,
            @Field("agama") String agama,
            @Field("status") String status,
            @Field("pekerjaan") String pekerjaan,
            @Field("wargaNegara") String warganegara,
            @Field("telp") String telp,
            @Field("hp") String hp,
            @Field("email") String email
    );

    /*jika hapusPelanggan diakses maka akan mengakses http://cingkleung.com/crm/hapuspelanggan.php
    dan akan mengirim data dan akan meretrun response yg digeneralisis menjadi List<Pelanggan>*/
    @FormUrlEncoded
    @POST("hapuspelanggan.php")
    Call<ResponseBody> hapusPelanggan(
            @Field("id") String id
    );

    /*jika getKontrak diakses maka akan mengakses http://cingkleung.com/crm/kontrak.php
    dan akan meretrun response yg digeneralisis menjadi List<Kontrak>*/
    @GET("kontrak.php")
    Call<List<Kontrak>> getKontrak();

    /*jika berhenti diakses maka akan mengakses http://cingkleung.com/crm/berhenti.php
    dan akan mengirim data dan akan meretrun response yg digeneralisis menjadi ResponseBody*/
    @FormUrlEncoded
    @POST("berhenti.php")
    Call<ResponseBody> berhenti(@Field("idPelanggan") String id);

    /*jika getToken diakses maka akan mengakses http://cingkleung.com/crm/token.php
    dan akan meretrun response yg digeneralisis menjadi List<Token>*/
    @GET("token.php")
    Call<List<Token>> getToken();
}
