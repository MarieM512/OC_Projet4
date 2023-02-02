package com.metay.mareu.di;

import com.metay.mareu.repositories.MeetingDataRepository;
import com.metay.mareu.api.MeetingDao;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static MeetingDao service = new MeetingDataRepository();

    /**
     * Get an instance on @{@link MeetingDao}
     * @return
     */
    public static MeetingDao getMeetingApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link MeetingDao}. Useful for tests, so we ensure the context is clean.
     * @return
     */
    public static MeetingDao getNewInstanceApiService() {
        return new MeetingDataRepository();
    }
}
