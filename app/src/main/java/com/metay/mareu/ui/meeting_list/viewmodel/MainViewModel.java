package com.metay.mareu.ui.meeting_list.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.metay.mareu.model.Meeting;
import com.metay.mareu.repositories.MeetingDataRepository;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Meeting>> mLiveData = new MutableLiveData<>();
    private final MeetingDataRepository mDataRepository;
    private final ArrayList<Meeting> mMeetingArrayList;

    public MainViewModel(MeetingDataRepository dataRepository) {
        this.mDataRepository = dataRepository;
        mLiveData.setValue(mDataRepository.getMeeting());
        mMeetingArrayList = mDataRepository.getMeeting();
    }

    public MutableLiveData<ArrayList<Meeting>> getMeetingList() {
        mLiveData.setValue(mMeetingArrayList);
        return mLiveData;
    }

    public void deleteMeeting(Meeting meeting) {
        mDataRepository.deleteMeeting(meeting);
        mLiveData.setValue(mMeetingArrayList);
    }

    public void addMeeting(Meeting meeting) {
        mDataRepository.createMeeting(meeting);
        mLiveData.setValue(mMeetingArrayList);
    }
}
