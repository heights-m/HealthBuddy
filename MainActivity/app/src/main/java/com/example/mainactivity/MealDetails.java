package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mainactivity.databinding.ActivityMealDetailsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MealDetails extends AppCompatActivity {

    //TODO: when back button clicked and goes back to prev mealDetails activity, reupdate recyclerview

    private ActivityMealDetailsBinding binding;
    private RecyclerView mealFoodList;
    private FoodListRecAdapter adapter;
    private TextView mealTitle;
    private int mealSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMealDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mealSelected = getIntent().getIntExtra("MEAL_SELECTED", 0);

        //initialising views
        mealFoodList = binding.mealFoodList;
        mealTitle = binding.mealTitle;
        FloatingActionButton addFoodFAB = binding.addFoodFAB;
        adapter = new FoodListRecAdapter();

        setMealTitle(mealSelected);

        //recyclerview & adapter
        mealFoodList.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MealDetails.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mealFoodList.setLayoutManager(layoutManager);

        DatabaseHandler db = new DatabaseHandler(this);
        ArrayList<IntakeInput> allFoodInputs = db.getAllIntake();


        adapter.setMealIntakeArray(allFoodInputs);

        addFoodFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(); //opening intake input dialog
            }
        });
    }

    private void setMealTitle(int mealSelected) {
        switch(mealSelected) {
            case 1:
                mealTitle.setText("Lunch");
                break;
            case 2:
                mealTitle.setText("Dinner");
                break;
            case 3:
                mealTitle.setText("Snacks");
                break;
            default:
                mealTitle.setText("Breakfast");
        }
    }

    public void openDialog() {
        IntakeInputDialogBuilder intakeInputDialog = new IntakeInputDialogBuilder();
        intakeInputDialog.show(getSupportFragmentManager(), null);
    }
}