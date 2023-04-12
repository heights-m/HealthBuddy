package com.example.mainactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.mainactivity.databinding.IntakeInputDialogBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class IntakeInputDialogBuilder extends AppCompatDialogFragment{

    //TODO: make sure to put try catch when setting user input to variables

    private EditText editTextFoodName, editTextKcal, editTxtAmt;
    private Spinner unitSpinner, mealSpinner;
    private IntakeInput intakeInput;
    private String unit, meal;
    private int mealInt;

    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  //inflate via view binder
        @NonNull IntakeInputDialogBinding binding = IntakeInputDialogBinding.inflate(LayoutInflater.from(getContext()));
        builder.setView(binding.getRoot())
                .setTitle("Add Food")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: set input buttons etc
                    }
                })
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: when clicked: call action, pass parameters, save to database
                        //eventually: have it so that there are optional inputs (protein, carbs, fats) which don't crash the app (possibly through sorting through an array n setting all values?)
                        DatabaseHandler dbHandler = new DatabaseHandler(getContext());
                        boolean success = false;
                        try {
                            intakeInput = new IntakeInput(-1,
                                    editTextFoodName.getText().toString(),
                                    DatabaseHandler.dateSelected,
                                    Integer.parseInt(editTextKcal.getText().toString()),
                                    0, 0, 0,
                                    Integer.parseInt(editTxtAmt.getText().toString()),
                                    unit,
                                    mealInt);
                            //Toast.makeText(getActivity(), intakeInput.toString(), Toast.LENGTH_LONG).show();
                            success = dbHandler.addIntakeItem(intakeInput);
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "Error saving item", Toast.LENGTH_SHORT).show();
                        }

                        if (success) {
                            Toast.makeText(getActivity(), "Item saved", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity().getApplicationContext(), MealDetails.class);
                            getActivity().startActivity(intent);
                        } else {Toast.makeText(getActivity(), "Error saving item", Toast.LENGTH_SHORT).show();}
                    }
                });


        //initialise views
        editTextFoodName = binding.editTextFoodName;
        editTextKcal = binding.editTextKcal;
        editTxtAmt = binding.editTextAmt;
        unitSpinner = binding.unitSpinner;
        mealSpinner = binding.mealSpinner;

        //Spinner click listeners
        unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit = parent.getItemAtPosition(position).toString();
//                switch (unit) {
//                    case "Serving(s)":
//                        unit = "serving";
//                        break;
//                    case "grams":
//                        unit = "g";
//                        break;
//                    case "mL":
//                        unit = "ml";
//                        break;
//                    case "cup(s)":
//                        unit = "cup";
//                        break;
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mealSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                meal = parent.getItemAtPosition(position).toString();
                switch (meal) {
                    case "Breakfast":
                        mealInt = 0;
                        break;
                    case "Lunch":
                        mealInt = 1;
                        break;
                    case "Dinner":
                        mealInt = 2;
                        break;
                    case "Snacks":
                        mealInt = 3;
                        break;
                }
                //Toast.makeText(getActivity(), String.valueOf(mealInt) + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> foodUnitArray = new ArrayList<>();
        foodUnitArray.add("Serving(s)");
        foodUnitArray.add("gram(s)");
        foodUnitArray.add("mL");
        foodUnitArray.add("cup(s)");
        ArrayList<String> mealTimeArray = new ArrayList<>();
        mealTimeArray.add("Breakfast");
        mealTimeArray.add("Lunch");
        mealTimeArray.add("Dinner");
        mealTimeArray.add("Snacks");

        ArrayAdapter<String> foodUnitAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, foodUnitArray);
        ArrayAdapter<String> mealTimeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mealTimeArray);
        unitSpinner.setAdapter(foodUnitAdapter);
        mealSpinner.setAdapter(mealTimeAdapter);

        return builder.create();
    }

}
