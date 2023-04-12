package com.example.mainactivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mainactivity.ui.main.SectionsPagerAdapter;
import com.example.mainactivity.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    /*TODO: get current date/date selected to initialise views in main page
            receive date selected as extra/bundle from calendar activity*/

    // when binding is implemented in a module, binding class with references to root view & all id'ed views is automatically made
    private ActivityMainBinding binding;
    private ImageView dateForwardBtn, dateBackBtn, activitySettings;
    private TextView currentDateTxt;
    private String dateIndex;
    private boolean todaySelected = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //binding step 1: call static inflate method
        setContentView(binding.getRoot()); //binding step 2: setContentView but with get root instead of find by id

        dateBackBtn = binding.dateBackBtn;
        dateForwardBtn = binding.dateForwardBtn;
        currentDateTxt = binding.currentDateTxt;
        //getting the date as of onCreate called
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
        String today = sdf.format(new Date());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager()); //fragment adapter
        ViewPager viewPager = binding.viewPager; //viewpager is the layout manager allowing page swiping
        viewPager.setAdapter(sectionsPagerAdapter); //passing the fragment adapter through the viewpager
        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        //getting parameters from other activities
        Bundle extraBundle = getIntent().getExtras();
        setCurrentDate(extraBundle, today);

    }

    //setting the title to the date selected by user (from the Calendar dialog) and checking if the date selected is today
    public void setCurrentDate (Bundle extraBundle, String today) {
        if (extraBundle != null) {
            //from class CalendarDialog
            //Toast.makeText(MainActivity.this, "dateIndex: " + extraBundle.getBundle("DATE_SELECTED_DATA").getString("dateIndex") + " todayIndex: " + today, Toast.LENGTH_LONG).show();
            String bndldate = extraBundle.getBundle("DATE_SELECTED_DATA").getString("dateIndex");
            if (bndldate.equals(today)) {
                setToday(today);
            } else {
                dateIndex = bndldate;
                currentDateTxt.setText(extraBundle.getBundle("DATE_SELECTED_DATA").getString("dateSelectedTxt"));
                todaySelected = false;
                DatabaseHandler.dateSelected = bndldate;
            }
        } else {
            setToday(today);
        }
    }

    //set onclick for changing date of data 1 forward or back via home screen buttons
    public void onDateFwdClick (View view) {
        //TODO: make this an actual button
        Toast.makeText(this, "Date Forward Clicked", Toast.LENGTH_SHORT).show();

    }

    public void onDateBackClick (View view) {
        //TODO: make this an actual button
        Toast.makeText(this, "Date Back Clicked", Toast.LENGTH_SHORT).show();

    }

    public void onCurrentDateClick (View view) {
        CalendarDialog calendarDialog = new CalendarDialog();
        calendarDialog.show(getSupportFragmentManager(), null);
    }

    private void setToday(String today) {
        todaySelected = true;
        dateIndex = today;
        currentDateTxt.setText("Today");
        DatabaseHandler.dateSelected = today;
    }

//    public void onBreakfastBtnClick (View view) {
//        Toast.makeText(this, "breakfast button Clicked", Toast.LENGTH_SHORT).show();
//    }


}