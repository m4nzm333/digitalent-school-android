package com.unlistedi.mystudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class SiswaDatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "student_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SISWA = "siswa";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_LATITUDE = "latitude";
    private Context context;

    // Ini Contruktor
    public SiswaDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    // Ini Buat Bikin Table Di SQL
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + TABLE_SISWA + "("+
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                KEY_NAMA + " TEXT,"+
                KEY_ALAMAT + " TEXT,"+
                KEY_LONGITUDE + " DOUBLE,"+
                KEY_LATITUDE + " DOUBLE)"
        );
    }

    // Buat Tambah Data
    public void addSiswa(Siswa siswa) {
        // Ambil Databasenya
        SQLiteDatabase db = this.getWritableDatabase();
        // Buat Data supaya bisa di baca sama SQLLite
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, siswa.nama);
        values.put(KEY_ALAMAT, siswa.alamat);
        values.put(KEY_LONGITUDE, siswa.longitude.toString());
        values.put(KEY_LATITUDE, siswa.latitude.toString());
        // Ini Eksekusi Query
        db.insert(TABLE_SISWA, null, values);
        Toast.makeText(this.context, "Siswa telah ditambahkan", Toast.LENGTH_SHORT).show();
    }

    // Buat Ambil Data
    public ArrayList<Siswa> getAllSiswa() {
        ArrayList<Siswa> studentsArryList = new ArrayList<>();
        String id, nama, alamat;
        Double longitude, latitude;
        String selectQuery = "SELECT * FROM " + TABLE_SISWA;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                id = c.getString(c.getColumnIndex(KEY_ID));
                nama = c.getString(c.getColumnIndex(KEY_NAMA));
                alamat = c.getString(c.getColumnIndex(KEY_ALAMAT));
                longitude = c.getDouble(c.getColumnIndex(KEY_LONGITUDE));
                latitude = c.getDouble(c.getColumnIndex(KEY_LATITUDE));
                studentsArryList.add(new Siswa(id, nama, alamat, longitude, latitude));
            } while (c.moveToNext());
            Log.d("array", studentsArryList.toString());
        }
        return studentsArryList;
    }


    public void updateSiswa(Siswa siswa){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAMA, siswa.nama);
        values.put(KEY_ALAMAT, siswa.alamat);
        values.put(KEY_LONGITUDE, siswa.longitude.toString());
        values.put(KEY_LATITUDE, siswa.latitude.toString());
        db.update(TABLE_SISWA, values,  KEY_ID + " = " + siswa.id, null);
        Toast.makeText(this.context, "Siswa telah diupdate", Toast.LENGTH_SHORT).show();
    }

    public void deleteSiswa(Siswa siswa){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SISWA, "id="+siswa.id, null);
        Toast.makeText(this.context, "Siswa telah dihapus", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_SISWA);
        onCreate(db);
    }
}
