package com.example.skday.gascrm.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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

/**
 * Created by skday on 26/05/16.
 */
public class FragBlToken extends Fragment {

    private EditText noKtp,noMeter,volume;
    private Button noKontrak, btnSubmit;
    private Spinner nominal;

    /*Berfungsi seperti onCreate pada activity, karena pada Fragment sehinggan
    menggunakan onCreatView*/
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_fragbltoken,container,false);
        noKtp = (EditText) rootView.findViewById(R.id.eNoKtp);
        noMeter = (EditText) rootView.findViewById(R.id.eNoMeter);
        noMeter.setText(" ");
        noMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mendapatkan data No meter berdasarkan noKtp yang diinputkan
                // pada saat EditText noMeter ditekan
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://10.0.2.2/crm/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface nM = retrofit.create(ApiInterface.class);
                Call<ResponseBody> call = nM.noMeter(noKtp.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String echo = response.body().string();
                            noMeter.setText(echo);//MensetText EditText noMeter dengan hasil yang didapat
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("InfoCRM", t.toString());
                    }
                });
            }
        });
        volume = (EditText) rootView.findViewById(R.id.eVolume);
        noKontrak = (Button) rootView.findViewById(R.id.eNoKontrak);
        noKontrak.setText(" ");
        noKontrak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mendapatkan data No Kontrak berdasarkan noKtp yang diinputkan
                // pada saat Button noKontrak ditekan
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://cingkleung.com/crm/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface nM = retrofit.create(ApiInterface.class);
                Call<ResponseBody> call = nM.noKontrak(noKtp.getText().toString());
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String echo = response.body().string();
                            noKontrak.setText(echo);//MensetText button dengan hasil yang didapat
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("InfoCRM", t.toString());
                    }
                });
            }
        });

        nominal = (Spinner) rootView.findViewById(R.id.eNominal);
        nominal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            /*Menset volum berdasarkan nominal yang dipilih*/
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String iNominal = nominal.getSelectedItem().toString();
                if (iNominal.equals("20000")){
                    volume.setText("2.9");
                }else if (iNominal.equals("50000")){
                    volume.setText("11.4");
                }else if (iNominal.equals("100000")){
                    volume.setText("25.7");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        btnSubmit = (Button) rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String iNoKtp = noKtp.getText().toString();
                String iNoKontrak = noKontrak.getText().toString();
                String iNoMeter = noMeter.getText().toString();
                String iNominal = nominal.getSelectedItem().toString();
                String iVolume = volume.getText().toString();
                //Melakukan insert data pembelian token pada database
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://cingkleung.com/crm/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiInterface post = retrofit.create(ApiInterface.class);
                Call<ResponseBody> call = post.buyToken(iNoKtp, iNoKontrak,
                        iNoMeter, iNominal, iVolume);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            String res = response.body().string();
                            if (res.equals("Success")){
                                /*Jika berhasil maka akan berpindah tampilan ke Log Token, dan mengeluarkan
                                pemberitahuan "Pembelian token berhasil"*/
                                Intent menu = new Intent(getActivity(),MainActivity.class);
                                menu.putExtra("menu",5);
                                Toast.makeText(getActivity(),"Pembelian token berhasil",Toast.LENGTH_SHORT).show();
                                getActivity().startActivity(menu);
                            }else{
                                /*Jika gagal maka akan mengeluarkan pemberitahuan "Pembelian token gagal"*/
                                Toast.makeText(getActivity(),"Pemebelian token gagal ",Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.i("InfoCRM",t.toString()); //Log digunakan untuk menampilkan pesaan pada logcat, .i artinya log info
                    }
                });
            }
        });
        return rootView;
    }
}
