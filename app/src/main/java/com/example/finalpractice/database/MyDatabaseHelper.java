package com.example.finalpractice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.finalpractice.model.GanViTri;
import com.example.finalpractice.model.NhanVien;
import com.example.finalpractice.model.ViTri;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "ProductDbHelper";
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String NHAN_VIEN = "nhan_vien";
    private static final String idNhanVien = "id";
    private static final String tenNhanVien = "name";
    private static final String ngaySinhNhanVien = "birthday";
    private static final String queQuanNhanVien = "home";
    private static final String trinhDoNhanVien = "level";

    private static final String VI_TRI = "vi_tri";
    private static final String idViTri = "id";
    private static final String tenViTri = "name";
    private static final String moTaViTri = "describe";

    private static final String GAN_VI_TRI = "gan_vi_tri";
    private static final String ganViTriId = "id";
    private static final String viTriId = "idViTri";
    private static final String nhanVienId = "idNhanVien";
    private static final String thoiDiemGan = "thoiDiemGan";
    private static final String moTa = "moTa";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNhanVienTable = "CREATE TABLE " + NHAN_VIEN + " ( " +
                idNhanVien + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                tenNhanVien + " VARCHAR (255), " +
                ngaySinhNhanVien + " VARCHAR (255), " +
                queQuanNhanVien + " VARCHAR (255), " +
                trinhDoNhanVien + " VARCHAR (255)" +
                ")";

        db.execSQL(createNhanVienTable);
        String createViTriTable = "CREATE TABLE " + VI_TRI + " ( " +
                idViTri + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                tenViTri + " VARCHAR (255), " +
                moTaViTri + " VARCHAR (255) " +
                ")";
        db.execSQL(createViTriTable);
        String createGanViTriTable = "CREATE TABLE " + GAN_VI_TRI + " ( " +
                ganViTriId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                thoiDiemGan + " VARCHAR (255), " +
                moTa + " VARCHAR (255)," +
                viTriId + " INTEGER," +
                nhanVienId + " INTEGER," +
                "FOREIGN KEY(" + viTriId + ") REFERENCES " + VI_TRI + "(" + idViTri + ")," +
                "FOREIGN KEY(" + nhanVienId + ") REFERENCES " + NHAN_VIEN + "(" + idNhanVien + ")" +
                ")";


        db.execSQL(createGanViTriTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addNhanVien(NhanVien nhanVien) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(tenNhanVien, nhanVien.getName());
        values.put(ngaySinhNhanVien, nhanVien.getBirthday());
        values.put(queQuanNhanVien, nhanVien.getHome());
        values.put(trinhDoNhanVien, nhanVien.getLevel());

        db.insert(NHAN_VIEN, null, values);
        db.close();
    }

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> nhanVienList = new ArrayList<>();
        String query = "SELECT * FROM " + NHAN_VIEN;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            NhanVien nhanVien = new NhanVien(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            nhanVienList.add(nhanVien);
            cursor.moveToNext();
        }
        return nhanVienList;
    }

    public void addViTri(ViTri viTri) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(tenViTri, viTri.getName());
        values.put(moTaViTri, viTri.getDescribe());


        db.insert(VI_TRI, null, values);
        db.close();
    }

    public List<ViTri> getAllViTri() {
        List<ViTri> viTriList = new ArrayList<>();
        String query = "SELECT * FROM " + VI_TRI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            ViTri viTri = new ViTri(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));

            viTriList.add(viTri);
            cursor.moveToNext();
        }
        return viTriList;
    }

    public List<GanViTri> getAllGanvitri() {
        List<GanViTri> ganvitriList = new ArrayList<>();
        String query = "SELECT * FROM " + GAN_VI_TRI;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            GanViTri ganViTri = new GanViTri(
                    getnhanVienById(cursor.getInt(4)),
                    getViTriById(cursor.getInt(3)),
                    cursor.getString(1),
                    cursor.getString(2));

            ganvitriList.add(ganViTri);
            cursor.moveToNext();
        }
        return ganvitriList;
    }

    public ViTri getViTriById(int id) {
        ViTri viTri = new ViTri();
        String query = "SELECT * FROM " + VI_TRI + " WHERE " + idViTri + "=" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            viTri = new ViTri(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));
            cursor.moveToNext();
        }
        return viTri;
    }

    public NhanVien getnhanVienById(int id) {
        NhanVien nhanVien = new NhanVien();
        String query = "SELECT * FROM " + NHAN_VIEN + " WHERE " + idNhanVien + "=" + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            nhanVien = new NhanVien(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            cursor.moveToNext();
        }
        return nhanVien;
    }

    public void addGanViTri(GanViTri ganViTri) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(thoiDiemGan, ganViTri.getThoiDiemGan());
        values.put(moTa, ganViTri.getDescribe());
        values.put(viTriId, ganViTri.getViTri().getId());
        values.put(nhanVienId, ganViTri.getNhanVien().getId());


        db.insert(GAN_VI_TRI, null, values);
        db.close();
    }
}
