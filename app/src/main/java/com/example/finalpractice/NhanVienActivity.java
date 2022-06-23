package com.example.finalpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.finalpractice.database.IOFile;
import com.example.finalpractice.database.MyDatabaseHelper;
import com.example.finalpractice.database.SharedPreference;
import com.example.finalpractice.model.NhanVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NhanVienActivity extends AppCompatActivity {
    Button btnThem;
    EditText txtNvName;
    EditText txtNvBirthday;
    EditText txtNvHome;
    EditText txtNvLevel;
    Button btnGetNhanVien;

    ListView lvNhanVien;
    ArrayAdapter<NhanVien> lvAdapter;
    ArrayList<NhanVien> nhanVienListFile;

    String filename = "nhanvien.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        setView();
        setEvent();
        loadDataShare();

    }

    private void setEvent() {
        btnThem.setOnClickListener(view -> {
            clickEventSharedPre();
            loadDataShare();
        });
        btnGetNhanVien.setOnClickListener(view -> {
            getNhanVienEvent();
        });
    }

    private void getNhanVienEvent() {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<NhanVien> nhanVienList = new ArrayList<>();
        nhanVienList = db.getAllNhanVien();
        List<NhanVien> nhanVienListProper = new ArrayList<>();
        nhanVienList.forEach(nhanVien -> {
            String c = nhanVien.getBirthday().substring(6, 10);
            boolean a = c.equals("1995");
            boolean b = nhanVien.getName().toLowerCase(Locale.ROOT).contains("nam");
            if (
                    a && b
            ) {
                nhanVienListProper.add(nhanVien);
            }
        });
        lvAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                nhanVienListProper);
        lvNhanVien.setAdapter(lvAdapter);
    }

    private void clickEvent() {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        String name = txtNvName.getText().toString();
        String birthday = txtNvBirthday.getText().toString();
        String home = txtNvHome.getText().toString();
        String level = txtNvLevel.getText().toString();
        NhanVien nhanVien = new NhanVien(name, birthday, home, level);
        db.addNhanVien(nhanVien);
        db.close();

    }

    private void clickEventFile() {
        String name = txtNvName.getText().toString();
        String birthday = txtNvBirthday.getText().toString();
        String home = txtNvHome.getText().toString();
        String level = txtNvLevel.getText().toString();
        NhanVien nhanVien = new NhanVien(name, birthday, home, level);
        nhanVienListFile.add(nhanVien);
        IOFile.ghi(this, filename, nhanVienListFile);
        nhanVienListFile = IOFile.doc(this, filename);

    }
    private void clickEventSharedPre() {
        String name = txtNvName.getText().toString();
        String birthday = txtNvBirthday.getText().toString();
        String home = txtNvHome.getText().toString();
        String level = txtNvLevel.getText().toString();
        NhanVien nhanVien = new NhanVien(name, birthday, home, level);
        nhanVienListFile.add(nhanVien);
        SharedPreference.ghi(nhanVienListFile,this);
        nhanVienListFile = SharedPreference.doc(this);

    }

    private void setView() {
        btnThem = findViewById(R.id.btnNvThem);
        txtNvName = findViewById(R.id.txtNvName);
        txtNvBirthday = findViewById(R.id.txtNvBirthday);
        txtNvHome = findViewById(R.id.txtNvHome);
        txtNvLevel = findViewById(R.id.txtNvLevel);
        lvNhanVien = findViewById(R.id.lvNhanVien);
        btnGetNhanVien = findViewById(R.id.btnGetNhanVien);
    }

    private void loadData() {
        txtNvLevel.setText("");
        txtNvHome.setText("");
        txtNvBirthday.setText("");
        txtNvName.setText("");
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<NhanVien> nhanVienList = new ArrayList<>();
        nhanVienList = db.getAllNhanVien();
        lvAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                nhanVienList);
        lvNhanVien.setAdapter(lvAdapter);

    }

    private void loadDataFile() {
        nhanVienListFile = IOFile.doc(this, filename);
        if (nhanVienListFile.size() > 0) {
            NhanVien.setSma(nhanVienListFile.get(nhanVienListFile.size() - 1).getId() + 1);
            lvAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,
                    nhanVienListFile);
            lvNhanVien.setAdapter(lvAdapter);

        }
        lvAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                nhanVienListFile);
        lvNhanVien.setAdapter(lvAdapter);

    }
    private void loadDataShare() {
        nhanVienListFile = SharedPreference.doc(this);
        if (nhanVienListFile.size() > 0) {
            NhanVien.setSma(nhanVienListFile.get(nhanVienListFile.size() - 1).getId() + 1);
            lvAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1,
                    nhanVienListFile);
            lvNhanVien.setAdapter(lvAdapter);

        }
        lvAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                nhanVienListFile);
        lvNhanVien.setAdapter(lvAdapter);

    }
}