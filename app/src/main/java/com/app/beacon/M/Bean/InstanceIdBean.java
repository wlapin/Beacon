package com.app.beacon.M.Bean;

public class InstanceIdBean {
    private String user;
    private String instanceId;

    public InstanceIdBean(String user, String instanceId) {
        this.user = user;
        this.instanceId = instanceId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    @Override
    public String toString() {
        return "InstanceidBean{" +
                "user='" + user + '\'' +
                ", instanceId='" + instanceId + '\'' +
                '}';
    }
}
