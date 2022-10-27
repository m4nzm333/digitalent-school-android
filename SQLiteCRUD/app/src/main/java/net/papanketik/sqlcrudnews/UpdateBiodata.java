package net.papanketik.sqlcrudnews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateBiodata extends AppCompatActivity {
    protected Cursor cursor;
    DataabaseHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text3, text4, text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_biodata);

        dbHelper = new DataabaseHelper(this);
        text1 = findViewById(R.id.editText1);
        text3 = findViewById(R.id.editText3);
        text4 = findViewById(R.id.editText4);
        text5 = findViewById(R.id.editText5);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM news WHERE title = '" + getIntent().getStringExtra("title") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text3.setText(cursor.getString(1).toString());
            text4.setText(cursor.getString(2).toString());
            text5.setText(cursor.getString(3).toString());
        }
        ton1 = findViewById(R.id.button1);
        ton2 = findViewById(R.id.button2);
        // daftarkan even onClick pada btnSimpan
        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update news set date_of_news='" +
                        text3.getText().toString()+"', title='"+

                        text4.getText().toString() +"', news='" +
                        text5.getText().toString() + "' where id_news='" +
                        text1.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
        ton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub<
                finish();
            }
        });
    }
}