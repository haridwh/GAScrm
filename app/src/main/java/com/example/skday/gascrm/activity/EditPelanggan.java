package com.example.skday.gascrm.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.api.ApiInterface;
import com.example.skday.gascrm.model.Pelanggan;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditPelanggan extends AppCompatActivity {

    private Pelanggan pelanggan;

    private String idPelanggan;

    public EditText
        noKtp,
        nama,
        ttl,
        alamat,
        rtRw,
        kelDesa,
        kecamatan,
        agama,
        pekerjaan,
        wargaNegara,
        noTelp,
        noHp,
        email;

    private Spinner
            jenisKelamin,
            kotaKabupaten,
            provinsi,
            status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pelanggan);
        pelanggan = (Pelanggan) getIntent().getSerializableExtra("Pelanggan");
        noKtp = (EditText) findViewById(R.id.editNoKtp);
        noKtp.setText(pelanggan.getNoKtp());
        nama = (EditText) findViewById(R.id.editNama);
        nama.setText(pelanggan.getNama());
        ttl = (EditText) findViewById(R.id.editTTL);
        ttl.setText(pelanggan.getTtl());
        alamat = (EditText) findViewById(R.id.editAlamat);
        alamat.setText(pelanggan.getAlamat());
        rtRw = (EditText) findViewById(R.id.editRtRw);
        rtRw.setText(pelanggan.getRtRw());
        kelDesa = (EditText) findViewById(R.id.editKelDes);
        kelDesa.setText(pelanggan.getKelDesa());
        kecamatan = (EditText) findViewById(R.id.editKecamatan);
        kecamatan.setText(pelanggan.getKecamatan());
        agama = (EditText)  findViewById(R.id.editAgama);
        agama.setText(pelanggan.getAgama());
        pekerjaan = (EditText) findViewById(R.id.editPekerjaan);
        pekerjaan.setText(pelanggan.getPekerjaan());
        wargaNegara = (EditText) findViewById(R.id.editWargaNegara);
        wargaNegara.setText(pelanggan.getWarganegara());
        noTelp = (EditText) findViewById(R.id.editNoTelp);
        noTelp.setText(pelanggan.getTelp());
        noHp = (EditText) findViewById(R.id.editNoHp);
        noHp.setText(pelanggan.getHp());
        email = (EditText) findViewById(R.id.editEmail);
        email.setText(pelanggan.getEmail());

        jenisKelamin = (Spinner) findViewById(R.id.editJK);
        kotaKabupaten = (Spinner) findViewById(R.id.editktKab);
        provinsi = (Spinner) findViewById(R.id.editProv);
        status = (Spinner) findViewById(R.id.editStatus);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_pel,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.edit_customer){
            String iNoKtp = noKtp.getText().toString();
            String iNama = nama.getText().toString();
            String iTTL = ttl.getText().toString();
            String iJk = jenisKelamin.getSelectedItem().toString();
            String iAlamat = alamat.getText().toString();
            String iRtRw = rtRw.getText().toString();
            String iKelDesa = kelDesa.getText().toString();
            String iKecamatan = kecamatan.getText().toString();
            String iKotaKab = kotaKabupaten.getSelectedItem().toString();
            String iProvinsi = provinsi.getSelectedItem().toString();
            String iAgama = agama.getText().toString();
            String iStatus = status.getSelectedItem().toString();
            String iPekerjaan = pekerjaan.getText().toString();
            String iWargaNegara = wargaNegara.getText().toString();
            String iTelp = noTelp.getText().toString();
            String iHp = noHp.getText().toString();
            String iEmail = email.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2/crm/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface post = retrofit.create(ApiInterface.class);
            Call<ResponseBody> call = post.editPelanggan(idPelanggan,iNoKtp,iNama,iTTL,iJk,iAlamat,iRtRw,iKelDesa,iKecamatan
                    ,iKotaKab,iProvinsi,iAgama,iStatus,iPekerjaan,iWargaNegara,iTelp,iHp,iEmail);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.i("InfoCRM","Edit Pelanggan");
                    Intent intent = new Intent(EditPelanggan.this,MainActivity.class);
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
