package com.metay.mareu.ui.meeting_list;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.projet4.R;
import com.example.projet4.databinding.ActivityAddMeetingBinding;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.metay.mareu.MainActivity;
import com.metay.mareu.injection.ViewModelFactory;
import com.metay.mareu.model.Meeting;
import com.metay.mareu.model.Room;
import com.metay.mareu.ui.meeting_list.viewmodel.MainViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;
    final Calendar myCalendar= Calendar.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.input_room);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, new ArrayList<Room>(Arrays.asList(Room.values())));
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener (new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (0 <= position && position <= 9) {
                    binding.ivRoom.setImageResource(Room.values()[position].getImage());
                }
            }
        });

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

        binding.inputDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddMeetingActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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

    private void updateLabel(){
        String myFormat="dd/MM/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.FRANCE);
        binding.inputDate.setText(dateFormat.format(myCalendar.getTime()));
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
