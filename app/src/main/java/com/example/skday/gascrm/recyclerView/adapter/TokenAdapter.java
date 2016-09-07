package com.example.skday.gascrm.recyclerView.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.model.Token;

import java.util.List;

/**
 * Created by skday on 14/06/16.
 */

/*Custome adapter untuk data Pelanggan*/
public class TokenAdapter extends RecyclerView.Adapter<TokenAdapter.ViewHolder> {
    private List<Token> tokenList;

    //digunakan untuk menginisialisasi view yg ada pada item_token.xml
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView noKtp, noMeter, nominal, volume;


        public ViewHolder(View itemView) {
            super(itemView);
            noKtp = (TextView) itemView.findViewById(R.id.tNoKtp);
            noMeter = (TextView) itemView.findViewById(R.id.tNoMeter);
            nominal = (TextView) itemView.findViewById(R.id.tNominal);
            volume = (TextView) itemView.findViewById(R.id.tVolume);
        }
    }

    public TokenAdapter (List<Token> tokenList){
        this.tokenList = tokenList;
    }

    //Menset item yang digunakn untuk RecyclerView
    @Override
    public TokenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_token,parent,false);
        return new ViewHolder(view);
    }

    //Menampilkan data yang didapatkan
    @Override
    public void onBindViewHolder(TokenAdapter.ViewHolder holder, int position) {
        Token t = tokenList.get(position);
        holder.noKtp.setText(t.getNoKtp());
        holder.noMeter.setText(t.getNoMeter());
        holder.nominal.setText(t.getNominal());
        holder.volume.setText(t.getVolume());
    }

    @Override
    public int getItemCount() {
        return tokenList.size();
    }
}
