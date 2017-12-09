package tercyduk.appngasal.apihelper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tercyduk.appngasal.Activity.DetailLapangan;
import tercyduk.appngasal.R;
import tercyduk.appngasal.coresmodel.LapanganFutsal;
import tercyduk.appngasal.coresmodel.LapanganImage;

/**
 * Created by User on 12/1/2017.
 */

public class AdapterLapangan extends ArrayAdapter<LapanganFutsal> {
    private List<LapanganFutsal> lapanganFutsals;
    private Context context;

    public AdapterLapangan(List<LapanganFutsal> lapanganFutsals ,Context context ){
        super(context, R.layout.item_futsal_grid, lapanganFutsals);
        this.context = context;
        this.lapanganFutsals = lapanganFutsals;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.item_futsal_grid,parent,false);

        LapanganFutsal lapanganFutsal = lapanganFutsals.get(position);
        TextView textViewFutsalName = (TextView) convertView.findViewById(R.id.item_lapang_grid_name);
        textViewFutsalName.setText(lapanganFutsal.getFutsal_name());
        TextView textViewPrice = (TextView) convertView.findViewById(R.id.item_lapang_grid_kecamatan);
        textViewPrice.setText(String.valueOf(lapanganFutsal.getPrice()));

        return  convertView;


    }

}
