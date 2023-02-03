package com.metay.mareu.ui.meeting_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.projet4.databinding.ActivityAddMeetingBinding;

public class AddMeetingActivity extends AppCompatActivity {

    private ActivityAddMeetingBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, AddMeetingActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }

}
