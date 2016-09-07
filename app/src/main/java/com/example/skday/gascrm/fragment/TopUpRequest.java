package com.example.skday.gascrm.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.activity.MainActivity;
import com.example.skday.gascrm.api.ApiInterface;
import com.example.skday.gascrm.model.User;
import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopUpRequest extends DialogFragment {

    Spinner spn;

    /*Berfungsi seperti onCreate pada activity, karena pada Fragment sehinggan
    menggunakan onCreatView*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_top_up_request,null);
        Button btn = (Button) v.findViewById(R.id.btnTopUpRequest);
        //Pilihan pada Spinner/DropDown diset pada activity_top_up_request.xml
        spn = (Spinner) v.findViewById(R.id.sTopUpNominal);
        getDialog().setTitle("Top Up Nominal");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Digunakan untuk mengambil data user yang telah disimpan
                pada saat di Activity Login*/
                SharedPreferences mPrefs = getActivity().getSharedPreferences("USER",Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = mPrefs.getString("User", "");
                User user = gson.fromJson(json, User.class);

                String nominal =  spn.getSelectedItem().toString();//Mengambil pilihan user pada spinner
                //Melakukan insert data topuprequest pada database
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2/crm/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface post = retrofit.create(ApiInterface.class);
                Call<ResponseBody> call = post.topUpRequest(nominal, user.id);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.i("InfoCRM",response.body().toString()); //Log digunakan untuk menampilkan pesaan pada logcat, .i artinya log info

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("InfoCRM", t.toString()); //Log digunakan untuk menampilkan pesaan pada logcat, .e artinya log error
                    }
                });
                dismiss(); //dissmiss digunakan untuk menghilangkan dialog fragment dari tampilan

                /*digunakan untuk berpindah dari satu activity ke activity lain,
                disini menggunakan getActivity karena berada dalam fragment.
                getActivity pada fragment ini akan menghasilkan MainActivit,
                sehingga perogram akan berpindah dari Main Activity ke MainActivity,
                yg bertujuan untuk merefresh data*/
                Intent menu = new Intent(getActivity(),MainActivity.class);
                menu.putExtra("menu",0); //.putExtra digunakan untuk mengirim pesan melalui intent ke activity tujuan, pada kasus ini pesan berupa 0 dengan key menu
                getActivity().startActivity(menu);
            }
        });
        return v;
    }
}
