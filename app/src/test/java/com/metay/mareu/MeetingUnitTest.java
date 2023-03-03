package com.metay.mareu;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.metay.mareu.api.FakeMeetingApiServiceGenerator;
import com.metay.mareu.api.MeetingApiService;
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

    private MeetingApiService mMeetingApiService;

    @Before
    public void setup() {
        mMeetingApiService = DI.getNewInstanceApiService();
    }

    @Test
    public void Meeting_isSave() {
        List<Meeting> meetings = mMeetingApiService.getMeeting();
        List<Meeting> expectedMeeting = FakeMeetingApiServiceGenerator.MEETINGS;
        assertThat(meetings, (IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray())));
    }

    @Test
    public void addMeeting() {
        Meeting meetingToAdd = mMeetingApiService.getMeeting().get(0);
        mMeetingApiService.createMeeting(meetingToAdd);
        assertTrue(mMeetingApiService.getMeeting().contains(meetingToAdd));
    }

    @Test
    public void removeMeeting() {
        Meeting meetingToDelete = mMeetingApiService.getMeeting().get(0);
        mMeetingApiService.deleteMeeting(meetingToDelete);
        assertFalse(mMeetingApiService.getMeeting().contains(meetingToDelete));
    }
}