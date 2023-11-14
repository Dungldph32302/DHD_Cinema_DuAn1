package com.example.dhd_cinema_duan1;

public class anhmodel {
    private int id;
    private String anh;

    public anhmodel(int id, String anh) {
        this.id = id;
        this.anh = anh;
    }

    public anhmodel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
