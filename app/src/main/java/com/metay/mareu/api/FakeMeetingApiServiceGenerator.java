package com.metay.mareu.api;

import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class FakeMeetingApiServiceGenerator {

    public static List<Meeting> generateFakeMeetings() {
        return new ArrayList<>(FAKE_MEETINGS);
    }

    public static List<Meeting> FAKE_MEETINGS = Arrays.asList(
            new Meeting("Test", "8/12","12h", Room.MARIO.getRoom(), "mariemetay@live.fr"),
            new Meeting("Second","8/12", "16h", Room.DONKEY_KONG.getRoom(), "sydney@live.fr, kyra@outlook.fr"),
            new Meeting("third", "8/12","17h", Room.DAISY.getRoom(), "bonjour@live.fr, kyra@outlook.fr"),
            new Meeting("bonjour","8/12", "8h", Room.PEACH.getRoom(), "test@live.fr"),
            new Meeting("bonsoir", "10/12","20h", Room.TOAD.getRoom(), "bonsoir@live.fr, kyra@outlook.fr"),
            new Meeting("noir", "9/12","9h", Room.BOWSER.getRoom(), "kyra@outlook.fr")
    );

    public static ArrayList<Meeting> generateMeetings() { return new ArrayList<>(MEETINGS);}

    public static List<Meeting> MEETINGS = Arrays.asList(
            new Meeting("Daily", "5/12", "9h45", Room.MARIO.getRoom(), "mariemetay@live.fr"),
            new Meeting("Synchro IT - Design", "8/12", "14h", Room.YOSHI.getRoom(), "mariemetay@live.fr")
    );
}
