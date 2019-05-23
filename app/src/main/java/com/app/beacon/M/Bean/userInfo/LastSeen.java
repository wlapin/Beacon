package com.app.beacon.M.Bean.userInfo;

public class LastSeen{
    private String Time;
    private Location Location;

    public LastSeen(String time, com.app.beacon.M.Bean.userInfo.Location location) {
        Time = time;
        Location = location;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public com.app.beacon.M.Bean.userInfo.Location getLocation() {
        return Location;
    }

    public void setLocation(com.app.beacon.M.Bean.userInfo.Location location) {
        Location = location;
    }

    @Override
    public String toString() {
        return "LastSeen{" +
                "Time='" + Time + '\'' +
                ", Location=" + Location +
                '}';
    }
}
