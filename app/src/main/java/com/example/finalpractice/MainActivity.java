package com.example.finalpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnNhanVien;
    Button btnViTri;
    Button btnGanViTri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);
        setView();
        setEvent();
    }

    private void setEvent() {
        btnViTri.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,ViTriActivity.class);
            startActivity(intent);
        });
        btnNhanVien.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,NhanVienActivity.class);
            startActivity(intent);
        });
        btnGanViTri.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,GanViTriActivity.class);
            startActivity(intent);
        });
    }

    private void setView() {
        btnGanViTri = findViewById(R.id.btnGanViTri);
        btnNhanVien = findViewById(R.id.btnNhanVien);
        btnViTri = findViewById(R.id.btnViTri);
    }
}