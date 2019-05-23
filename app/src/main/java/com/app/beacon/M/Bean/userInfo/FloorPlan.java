package com.app.beacon.M.Bean.userInfo;
public class FloorPlan{
    private int id;
    private String name;
    private Position Position;

    public FloorPlan(int id, String name, com.app.beacon.M.Bean.userInfo.Position position) {
        this.id = id;
        this.name = name;
        Position = position;
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

    public com.app.beacon.M.Bean.userInfo.Position getPosition() {
        return Position;
    }

    public void setPosition(com.app.beacon.M.Bean.userInfo.Position position) {
        Position = position;
    }

    @Override
    public String toString() {
        return "FloorPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Position=" + Position +
                '}';
    }
}