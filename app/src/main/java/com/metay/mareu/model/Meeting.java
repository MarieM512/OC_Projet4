package com.metay.mareu.model;

public class Meeting {

    /** Name of the meeting */
    private String name;

    /** Time of the meeting */
    private String time;

    /** Room of the meeting */
    private Room room;

    /** Participants of the meeting */
    private String members;

    public Meeting(String name, String time, Room room, String members) {
        this.name = name;
        this.time = time;
        this.room = room;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }
}
