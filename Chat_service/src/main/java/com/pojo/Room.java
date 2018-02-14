package com.pojo;

import java.util.Date;

public class Room {
    private Integer id;

    private Date createtime;

    private Date lastupdatetime;

    private Integer groupId;

    private Integer userId;

    private Integer serviceId;

    private String roomName;

    private String roomDescription;

    private String roomIcon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName == null ? null : roomName.trim();
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription == null ? null : roomDescription.trim();
    }

    public String getRoomIcon() {
        return roomIcon;
    }

    public void setRoomIcon(String roomIcon) {
        this.roomIcon = roomIcon == null ? null : roomIcon.trim();
    }
}