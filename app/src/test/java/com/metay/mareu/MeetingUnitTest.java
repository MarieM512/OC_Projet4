package com.metay.mareu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.metay.mareu.api.FakeMeetingApiServiceGenerator;
import com.metay.mareu.api.MeetingDao;
import com.metay.mareu.di.DI;
import com.metay.mareu.model.Meeting;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MeetingUnitTest {

    private MeetingDao mMeetingApiService;

    @Before
    public void setup() {
        mMeetingApiService = DI.getNewInstanceApiService();
    }

    @Test
    public void fakeMeeting_isSave() {
        List<Meeting> meetings = mMeetingApiService.getFakeMeeting();
        List<Meeting> expectedMeeting = FakeMeetingApiServiceGenerator.FAKE_MEETINGS;
        assertThat(meetings, (IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray())));
    }

    @Test
    public void addFakeMeeting() {
        Meeting meetingToAdd = mMeetingApiService.getFakeMeeting().get(0);
        mMeetingApiService.createMeeting(meetingToAdd);
        assertTrue(mMeetingApiService.getMeeting().contains(meetingToAdd));
    }

    @Test
    public void removeFakeMeeting() {
        Meeting meetingToAdd = mMeetingApiService.getFakeMeeting().get(0);
        mMeetingApiService.createMeeting(meetingToAdd);
        Meeting meetingToDelete = mMeetingApiService.getMeeting().get(0);
        mMeetingApiService.deleteMeeting(meetingToDelete);
        assertFalse(mMeetingApiService.getMeeting().contains(meetingToDelete));
    }
}