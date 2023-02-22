package com.metay.mareu.repositories;

import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.model.Meeting;

import java.util.ArrayList;

public class MeetingRepository {

    private final MeetingApiService mApiService;

    public MeetingRepository(MeetingApiService meetingApiService) {
        this.mApiService = meetingApiService;
    }

    public ArrayList<Meeting> getMeeting() {
        return mApiService.getMeeting();
    }

    public void createMeeting(Meeting meeting) {
        mApiService.createMeeting(meeting);
    }

    public void deleteMeeting(Meeting meeting) {
        mApiService.deleteMeeting(meeting);
    }
}
