package tercyduk.appngasal.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import tercyduk.appngasal.R;
import tercyduk.appngasal.coresmodel.LapanganFutsal;
import tercyduk.appngasal.modules.auth.user.Login;

public class DetailLapangan extends AppCompatActivity {
    private LapanganFutsal _lapang;
    ImageLoader imageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _lapang =(LapanganFutsal)getIntent().getExtras().get("LapanganFutsal");
        setContentView(R.layout.activity_detail_lapangan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent _intent = getIntent();
        String id = _intent.getStringExtra("id");
//        String images = _intent.getStringExtra("photo_url");
//        String futsalname = _intent.getStringExtra("futsal_name");
        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();

        TextView namalapang =(TextView)findViewById(R.id.detail_lapang_name);
        namalapang.setText(_lapang.getFutsal_name());
        TextView alamat = (TextView)findViewById(R.id.detail_lapang_alamat);
        alamat.setText(_lapang.getAddress());
        TextView kecamatan = (TextView)findViewById(R.id.detail_lapang_kecamatan);
        kecamatan.setText(_lapang.getDistricts());
        TextView deskripsi = (TextView)findViewById(R.id.detail_lapang_deskripsi);
        deskripsi.setText(_lapang.getDescription());
        TextView price = (TextView)findViewById(R.id.detail_lapang_price);
        price.setText("RP " +_lapang.getPrice().intValue());
//        ImageView image = (ImageView)findViewById(R.id.detail_lapang_photos);
//        imageLoader.displayImage(images, image);
    }


    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
