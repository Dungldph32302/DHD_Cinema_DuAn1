package com.example.dhd_cinema_duan1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dhd_cinema_duan1.DataBase.Dbhelper;

import java.util.ArrayList;

public class anhdao {
    private final Context context;
    private final Dbhelper dbhelper;

    public anhdao(Context context) {
        this.context = context;
        this.dbhelper = new Dbhelper(context);
    }

    public ArrayList<anhmodel> getAllGhe(){
        ArrayList<anhmodel>list= new ArrayList<>();
        SQLiteDatabase db=dbhelper.getReadableDatabase();
        try {
            Cursor cursor=db.rawQuery("Select * from anh  ",null);
            if(cursor.getCount()>0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    anhmodel sp= new anhmodel();
                    sp.setId(cursor.getInt(0));
                    sp.setAnh(cursor.getString(1));
                    list.add(sp);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.i(TAG, "Lỗi allSP",e);
        }
        return list;
    }
    public boolean addanh(anhmodel sp){
        ContentValues values=new ContentValues();
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        values.put("anh",sp.getAnh());
        long row=db.insert("anh",null,values);
        return (row>0);
    }
}
