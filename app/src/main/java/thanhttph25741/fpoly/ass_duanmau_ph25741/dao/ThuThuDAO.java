package thanhttph25741.fpoly.ass_duanmau_ph25741.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import thanhttph25741.fpoly.ass_duanmau_ph25741.database.DbHelper;

public class ThuThuDAO {
    DbHelper dbHelper;
    public ThuThuDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public boolean checkdangNhap(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{matt, matkhau});
        if(cursor.getCount()!=0){
            return true;
        }else {
            return false;
        }
    }
    public int capNhatMatKhau(String username, String oldpass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{username, oldpass});
        if(cursor.getCount() >0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
           long check= sqLiteDatabase.update("THUTHU",contentValues,"matt=?", new String[]{username} );
        if(check == -1)
            return -1;
        return 1;

        }
        return 0;
    }
}
