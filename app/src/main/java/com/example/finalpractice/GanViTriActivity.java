package com.example.finalpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.finalpractice.database.MyDatabaseHelper;
import com.example.finalpractice.model.GanViTri;
import com.example.finalpractice.model.NhanVien;
import com.example.finalpractice.model.ViTri;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GanViTriActivity extends AppCompatActivity {
    Button btnGanViTri;
    Spinner spnNhanVien;
    Spinner spnViTri;
    ListView lvGanViTri;
    EditText txtMota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gan_vi_tri);
        setView();
        setEvent();
    }

    private void setEvent() {
        loadNhanVien();
        loadViTri();
        btnGanViTri.setOnClickListener(view -> {
            loadGanViTri();
        });
    }

    private void loadGanViTri() {
        MyDatabaseHelper db =new MyDatabaseHelper(this);
        NhanVien selectedNhanVien = (NhanVien) spnNhanVien.getSelectedItem();
        ViTri selectedViTri = (ViTri) spnViTri.getSelectedItem();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        String thoiDiemGan = dtf.format(date);
        GanViTri ganViTri = new GanViTri(selectedNhanVien,selectedViTri,thoiDiemGan,txtMota.getText().toString());
        db.addGanViTri(ganViTri);
        loadData();
        db.close();
    }

    private void loadData() {
        txtMota.setText("");
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<GanViTri> ganViTriList = db.getAllGanvitri();
        ArrayAdapter<GanViTri> lvGanViTriAdapter = new ArrayAdapter<GanViTri>(this, android.R.layout.simple_list_item_1,ganViTriList);
        lvGanViTri.setAdapter(lvGanViTriAdapter);
    }

    private void loadViTri() {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<ViTri> viTriList = new ArrayList<>();
        viTriList = db.getAllViTri();
        ArrayAdapter<ViTri> spnViTriAdapter = new ArrayAdapter<ViTri>(this, android.R.layout.simple_list_item_1,viTriList);
        spnViTriAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnViTri.setAdapter(spnViTriAdapter);
    }

    private void loadNhanVien() {
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        List<NhanVien> nhanVienList = new ArrayList<>();
        nhanVienList = db.getAllNhanVien();
        ArrayAdapter<NhanVien> spnNhanVienAdapter = new ArrayAdapter<NhanVien>(this, android.R.layout.simple_list_item_1,nhanVienList);
        spnNhanVienAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnNhanVien.setAdapter(spnNhanVienAdapter);
    }

    private void setView() {
        btnGanViTri = findViewById(R.id.btnGanViTri);
        spnNhanVien = findViewById(R.id.spnNhanVien);
        spnViTri = findViewById(R.id.spnViTri);
        lvGanViTri = findViewById(R.id.lvGanViTri);
        txtMota = findViewById(R.id.txtMoTa);
    }
}