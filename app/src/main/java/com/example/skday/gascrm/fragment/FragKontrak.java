package com.example.skday.gascrm.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.api.ApiInterface;
import com.example.skday.gascrm.model.Kontrak;
import com.example.skday.gascrm.recyclerView.adapter.KontrakAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragKontrak extends Fragment {
    private RecyclerView recyclerView;

    /*Berfungsi seperti onCreate pada activity, karena pada Fragment sehinggan
    menggunakan onCreatView*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragkontrak,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listKontrak);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); //menset RecyclerView berbentuk Linear
        recyclerView.setLayoutManager(layoutManager);

        //Mengambil data kontrak dari database
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/crm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);
        Call<List<Kontrak>> call = request.getKontrak();
        call.enqueue(new Callback<List<Kontrak>>() {
            @Override
            public void onResponse(Call<List<Kontrak>> call, Response<List<Kontrak>> response) {
                List<Kontrak> listKontrak = response.body(); //Menyimpan data yang didapat ke dalam listKontrak
                //memasukkan data pada listKontrak kedalam recyclerView, dan menampilkannya dengan menggunakan customeAdapter
                recyclerView.setAdapter(new KontrakAdapter(getActivity(),listKontrak));
            }

            @Override
            public void onFailure(Call<List<Kontrak>> call, Throwable t) {
                Log.e("InfoCRM", t.toString()); //Log digunakan untuk menampilkan pesaan pada logcat/android monitor, .e artinya log error
            }
        });
        return rootView;
    }
}
