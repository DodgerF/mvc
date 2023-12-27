package com.example.mvc;

public class Table {
    private double height;
    private double width;

    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    public void setHeight(double h) {
        if (h < 0) return;
        height = h;
    }
    public void setWidth(double w) {
        if (w < 0) return;
        width = w;
    }
}
