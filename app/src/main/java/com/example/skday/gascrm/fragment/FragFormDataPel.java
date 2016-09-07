package com.example.skday.gascrm.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.activity.MainActivity;
import com.example.skday.gascrm.api.ApiInterface;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragFormDataPel extends Fragment {

    private EditText
        noKtp,
        nama,
        tTL,
        alamat,
        rtRw,
        kelDes,
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

    /*Berfungsi seperti onCreate pada activity, karena pada Fragment sehinggan
    menggunakan onCreatView*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragformdatapel,container,false);
        setHasOptionsMenu(true);
        noKtp = (EditText) rootView.findViewById(R.id.eNoKtp);
        nama = (EditText) rootView.findViewById(R.id.eNama);
        tTL = (EditText) rootView.findViewById(R.id.eTTL);
        alamat = (EditText) rootView.findViewById(R.id.eAlamat);
        rtRw = (EditText) rootView.findViewById(R.id.eRtRw);
        kelDes = (EditText) rootView.findViewById(R.id.eKelDes);
        kecamatan = (EditText) rootView.findViewById(R.id.eKecamatan);
        agama = (EditText) rootView.findViewById(R.id.eAgama);
        pekerjaan = (EditText) rootView.findViewById(R.id.ePekerjaan);
        wargaNegara = (EditText) rootView.findViewById(R.id.eWargaNegara);
        noTelp = (EditText) rootView.findViewById(R.id.eNoTelp);
        noHp = (EditText) rootView.findViewById(R.id.eNoHp);
        email = (EditText) rootView.findViewById(R.id.eEmail);

        jenisKelamin = (Spinner) rootView.findViewById(R.id.eJK);
        kotaKabupaten = (Spinner) rootView.findViewById(R.id.ektKab);
        provinsi = (Spinner) rootView.findViewById(R.id.eProv);
        status = (Spinner) rootView.findViewById(R.id.eStatus);

        return rootView;
    }

    /*Digunakan untuk membuat option menu pada toolbar*/
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_from_data_pel,menu);
    }


    /*Dijalan saat menu pada toolbar di klik*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mengecek apakah id menu yg di klik sama dengan add_customer
        if (item.getItemId()==R.id.add_customer){
            String iNoKtp = noKtp.getText().toString();
            final String iNama = nama.getText().toString();
            String iTTL = tTL.getText().toString();
            String iJk = jenisKelamin.getSelectedItem().toString();
            String iAlamat = alamat.getText().toString();
            String iRtRw = rtRw.getText().toString();
            String iKelDesa = kelDes.getText().toString();
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

            //Menginsertkan data pelanggan baru pada database
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2/crm/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface post = retrofit.create(ApiInterface.class);
            Call<ResponseBody> call = post.pelangganBaru(iNoKtp,iNama,iTTL,iJk,iAlamat,iRtRw,iKelDesa,iKecamatan
                ,iKotaKab,iProvinsi,iAgama,iStatus,iPekerjaan,iWargaNegara,iTelp,iHp,iEmail);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Intent menu = new Intent(getActivity(),MainActivity.class);
                    menu.putExtra("menu",3);
                    try {
                        String res = response.body().string();
                        //mengecek apakah respon dari webservice sama dengan success
                        if (res.equals("Success")){
                            //jika ya, akan memunculkan pemberitahuan "namaPelanggan berhasil ditambahkan"
                            Toast.makeText(getActivity(),iNama+" berhasil ditambahkan",Toast.LENGTH_SHORT).show();
                        }else{
                            //jika tidak, akan memunculkan pemberitahuan "Gagal menambahkan namaPelanggan"
                            Toast.makeText(getActivity(),"Gagal menambahkan "+iNama,Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    getActivity().startActivity(menu); //berpindah activity
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("InfoCRM", t.toString()); //Log digunakan untuk menampilkan pesaan pada logcat, .e artinya log error
                }
            });
        }
        return false;
    }
}
