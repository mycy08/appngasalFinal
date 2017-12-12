package tercyduk.appngasal.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import tercyduk.appngasal.R;
import tercyduk.appngasal.modules.auth.user.Login;

public class DetailLapangan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lapangan);
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
            doChangeActivity(getApplicationContext(),LapanganRview.class);
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
