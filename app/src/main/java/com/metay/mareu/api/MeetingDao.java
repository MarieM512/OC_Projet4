package com.metay.mareu.api;

import com.metay.mareu.model.Meeting;

import java.util.List;

public interface MeetingDao {

    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeeting();

    /**
     * Get all my Fake Meetings
     * @return {@link List}
     */
    List<Meeting> getFakeMeeting();

    /**
     * Create a meeting
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    /**
     * Delete a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);
}
