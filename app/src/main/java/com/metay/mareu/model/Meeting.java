package com.metay.mareu.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;
import java.util.UUID;

public class Meeting {

    /** ID of the meeting */
    private String id;

    /** Name of the meeting */
    private String name;

    /** Time of the meeting */
    private String time;

    /** Room of the meeting */
    private Room room;

    /** Participants of the meeting */
    private String members;

    public Meeting(String name, String time, Room room, String members) {
        this.id = UUID.randomUUID().toString(); // Automatically generate
        this.name = name;
        this.time = time;
        this.room = room;
        this.members = members;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

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

    @Override
    public String toString() {
        return "Meeting{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", room=" + room +
                ", members='" + members + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id.equals(meeting.id) && name.equals(meeting.name) && time.equals(meeting.time) && room == meeting.room && members.equals(meeting.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, time, room, members);
    }

    public static DiffUtil.ItemCallback<Meeting> sItemCallback = new DiffUtil.ItemCallback<Meeting>() {
        @Override
        public boolean areItemsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
            return oldItem.equals(newItem);
        }
    };
}
