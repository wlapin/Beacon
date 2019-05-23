package com.app.beacon.M.Bean.userInfo;

public class Metadata{
    private String keyname;
    private String value;
    private String display;

    public Metadata(String keyname, String value, String display) {
        this.keyname = keyname;
        this.value = value;
        this.display = display;
    }

    public String getKeyname() {
        return keyname;
    }

    public void setKeyname(String keyname) {
        this.keyname = keyname;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "Metadata{" +
                "keyname='" + keyname + '\'' +
                ", value='" + value + '\'' +
                ", display='" + display + '\'' +
                '}';
    }
}