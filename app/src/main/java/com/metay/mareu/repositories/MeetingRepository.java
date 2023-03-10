package com.metay.mareu.repositories;

import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.model.Meeting;

import java.util.ArrayList;

/**
 * Use this class to use function in {@link MeetingApiService}
 */
public class MeetingRepository {

    private final MeetingApiService mApiService;

    public MeetingRepository(MeetingApiService meetingApiService) {
        this.mApiService = meetingApiService;
    }

    /**
     * Get the meetings in {@link MeetingApiService}
     *
     * @return the list of the meetings
     */
    public ArrayList<Meeting> getMeeting() {
        return mApiService.getMeeting();
    }

    /**
     * Create the meeting in {@link MeetingApiService}
     *
     * @param meeting meeting to create
     */
    public void createMeeting(Meeting meeting) {
        mApiService.createMeeting(meeting);
    }

    /**
     * Delete the meeting in {@link MeetingApiService}
     *
     * @param meeting meeting to delete
     */
    public void deleteMeeting(Meeting meeting) {
        mApiService.deleteMeeting(meeting);
    }
}
