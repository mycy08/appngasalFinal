package tercyduk.appngasal.Activity;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import tercyduk.appngasal.R;
import tercyduk.appngasal.apihelper.APIClient;
import tercyduk.appngasal.apihelper.LoginService;
import tercyduk.appngasal.coresmodel.User;
import tercyduk.appngasal.modules.auth.user.Login;
import tercyduk.appngasal.modules.auth.user.Register;

public class EditProfile extends AppCompatActivity {
    EditText etName, etAlamat, etBirth, etNohp;
    TextInputLayout tilName, tilAlamat, tilhp, tilBirth;
    TextView txtLogin;
    Button btnUpdate;
    Context mContext;
    AlertDialog alertDialog;

    AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initComponents();


    }

    private void initComponents() {

        etNohp = (EditText) findViewById(R.id.edp_txt_hp);
        etAlamat = (EditText) findViewById(R.id.edp_txt_alamat);
        etName = (EditText) findViewById(R.id.edp_txt_name);
        etBirth = (EditText) findViewById(R.id.email);
        tilName = (TextInputLayout) findViewById(R.id.edp_name_container);
        tilAlamat = (TextInputLayout) findViewById(R.id.edp_alamat_container);
        tilhp = (TextInputLayout) findViewById(R.id.edp_hp_container);
        btnUpdate = (Button) findViewById(R.id.btnCreateAccount);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean _isvalid = true;
                tilName.setErrorEnabled(false);
                tilAlamat.setErrorEnabled(false);
                tilhp.setErrorEnabled(false);
                tilBirth.setErrorEnabled(false);
                if (TextUtils.isEmpty(etName.getText())) {
                    _isvalid = false;
                    tilName.setErrorEnabled(true);
                    tilName.setError("Nama harus diisi");
                } else if (etName.getText().length() < 7) {
                    _isvalid = false;
                    tilName.setErrorEnabled(true);
                    tilName.setError("Nama minimal 7 huruf");
                } else if (TextUtils.isEmpty((etAlamat.getText()))) {
                    _isvalid = false;
                    tilhp.setErrorEnabled(true);
                    tilhp.setError("Alamat harus diisi");
                } else if (TextUtils.isEmpty((etNohp.getText()))) {
                    _isvalid = false;
                    tilhp.setErrorEnabled(true);
                    tilhp.setError("Handphone harus diisi");
                } else if (etNohp.getText().length() <= 12) {
                    _isvalid = false;
                    tilhp.setErrorEnabled(true);
                    tilhp.setError("Handphone minimal 12 huruf");
                }
                if(_isvalid)
                {
                    try {
                        Intent inten = getIntent();
                        String token = inten.getStringExtra("token");
                        Toast.makeText(getApplicationContext(),token.toString(), Toast.LENGTH_SHORT).show();
                        User user = new User();
                        user.setName(etName.getText().toString());
                        user.setAddress(etAlamat.getText().toString());
                        user.setPhone_number(etNohp.getText().toString());
                        LoginService loginService = APIClient.getClient().create(LoginService.class);
                        Call call = loginService.update(user);
                        call.enqueue(new Callback() {
                            @Override
                            public void onResponse(Call call, retrofit2.Response response) {

                                Boolean result = (Boolean) response.body();
                                    if (result) {
                                    Intent intent = new Intent(EditProfile.this, LapanganRview.class);
                                    startActivity(intent);
                                } else {
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

                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });

    }@Override
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