package com.example.bishal_amgai_lab2;

public class Cars {
    private int Carid;
    private String Brand;
    private String Model;
    private int Year;

    public int getCarid() {
        return Carid;
    }

    public void setCarid(int carid) {
        Carid = carid;
    }

    public String getBrand() {
        return Brand;
    }

    public Cars(int carid, String brand, String model, int year) {
        Carid = carid;
        Brand = brand;
        Model = model;
        Year = year;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }
}
