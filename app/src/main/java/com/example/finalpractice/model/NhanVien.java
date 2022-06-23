package com.example.finalpractice.model;

import java.io.Serializable;

public class NhanVien implements Serializable {
    private int id;
    private String name;
    private String birthday;
    private String home;
    private String level;

    public static int getSma() {
        return sma;
    }

    public static void setSma(int sma) {
        NhanVien.sma = sma;
    }

    private  static  int sma = 0;

    public NhanVien(int id, String name, String birthday, String home, String level) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.home = home;
        this.level = level;
    }
    public NhanVien( String name, String birthday, String home, String level) {
        this.id = sma ++; // vao ra file
        this.name = name;
        this.birthday = birthday;
        this.home = home;
        this.level = level;
    }
    public NhanVien() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Id: "+this.id +"|| Name: " + this.name + "|| Birthday: "
                + this.birthday + "|| Level:" + this.level + "|| Home:" + this.home;
    }
}