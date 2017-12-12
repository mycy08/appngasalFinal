package tercyduk.appngasal.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

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
    String srch="";
    ArrayList<String> futsal_name = new ArrayList<String>();
    ArrayList<String> price = new ArrayList<String>();
    ArrayList<LapanganFutsal>lapang;
    RecyclerView rv;
    private Context context;
    AdapterRViewLapangan adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapangan_rview);
        this.setTitle("Lapangan");
        initViews();
        init();

    }

    private void initViews(){
        rv = (RecyclerView)findViewById(R.id.lapang_rv);
        rv.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(layoutManager);

    }
    private void init(){

        Intent inten = getIntent();
        String token = inten.getStringExtra("token");
        Toast.makeText(getApplicationContext(),token.toString(), Toast.LENGTH_SHORT).show();

        LapanganFutsalService lapanganFutsalService = APIClient.getClient().create(LapanganFutsalService.class);
        retrofit2.Call call = lapanganFutsalService.getSecret("Bearer "+token.toString());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                ArrayList<LapanganFutsal> lapanganFutsals =  (ArrayList<LapanganFutsal>) response.body();
                adapter = new AdapterRViewLapangan(lapanganFutsals);
                rv.setAdapter(adapter);
//                adapter.setLapang(lapanganFutsals);
//                rv.setAdapter(adapter);
            }



            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        context = getApplicationContext();
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






