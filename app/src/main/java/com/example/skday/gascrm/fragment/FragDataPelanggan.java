package com.example.skday.gascrm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.api.ApiInterface;
import com.example.skday.gascrm.model.Pelanggan;
import com.example.skday.gascrm.recyclerView.adapter.DataPelangganAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragDataPelanggan extends Fragment {
    private RecyclerView recyclerView;

    /*Berfungsi seperti onCreate pada activity, karena pada Fragment sehinggan
   menggunakan onCreatView*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragdatapelanggan,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listPelanggan);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); //menset RecyclerView berbentuk Linear
        recyclerView.setLayoutManager(layoutManager);

        //Mengambil data pelanggan dari database
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/crm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);
        Call<List<Pelanggan>> call = request.getPelanggan();
        call.enqueue(new Callback<List<Pelanggan>>() {
            @Override
            public void onResponse(Call<List<Pelanggan>> call, Response<List<Pelanggan>> response) {
                List<Pelanggan> listPelanggan = response.body(); //menyimpan data yang didapat pada listPelanggan
                //memasukkan data pada listPelanggan kedalam recyclerView, dan menampilkannya dengan menggunakan customeAdapter
                recyclerView.setAdapter(new DataPelangganAdapter(getActivity(),listPelanggan));
            }

            @Override
            public void onFailure(Call<List<Pelanggan>> call, Throwable t) {
                Log.e("InfoCRM", t.toString()); //Log digunakan untuk menampilkan pesaan pada logcat/android monitor, .e artinya log error
            }
        });
        return rootView;
    }
}
