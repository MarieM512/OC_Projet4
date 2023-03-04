package com.metay.mareu.api;

import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generate fake meeting
 */
public abstract class FakeMeetingApiServiceGenerator {

    /**
     * Generate all the meetings in the MEETINGS List
     *
     * @return {@link List}
     */
    public static ArrayList<Meeting> generateMeetings() {
        return new ArrayList<>(MEETINGS);
    }

    public static List<Meeting> MEETINGS = Arrays.asList(
            new Meeting("Daily", "05/12/23", "9h45", Room.MARIO, "mariemetay@live.fr"),
            new Meeting("Synchro IT - Design", "05/04/23", "14h15", Room.YOSHI, "mariemetay@live.fr")
    );
}
