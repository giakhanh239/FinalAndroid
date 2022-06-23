package com.example.finalpractice.database;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.finalpractice.model.NhanVien;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SharedPreference {
    public static  ArrayList<NhanVien> doc(Context c){
        ArrayList<NhanVien> list = new ArrayList<>();
        SharedPreferences sharedPref = c.getSharedPreferences("MyPreferences", c.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPref.getString("MyObject","");
        list = gson.fromJson(json,ArrayList.class);
        return list;
    }
    public static  void ghi(ArrayList<NhanVien> list,Context c){
        SharedPreferences sharedPref = c.getSharedPreferences("MyPreferences", c.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("MyObject", json);
        editor.commit();
    }

}
