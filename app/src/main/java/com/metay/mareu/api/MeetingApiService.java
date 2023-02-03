package com.metay.mareu.api;

import com.metay.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {

    /**
     * Get all my Fake Meetings
     * @return {@link List}
     */
    List<Meeting> getFakeMeeting();

    /**
     * Create a fake meeting
     * @param meeting
     */
    void createFakeMeeting(Meeting meeting);

    /**
     * Delete a fake meeting
     * @param meeting
     */
    void deleteFakeMeeting(Meeting meeting);

    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeeting();

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
