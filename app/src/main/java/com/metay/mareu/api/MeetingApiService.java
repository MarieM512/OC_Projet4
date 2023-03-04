package com.metay.mareu.api;

import com.metay.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface to manage the meeting
 */
public interface MeetingApiService {

    /**
     * Get all my Meetings
     *
     * @return {@link List}
     */
    ArrayList<Meeting> getMeeting();

    /**
     * Create a meeting
     *
     * @param meeting
     */
    void createMeeting(Meeting meeting);

    /**
     * Delete a meeting
     *
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);
}
