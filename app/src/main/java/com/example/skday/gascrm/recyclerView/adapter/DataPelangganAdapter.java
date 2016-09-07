package com.example.skday.gascrm.recyclerView.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.activity.EditPelanggan;
import com.example.skday.gascrm.activity.KontrakBaru;
import com.example.skday.gascrm.activity.MainActivity;
import com.example.skday.gascrm.api.ApiInterface;
import com.example.skday.gascrm.model.Pelanggan;


import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by skday on 13/06/16.
 */

/*Custome adapter untuk data Pelanggan*/
public class DataPelangganAdapter extends RecyclerView.Adapter<DataPelangganAdapter.ViewHolder> {
    private List<Pelanggan> pelangganList;
    private Context context;

    //digunakan untuk menginisialisasi view yg ada pada item_data_pelanggan.xml
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView idPelanggan, noKtp, nama, kotaKabupaten;
        ImageView pMenu;

        public ViewHolder(View itemView) {
            super(itemView);
            idPelanggan = (TextView) itemView.findViewById(R.id.iIdPelanggan);
            noKtp = (TextView) itemView.findViewById(R.id.iNoKtp);
            nama = (TextView) itemView.findViewById(R.id.iNama);
            kotaKabupaten = (TextView) itemView.findViewById(R.id.iKotaKabupaten);
            pMenu = (ImageView) itemView.findViewById(R.id.pMenu);
        }
    }

    //Constructor
    public DataPelangganAdapter (Context context, List<Pelanggan> pelangganList){
        this.pelangganList = pelangganList;
        this.context = context;//activity yg menggunakan adapter ini
    }

    //Menset item yang digunakan untuk RecyclerView
    @Override
    public DataPelangganAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_data_pelanggan,parent,false);
        return new ViewHolder(view);
    }

    //Menampilkan data yang didapatkan
    @Override
    public void onBindViewHolder(final DataPelangganAdapter.ViewHolder holder, final int position) {
        Pelanggan p = pelangganList.get(position);
        holder.idPelanggan.setText(p.getIdPelanggan());
        holder.noKtp.setText(p.getNoKtp());
        holder.nama.setText(p.getNama());
        holder.kotaKabupaten.setText(p.getKotaKab());

        //saat tombol titik tiga diklik akan memunculkan menu
        holder.pMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showMenu(holder.pMenu,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pelangganList.size();
    }

    //Memunculkan menu
    public void showMenu (View view, int position){
        PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.pop_menu, popupMenu.getMenu()); //menset item menu yang ada dengan menggunakan Menu pop_menu
        popupMenu.setOnMenuItemClickListener(new MyMenuItemClickListener(position));
        popupMenu.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener{
        private int position;
        MyMenuItemClickListener(int position){
            this.position = position;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Intent intent;
            switch (item.getItemId()){
                //mengecek apakah menu dengan id buatKontrak diklik
                case R.id.buatKontrak:
                    //berpindah activity ke activity form untuk kontrak baru
                    intent = new Intent(context, KontrakBaru.class);
                    intent.putExtra("idPelanggan",pelangganList.get(position).getIdPelanggan());
                    context.startActivity(intent);
                    return true;
                //mengecek apakah menu dengan id editData diklik
                case R.id.editData:
                    //berpindah activity ke activity form untuk mengedit data
                    intent = new Intent(context, EditPelanggan.class);
                    intent.putExtra("Pelanggan",pelangganList.get(position));
                    context.startActivity(intent);
                    return true;
                //mengecek apakah menu dengan id hapusData diklik
                case R.id.hapusData:
                    //menghapus data dari database sesuai dengan id pelanggan yg diklik menunya
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2/crm/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiInterface delete = retrofit.create(ApiInterface.class);
                    Call<ResponseBody> call = delete.hapusPelanggan(pelangganList.get(position).getIdPelanggan());
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String res = response.body().string();
                                if (res.equals("Success")){
                                    /*jika berhasil akan merefresh tampilan data pelanggan, dan menampilkan
                                    pemberitahuan "Pelanggan berhasil dihapus"*/
                                    Intent menu = new Intent(context,MainActivity.class);
                                    menu.putExtra("menu",3);
                                    Toast.makeText(context,"Pelanggan berhasil dihapus",Toast.LENGTH_SHORT).show();
                                    context.startActivity(menu);
                                }else if (res.equals("Failure")){
                                    //jika gagal akan menampilkan pemberitahuan "Pelanggan gagal dihapus
                                    Toast.makeText(context,"Pelanggan gagal dihapus",Toast.LENGTH_SHORT).show();
                                }else if (res.equals("Kontrak")){
                                    //jika pelanggan memiliki kontrak akan menampilkan pemberitahuan "Gagal dihapus, Pelanggan memiliki kontrak"
                                    Toast.makeText(context,"Gagal dihapus, Pelanggan memiliki kontrak",Toast.LENGTH_SHORT).show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.e("InfoCRM", t.toString());
                        }
                    });
                    return true;
                default:
            }
            return false;
        }
    }
}
