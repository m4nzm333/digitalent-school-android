package net.papanketik.sqlcrudnews;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "news";
    private static final int DATABASE_VERSION = 1;

    public DataabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String sql = "CREATE TABLE biodata (id integer primary key AUTOINCREMENT, nama text, tgl text, jeniskelamin text, alamat text);";
        String sql = "CREATE TABLE news (id_news integer primary key AUTOINCREMENT, title text, date_of_news text, news text);";
        Log.d("data", sql);
        db.execSQL(sql);

        String sql2 = "INSERT INTO news (date_of_news, title, news) VALUES ('Demo Besar', '04-08-1999', 'Demo Besar di Pettarani');";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
