package com.example.finalpractice.model;

import java.io.Serializable;

public class ViTri implements Serializable {
    int id;
    private  String name;
    private  String describe;
    private  static int sma = 0;

    public ViTri(String name, String describe) {
        this.id = sma++; // vao ra file
        this.name = name;
        this.describe = describe;
    }

    public ViTri(int id, String name, String describe) {
        this.id = id;
        this.name = name;
        this.describe = describe;
    }

    public ViTri() {
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "|| Name:'" + name + '\'' +
                "|| Describe='" + describe + '\'' ;
    }
}
