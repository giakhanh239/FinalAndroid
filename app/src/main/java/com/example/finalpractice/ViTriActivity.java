package com.example.finalpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.finalpractice.database.MyDatabaseHelper;
import com.example.finalpractice.model.NhanVien;
import com.example.finalpractice.model.ViTri;

import java.util.ArrayList;
import java.util.List;

public class ViTriActivity extends AppCompatActivity {
    Button btnThem;
    EditText txtVtName;
    EditText txtVtDescribe;

    ListView lvViTri;
    ArrayAdapter<ViTri> lvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vi_tri);
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
        String name = txtVtName.getText().toString();
        String describe = txtVtDescribe.getText().toString();
        ViTri viTri = new ViTri(name, describe);
        db.addViTri(viTri);
        db.close();

    }

    private void setView() {
        btnThem = findViewById(R.id.btnVtThem);
        txtVtName = findViewById(R.id.txtVtName);
        txtVtDescribe = findViewById(R.id.txtVtDescribe);
        lvViTri = findViewById(R.id.lvViTri);
    }

    private void loadData() {
        txtVtDescribe.setText("");
        txtVtName.setText("");
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<ViTri> viTriList = new ArrayList<>();
        viTriList = db.getAllViTri();
        lvAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                viTriList);
        lvViTri.setAdapter(lvAdapter);

    }
}