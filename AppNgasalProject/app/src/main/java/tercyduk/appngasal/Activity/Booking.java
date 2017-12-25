package tercyduk.appngasal.Activity;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import tercyduk.appngasal.R;
import tercyduk.appngasal.coresmodel.User;

public class Booking extends AppCompatActivity  {
    EditText EtextDate;
    Calendar mCurrentdate;
    Spinner spinnerDialog;
    private User _user;
    ArrayAdapter<CharSequence> adapater;
    int[] angka ={1,2,3};
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
         txt = (TextView)findViewById(R.id.txttest);


        //SpinnerJam
        spinnerDialog =(Spinner) findViewById(R.id.SpinnerJam);
        adapater = ArrayAdapter.createFromResource(this,R.array.spinneroption,android.R.layout.simple_spinner_item);
        adapater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDialog.setAdapter(adapater);
        spinnerDialog.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        txt.setText(""+angka[position]);
                        break;
                    case 1:
                        txt.setText(""+angka[position]);
                        break;
                    case 2:
                        txt.setText(""+angka[position]);
                        break;
                    case 3:
                        txt.setText(""+angka[position]);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Calender
        EtextDate = (EditText) findViewById(R.id.edTextDate);
        EtextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentdate = Calendar.getInstance();
//                mCurrentdate.setTimeInMillis(System.currentTimeMillis() - 1000);
                int year = mCurrentdate.get(Calendar.YEAR);
                int month = mCurrentdate.get(Calendar.MONTH);
                int day = mCurrentdate.get(Calendar.DAY_OF_WEEK);
                final DatePickerDialog datePickerDialog = new DatePickerDialog(Booking.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int Tahunbook, int Bulanbook, int Haribook) {
                        int Bulans = Bulanbook +1 ;
                        EtextDate.setText(Haribook + "-" + Bulans + "-" + Tahunbook);
                        mCurrentdate.set(Tahunbook, Bulanbook, Haribook);


                    }
                }, year, month, day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });


    }



}
