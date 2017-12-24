package tercyduk.appngasal.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tercyduk.appngasal.R;

public class TopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Topup");
    }
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
