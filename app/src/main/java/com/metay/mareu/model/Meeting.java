package com.metay.mareu.model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;
import java.util.UUID;

/**
 * Model of Meeting
 */
public class Meeting {

    /**
     * ID of the meeting
     */
    private String id;

    /**
     * Name of the meeting
     */
    private String name;

    /**
     * Time of the meeting
     */
    private String time;

    /**
     * Day of the meeting
     */
    private String date;

    /**
     * Room of the meeting
     */
    private Room room;

    /**
     * Participants of the meeting
     */
    private String guests;

    public Meeting(String name, String date, String time, Room room, String guests) {
        this.id = UUID.randomUUID().toString(); // Automatically generate
        this.name = name;
        this.date = date;
        this.time = time;
        this.room = room;
        this.guests = guests;
    }

    /**
     * Get the ID of the meeting
     *
     * @return the id of the meeting
     */
    public String getId() {
        return id;
    }

    /**
     * Set the ID of the meeting
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the name of the meeting
     *
     * @return the name of the meeting
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the meeting
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the date of the meeting
     *
     * @return the date of the meeting
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date of the meeting
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the time of the meeting
     *
     * @return the time of the meeting
     */
    public String getTime() {
        return time;
    }

    /**
     * Set the time of the meeting
     *
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Get the room name of the meeting
     *
     * @return the room name of the meeting
     */
    public String getRoom() {
        return room.getRoom();
    }

    /**
     * Set the room name of the meeting
     *
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Get the image room of the meeting
     *
     * @return the image room of the meeting
     */
    public Integer getImgRoom() {
        return room.getImage();
    }

    /**
     * Get the participants of the meeting
     *
     * @return the guests of the meeting
     */
    public String getGuests() {
        return guests;
    }

    /**
     * Set the participants of the meeting
     *
     * @param guests
     */
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
