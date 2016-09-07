package com.example.skday.gascrm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.api.ApiInterface;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KontrakBaru extends AppCompatActivity {

    private EditText
        idPelanggan,
        noMeter,
        tmpDaftar,
        tglKontrak,
        tipeProduk,
        tipeLayanan,
        flow,
        biaya,
        noMrs,
        noRs,
        alamat,
        rtRw,
        kelDesa,
        kecamatan,
        kotaKab,
        provinsi,
        status;

    private Spinner
        wilayah,
        tarifGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontrak_baru);
        idPelanggan = (EditText) findViewById(R.id.bIdPelanggan);
        Bundle extras = getIntent().getExtras();
        idPelanggan.setText(extras.getString("idPelanggan"));
        noMeter = (EditText) findViewById(R.id.bNoMeter);
        tmpDaftar = (EditText) findViewById(R.id.bTmpDaftar);
        tglKontrak = (EditText) findViewById(R.id.bTglKontrak);
        tipeProduk = (EditText) findViewById(R.id.bTipeProduk);
        tipeLayanan = (EditText) findViewById(R.id.bTipeLayanan);
        flow = (EditText) findViewById(R.id.bFlow);
        biaya = (EditText) findViewById(R.id.bBiaya);
        noMrs = (EditText) findViewById(R.id.bNoMrs);
        noRs = (EditText) findViewById(R.id.bNoRs);
        alamat = (EditText) findViewById(R.id.bAlamat);
        rtRw = (EditText) findViewById(R.id.bRtRw);
        kelDesa = (EditText) findViewById(R.id.bKelDesa);
        kecamatan = (EditText) findViewById(R.id.bKecamatan);
        kotaKab = (EditText) findViewById(R.id.bKotaKab);
        provinsi = (EditText) findViewById(R.id.bProvinsi);
        status = (EditText) findViewById(R.id.bStatus);

        wilayah = (Spinner) findViewById(R.id.bWilayah);
        tarifGroup = (Spinner) findViewById(R.id.bTarifGroup);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_kontrak_baru,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.buat_kontrak) {
            String iIdPelanggan = idPelanggan.getText().toString();
            String iNoMeter = noMeter.getText().toString();
            String iTmpDaftar = tmpDaftar.getText().toString();
            String iTglKontrak = tglKontrak.getText().toString();
            String iTipeProduk = tipeProduk.getText().toString();
            String iTipeLayanan = tipeLayanan.getText().toString();
            String iFlow = flow.getText().toString();
            String iBiaya = biaya.getText().toString();
            String iNoMrs = noMrs.getText().toString();
            String iNoRs = noRs.getText().toString();
            String iAlamat = alamat.getText().toString();
            String iRtRw = rtRw.getText().toString();
            String iKelDesa = kelDesa.getText().toString();
            String iKecamatan = kecamatan.getText().toString();
            String iKotaKab = kotaKab.getText().toString();
            String iProvinsi = provinsi.getText().toString();
            String iStatus = status.getText().toString();
            String iWilayah = wilayah.getSelectedItem().toString();
            String iTarifGroup = tarifGroup.getSelectedItem().toString();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2/crm/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface post = retrofit.create(ApiInterface.class);
            Call<ResponseBody> call = post.buatKontrak(iIdPelanggan, iNoMeter, iWilayah, iTmpDaftar,
                    iTglKontrak, iTipeProduk, iTipeLayanan, iTarifGroup, iFlow, iBiaya, iNoMrs,
                    iNoRs, iAlamat, iRtRw, iKelDesa, iKecamatan, iKotaKab, iProvinsi, iStatus);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.i("InfoCRM","Buat Kontrak");
                    Intent intent = new Intent(KontrakBaru.this,MainActivity.class);
                    intent.putExtra("menu",4);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.i("InfoCRM",t.toString());
                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
