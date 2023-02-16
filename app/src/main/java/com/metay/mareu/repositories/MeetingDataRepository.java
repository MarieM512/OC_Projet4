package com.metay.mareu.repositories;


import static com.metay.mareu.api.FakeMeetingApiServiceGenerator.generateFakeMeetings;
import static com.metay.mareu.api.FakeMeetingApiServiceGenerator.generateMeetings;

import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingDataRepository implements MeetingApiService {

    private List<Meeting> mMeetingList = generateFakeMeetings();
    private ArrayList<Meeting> mMeetings = generateMeetings();

    private static MeetingDataRepository instance;

    public static MeetingDataRepository getInstance() {
        if (instance == null) {
            instance = new MeetingDataRepository();
        }
        return instance;
    }

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
    public ArrayList<Meeting> getMeeting() {
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
