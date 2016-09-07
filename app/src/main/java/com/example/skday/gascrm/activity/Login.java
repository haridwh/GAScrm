package com.example.skday.gascrm.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.api.ApiInterface;
import com.example.skday.gascrm.model.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    EditText username,
        password;
    Button login;

    /*onCreate dijalankan saat activity pertama dibuat,
    akan menset layout yang digunakan dan juga
    menginisialisasi interface.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.eUsername);
        password = (EditText) findViewById(R.id.ePassword);
        login = (Button) findViewById(R.id.btnLogin);
        /*saat tombol login ditekan maka akan menjalankan setOnClickListener*/
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final String iUsername = username.getText().toString();//Mengambil string yang diinputkan oleh user pada EditText username
                final String iPassword = password.getText().toString();//Mengambil string yang diinputkan oleh user pada EditText password
                /*mengecek apakah form username dan password kosong atau tidak
                jika kososng akan mengeluarkan pemberitahuan "Username atau Password tidak boleh kosong"*/
                if (iUsername.equalsIgnoreCase("") || iPassword.equalsIgnoreCase("")){
                    Toast.makeText(getBaseContext(),"Username atau Password tidak boleh kosong",Toast.LENGTH_LONG).show();
                }else{
                    /*jika tidak kosong maka akan mengecek pada database apakah terdapat username sesuai
                    dengan yang diinputkan oleh user*/
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2/crm/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiInterface post = retrofit.create(ApiInterface.class);
                    Call<User> call = post.login(iUsername);
                    Log.i("InfoCRM","POST");
                    call.enqueue(new Callback<User>() {
                        /*onRespon dijalankan jika berhasil terhubung dengan API*/
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Log.i("InfoCRM","Response");
                            /*mengecek apakah terdapat username yang diinputkan atau tidak*/
                            if (response.body()!=null){
                                /*jika ada maka akan mengecek apakah username sama dengan password yang
                                diinputkan atau tidak, hal ini dilakukan karena pada database tidak ada
                                row password maka diasumsikan password sama dengan username, dan jika sama
                                akan berpindah ke halaman utama aplikasi*/
                                if (iUsername.equals(iPassword)){
                                    User user = response.body();
                                    Log.i("InfoCRM", user.id);
                                    /*Digunakan untuk menyimpan data User yang didapat dari
                                    database untuk digunakan pada proses lain*/
                                    SharedPreferences mPrefs = getSharedPreferences("USER", MODE_PRIVATE);
                                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                                    Gson gson = new Gson();
                                    String json = gson.toJson(user);
                                    prefsEditor.putString("User", json);
                                    prefsEditor.commit();
                                    //Untuk berpindah activity
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                }else{
                                    /*jika username tidak sama dengan password maka akan mengeluarkan
                                    pemberitahuan "Username atau Password salah"*/
                                    Toast.makeText(getBaseContext(),"Username atau Password salah",Toast.LENGTH_LONG).show();
                                }
                            }else{
                                /*jika username tidak terdapat di database maka akan mengeluarkan
                                pemberitahuan "Username atau Password salah"*/
                                Toast.makeText(getBaseContext(),"Username atau Password salah",Toast.LENGTH_LONG).show();
                            }
                        }
                        /*onFailure dijalankan jika gagal terhubung dengan API*/
                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.e("InfoCRM", t.toString()); //akan mengeluarkan pemberitahuan pada logcat
                        }
                    });
                }
            }
        });
    }

    /*onBackPressed dijalankan saat tombol back ditekan,
    dan aplikasi akan tertutup*/
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
