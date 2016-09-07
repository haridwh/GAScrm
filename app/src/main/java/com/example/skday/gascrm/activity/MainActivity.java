package com.example.skday.gascrm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.fragment.FragBlToken;
import com.example.skday.gascrm.fragment.FragDataPelanggan;
import com.example.skday.gascrm.fragment.FragDepo;
import com.example.skday.gascrm.fragment.FragFormDataPel;
import com.example.skday.gascrm.fragment.FragKontrak;
import com.example.skday.gascrm.fragment.FragLogToken;
import com.example.skday.gascrm.fragment.TopUpRequest;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;

    /*onCreate dijalankan saat activity pertama dibuat,
    akan menset layout yang digunakan dan juga
    menginisialisasi interface.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        /*saat menekan Floating Button maka akan mengeluarkan DialogFragment
        untuk melakukan Top Up Nominal Deposit*/
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager topUpManager = getSupportFragmentManager();
                TopUpRequest topUpRequest = new TopUpRequest();
                topUpRequest.show(topUpManager, "Top Up Nominal");
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Bundle extras = getIntent().getExtras(); //menerima pesan dari activitas lain
        /*mengecek apakah ada pesan atau tidak*/
        if (extras!=null){
            int menu = extras.getInt("menu"); //mengambil pesan dengan key "menu"
            refreshView(menu); //menjalankan method refreshView
        }else{
            /*jika tidak ada pesan maka akan menjalankan
            refreshView dengan posisi 0*/
            refreshView(0);
        }
    }

    /*onBackPressed dijalankan saat tombol back ditekan*/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*Mengecek apakan Navigation terbuka, jika iya makan akan
        menutup navigation*/
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            /*jika navigation tidak terbuka maka aplikasi akan menutup*/
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    /*Dijalankan saat menu pada Navigation dipilih, menu yg ditampilkan
    pada Navigation sesuai dengan menu yg terdapat pada file
    app>res>menu>activity_main_drawer.xml*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_depositAgent) {
            /*jika menu yg dipilih memiliki id nav_depositAgent,
            maka akan berpindah tampilan ke fragment FragDepo*/
            FragDepo fragment = new FragDepo();
            fab.setVisibility(View.VISIBLE); //Menampilkan Floating Button
            changeFragment(fragment,item.getTitle().toString());
        } else if (id == R.id.nav_blToken) {
            /*jika menu yg dipilih memiliki id nav_blToken,
            maka akan berpindah tamilan ke fragment FragBlToken*/
            FragBlToken fragment = new FragBlToken();
            fab.setVisibility(View.GONE); //Menghilangkan Floating Button
            changeFragment(fragment,item.getTitle().toString());
        }else if (id == R.id.nav_formDataPel) {
            /*jika menu yg dipilih memiliki id nav_formDataPel,
            maka akan berpindah tampilan ke fragment FragFormDatapel*/
            FragFormDataPel fragment = new FragFormDataPel();
            fab.setVisibility(View.GONE);
            changeFragment(fragment,item.getTitle().toString());
        }else if (id == R.id.nav_dataPel){
            /*jika menu yg dipilih memiliki id nav_dataPel,
            maka akan berpindah tampilan ke fragment FragDataPelanggan*/
            FragDataPelanggan fragment = new FragDataPelanggan();
            fab.setVisibility(View.GONE);
            changeFragment(fragment,item.getTitle().toString());
        }else if (id == R.id.nav_dataKontrak){
            /*jika menu yg dipilih memiliki id nav_dataKontrak,
            maka akan berpindah tampilan ke fragment FragKontrak*/
            FragKontrak fragment = new FragKontrak();
            fab.setVisibility(View.GONE);
            changeFragment(fragment,item.getTitle().toString());
        }else if (id == R.id.nav_logToken){
            /*jika menu yg dipilih memiliki id nav_logToken,
            maka akan berpindah tampilan ke fragment FragLogToken*/
            FragLogToken fragment = new FragLogToken();
            fab.setVisibility(View.GONE);
            changeFragment(fragment,item.getTitle().toString());
        }else if (id == R.id.nav_logout) {
            /*jika menu yg dipilih memiliki id nav_logout,
            maka akan berpindah ke tampilan login*/
            startActivity(new Intent(this, Login.class));
        }

        /*setelah memilih menu makan navigation akan ditutup*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*method ini digunakan untuk mengganti tampilan Fragment pada FrameLayout
    yang terdapat pada tampilan utama*/
    public void changeFragment(Fragment fragment, String title){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, fragment);
        ft.commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    /*Mengganti Fragment yang sedang ditampilkan sesuai dengan inputan
    posisi pada parameter*/
    public void refreshView(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new FragDepo();
                fab.setVisibility(View.VISIBLE);
                changeFragment(fragment,"Deposit Agent");
                break;
            case 1:
                fragment = new FragBlToken();
                fab.setVisibility(View.GONE);
                changeFragment(fragment,"Pemebelian Token");
                break;
            case 2:
                fragment = new FragFormDataPel();
                fab.setVisibility(View.GONE);
                changeFragment(fragment,"Tambah Pelanggan");
                break;
            case 3:
                fragment = new FragDataPelanggan();
                fab.setVisibility(View.GONE);
                changeFragment(fragment,"Data Pelanggan");
                break;
            case 4:
                fragment = new FragKontrak();
                fab.setVisibility(View.GONE);
                changeFragment(fragment,"Data Kontrak");
                break;
            case 5:
                fragment = new FragLogToken();
                fab.setVisibility(View.GONE);
                changeFragment(fragment,"Log Token");
                break;
            default:
                fragment = new FragDepo();
                fab.setVisibility(View.VISIBLE);
                changeFragment(fragment,"Deposit Agent");
                break;
        }
    }
}
