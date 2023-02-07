package com.metay.mareu.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Meeting {

    /** ID of the meeting */
    private String id;

    /** Name of the meeting */
    private String name;

    /** Time of the meeting */
    private String time;

    /** Day of the meeting */
    private String date;

    /** Room of the meeting */
    private String room;

    /** Participants of the meeting */
    private String guests;

    public Meeting(String name,String date, String time, String room, String guests) {
        this.id = UUID.randomUUID().toString(); // Automatically generate
        this.name = name;
        this.date = date;
        this.time = time;
        this.room = room;
        this.guests = guests;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                ", room=" + room +
                ", guests='" + guests + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return id.equals(meeting.id) && name.equals(meeting.name) && time.equals(meeting.time) && date.equals(meeting.date) && room == meeting.room && guests.equals(meeting.guests);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, time, date, room, guests);
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
