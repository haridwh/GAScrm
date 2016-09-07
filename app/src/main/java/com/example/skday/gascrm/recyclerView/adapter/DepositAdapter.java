package com.example.skday.gascrm.recyclerView.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.skday.gascrm.R;
import com.example.skday.gascrm.model.Deposit;
import java.util.List;

/**
 * Created by skday on 06/06/16.
 */

/*Custome adapter untuk data Pelanggan*/
public class DepositAdapter extends RecyclerView.Adapter<DepositAdapter.ViewHolder> {
    private List<Deposit> depositList;

    //digunakan untuk menginisialisasi view yg ada pada item_depo.xml
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView fullName, nominal, approval, date, status;

        public ViewHolder(View itemView) {
            super(itemView);
            fullName = (TextView) itemView.findViewById(R.id.fullname);
            nominal = (TextView) itemView.findViewById(R.id.nominal);
            approval = (TextView) itemView.findViewById(R.id.approval);
            date = (TextView) itemView.findViewById(R.id.date);
            status = (TextView) itemView.findViewById(R.id.status);
        }
    }

    //Constructor
    public DepositAdapter (List<Deposit> depositList){
        this.depositList = depositList;

    }

    //Menset item yang digunakn untuk RecyclerView
    @Override
    public DepositAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_depo, parent, false);
        return new ViewHolder(view);
    }

    //Menampilkan data yang didapatkan
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Deposit d = depositList.get(position);
        holder.fullName.setText(d.getFullname());
        holder.nominal.setText(d.getNominal()+"");
        switch (d.getApproval()){
            case '1' : holder.approval.setText("Not Approved Yet"); break;
            case '2' : holder.approval.setText("Administrator");break;
        }
        holder.date.setText(d.getTanggal());
        switch (d.getStatus()){
            case '1' : holder.status.setText("Requested");break;
            case '2' : holder.status.setText("Paid/Confirmed");break;
            case '3' : holder.status.setText("Approved");break;
            default : holder.status.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return depositList.size();
    }
}
