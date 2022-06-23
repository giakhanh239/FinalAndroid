package com.example.finalpractice.model;

public class ViTri {
    int id;
    private  String name;
    private  String describe;

    public ViTri(String name, String describe) {
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
