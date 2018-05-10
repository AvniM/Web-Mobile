package com.android.umkc.datasciencecheatsheets.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.umkc.datasciencecheatsheets.model.Topic;


public class SQLiteDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Topics.db";

    public static final String TABLE_NAME = "topic";
    public static final String COLUMN_ID = "topic_id";
    public static final String COLUMN_NAME = "topic_name";
    public static final String COLUMN_COUNT = "topic_count";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT, " + COLUMN_COUNT + " TEXT )";

    public SQLiteDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }


    public int getCount() {
        Cursor c = null;
        try {
            SQLiteDatabase db = getReadableDatabase();
            String query = "select count(*) from topic";
            c = db.rawQuery(query, null);
            if (c.moveToFirst()) {
                return c.getInt(0);
            }
            return 0;
        }
        finally {
            if (c != null) {
                c.close();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void insert(Topic topic){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, topic.getName());
        values.put(COLUMN_COUNT, topic.getCount());

        // Insert row (returns primary key)
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert(TABLE_NAME, null, values);
    }

    public Cursor retrieve(){
        SQLiteDatabase db = getReadableDatabase();
        String[] cols = {COLUMN_NAME};
        Cursor c = db.query(
                TABLE_NAME,                    // The table to query
                cols,                                 // The columns to return
                null,                                       // The columns for the WHERE clause
                null,                                       // The values for the WHERE clause
                null,                                       // don't group the rows
                null,                                       // don't filter by row groups
                null                                   // The sort order
        );

        return c;
    }

    public void delete(String name){

        String whereClause = COLUMN_NAME + " = ?";
        String[] args = { name };

        SQLiteDatabase db = getReadableDatabase();
        db.delete(TABLE_NAME, whereClause, args);

    }
}