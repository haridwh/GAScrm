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
import com.example.skday.gascrm.activity.MainActivity;
import com.example.skday.gascrm.api.ApiInterface;
import com.example.skday.gascrm.model.Kontrak;

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
public class KontrakAdapter extends RecyclerView.Adapter<KontrakAdapter.ViewHolder>{
    private List<Kontrak> kontrakList;
    private Context context;

    //digunakan untuk menginisialisasi view yg ada pada item_kontrak.xml
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView idPelanggan, noMeter, tarifGroup, wilayah, noKontrak, mulaiKontrak;
        ImageView kMenu;

        public ViewHolder(View itemView) {
            super(itemView);
            idPelanggan = (TextView) itemView.findViewById(R.id.kIdPelanggan);
            noMeter = (TextView) itemView.findViewById(R.id.kNoMeter);
            tarifGroup = (TextView) itemView.findViewById(R.id.kTarifGroup);
            wilayah = (TextView) itemView.findViewById(R.id.kWilayah);
            noKontrak = (TextView) itemView.findViewById(R.id.kNoKontrak);
            mulaiKontrak = (TextView) itemView.findViewById(R.id.kMulaiKontrak);
            kMenu = (ImageView) itemView.findViewById(R.id.kMenu);
        }
    }

    //Constructor
    public KontrakAdapter (Context context, List<Kontrak> kontrakList){
        this.kontrakList = kontrakList;
        this.context = context;//activity yg menggunakan adapter ini
    }

    //Menset item yang digunakn untuk RecyclerView
    @Override
    public KontrakAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kontrak,parent,false);
        return new ViewHolder(view);
    }

    //Menampilkan data yang didapatkan
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Kontrak k = kontrakList.get(position);
        holder.idPelanggan.setText(k.getIdPelanggan());
        holder.noMeter.setText(k.getNoMeter());
        holder.tarifGroup.setText(k.getTarifGroup());
        holder.wilayah.setText(k.getWilayah());
        holder.noKontrak.setText(k.getIdKontrak());
        holder.mulaiKontrak.setText(k.getTglKontrak());
        //saat tombol titik tiga diklik akan memunculkan menu
        holder.kMenu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showMenu(holder.kMenu,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kontrakList.size();
    }

    //Memunculkan menu
    public void showMenu (View view, int position){
        PopupMenu popupMenu = new PopupMenu(context, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.pop_menu_kontrak, popupMenu.getMenu());
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
            switch (item.getItemId()){
                //mengecek apakah menu dengan id berhenti diklik
                case R.id.berhenti:
                    //akan menghapus data kontrak berdasarkan id pelanggan yang dipilih
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://10.0.2.2/crm/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    ApiInterface berhenti = retrofit.create(ApiInterface.class);
                    Call<ResponseBody> call = berhenti.berhenti(kontrakList.get(position).getIdPelanggan());
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {
                                String res = response.body().string();
                                if (res.equals("Success")){
                                    /*jika berhasil akan merefresh tampilan data kontrak
                                    dan akan menampilkan pemberitahuan "Berhasil berhenti berlangganan"*/
                                    Intent intent = new Intent(context, MainActivity.class);
                                    intent.putExtra("menu",4);
                                    Toast.makeText(context,"Berhasil berhenti berlangganan",Toast.LENGTH_SHORT).show();
                                    context.startActivity(intent);
                                }else {
                                    //jika gagal akan menampilkan pemberitahuan "Gagal"
                                    Toast.makeText(context,"Gagal",Toast.LENGTH_SHORT).show();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.i("InfoCRM",t.toString());
                        }
                    });
                    return true;
                default:
            }
            return false;
        }
    }
}
