package com.metay.mareu.injection;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.metay.mareu.api.MeetingDataApiService;
import com.metay.mareu.di.DI;
import com.metay.mareu.repositories.MeetingRepository;
import com.metay.mareu.ui.meeting_list.viewmodel.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MeetingRepository mMeetingRepository;

    private static ViewModelFactory factory;

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
