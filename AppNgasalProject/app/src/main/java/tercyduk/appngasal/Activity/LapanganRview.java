package tercyduk.appngasal.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tercyduk.appngasal.R;
import tercyduk.appngasal.apihelper.APIClient;
import tercyduk.appngasal.apihelper.Adapter.AdapterRViewLapangan;
import tercyduk.appngasal.apihelper.LapanganFutsalService;
import tercyduk.appngasal.coresmodel.LapanganFutsal;
import tercyduk.appngasal.modules.auth.user.Login;

public class LapanganRview extends AppCompatActivity  {

    List<LapanganFutsal> lapang;
    RecyclerView rv;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapangan_rview);
        rv = (RecyclerView) findViewById(R.id.lapang_rv);
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        lapang = new ArrayList<>();
        Intent inten = getIntent();
        String token = inten.getStringExtra("token");

        LapanganFutsalService lapanganFutsalService = APIClient.getClient().create(LapanganFutsalService.class);

        Call<List<LapanganFutsal>> call=   lapanganFutsalService.getSecret("Bearer "+token);
        call.enqueue(new Callback<List<LapanganFutsal>>() {
            @Override
            public void onResponse(Call<List<LapanganFutsal>> call, Response<List<LapanganFutsal>> response) {

                final List<LapanganFutsal> list = response.body();
                LapanganFutsal lapanganFutsal = null;
                for (int i =0 ;i<list.size();i++){
                    lapanganFutsal = new LapanganFutsal();
                    final String id = list.get(i).getId();
                    String name = list.get(i).getFutsal_name();
                    String districts = list.get(i).getDistricts();

                    String image = list.get(i).getPhoto_url();
                    double price = list.get(i).getPrice();
                    lapanganFutsal.setPrice(price);
                    lapanganFutsal.setDistricts(districts);
                    lapanganFutsal.setId(id);
                    lapanganFutsal.setFutsal_name(name);
                    lapanganFutsal.setPhoto_url(image);
                    lapang.add(lapanganFutsal);


                }


                AdapterRViewLapangan recyclerAdapter = new AdapterRViewLapangan(lapang,ImageLoader.getInstance());
                rv.setHasFixedSize(true);
                //RecyclerView.LayoutManager recyce = new GridLayoutManager(LapanganRview.this,2);
                RecyclerView.LayoutManager recyce = new LinearLayoutManager(LapanganRview.this);
                //rv.addItemDecoration(new GridSpacingdecoration(2, dpToPx(10), true));
                rv.setLayoutManager(recyce);
                //rv.setItemAnimator( new DefaultItemAnimator());
                rv.setAdapter(recyclerAdapter);








            }

            @Override
            public void onFailure(Call<List<LapanganFutsal>> call, Throwable t) {

            }
        });
    }
    public void startTwo(View v){
        startActivity((new Intent(this, EditProfile.class)));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.home)
        {
            Intent intent = new Intent(LapanganRview.this, LapanganRview.class);
            startActivity(intent);
        }
        else if(id==R.id.editpro){
            doChangeActivity(getApplicationContext(),EditProfile.class);
        }
        else if(id == R.id.topup)
        {
        }
        else if (id == R.id.report)
        {
        }
        else if(id == R.id.Wallet)
        {
        }
        else if(id== R.id.logout)
        {
            doChangeActivity(getApplicationContext(), Login.class);
        }
        return true;
    }
    public static void doChangeActivity (Context context, Class destination) {
        Intent _intent = new Intent(context, destination);
        _intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(_intent);
    }
}






