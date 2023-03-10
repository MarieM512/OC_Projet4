package com.metay.mareu.injection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.metay.mareu.di.DI;
import com.metay.mareu.repositories.MeetingRepository;
import com.metay.mareu.ui.meeting_list.viewmodel.MainViewModel;

/**
 * ViewModelFactory to link {@link MainViewModel} with {@link MeetingRepository}
 */
public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MeetingRepository mMeetingRepository;

    private static ViewModelFactory factory;

    /**
     * Get a new instance on {@link ViewModelFactory}. Useful for tests, so we ensure the context is clean.
     *
     * @param context context
     * @return {@link ViewModelFactory}
     */
    public static ViewModelFactory getInstance(Context context) {
        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(context);
                }
            }
        }
        return factory;
    }

    public ViewModelFactory(Context context) {
        this.mMeetingRepository = new MeetingRepository(DI.getMeetingApiService());
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(mMeetingRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
