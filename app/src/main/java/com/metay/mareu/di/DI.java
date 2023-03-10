package com.metay.mareu.di;

import com.metay.mareu.api.MeetingDataApiService;
import com.metay.mareu.api.MeetingApiService;

/**
 * Dependency injector to get instance of services
 */
public class DI {

    private static MeetingApiService service = new MeetingDataApiService();

    /**
     * Get an instance on @{@link MeetingApiService}
     *
     * @return {@link MeetingDataApiService}
     */
    public static MeetingApiService getMeetingApiService() {
        return service;
    }

    /**
     * Get always a new instance on @{@link MeetingApiService}. Useful for tests, so we ensure the context is clean.
     *
     * @return {@link MeetingDataApiService}
     */
    public static MeetingApiService getNewInstanceApiService() {
        return new MeetingDataApiService();
    }
}
