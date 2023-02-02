package com.metay.mareu.api;

import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FakeMeetingApiServiceGenerator {

    public static List<Meeting> generateMeetings() {
        return new ArrayList<>(FAKE_MEETINGS);
    }

    public static List<Meeting> FAKE_MEETINGS = Arrays.asList(
            new Meeting("Test", "12h", Room.MARIO, "mariemetay@live.fr"),
            new Meeting("Second", "16h", Room.HARMONIE, "sydney@live.fr, kyra@outlook.fr")
    );
}
