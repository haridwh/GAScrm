package com.example.skday.gascrm.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.skday.gascrm.model.User;
import com.example.skday.gascrm.recyclerView.adapter.DepositAdapter;
import com.example.skday.gascrm.api.ApiInterface;
import com.example.skday.gascrm.model.Deposit;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by skday on 26/05/16.
 */
public class FragDepo extends Fragment {

    private RecyclerView recyclerView;

    /*Berfungsi seperti onCreate pada activity, karena pada Fragment sehinggan
    menggunakan onCreatView*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_fragdepo,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listDeposit);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); //menset RecyclerView berbentuk Linear
        recyclerView.setLayoutManager(layoutManager);
        /*Digunakan untuk mengambil data user yang telah disimpan
        pada saat di Activity Login*/
        SharedPreferences mPrefs = getActivity().getSharedPreferences("USER", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("User", "");
        User user = gson.fromJson(json, User.class);

        /*Mengambil data Deposit pada database sesuai dengan id user yang sedang login*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/crm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);
        Call<List<Deposit>> call = request.getDepo(user.id);
        call.enqueue(new Callback<List<Deposit>>() {
            @Override
            public void onResponse(Call<List<Deposit>> call, Response<List<Deposit>> response) {
                List<Deposit> listDepo = response.body(); //menyimpan data yang didapat pada listDepo
                if (listDepo != null){
                    //memasukkan data pada listDepo kedalam recyclerView, dan menampilkannya dengan menggunakan customeAdapter
                    recyclerView.setAdapter(new DepositAdapter(listDepo));
                }
            }

            @Override
            public void onFailure(Call<List<Deposit>> call, Throwable t) {
                Log.e("InfoCRM", t.toString()); //Log digunakan untuk menampilkan pesaan pada logcat/android monitor, .e artinya log error
            }
        });

        return rootView;
    }
}
