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
import tercyduk.appngasal.apihelper.APIClient;
import tercyduk.appngasal.apihelper.LapanganFutsalService;
import tercyduk.appngasal.coresmodel.LapanganFutsal;
import tercyduk.appngasal.coresmodel.User;

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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_futsal_grid, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRViewLapangan.ViewHolder holder, int i) {
        final LapanganFutsal _lapang = this.lapang.get(i);
        holder.lapangName.setText(lapang.get(i).getFutsal_name());
        holder.lapangKecamatan.setText(lapang.get(i).getDistricts());
        holder.lapangPrice.setText(String.valueOf(lapang.get(i).getPrice()));
        ///TAROK DLU GAMBAR NYA DI API TERUS TEST PANGGIL NYA GIMANA
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
        private ImageView image;//TAMBAH DI SINI INISIALISAI IMAGEVIEW
        private TextView lapangName,lapangKecamatan,lapangPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            lapangName = (TextView) itemView.findViewById(R.id.lapang_name);
            lapangKecamatan = (TextView) itemView.findViewById(R.id.lapang_kecamatan);
            lapangPrice = (TextView) itemView.findViewById(R.id.lapang_price);
        }
    }

}
