package nhom1.fpoly.duan1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import nhom1.fpoly.duan1.database.CreateDatabase;

public class ThongKeDao {
    CreateDatabase dbHelper;
    public ThongKeDao(Context context){
        dbHelper= new CreateDatabase(context);
    }
    public int getDoanhThu(String ngaybatdau,String ngayketthuc){
        ngaybatdau = ngaybatdau.replace("-","");
        ngayketthuc = ngayketthuc.replace("-","");
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM (totalMoney) FROM Oder WHERE substr(dateOder,7)||substr(dateOder,4,2)||substr(dateOder,1,2) BETWEEN ? AND ?",new String[]{ngaybatdau,ngayketthuc});
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
