package com.unlistedi.aplikasicrudku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;
    public DatabaseHelper(Context context){
        super(context, "student", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS student (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nama TEXT," +
                "alamat TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS student");
        onCreate(db);
    }

    public void insertStudent(String nama, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", nama);
        values.put("alamat", alamat);
        db.insert("student", null, values);
        Toast.makeText(this.context, "Data student telah dimasukkan", Toast.LENGTH_SHORT).show();
        db.close();
    }

    // Fungsi Tangkap Data
    public ArrayList<String> getStudentId() {
        ArrayList<String> studentsArrayList = new ArrayList<String>();
        String id;
        String selectQuery = "SELECT * FROM student";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                id = c.getString(c.getColumnIndex("id"));
                studentsArrayList.add(id);
            } while (c.moveToNext());
        }
        db.close();
        return studentsArrayList;
    }
    public ArrayList<String> getStudentNama() {
        ArrayList<String> studentsArrayList = new ArrayList<String>();
        String name;
        String selectQuery = "SELECT * FROM student";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                name = c.getString(c.getColumnIndex("nama"));
                studentsArrayList.add(name);
            } while (c.moveToNext());
        }
        db.close();
        return studentsArrayList;
    }
    public ArrayList<String> getStudentAlamat() {
        ArrayList<String> studentsArrayList = new ArrayList<String>();
        String alamat;
        String selectQuery = "SELECT * FROM student";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                alamat = c.getString(c.getColumnIndex("alamat"));
                studentsArrayList.add(alamat);
            } while (c.moveToNext());
        }
        db.close();
        return studentsArrayList;
    }

    // Fungsi Update
    public void updateStudent(String id, String nama, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", nama);
        values.put("alamat", alamat);
        db.update("student", values, "id = \""+id+"\"", null);
        Toast.makeText(this.context, "Data student telah diupdate", Toast.LENGTH_SHORT).show();
        db.close();
    }

    // Fungsi Delete
    public void deleteStudent(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("student", "id = "+id, null);
        Toast.makeText(this.context, "Data student telah dihapus", Toast.LENGTH_SHORT).show();
        db.close();
    }

}
