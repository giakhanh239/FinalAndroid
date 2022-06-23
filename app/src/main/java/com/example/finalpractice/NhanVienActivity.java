package com.example.finalpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.finalpractice.database.MyDatabaseHelper;
import com.example.finalpractice.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienActivity extends AppCompatActivity {
    Button btnThem;
    EditText txtNvName;
    EditText txtNvBirthday;
    EditText txtNvHome;
    EditText txtNvLevel;

    ListView lvNhanVien;
    ArrayAdapter<NhanVien> lvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);
        setView();
        setEvent();
        loadData();
    }

    private void setEvent() {
        btnThem.setOnClickListener(view -> {
            clickEvent();
            loadData();
        });
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

    private void setView() {
        btnThem = findViewById(R.id.btnNvThem);
        txtNvName = findViewById(R.id.txtNvName);
        txtNvBirthday = findViewById(R.id.txtNvBirthday);
        txtNvHome = findViewById(R.id.txtNvHome);
        txtNvLevel = findViewById(R.id.txtNvLevel);
        lvNhanVien = findViewById(R.id.lvNhanVien);
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
}