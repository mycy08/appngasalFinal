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
import com.nostra13.universalimageloader.core.ImageLoader;
/**
 * Created by User on 12/9/2017.
 */

        public class AdapterRViewLapangan extends RecyclerView.Adapter<AdapterRViewLapangan.ViewHolder> {
            List<LapanganFutsal> lapang;
            ImageLoader imageLoader;

        public AdapterRViewLapangan(List<LapanganFutsal> lapang, ImageLoader imageLoader) {
                this.lapang = lapang;
                this.imageLoader=imageLoader;
         }

    @Override
    public AdapterRViewLapangan.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_futsal_grid, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterRViewLapangan.ViewHolder holder, int position) {
        final LapanganFutsal _lapang = lapang.get(position);
        final String idf = _lapang.getId();
        final String token =_lapang.getToken();
        holder.lapangKecamatan.setText(_lapang.getDistricts());
        holder.lapangName.setText(_lapang.getFutsal_name());
        //holder.lapangKecamatan.setText(_lapang.getDistricts());
        holder.lapangPrice.setText("RP "+(_lapang.getPrice()).intValue());

        final String image1 = _lapang.getPhoto_url();
        imageLoader.displayImage(image1, holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent _intent = new Intent(v.getContext(), DetailLapangan.class);
                _intent.putExtra("id",idf);
                _intent.putExtra("LapanganFutsal",_lapang);
                _intent.putExtra("token",token);
                v.getContext().startActivity(_intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lapang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;//TAMBAH DI SINI INISIALISAI IMAGEVIEW
        public TextView lapangName,lapangKecamatan,lapangPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            lapangName = (TextView) itemView.findViewById(R.id.lapang_name);
            lapangKecamatan = (TextView) itemView.findViewById(R.id.lapang_kecamatan);
            lapangPrice = (TextView) itemView.findViewById(R.id.lapang_price);
            image =(ImageView) itemView.findViewById(R.id.lapang_photo);
        }
    }

}
