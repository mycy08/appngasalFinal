package tercyduk.appngasal.apihelper.Adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tercyduk.appngasal.Activity.DetailLapangan;
import tercyduk.appngasal.R;
import tercyduk.appngasal.coresmodel.LapanganFutsal;

/**
 * Created by User on 12/9/2017.
 */

public class AdapterRViewLapangan extends RecyclerView.Adapter<AdapterRViewLapangan.ViewHolder> {
    private ArrayList<LapanganFutsal> lapang;

    public AdapterRViewLapangan(ArrayList<LapanganFutsal> lapang) {
        this.lapang = lapang;
    }

    @Override
    public AdapterRViewLapangan.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user_grid, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRViewLapangan.ViewHolder holder, int i) {
        final LapanganFutsal _lapang = this.lapang.get(i);
        holder.email.setText(String.valueOf(lapang.get(i).getPrice()));
        holder.name.setText(lapang.get(i).getFutsal_name());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(v.getContext(), DetailLapangan.class);
                _intent.putExtra("Lapangan", _lapang);
                v.getContext().startActivity(_intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lapang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name, email;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.item_user_grid_name);
            email = (TextView) itemView.findViewById(R.id.item_user_grid_email);
        }
    }

}
