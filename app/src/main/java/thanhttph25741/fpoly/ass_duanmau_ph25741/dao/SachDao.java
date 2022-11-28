package thanhttph25741.fpoly.ass_duanmau_ph25741.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.ICUUncheckedIOException;

import androidx.core.app.NavUtils;

import java.util.ArrayList;

import thanhttph25741.fpoly.ass_duanmau_ph25741.database.DbHelper;
import thanhttph25741.fpoly.ass_duanmau_ph25741.model.Sach;

public class SachDao {
    DbHelper dbHelper;
    public SachDao(Context context){
        dbHelper = new DbHelper(context);

    }

    public ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.masach,sc.tensach,sc.giathue,sc.maloai,lo.tenloai FROM SACH sc, LOAISACH lo WHERE sc.maloai = lo.maloai", null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2), cursor.getInt(3),cursor.getString(4)));

            }while (cursor.moveToNext());

        }

        return list;

    }
    public boolean  themSachMoi(String tensach, int giatien, int maloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach",tensach);
        contentValues.put("giathue", giatien);
        contentValues.put("maloai", maloai);
        long check = sqLiteDatabase.insert("SACH",null,contentValues);
        if(check==-1)
            return false;
        return true;

    }
}
