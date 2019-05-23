package com.app.beacon.M.Bean;

import com.app.beacon.M.Bean.userInfo.LastSeen;
import com.app.beacon.M.Bean.userInfo.Metadata;

import java.util.List;

public class userBean {
    private String Device;
    private String Category;
    private String Client;
    public LastSeen LastSeen;
    public List<Metadata> Metadata;

    public userBean(String device, String category, String client, com.app.beacon.M.Bean.userInfo.LastSeen lastSeen, List<com.app.beacon.M.Bean.userInfo.Metadata> metadata) {
        Device = device;
        Category = category;
        Client = client;
        LastSeen = lastSeen;
        Metadata = metadata;
    }

    public String getDevice() {
        return Device;
    }

    public void setDevice(String device) {
        Device = device;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

    public com.app.beacon.M.Bean.userInfo.LastSeen getLastSeen() {
        return LastSeen;
    }

    public void setLastSeen(com.app.beacon.M.Bean.userInfo.LastSeen lastSeen) {
        LastSeen = lastSeen;
    }

    public List<com.app.beacon.M.Bean.userInfo.Metadata> getMetadata() {
        return Metadata;
    }

    public void setMetadata(List<com.app.beacon.M.Bean.userInfo.Metadata> metadata) {
        Metadata = metadata;
    }

    @Override
    public String toString() {
        return "userBean{" +
                "Device='" + Device + '\'' +
                ", Category='" + Category + '\'' +
                ", Client='" + Client + '\'' +
                ", LastSeen=" + LastSeen +
                ", Metadata=" + Metadata +
                '}';
    }
}





