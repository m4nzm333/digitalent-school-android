package net.papanketik.sqlcrudnews;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddBiodata extends AppCompatActivity {
    @BindView(R.id.tombolkalendar)
    Button tombolkalendar;
    @BindView(R.id.editText3)
    EditText text3;

    protected Cursor cursor;
    DataabaseHelper dbHelper;
    Button ton1, ton2;
    EditText text4, text5;

    Calendar Kalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_biodata);

        ButterKnife.bind(this);
        Kalendar = Calendar.getInstance();

        dbHelper = new DataabaseHelper(this);
        text3 = findViewById(R.id.editText3);
        text4 = findViewById(R.id.editText4);
        text5 = findViewById(R.id.editText5);
        ton1 = findViewById(R.id.button1);
        ton2 = findViewById(R.id.button2);

        tombolkalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddBiodata.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Kalendar.set(Calendar.YEAR, year);
                        Kalendar.set(Calendar.MONTH, month);
                        Kalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                        String formatTanggal = "dd-MM-yyyy";
                        SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);
                        text3.setText(sdf.format(Kalendar.getTime()));
                    }
                },
                        Kalendar.get(Calendar.YEAR), Kalendar.get(Calendar.MONTH),
                        Kalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                String inputDate = text3.getText().toString();
                String inputTitle = text4.getText().toString();
                String inputNews = text5.getText().toString();
                boolean inputankosong = false;
                boolean inputanvalid = false;


                if (TextUtils.isEmpty(inputDate)){
                    inputankosong = true;
                    text3.setError("Field tidak boleh kosong!");
                }

                if (TextUtils.isEmpty(inputTitle)){
                    inputankosong = true;
                    text4.setError("Field tidak boleh kosong!");
                }

                if (TextUtils.isEmpty(inputNews)){
                    inputankosong = true;
                    text5.setError("Field tidak boleh kosong!");
                }

                if (text4.length() > 5) {
                    inputanvalid = true;
                    text4.setError("Jumlah karakter max 5");
                }

                if (text5.length() > 10) {
                    inputanvalid = true;
                    text5.setError("Jumlah karakter max 10");

                }

                if (!inputankosong && !inputanvalid){
                    // TODO Auto-generated method stub
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL("insert into news(date_of_news, title, news) values('" +
                            inputDate +"','"+
                            inputTitle +"','" +
                            inputNews + "')");
                    Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                    MainActivity.ma.RefreshList();
                    finish();
                }
            }
        });

        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }


}
