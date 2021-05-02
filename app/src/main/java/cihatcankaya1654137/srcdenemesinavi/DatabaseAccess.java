package cihatcankaya1654137.srcdenemesinavi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }


    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<String> getSoru(String Sinav) {
        List<String> list = new ArrayList<>();
        Cursor cursor = null;
        if(!Sinav.equals("")){
            cursor = database.rawQuery("SELECT Soru FROM SORULAR WHERE trim(Sinav) LIKE " + DatabaseUtils.sqlEscapeString(Sinav), null);
        }else{
            cursor = database.rawQuery("SELECT Soru FROM SORULAR", null);
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public List<String> getC1(String Sinav) {
        List<String> list = new ArrayList<>();
        Cursor cursor = null;
        if(!Sinav.equals("")){
            cursor = database.rawQuery("SELECT C1 FROM SORULAR WHERE trim(Sinav) LIKE " + DatabaseUtils.sqlEscapeString(Sinav), null);
        }else{
            cursor = database.rawQuery("SELECT C1 FROM SORULAR", null);
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getC2(String Sinav) {
        List<String> list = new ArrayList<>();
        Cursor cursor = null;
        if(!Sinav.equals("")){
            cursor = database.rawQuery("SELECT C2 FROM SORULAR WHERE trim(Sinav) LIKE " + DatabaseUtils.sqlEscapeString(Sinav), null);
        }else{
            cursor = database.rawQuery("SELECT C2 FROM SORULAR", null);
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getC3(String Sinav) {
        List<String> list = new ArrayList<>();
        Cursor cursor = null;
        if(!Sinav.equals("")){
            cursor = database.rawQuery("SELECT C3 FROM SORULAR WHERE trim(Sinav) LIKE " + DatabaseUtils.sqlEscapeString(Sinav), null);
        }else{
            cursor = database.rawQuery("SELECT C3 FROM SORULAR", null);
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getC4(String Sinav) {
        List<String> list = new ArrayList<>();
        Cursor cursor = null;
        if(!Sinav.equals("")){
            cursor = database.rawQuery("SELECT C4 FROM SORULAR WHERE trim(Sinav) LIKE " + DatabaseUtils.sqlEscapeString(Sinav), null);
        }else{
            cursor = database.rawQuery("SELECT C4 FROM SORULAR", null);
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }


    public List<String> getDC(String Sinav) {
        List<String> list = new ArrayList<>();
        Cursor cursor = null;
        if(!Sinav.equals("")){
            cursor = database.rawQuery("SELECT DC FROM SORULAR WHERE trim(Sinav) LIKE " + DatabaseUtils.sqlEscapeString(Sinav), null);
        }else{
            cursor = database.rawQuery("SELECT DC FROM SORULAR", null);
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

   /* public List<String> getSinav() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT Sinav FROM SORULAR", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }*/
    public List<String> renkal() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Renk WHERE id = 1", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            list.add(cursor.getString(1));
            list.add(cursor.getString(2));
            list.add(cursor.getString(3));
            list.add(cursor.getString(4));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void renkver(String arka,String btn,String yazi,String Diger) {
        List<String> list = new ArrayList<>();
        ContentValues renks = new ContentValues();
        renks.put("Buton",btn );
        renks.put("Arkaplan", arka);
        renks.put("yazi", yazi);
        renks.put("diger", Diger);
        database.update("Renk",renks,"id=1",  null);

    }
}