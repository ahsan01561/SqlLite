package com.example.ahsan.word;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.channels.SelectableChannel;

/**
 * Created by ahsan on 07-03-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "rizwan.db";
    public static final String TABLE_NAME = "langda";
    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "subject";
    public static final String COL_4 = "marks";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(id Integer primary key,name text,subject text,marks Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean onCreate(String id, String name, String subject, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, id);
        cv.put(COL_2, name);
        cv.put(COL_3, subject);
        cv.put(COL_4, marks);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("Select * from " + TABLE_NAME, null);
        return c;
    }

    public boolean onUpdate(String id, String name, String subject, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, id);
        cv.put(COL_2, name);
        cv.put(COL_3, subject);
        cv.put(COL_4, marks);
        db.update(TABLE_NAME, cv, "ID=?", new String[]{id});
        return true;
    }
    public Integer delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{id});
    }
}