package com.metay.mareu;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.projet4.R;
import com.example.projet4.databinding.ActivityMainBinding;
import com.metay.mareu.api.MeetingApiService;
import com.metay.mareu.di.DI;
import com.metay.mareu.injection.ViewModelFactory;
import com.metay.mareu.model.Meeting;
import com.metay.mareu.ui.meeting_list.AddMeetingActivity;
import com.metay.mareu.ui.meeting_list.MeetingListAdapter;
import com.metay.mareu.ui.meeting_list.MeetingInterface;
import com.metay.mareu.ui.meeting_list.viewmodel.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements MeetingInterface {

    private ActivityMainBinding binding;
    private MeetingListAdapter mMeetingListAdapter;
    private MainViewModel model;
    final Calendar myCalendar= Calendar.getInstance();

    // Date
    String myFormat = "dd/MM/yy";
    SimpleDateFormat dateFormat = null;
    String dateSelected = "";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.toolbar);

        binding.rvMeeting.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mMeetingListAdapter = new MeetingListAdapter(Meeting.sItemCallback, this);

        binding.addMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMeetingActivity.navigate(MainActivity.this);
            }
        });

        binding.rvMeeting.setAdapter(mMeetingListAdapter);

        model = new ViewModelProvider(this, ViewModelFactory.getInstance(this)).get(MainViewModel.class);

        model.getMeetingList().observe(this, mLiveData -> {
            mMeetingListAdapter.submitList(mLiveData);
            mMeetingListAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void removeMeeting(Meeting meeting) {
        model.deleteMeeting(meeting);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                dateFormat = new SimpleDateFormat(myFormat, Locale.FRANCE);
                dateSelected = dateFormat.format(myCalendar.getTime());
                Toast.makeText(getApplicationContext(), dateSelected, Toast.LENGTH_SHORT).show();
                model.dateFilter(dateSelected);
            }
        };

        switch (item.getItemId()) {
            case R.id.filter_date:
                new DatePickerDialog(this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.filter_room:
                Log.d("test", "room");
                break;
            case R.id.filter_clear:
                model.getMeetingList();
                Toast.makeText(this, "All filter are removed", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}