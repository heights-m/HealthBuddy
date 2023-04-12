package com.example.mainactivity.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mainactivity.MealDetails;
import com.example.mainactivity.databinding.FragmentHomeBinding;
import com.example.mainactivity.databinding.HomeMealDisplayBinding;

import java.util.Objects;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment { //no constructor??
    //TODO: upon initialisation, refer to database for all values
    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentHomeBinding binding;
    private ConstraintLayout sectionOverview, sectionFitness, sectionFood;
    private Context mContext;
    HomeMealDisplayBinding breakfastButton, lunchButton, dinnerButton, snacksButton;


    public static HomeFragment newInstance(int index) {
        HomeFragment fragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);  //bundle works like a hashmap with a key and value
        fragment.setArguments(bundle);  //supply constructor arguments for fragment (when no fragManager is used)
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) { //return bundle arguments given when fragment was instantiated (see line 32)
            index = getArguments().getInt(ARG_SECTION_NUMBER); //so if fragment exists, get the int(tab no.), otherwise index = 1
        }
        pageViewModel.setIndex(index); //pass current tab no to ViewModel
        mContext = Objects.requireNonNull(getActivity()).getApplicationContext();
    }

    @Override
    public View onCreateView(  //called to instantiate UI (optional)
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();//in fragments binding class is called in onCreateView

        /*final TextView textView = binding.sectionLabel; //basically same as find view by id
        pageViewModel.getText().observe(getViewLifecycleOwner(), s -> { //when observer detects a change in the lifecycle (data?) aka i think
            textView.setText(s);                                        //when onCreateView is called (bc fragment change)
        });*/
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //view initialise
        ImageButton fitAddBtn = binding.fitAddBtn;
        breakfastButton = binding.breakfastButton;
        lunchButton = binding.lunchButton;
        dinnerButton = binding.dinnerButton;
        snacksButton = binding.snacksButton;

        //view button initialise
        fitAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "fitAddBtn clicked", Toast.LENGTH_SHORT).show();
            }
        });

        (breakfastButton.mealLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMealDetails(0);
            }
        });

        (lunchButton.mealLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMealDetails(1);
            }
        });

        (dinnerButton.mealLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMealDetails(2);
            }
        });

        (snacksButton.mealLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMealDetails(3);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void openMealDetails(int meal) {
        //Toast.makeText(getActivity(), meal + " button clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity().getApplicationContext(), MealDetails.class);
        intent.putExtra("MEAL_SELECTED", meal);
        getActivity().startActivity(intent);
    }

//    public void onBreakfastBtnClick (View view) {
//        breakfastButton.addFoodImg.setVisibility(View.GONE);
//        breakfastButton.mealButton.setVisibility(View.VISIBLE);
//    }
}