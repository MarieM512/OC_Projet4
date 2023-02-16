package com.metay.mareu.ui.meeting_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.projet4.R;
import com.example.projet4.databinding.ActivityAddMeetingBinding;
import com.metay.mareu.MainActivity;
import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.di.DI;
import com.metay.mareu.injection.ViewModelFactory;
import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;
import com.metay.mareu.ui.meeting_list.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    private MeetingApiService mApiService;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mApiService = DI.getMeetingApiService();

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.input_room);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<Room>(Arrays.asList(Room.values())));
        autoCompleteTextView.setAdapter(adapter);

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        MainViewModel model = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(MainViewModel.class);

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meeting meeting = new Meeting(
                        binding.inputName.getText().toString(),
                        binding.inputDate.getText().toString(),
                        binding.inputTime.getText().toString(),
                        binding.inputRoom.getText().toString(),
                        binding.inputGuests.getText().toString()
                );
                model.addMeeting(meeting);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Used to navigate to this activity
     *
     * @param activity
     */
    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}
