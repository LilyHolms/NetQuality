package me.eagzzycsl.netquality;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper implements AboutDb {

    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sql_create_header = "CREATE TABLE IF NOT EXISTS " + TABLE_History.TABLE_NAME + "("
                + TABLE_History.FILED.ID + " integer primary key autoincrement";
        StringBuilder stringBuilder = new StringBuilder(sql_create_header);
        for (MyInfoType myInfoType : MyInfoType.values()) {
            stringBuilder.append(",").append(myInfoType.getKey()).append(" text");
        }
        stringBuilder.append(");");
        db.execSQL(stringBuilder.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}