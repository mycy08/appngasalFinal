package tercyduk.appngasal.modules.auth.user;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import tercyduk.appngasal.R;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tercyduk.appngasal.apihelper.APIClient;
import tercyduk.appngasal.apihelper.Adapter.AdapterLapangan;
import tercyduk.appngasal.apihelper.LapanganFutsalService;
import tercyduk.appngasal.coresmodel.LapanganFutsal;

public class HomePageLView extends Activity {
    ArrayList<String> futsal_name = new ArrayList<String>();
    ArrayList<String> price = new ArrayList<String>();
    ListView ListViewLapangan;
    AdapterLapangan adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_lview);

        Intent inten = getIntent();
        String token = inten.getStringExtra("token");
        Toast.makeText(getApplicationContext(),token.toString(), Toast.LENGTH_SHORT).show();

        ListViewLapangan = (ListView) findViewById(R.id.ListViewLapangan);
        LapanganFutsalService lapanganFutsalService = APIClient.getClient().create(LapanganFutsalService.class);
        retrofit2.Call call = lapanganFutsalService.getSecret("Bearer "+token.toString());
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                List<LapanganFutsal> lapanganFutsals =  (List<LapanganFutsal>) response.body();
                ListViewLapangan.setAdapter(new AdapterLapangan(lapanganFutsals,getApplicationContext()));
            }



            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }
}
