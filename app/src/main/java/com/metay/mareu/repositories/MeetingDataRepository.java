package com.metay.mareu.repositories;


import static com.metay.mareu.api.FakeMeetingApiServiceGenerator.generateMeetings;

import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingDataRepository implements MeetingApiService {

    private List<Meeting> mMeetingList = generateMeetings();
    private ArrayList<Meeting> mMeetings = new ArrayList<>();

    @Override
    public List<Meeting> getFakeMeeting() {
        return mMeetingList;
    }

    @Override
    public void createFakeMeeting(Meeting meeting) {
        mMeetingList.add(meeting);
    }

    @Override
    public void deleteFakeMeeting(Meeting meeting) {
        mMeetingList.remove(meeting);
    }

    @Override
    public List<Meeting> getMeeting() {
        return mMeetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }
}
