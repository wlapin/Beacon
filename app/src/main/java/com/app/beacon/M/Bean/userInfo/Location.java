package com.app.beacon.M.Bean.userInfo;

public class Location{
    private FloorPlan FloorPlan;
    private Building Building;
    private Site Site;
    private Room Room;

    public Location(FloorPlan floorPlan, Building building, Site site, Room room) {
        FloorPlan = floorPlan;
        Building = building;
        Site = site;
        Room = room;
    }

    public FloorPlan getFloorPlan() {
        return FloorPlan;
    }

    public void setFloorPlan(FloorPlan floorPlan) {
        FloorPlan = floorPlan;
    }

    public Building getBuilding() {
        return Building;
    }

    public void setBuilding(Building building) {
        Building = building;
    }

    public Site getSite() {
        return Site;
    }

    public void setSite(Site site) {
        Site = site;
    }

    public Room getRoom() {
        return Room;
    }

    public void setRoom(Room room) {
        Room = room;
    }

    @Override
    public String toString() {
        return "Location{" +
                "FloorPlan=" + FloorPlan +
                ", Building=" + Building +
                ", Site=" + Site +
                ", Room=" + Room +
                '}';
    }
}