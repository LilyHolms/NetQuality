package me.eagzzycsl.netquality;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class SQLMan implements AboutDb {
    private static SQLiteDatabase myDb;

    public SQLMan(Context context) {

        myDb = new MySQLiteHelper(context, DB_NAME,
                null, DB_VERSION).getReadableDatabase();
    }

    private static SQLMan instance;

    public static SQLMan getInstance() {
        if (instance == null) {
            instance = new SQLMan(MyApplication.getContext());
        }
        return instance;
    }


    private final static String Qs_readAll = "select " + MyInfoType.stamp.getKey() + " from " + TABLE_History.TABLE_NAME;


    public ArrayList<DatumHis> readAll() {
        Cursor c = myDb.rawQuery(Qs_readAll, null);
        ArrayList<DatumHis> hisData = new ArrayList<>(c.getCount());
        final int stamp_id = c.getColumnIndex(MyInfoType.stamp.getKey());
        while (c.moveToNext()) {
            hisData.add(new DatumHis(
                    c.getString(stamp_id))
            );
        }
        c.close();
        return hisData;
    }

    private final static String Qs_read = "select * from " + TABLE_History.TABLE_NAME + " where " + MyInfoType.stamp.getKey() + "=?";

    public ArrayList<DatumInfo> read(String stamp) {
        Cursor c = myDb.rawQuery(Qs_read, new String[]{stamp});
        ArrayList<DatumInfo> netStates = new ArrayList<>(c.getCount());

        while (c.moveToNext()) {
            for (int i = 0; i < c.getColumnCount(); i++) {
                MyInfoType myInfoType = MyInfoType.getEnum(c.getColumnName(i));
                if (myInfoType != null) {
                    netStates.add(new DatumInfo(myInfoType, c.getString(i)));
                }
            }
        }
        c.close();
        return netStates;
    }

    public void insert(ArrayList<DatumInfo> netStates, String stamp) {
        if (netStates == null) {
            return;
        }
        ContentValues cv = new ContentValues();
        cv.put(MyInfoType.stamp.getKey(), stamp);
        for (DatumInfo netState : netStates) {
            cv.put(netState.getKey(), netState.getValue());
        }
        myDb.insert(TABLE_History.TABLE_NAME, null, cv);
    }

    public void deleteAll() {
        myDb.delete(TABLE_History.TABLE_NAME, null, null);
    }
}
