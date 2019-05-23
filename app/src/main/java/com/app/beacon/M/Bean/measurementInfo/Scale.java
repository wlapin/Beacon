package com.app.beacon.M.Bean.measurementInfo;

public class Scale {
    private double pixels;
    private double centimeters;
    private point1 point1;
    private point2 point2;

    public Scale(double pixels, double centimeters, com.app.beacon.M.Bean.measurementInfo.point1 point1, com.app.beacon.M.Bean.measurementInfo.point2 point2) {
        this.pixels = pixels;
        this.centimeters = centimeters;
        this.point1 = point1;
        this.point2 = point2;
    }

    public double getPixels() {
        return pixels;
    }

    public void setPixels(double pixels) {
        this.pixels = pixels;
    }

    public double getCentimeters() {
        return centimeters;
    }

    public void setCentimeters(double centimeters) {
        this.centimeters = centimeters;
    }

    public com.app.beacon.M.Bean.measurementInfo.point1 getPoint1() {
        return point1;
    }

    public void setPoint1(com.app.beacon.M.Bean.measurementInfo.point1 point1) {
        this.point1 = point1;
    }

    public com.app.beacon.M.Bean.measurementInfo.point2 getPoint2() {
        return point2;
    }

    public void setPoint2(com.app.beacon.M.Bean.measurementInfo.point2 point2) {
        this.point2 = point2;
    }

    @Override
    public String toString() {
        return "Scale{" +
                "pixels=" + pixels +
                ", centimeters=" + centimeters +
                ", point1=" + point1 +
                ", point2=" + point2 +
                '}';
    }
}
