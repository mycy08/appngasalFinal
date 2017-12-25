package tercyduk.appngasal.Activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import tercyduk.appngasal.Main2Activity;
import tercyduk.appngasal.R;
import tercyduk.appngasal.apihelper.APIClient;
import tercyduk.appngasal.apihelper.Adapter.CustomeSpinnerAdapter;
import tercyduk.appngasal.apihelper.UserClient;
import tercyduk.appngasal.coresmodel.SpinnerData;
import tercyduk.appngasal.coresmodel.User;

public class DetailTopups extends AppCompatActivity {
    Spinner CustomeSpinner;
    TextView txtName, txtTopup;
    TextView txtbanks;
    Calendar mCurrentdate;
    EditText edtDetailtop;
    Button btnConfirm,btnCancel;
    String id,names,alamat,no_hp,emails;
    AlertDialog alertDialog;
    AlertDialog.Builder alertDialogBuilder;
    String[] bank ={"Untuk Topup Pada Bank Central Asia dapat dikirim ke Nomor Rekening : 123456789 Dengan Nama : Cahyono Bin Laden",
            "Untuk Topup Pada Bank Mandiri dapat dikirim ke Nomor Rekening : 987654321 Dengan Nama : Cahyono Bin Laden",
            "Untuk Topup Pada Bank Negara Indonesia dapat dikirim ke Nomor Rekening : 543216789 Dengan Nama : Cahyono Bin Laden",
            "Untuk Topup Pada Bank Permata dapat dikirim ke Nomor Rekening :678912345 Dengan Nama : Cahyono Bin Laden"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_topups);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initComponents();
        CustomeSpinner = (Spinner)findViewById(R.id.spinnerbank);
        txtbanks = (TextView)findViewById(R.id.txtbank);
        final List<SpinnerData> customeList =new ArrayList<>();
        customeList.add(new SpinnerData(R.mipmap.bca,"Bank Central Asia"));
        customeList.add(new SpinnerData(R.mipmap.mandiri,"Bank Mandiri"));
        customeList.add(new SpinnerData(R.mipmap.bni,"Bank Negara Indonesia"));
        customeList.add(new SpinnerData(R.mipmap.permata,"Bank Permata"));
        CustomeSpinnerAdapter customeSpinnerAdapter =new CustomeSpinnerAdapter(DetailTopups.this,R.layout.spinner_layour,customeList);
        CustomeSpinner.setAdapter(customeSpinnerAdapter);
        CustomeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        txtbanks.setText(""+bank[position]);
                        break;
                    case 1:
                        txtbanks.setText(""+bank[position]);
                        break;
                    case 2:
                        txtbanks.setText(""+bank[position]);
                        break;
                    case 3:
                        txtbanks.setText(""+bank[position]);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Calender
        edtDetailtop = (EditText) findViewById(R.id.edttopups);
        edtDetailtop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentdate = Calendar.getInstance();
//                mCurrentdate.setTimeInMillis(System.currentTimeMillis() - 1000);
                int year = mCurrentdate.get(Calendar.YEAR);
                int month = mCurrentdate.get(Calendar.MONTH);
                int day = mCurrentdate.get(Calendar.DAY_OF_WEEK);
                final DatePickerDialog datePickerDialog = new DatePickerDialog(DetailTopups.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int Tahunbook, int Bulanbook, int Haribook) {
                        int Bulans = Bulanbook +1 ;
                        edtDetailtop.setText(Haribook + "-" + Bulans + "-" + Tahunbook);
                        mCurrentdate.set(Tahunbook, Bulanbook, Haribook);


                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });
    }
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
    private void initComponents() {

        final Intent inten = getIntent();
        final String token = inten.getStringExtra("token");
        final String email = inten.getStringExtra("email");
        final String topup = inten.getStringExtra("topup");
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailTopups.this, Main2Activity.class);
                intent.putExtra("token",token);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });



        UserClient userClient= APIClient.getClient().create(UserClient.class);
        Call<User> call = userClient.find("Bearer "+token, email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if(response.body() != null){
                    User users= response.body();
                    id = users.getId();
                    emails = users.getEmail();
                    names = users.getName();
                    txtName = (TextView) findViewById(R.id.txtNametop);
                    txtName.setText(users.getName());
                    txtTopup = (TextView)findViewById(R.id.txtJumlahTop);
                    txtTopup.setText(topup);

                }

            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                alertDialogBuilder.setMessage("Jaringan Sedang Bermasalah").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.homes:
                final Intent inten = getIntent();
                final String token = inten.getStringExtra("token");
                final String email = inten.getStringExtra("email");
                Intent intentToMainmenu = new Intent(DetailTopups.this, Main2Activity.class);
                intentToMainmenu.putExtra("token", token);
                intentToMainmenu.putExtra("email", email);
                startActivity(intentToMainmenu);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
