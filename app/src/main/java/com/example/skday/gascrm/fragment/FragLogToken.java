package com.example.skday.gascrm.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.api.ApiInterface;
import com.example.skday.gascrm.model.Token;
import com.example.skday.gascrm.recyclerView.adapter.TokenAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragLogToken extends Fragment {
    private RecyclerView recyclerView;

    /*Berfungsi seperti onCreate pada activity, karena pada Fragment sehinggan
    menggunakan onCreatView*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fraglogtoken,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listToken);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity()); //menset RecyclerView berbentuk Linear
        recyclerView.setLayoutManager(layoutManager);

        //Mengambil data Log token dari database
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/crm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface request = retrofit.create(ApiInterface.class);
        Call<List<Token>> call = request.getToken();
        call.enqueue(new Callback<List<Token>>() {
            @Override
            public void onResponse(Call<List<Token>> call, Response<List<Token>> response) {
                List<Token> listToken = response.body(); //Menyimpan data yang didapat ke dalam listToken
                //memasukkan data pada listTokens kedalam recyclerView, dan menampilkannya dengan menggunakan customeAdapter
                recyclerView.setAdapter(new TokenAdapter(listToken));
            }

            @Override
            public void onFailure(Call<List<Token>> call, Throwable t) {
                Log.e("InfoCRM",t.toString()); //Log digunakan untuk menampilkan pesaan pada logcat/android monitor, .e artinya log error
            }
        });
        return rootView;
    }
}
