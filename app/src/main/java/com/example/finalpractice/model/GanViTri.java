package com.example.finalpractice.model;

import java.io.Serializable;

public class GanViTri implements Serializable {
    private int id;
    private NhanVien nhanVien;
    private ViTri viTri;
    private String thoiDiemGan;
    private String describe;
    private static int sma = 0;

    public GanViTri(NhanVien nhanVien, ViTri viTri, String thoiDiemGan, String describe) {
        this.id = sma++;
        this.nhanVien = nhanVien;
        this.viTri = viTri;
        this.thoiDiemGan = thoiDiemGan;
        this.describe = describe;
    }

    public GanViTri() {
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public ViTri getViTri() {
        return viTri;
    }

    public void setViTri(ViTri viTri) {
        this.viTri = viTri;
    }

    public String getThoiDiemGan() {
        return thoiDiemGan;
    }

    public void setThoiDiemGan(String thoiDiemGan) {
        this.thoiDiemGan = thoiDiemGan;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "GanViTri{" +
                "nhanVien=" + nhanVien +
                ", viTri=" + viTri +
                ", thoiDiemGan='" + thoiDiemGan + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
