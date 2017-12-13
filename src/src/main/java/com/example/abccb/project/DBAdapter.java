package com.example.abccb.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
    private static final String DB_NAME="freeclassroom.db";
    private static final String DB_TABLE="freeclassroominfo";
    private static final int DB_VERSION=1;

    public static final String KEY_ID="_id";
    public static final String KEY_CLASSROOM="classroom";
    public static final String KEY_WEEK="week";
    public static final String KEY_TIME="time";

    private SQLiteDatabase db;
    private final Context context;
    private DBOpenHelper dbOpenHelper;

    private static class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        private static final String DB_CREATE="create table"+DB_TABLE+"("+KEY_ID+" interger" +
                " primary key autoincrement,"+KEY_CLASSROOM+"String,"+KEY_WEEK+"integer," +
                KEY_TIME+" String);";

        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
            _db.execSQL(String.format("DROP TABLE IF EXISTS%s", DB_TABLE));
            onCreate(_db);
        }
    }

    public DBAdapter(Context _context) {
        context =_context;
    }

    public void open()throws SQLiteException{
        dbOpenHelper=new DBOpenHelper(context,DB_NAME,null,DB_VERSION);
        try{
            db=dbOpenHelper.getWritableDatabase();
        }catch (SQLiteException ex){
            db=dbOpenHelper.getReadableDatabase();
        }
    }
    public long insert(FreeClassroom freeClassroom){
        ContentValues contentValues1=new ContentValues();
        contentValues1.put(KEY_CLASSROOM,"浦一403");
        contentValues1.put(KEY_WEEK,"四");
        contentValues1.put(KEY_TIME,"14-16点");
        return db.insert(DB_TABLE,null,contentValues1);
    }


    public  Cursor queryAllFreeRoom(int week){
        if(week>5){
            week=1;
        }else if(week==0){
            week=1;
        }
      // return db.query(DB_TABLE,new String[]{});
    }

    public void close(){
        if(db!=null){
            db.close();
            db=null;
        }
    }
}
